package ee.spring.factoryDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 一个创建Bean对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean：用java语言编写的可重用组件。
 *      javabean ->  实体类
 *   它就是创建service和dao对象的。
 *   第一个：需要一个配置文件来配置我们的service和dao
 *           配置的内容：唯一标识=全限定类名（key=value)
 *   第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *   我的配置文件可以是xml也可以是properties
 */
public class BeanFactory {
    private static Properties pro;
    static{
        try {
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("info.properties");
            pro = new Properties();
            pro.load(is);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("初始化properties失败!");
        }
    }

    public static Object getBean(String beanName){
        Object bean = null;
        try {
            String beanPath = pro.getProperty(beanName);
            bean = Class.forName(beanPath).getDeclaredConstructor().newInstance();  //反射的方式创建对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
