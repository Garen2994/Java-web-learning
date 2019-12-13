package ee.demo.Web;

import ee.demo.Service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DelSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String[] ids = request.getParameterValues("uid");
        if (ids != null && ids.length != 0) {
            UserServiceImpl service = new UserServiceImpl();
            service.deleteUser(ids);
        }

        response.sendRedirect(request.getContextPath() + "/UserListServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        this.doPost(request, response);
    }
}
