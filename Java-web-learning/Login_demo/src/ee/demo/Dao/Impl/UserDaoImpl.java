package ee.demo.Dao.Impl;
/**
 * @Title : 数据库操作Dao实现类
 * @Author : Garen
 * @Last Updated : 2019/12/13 16:39
 */
import ee.demo.Dao.UserDao;
import ee.demo.Domain.User;
import ee.demo.Utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @desciption 所有记录查询
     * @return
     */
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

    /**
     * @description 管理员用户查询
     * @param loginUser
     * @return
     */
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

    /**
     * @description 添加记录
     * @param user
     */
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

    /**
     * @description 根据id唯一标识 删除记录
     * @param id
     */
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

    /**
     * @description 根据id查询记录
     * @param id
     * @return
     */
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

    /**
     * @description 修改记录
     * @param user
     */
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
     * @description 分页查询:计算所查记录个数
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        try {
            //拼接sql查询语句
            String sql = "SELECT count(*) FROM user WHERE 1 = 1";
            StringBuilder sb = new StringBuilder(sql);   //根据condition map中是否有值来拼接SQL语句
            //遍历map
            Set<String> keySet = condition.keySet();
            //存储map中的value
            List<Object> condition_value = new ArrayList<Object>();
            for (String key : keySet) {
                if("currentPage".equals(key)||"row".equals(key)){  //排除无关参数
                    continue;
                }
                String value = condition.get(key)[0];   //只获取一个值即可
                if (value != null && !value.equals("")) {     //证明有值
                    sb.append(" and " + key + " like ?");
                    condition_value.add("%" + value + "%");     //? 条件的值
                }
            }
            sql = sb.toString();
            System.out.println(sql);
            System.out.println(condition_value);
            int i = template.queryForObject(sql, Integer.class, condition_value.toArray());
            System.out.println(i);
            return i; //自动拆箱Integer->int
        } catch (Exception e) {
            throw new RuntimeException("计算错误");
        }
    }

    /**
     * @param start
     * @param rows
     * @return
     * @description 条件 + 分页查询：拼接sql
     */
    @Override
    public List<User> findUserByPage(int start, int rows, Map<String, String[]> condition) {
        try {
            //拼接sql查询语句
            String sql = "SELECT* FROM user WHERE 1 = 1";
            StringBuilder sb = new StringBuilder(sql);   //根据condition map中是否有值来拼接SQL语句
            //遍历map
            Set<String> keySet = condition.keySet();
            //存储map中的value
            List<Object> condition_value = new ArrayList<Object>();
            for (String key : keySet) {
                if("currentPage".equals(key)||"row".equals(key)){  //排除无关参数
                    continue;
                }
                String value = condition.get(key)[0];   //只获取一个值即可
                if (value != null && !value.equals("")) {     //证明有值
                    sb.append(" and " + key + " like ?");
                    condition_value.add("%" + value + "%");     //? 条件的值
                }
            }
            sb.append(" limit ?,? ");
            condition_value.add(start);
            condition_value.add(rows);
            sql = sb.toString();
            System.out.println(sql);
            System.out.println(condition_value);
            List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class),
                    condition_value.toArray());
            for (User user : list) {
                System.out.println(user.toString());
            }
            return list;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


}
