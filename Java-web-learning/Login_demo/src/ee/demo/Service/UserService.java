package ee.demo.Service;

import ee.demo.Domain.PageBean;
import ee.demo.Domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Title : 用户管理业务接口
 * @Author : Garen
 * @Date : 2019/12/9 21:10
 */
public interface UserService {
    /**
     * @description 查询所有用户信息
     * @return
     */
    public User login(User loginUser);
    public List<User> findAll();
    public void addUser(User user);
    public void deleteUser(String id);
    public User findUserById(String id);
    public void updateUser(User user);
    public void deleteUser(String[] ids);
    public PageBean findUserByPage(String currentPage, String row, Map<String, String[]> condition);  //分页条件查询
}
