package ee.demo.Filter;
/**
 * @Title : Test the order of the Filter chain's running  ：To  Filter1 ->Filter 2 -> resource ->Back: Filter 2->Filter 1
 * @Author : Garen
 * @Date : 2019/12/14 21:35
 */
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/index.jsp")
@WebFilter("/Hello.jsp")
//@WebFilter("*.jsp")
public class FilterDemo4 implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo4 is running");  //只有运行index.jsp才会执行
        chain.doFilter(req, resp);
        System.out.println("FilterDemo4 is coming back");
    }

    public void destroy() {

    }


}
