package ee.demo.Web;

import ee.demo.Domain.User;
import ee.demo.Service.Impl.UserServiceImpl;
import ee.demo.Service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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
@WebServlet("/LoginProServlet")
public class LoginProServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //3.获取验证码
        String input_code = request.getParameter("verifycode");//获取用户填写的验证码
        HttpSession session = request.getSession();
        String generate_code = (String) session.getAttribute("checkCode_Session");  //随机生成的验证码
        session.removeAttribute("checkCode_session");   //保证验证码一次性
        if (!generate_code.equalsIgnoreCase(input_code)) {  //验证码错误

            request.setAttribute("login_msg","验证码错误！请重新输入");  //输出提示信息
            request.getRequestDispatcher("/login.jsp").forward(request, response); //重新跳转至登录界面

            return;
        }
        //2.获取数据
        Map<String, String[]> paramMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, paramMap);  //将参数map封装为对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        //4.获取service查询
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);

        if (loginUser != null) {//登录成功
            session.setAttribute("user", loginUser);
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");    //request没有共享数据，所以用重定向

        } else {
            request.setAttribute("login_msg", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response); //重新跳转至登录界面

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        this.doPost(request, response);
    }
}
