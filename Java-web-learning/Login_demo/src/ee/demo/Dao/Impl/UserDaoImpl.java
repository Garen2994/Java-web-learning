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
            String sql = "SELECT * FROM user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(),
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
            String sql = "INSERT INTO user values(null,?,?,?,?,?,?,null,null)";   //必须按照数据库表设计的顺序添加
            template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),
                    user.getEmail());
//            System.out.println("添加用户成功!");
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("添加记录失败!");
        }
    }
}
