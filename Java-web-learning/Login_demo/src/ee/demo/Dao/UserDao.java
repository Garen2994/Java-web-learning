package ee.demo.Dao;

import ee.demo.Domain.User;

import java.util.List;
import java.util.Map;

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
    public void deleteById(int id);
    public User findUserById(int id);
    public void update(User user);
    public int findTotalCount(Map<String,String[]> condition);
    public List<User> findUserByPage(int start, int rows, Map<String,String[]> condition)；

}
