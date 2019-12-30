package ee.web.ProvinceCase.Dao.Impl;

import ee.web.ProvinceCase.Dao.ProvinceDao;
import ee.web.ProvinceCase.Domain.Province;
import ee.web.demo.Utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    private DataSource dataSource = JDBCUtils.getDataSource();
    private JdbcTemplate template = new JdbcTemplate(dataSource);

    /**
     * @description 查询所有信息
     * @return
     */
    public List<Province> findAll() {
        try {
            String sql = "Select * from province";
            List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("查询失败！");
            return null;
        }

    }
}
