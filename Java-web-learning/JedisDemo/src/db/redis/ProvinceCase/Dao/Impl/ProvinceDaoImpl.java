package db.redis.ProvinceCase.Dao.Impl;


import db.redis.ProvinceCase.Dao.ProvinceDao;
import db.redis.ProvinceCase.Domain.Province;
import db.redis.ProvinceCase.Utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.List;

/**
 * @Title : Province数据库操作Dao
 * @Author : Garen
 * @Date : 2019/12/18 17:27
 */
public class ProvinceDaoImpl implements ProvinceDao {
    private DataSource dataSource = JDBCUtils.getDataSource();
    private JdbcTemplate template = new JdbcTemplate(dataSource);

    /**
     * @return
     * @description 查询所有信息
     */
    public List<Province> findAll() {
        try {
            String sql = "SELECT * FROM province_table";
            List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("查询失败！");
            return null;
        }

    }
}
