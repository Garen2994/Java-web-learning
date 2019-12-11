package ee.demo.Dao;

import ee.demo.Domain.User;

import java.util.List;

/**
 * Title : 用户数据访问接口
 *
 * @Author : Garen
 * @Date : 2019/12/9 21:15
 */
public interface UserDao {

    public List<User> findAll();
    public User findByNameAndPassword(User loginUser);
    public void add(User user);
}
