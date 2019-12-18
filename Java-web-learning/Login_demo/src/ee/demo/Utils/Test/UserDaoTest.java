package ee.demo.Utils.Test;

import ee.demo.Dao.Impl.UserDaoImpl;
import ee.demo.Dao.UserDao;
import ee.demo.Domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
        User user = new User();
        user.setUsername("ariana123");
        user.setPassword("arianagrande");
        User res = dao.findByNameAndPassword(user);
        System.out.println(res.toString());
    }
    @Test
    public void addTest(){
        User user = new User();
        user.setName("Kendall Jenner");
        user.setGender("女");
        user.setAge(26);
        user.setAddress("美国");
        user.setQq("1721864383");
        user.setEmail("garen2994@hotmail.com");
        UserDao dao = new UserDaoImpl();
        dao.add(user);
    }
    @Test
    public void deleteByIdTest(){
        int id = 6;
        UserDao dao = new UserDaoImpl();
        dao.deleteById(id);
    }
    @Test
    public void findUserByIdTest(){
        int id = 2;
        UserDao dao = new UserDaoImpl();
        User user = dao.findUserById(id);
        System.out.println(user.toString());
    }

    @Test
    public void updateTest(){
        User user = new User();
        user.setName("Skylar");
        user.setGender("女");
        user.setAge(29);
        user.setAddress("美国");
        user.setQq("123456789");
        user.setEmail("skylar@gmail.com");
        user.setUsername("grey456");
        user.setPassword("123456");
        user.setId(2);
        UserDaoImpl dao = new UserDaoImpl();
        dao.update(user);
    }

}