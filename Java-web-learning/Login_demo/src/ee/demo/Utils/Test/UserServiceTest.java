package ee.demo.Utils.Test;

import ee.demo.Domain.User;
import ee.demo.Service.Impl.UserServiceImpl;
import ee.demo.Service.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserServiceTest {

    @Test
    public void findAllTest(){
        UserService service = new UserServiceImpl();
        List<User> users = service.findAll();
        for (User user : users) {
            System.out.println(user.toString());
        }

    }

}
