package ee.demo.Service.Impl;

import ee.demo.Dao.Impl.UserDaoImpl;
import ee.demo.Dao.UserDao;
import ee.demo.Domain.User;
import ee.demo.Service.UserService;

import java.util.List;

/**
 * @Title : 用户管理业务接口的实现类
 * @Author : Garen
 * @Date : 2019/12/9 21:11
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User loginUser) {
        return dao.findByNameAndPassword(loginUser);
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.deleteById(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
       dao.update(user);
    }
}
