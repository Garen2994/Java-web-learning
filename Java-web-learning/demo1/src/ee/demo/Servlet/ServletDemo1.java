package ee.demo.Servlet;

        import javax.servlet.*;
        import javax.servlet.annotation.WebServlet;
        import java.io.IOException;
@WebServlet("/demo1")
public class ServletDemo1 implements Servlet {
    public ServletDemo1() {
    }
//Servlet生命周期 1 init 2 service 3 destroy

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
    }

    /**
     * description: 获取Servlet配置对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servlet 4.0 is coming...");
    }

    /**
     * description：获取Servlet信息
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Destroy...");
    }
}
