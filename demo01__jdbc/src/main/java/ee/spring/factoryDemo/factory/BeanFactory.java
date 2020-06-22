package ee.spring.factoryDemo.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
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
    private static Map<String,Object> beans;
    private static Properties props;
    static{
        try {
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("Bean.properties");
            props = new Properties();
            props.load(is);
            beans = new HashMap<String,Object>();
            Enumeration<Object> keys = props.keys();

            //遍历枚举
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String beanPath = props.getProperty(key);
                Object value = Class.forName(beanPath).getDeclaredConstructor().newInstance();  //反射的方式创建对象
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败!");
        }
    }

    /**
     * @description 根据Bean的名称获取对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return beans.get(beanName);
    }
}
