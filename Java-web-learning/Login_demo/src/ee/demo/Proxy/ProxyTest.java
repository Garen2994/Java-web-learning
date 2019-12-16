package ee.demo.Proxy;
/**
 * @Title :动态代理的使用Case
 * @Author : Garen
 * @Date : 2019/12/16 14:42
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //真实对象
        Lenovo lenovo = new Lenovo();

        //动态代理增强lenovo对象
        /**
         * 三个参数： 1.类加载器  真实对象.getClass().getClassLoader()
         *          2.接口数组  真实对象.getClass().getInterfaces()
         *          3.处理器  new InvocationHandler()
         */

        SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(),
                lenovo.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     *
                     * @param proxy : 代理对象
                     * @param method : 代理对象调用的方法 被封装为的对象
                     * @param args : 代理对象调用方法时 传递的实际参数
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //增强参数列表
                        if (method.getName() == "sale") {
                            double money = (double) args[0];
                            money = money * 0.85;
                            System.out.println("专车接送...");
                            //使用真实对象调用该方法
                            String obj = (String) method.invoke(lenovo, money);
                            //增强返回值类型
                            System.out.println("免费送货...");
                            return obj + " + 鼠标垫";

                        } else {
                            Object obj = method.invoke(lenovo, args);
                            return obj;
                        }
                    }
                });

        //调用方法

        String computer = proxy_lenovo.sale(10000);
        System.out.println(computer);
//        proxy_lenovo.show();
    }
}
