package ee.web.demo.Test;

import ee.web.demo.Domain.User;
import org.junit.jupiter.api.Test;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
/**
 * @Title : BeanUtils是用于封装JavaBean的工具类，本案例domain中的User就是一个JavaBean。JavaBean要满足4个条件(略)
 * @Author : Garen
 * @Date : 2019/12/2 17:38
 */
public class BeanUtilsTest {

    @Test
    public void test(){
        User user = new User();
        try {
//            BeanUtils.setProperty(user,"username","garenhou");  //只操作属性,不操作成员变量
            BeanUtils.setProperty(user,"gender","male");//是成员变量 不是属性
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(user);
    }
}
