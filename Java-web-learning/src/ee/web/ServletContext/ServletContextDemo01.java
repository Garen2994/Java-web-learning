package ee.web.ServletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Title : ServletContext对象获取
 * @Author : Garen
 * @Date : 2019/12/3 22:41
 */
@WebServlet("/ServletContextDemo01")
public class ServletContextDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过request对象来获取
        ServletContext servletContext1 = request.getServletContext();
        //2.通过HttpServlet对象获取
//        ServletContext servletContext2 = this.getServletContext();
//        System.out.println(servletContext1 == servletContext2);   //true

        /*
            ServletContext对象的功能
         */

        //-1- 获取文件MIME类型  eg：text/html
//        servletContext2.getMimeType();
        String filename = "a.jpg";
        String mimeType = servletContext1.getMimeType(filename);
        System.out.println(mimeType);    //输出 -> image/jpeg
        //-2- 设置共享数据
        servletContext1.setAttribute("msg","haha");   //**范围是所有用户所有请求的数据

        //-3- 获取真实的(服务器)的路径
        String realPath = servletContext1.getRealPath("/b.txt");   //Web目录下资源访问
        System.out.println(realPath);

        String realPath1 = servletContext1.getRealPath("/WEB-INF/a.txt");   //WEB-INF下资源访问
        System.out.println(realPath1);

        String realPath2 = servletContext1.getRealPath("/WEB-INF/classes/c.txt");  //src下资源访问
        System.out.println(realPath2);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
