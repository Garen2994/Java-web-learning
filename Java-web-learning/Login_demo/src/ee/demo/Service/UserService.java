package ee.demo.Service;

import ee.demo.Domain.User;

import java.util.List;

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
    public List<User> findAll();
}
