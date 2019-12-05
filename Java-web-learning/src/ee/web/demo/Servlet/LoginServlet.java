package ee.web.demo.Servlet;

import ee.web.demo.Dao.UserDao;
import ee.web.demo.Domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Title : 处理信息、转发及存储页面
 * @Author : Garen
 * @Date : 2019/12/2 20:41
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取请求参数
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        System.out.println(username);
//        System.out.println(password);
//
//        //封装数据为一个对象
//        User loginUser = new User();
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);

        Map<String,String[]> map = request.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,map);   //将获取的参数map封装为User类型的loginUser中
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(loginUser.toString());
        //调用UserDao中的login方法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
//        System.out.println(user.toString());
        //判断
        if(user == null){
            //转发到登陆失败界面
            request.getRequestDispatcher("/failServlet").forward(request,response);

        }else {
            //存储数据
            request.setAttribute("user", user);
            //转发到登陆成功界面
            request.getRequestDispatcher("/successServlet").forward(request, response);
        }
    }
}
