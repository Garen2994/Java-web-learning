package ee.demo.Web;

import ee.demo.Domain.User;
import ee.demo.Service.Impl.UserServiceImpl;
import ee.demo.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserServiceImpl();   // Servlet use the service layer
        List<User> users = service.findAll();
        request.setAttribute("users",users);   //store the result list to request domain
        request.getRequestDispatcher("/list.jsp").forward(request,response);  //forword the result list to list.jsp page
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
