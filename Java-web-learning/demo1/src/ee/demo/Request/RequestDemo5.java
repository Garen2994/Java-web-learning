package ee.demo.Request;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestDemo5")
public class RequestDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        //设置request域数据
        request.setAttribute("msg","Hello,bitch..");
        
        System.out.println("Demo555 is running");
        request.getRequestDispatcher("/requestDemo6").forward(request, response);
        ServletContext servletContext = request.getServletContext();
        System.out.println(servletContext);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //获取请求头数据
        this.doPost(request,response);
    }
}

