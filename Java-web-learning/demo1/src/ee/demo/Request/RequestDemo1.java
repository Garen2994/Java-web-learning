package ee.demo.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestDemo")
public class RequestDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //获取请求方式
        String method = request.getMethod();
        System.out.println("请求方式：" + method);
        //获取虚拟目录
        String path = request.getContextPath();
        System.out.println("虚拟目录："+ path);
        //获取Servlet路径
        String servletPath = request.getServletPath();
        System.out.println("Servlet路径："+ servletPath);
        //获取GET请求方式参数
        String queryString = request.getQueryString();
        System.out.println(queryString);
        //获取请求URI
        String requestURI = request.getRequestURI();
        System.out.println("URI：" + requestURI);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("URL：" + requestURL);
        //获取协议版本
        String protocol = request.getProtocol();
        System.out.println("协议版本："+protocol);


        //获取请求头
        String header = request.getHeader("/demo1");
    }
}
