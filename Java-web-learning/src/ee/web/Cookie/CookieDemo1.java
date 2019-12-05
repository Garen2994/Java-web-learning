package ee.web.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CookieDemo1")
public class CookieDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建Cookie对象
        Cookie c = new Cookie("msg", "hello");
        c.setMaxAge(30);  //持续化存储到硬盘 持续时间30s
        Cookie c1 = new Cookie("msg1","Ciao");
        c1.setMaxAge(0);   //删除
        Cookie c2 = new Cookie("msg2","您好");   //Tomcat 8 之前中文要先转码(URL编码 %)
        c2.setMaxAge(-1);  //默认值 存储在浏览器的内存中
        //2.发送Cookie
        c.setPath("/");
        c1.setPath("/");
        c2.setPath("/");
        response.addCookie(c);
        response.addCookie(c1);
        response.addCookie(c2);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
