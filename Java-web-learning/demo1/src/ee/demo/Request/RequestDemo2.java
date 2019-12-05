package ee.demo.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/requestDemo2")
public class RequestDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //获取请求头数据
        Enumeration<String> names = request.getHeaderNames();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.println("请求头："+name);
        }

        //user-agent中浏览器
        String agent = request.getHeader("user-agent");
        if(agent.contains("Firefox")){
            System.out.println("来自：火狐浏览器");
        }else if(agent.contains("Chrome")){
            System.out.println("来自：谷歌浏览器");

        }

        //获取referer
        String referer = request.getHeader("referer");
        System.out.println(referer);
        if(referer != null){
            if(referer.contains("/day14"))
                System.out.println("正常访问..");
            else
                System.out.println("盗链违法...");
        }
    }
}

