package ee.demo.Dao.Impl;

import ee.demo.Dao.UserDao;
import ee.demo.Domain.User;
import ee.demo.Utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<User> findAll() {
        try {
            String sql = "SELECT *FROM user";
            List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

            return users;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByNameAndPassword(User loginUser) {
        try {
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {
        try {
            String sql = "INSERT INTO user VALUES(null,?,?,?,?,?,?,null,null)";   //必须按照数据库表设计的顺序添加
            template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(),
                    user.getEmail());
//            System.out.println("添加用户成功!");
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("添加记录失败!");
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            String sql = "DELETE FROM user WHERE id = ?";
            template.update(sql, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    @Override
    public User findUserById(int id) {
        try {
            String sql = "SELECT * FROM user WHERE id = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User user) {
        try {
            String sql = "UPDATE user SET name = ? , gender = ?, age = ?,address = ?,qq = ?, email = ? WHERE id = ?";
            template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(),
                    user.getEmail(), user.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("修改失败");
        }
    }

    /**
     * @return
     * @description
     */
    @Override
    public int findTotalCount() {
        try {
            String sql = "SELECT count(*) FROM user";
            Integer totalCount = template.queryForObject(sql, Integer.class);   //自动拆箱Integer->int
            return totalCount;
        } catch (Exception e) {
            throw new RuntimeException("计算错误");
        }
    }

    /**
     * @param start
     * @param rows
     * @return
     * @description 分页查询每页记录
     */
    @Override
    public List<User> findUserByPage(int start, int rows) {
        try {
            String sql = "SELECT *FROM user LIMIT ? , ?";
            List<User> userList = template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
            return userList;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


}
