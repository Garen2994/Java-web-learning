package ee.web.CookieDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title : 案例：记住上一次访问时间
 * -1- 需求：
 *      1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
 *      2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串
 * -2- 分析：
 *      1. 可以采用Cookie来完成
 *      2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 *          1. 有：不是第一次访问
 *              1) 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
 *              2)写回Cookie：lastTime=2018年6月10日11:50:01
 *          2. 没有：是第一次访问
 *              1) 响应数据：您好，欢迎您首次访问
 *              2) 写回Cookie：lastTime=2018年6月10日11:50:01
 * @Author : Garen
 * @Date : 2019/12/5 15:56
 */
@WebServlet("/CookieTestServlet")
public class CookieTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //Get cookie "LastTime"

        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (cookies != null && cookies.length > 0) {   //cookie not null
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals("LastTime")) {    //Not first time to visit
                    //set time format
                    flag = true;
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = sdf.format(date);
                    System.out.println("编码前："+str_date);
                    //URL编码
                    str_date = URLEncoder.encode(str_date,"utf-8");
                    System.out.println("编码后："+str_date);
                    cookie.setValue(str_date);
                    cookie.setMaxAge(60 * 60 * 24 * 30); // set age as a month
                    response.addCookie(cookie);

                    //response,get cookie's value-time
                    String value = cookie.getValue();
                    System.out.println("解码前："+value);
                    //URL解码：
                    value = URLDecoder.decode(value,"utf-8");
                    System.out.println("解码后："+value);
                    PrintWriter writer = response.getWriter();
                    writer.write("<hr>欢迎回来，您上次访问时间为 " + value + "</hr>");
                    break;  // done
                }
            }
        }
        if(cookies == null || cookies.length==0 || flag == false ){
            //cookies is null, first time to visit
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);

            System.out.println("编码前："+str_date);
            //URL编码
            str_date = URLEncoder.encode(str_date,"utf-8");
            System.out.println("编码后："+str_date);

            //new a cookie
            Cookie cookie = new Cookie("LastTime",str_date);

            //set age
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
            response.getWriter().write("<hr>您好，欢迎您首次访问</hr>");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
