package ee.demo.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo2 implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo2 is running");
        //放行
        chain.doFilter(req, resp);

        System.out.println("FilterDemo2 is coming back");
    }

    public void destroy() {

    }


}
