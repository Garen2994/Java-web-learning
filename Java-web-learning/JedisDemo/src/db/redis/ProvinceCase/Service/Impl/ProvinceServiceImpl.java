package db.redis.ProvinceCase.Service.Impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.redis.ProvinceCase.Dao.Impl.ProvinceDaoImpl;
import db.redis.ProvinceCase.Dao.ProvinceDao;
import db.redis.ProvinceCase.Domain.Province;
import db.redis.ProvinceCase.Service.ProvinceService;
import db.redis.ProvinceCase.Utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    ProvinceDao dao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /**
     * @return
     * @description 使用Redis缓存进行查询
     */
    @Override
    public String findAllJson() {
        //从redis中1查询数据
        //Jedis获得连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        if (province_json == null || province_json.length() == 0) {   //Redis中没有数据，从数据库中加载到缓存中
            System.out.println("Redis中未缓存数据，存储中。。。");
            List<Province> provinceList = dao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(provinceList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                System.out.println("加载失败");
            }
            //将json存入到Redis中
            jedis.set("province", province_json);
            jedis.close(); //归还连接
        } else {   //Redis中有数据
            System.out.println("Redis中缓存了数据,读取中。。。");
        }
        return province_json;
    }
}
