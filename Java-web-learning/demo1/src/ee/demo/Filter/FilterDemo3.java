package ee.demo.Filter;

/**
 * @Author : Garen
 * @Date : 2019/12/14 21:35
 */
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/Hello.jsp")
public class FilterDemo3 implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo3 is Running");
        chain.doFilter(req, resp);
        System.out.println("FilterDemo3 is coming back");

    }

    public void destroy() {
        System.out.println("destory...");   //正常关闭
    }


}
