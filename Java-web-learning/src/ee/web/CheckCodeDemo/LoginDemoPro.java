package ee.web.CheckCodeDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Title : 改进验证码案例:
 * @Description : 1. 案例需求：
 * 1. 访问带有验证码的登录页面login.jsp
 * 2. 用户输入用户名，密码以及验证码。
 * * 如果用户名和密码输入有误，跳转登录页面，提示:用户名或密码错误
 * * 如果验证码输入有误，跳转登录页面，提示：验证码错误
 * * 如果全部输入正确，则跳转到主页success.jsp，显示：用户名,欢迎您
 * @Author : Garen
 * @Date : 2019/12/6 15:26
 **/
@WebServlet("/LoginDemoPro")
public class LoginDemoPro extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //1.set encode format
        request.setCharacterEncoding("utf-8");
        //2.get the parameter
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        //3.get checkcode generated randomly
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("checkCode_Session");
        //ignore case
        System.out.println(code);
        session.removeAttribute("checkCode_session");
        if (code!= null && code.equalsIgnoreCase(checkCode)) {//3.1 check that if the checkcode is matching
            if ("garen".equals(username) && ("123456".equals(password))) {  //can design to use the database to query

                //3.2 if it matched,then check the username and password
                //3.2.1 success -> store the data
                session.setAttribute("user", username);
                //3.2.2 re-direct to success.jsp
                response.sendRedirect(request.getContextPath() + "/success.jsp");   //re-direct 要虚拟目录 (可以是不同服务器的资源)
            } else {
                //3.2.3 fail -> send the warning info
                request.getSession().setAttribute("login_fail", "Username or password ERROR!");
                //3.2.4 forword to login.jsp
                request.getRequestDispatcher("/login.jsp").forward(request, response);  //forward 不要虚拟目录(同一服务器下的资源)
                System.out.println("重定向");
            }
        } else {   //3.3 if it not matched
            //checkcode wrong info -> forward the info to login.jsp
            request.getSession().setAttribute("checkCode_fail", "CheckCode ERROR!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            System.out.println("重定向");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        this.doPost(request, response);
    }
}
