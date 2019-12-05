package ee.web.Response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @Title : 服务器输出字节/字符到客户端
 * @Author : Garen
 * @Date : 2019/12/3 15:37
 */
@WebServlet("/ResponseServlet03")
public class ResponseServlet03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
//        response.setCharacterEncoding("utf-8");   //服务器自己的编码方式
//        response.setHeader("content-type","text/html;charset=utf-8");    //告诉客户端用什么解码
        response.setContentType("text/html;charset=utf-8");   //上面一行的简写
        //获取字符输出流
        PrintWriter pw = response.getWriter();
        //输出数据
        pw.write("您好呀,response03");

        //输出字节流
        ServletOutputStream sos = response.getOutputStream();
        sos.write("您好您好".getBytes("utf-8"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
