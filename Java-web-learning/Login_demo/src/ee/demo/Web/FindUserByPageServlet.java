package ee.demo.Web;
/**
 * @Title : 分页查询Servlet
 * @Author : Garen
 * @Date : 2019/12/12 20:48
 */

import ee.demo.Domain.PageBean;
import ee.demo.Service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        //从客户端获取数据currentPage和row
        String currentPage = request.getParameter("currentPage");    //当前页码
        String row = request.getParameter("row");    //每页记录数
        if (currentPage == null || currentPage.equals("")  || Integer.parseInt(currentPage) <= 1) {
            currentPage = "1";
        }
        if (row == null || row.equals("")) {
            row = "5";  //设置默认值
        }

        Map<String, String[]> condition = request.getParameterMap();
        UserServiceImpl service = new UserServiceImpl();
        PageBean pb = service.findUserByPage(currentPage, row, condition);

        request.setAttribute("pb", pb);
        request.setAttribute("condition",condition);   //将查询条件存入,方便回显查询条件
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        this.doPost(request, response);
    }
}
