package ee.demo.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@WebServlet("/requestDemo4")
public class RequestDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求参数
        String username = request.getParameter("username");
        System.out.println("post");
        System.out.println(username);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request,response);
//        String username = request.getParameter("username");
//        System.out.println("get");
//        System.out.println(username);
//
//        String[] hobby = request.getParameterValues("hobby");
//        System.out.println(Arrays.toString(hobby));

        //获取所有参数的map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String s : keySet) {

            String[] values = parameterMap.get(s);
            for (String value : values) {
                System.out.println(value);
            }
        }
    }

}
