package ee.web.demo.Dao;

import ee.web.demo.Domain.User;
import ee.web.demo.Utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Title : 操作数据库中的类(使用Spring JDBC from spring-jdbc-5.0.0 org.springframework.jdbc.core.*)
 * @Author : Garen
 * @Date : 2019/11/28 17:11
 */
public class UserDao {

//    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private DataSource dataSource = JDBCUtils.getDataSource();
    private JdbcTemplate template = new JdbcTemplate(dataSource);
    /**
     * @description 登录方法
     * @param loginUser
     * @return 返回一个User类 对象
     */
    public User login(User loginUser){
        try{
//            String sql = "SELECT * FROM user WHERE USERNAME= ? AND PASSWORD = ?";
//            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),
//                    loginUser.getUsername(),loginUser.getPassword()  //查询结果 返回
            String sql = "SELECT * FROM user WHERE USERNAME= ? AND PASSWORD = ?";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),loginUser.getPassword() //查询结果 返回对象
            );
            System.out.println("查询成功!");
            return user;

        }catch(Exception e){
            e.getStackTrace();//记录日志
//            throw new RuntimeException("查询失败！");
            return null;
        }

    }


}
