package ee.web.demo.Test;

import ee.web.demo.Dao.UserDao;
import ee.web.demo.Domain.User;
import org.junit.jupiter.api.Test;

/**
 * Description :
 * @Author : Garen
 * @Date : 2019/11/29 20:32
 */
public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUsername("garen");
        loginUser.setPassword("123456");
        System.out.println(loginUser.toString());
//        System.out.println(loginUser.toString());
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        System.out.println(user.getUsername());

       System.out.println(user);
    }
}
