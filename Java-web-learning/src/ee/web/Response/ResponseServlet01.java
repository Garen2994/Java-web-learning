package ee.web.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ResponseServlet01")
public class ResponseServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1.设置状态码
//        response.setStatus(302);
//        //2.设置响应头
//        response.setHeader("location","/1129/ResponseServlet02");
        //动态获取虚拟目录
        String contextPath = request.getContextPath();
        //使用response的sendRedirect方法同样能实现重定向
        response.sendRedirect(contextPath + "/ResponseServlet02");
        System.out.println("ResponseServlet01 is running...");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
