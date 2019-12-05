package ee.web.demo.Servlet;

import ee.web.demo.Domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Title : 登录成功页面
 * @Author : Garen
 * @Date : 2019/12/2 20:38
 */
@WebServlet("/successServlet")
public class successServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //获取request域中的共享信息

        User user = (User)request.getAttribute("user");

        //在页面写提示信息
        if(user != null){
            String username = user.getUsername();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登录成功！"+ username +", 欢迎你");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
