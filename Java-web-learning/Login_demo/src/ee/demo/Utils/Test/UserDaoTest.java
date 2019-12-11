package ee.demo.Utils.Test;

import ee.demo.Dao.Impl.UserDaoImpl;
import ee.demo.Dao.UserDao;
import ee.demo.Domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Title : UserDao Test
 * @Author : Garen
 * @Date : 2019/12/9 22:03
 */
public class UserDaoTest {

    UserDao dao = new UserDaoImpl();
    @Test
    public void findallTest() {
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void findByNameTest(){
//        User user = new User();
//        user.setName("Ariana");
//        User res = dao.findByName(user);
        User res = dao.findByName("Ariana");
        System.out.println(res.toString());
    }
}