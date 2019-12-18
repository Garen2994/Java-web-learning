package ee.demo.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException,
            IOException {
        //Http强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //获取请求资源路径
        String uri = request.getRequestURI();
        //要排除配置、样式文件
        if (uri.contains("/login.jsp") || uri.contains("/LoginProServlet")||uri.contains("/css/")||uri.contains("/js" +
                "/")||uri.contains("/fonts/") || uri.contains("/RandomCodeServlet")) {
            //如果包含，证明用户就是想登录
            chain.doFilter(req, resp);
        }else{
            //不包含，先验证是否已登录
            Object user = request.getSession().getAttribute("user");
            //如果user不为空，说明用户已经登录
            if (user!=null){
                chain.doFilter(req,resp);
            }else{
                //为空，未登录->跳转至登录界面
                request.setAttribute("login_msg","您尚未登录，请先登录");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }


}
