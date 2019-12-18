package db.redis.ProvinceCase.Service;



import db.redis.ProvinceCase.Domain.Province;

import java.util.List;

public interface ProvinceService {
    public List<Province> findAll();
    public String findAllJson();

}
