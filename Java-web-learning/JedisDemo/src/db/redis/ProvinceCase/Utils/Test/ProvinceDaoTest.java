package db.redis.ProvinceCase.Utils.Test;

import db.redis.ProvinceCase.Dao.Impl.ProvinceDaoImpl;
import db.redis.ProvinceCase.Domain.Province;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProvinceDaoTest {
    @Test
    public void ProvinceDaoTest(){
        ProvinceDaoImpl dao = new ProvinceDaoImpl();
        List<Province> list = dao.findAll();
        for (Province province : list) {
            System.out.println(province);
        }
    }
}
