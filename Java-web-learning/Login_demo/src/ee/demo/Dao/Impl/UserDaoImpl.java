package ee.demo.Dao.Impl;

import ee.demo.Dao.UserDao;
import ee.demo.Domain.User;
import ee.demo.Utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<User> findAll() {
        String sql = "SELECT *FROM user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    public User findByName(String  name){
        String sql = "SELECT * FROM user where name = ?";
        User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),name);
        return user;
    }

}
