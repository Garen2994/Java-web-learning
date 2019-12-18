package ee.demo.Filter;
/**
 * @Title : Filter + 动态代理实现敏感词汇过滤
 * @Author : Garen
 * @Date : 2019/12/16 14:44
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException,
            IOException {
//        System.out.println("SensitiveWordFilter is running...");
        ServletRequest proxy_request = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(),
                req.getClass().getInterfaces(),
                new InvocationHandler() {  //匿名内部类
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //增强方法
                        //只增强getParamter方法 还有getParameterMap...

                        if (method.getName().equals("getParameter")) {
                            String value = (String) method.invoke(req, args);
                            if (value != null) {
                                for (String str : list) {
                                    if (value.contains(str)) {
                                        value = value.replaceAll(str, "***");
                                    }
                                }
                            }
//                            System.out.println(value);
                            return value;
                        }
                        return method.invoke(req, args);
                    }
                });
        chain.doFilter(proxy_request, resp);
    }

    private List<String> list = new ArrayList<String>();  //敏感词汇集合

    public void init(FilterConfig config) throws ServletException {
        //获取文件真实路径
        try {
//            System.out.println("init...");
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/sensitiveword.txt");//src下资源路径
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
//            System.out.println(list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {

    }


}
