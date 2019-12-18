package ee.demo.Service.Impl;

import ee.demo.Dao.Impl.UserDaoImpl;
import ee.demo.Dao.UserDao;
import ee.demo.Domain.PageBean;
import ee.demo.Domain.User;
import ee.demo.Service.UserService;

import java.util.List;
import java.util.Map;

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

    @Override
    public void deleteUser(String[] ids) {
        for (String id : ids) {
            dao.deleteById(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean findUserByPage(String currentPage, String row,  Map<String, String[]> condition) {
        int current_page = Integer.parseInt(currentPage);
        int rows = Integer.parseInt(row);
        PageBean<User> pb = new PageBean<User>();

        pb.setRow(rows);
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
        if(current_page > totalPage){
            current_page = totalPage;
        }
        pb.setTotalPage(totalPage);
        pb.setCurrentPage(current_page);

        int start = (current_page - 1) * rows;
        List<User> userList = dao.findUserByPage(start, rows, condition);
        pb.setList(userList);
        return pb;
    }
}
