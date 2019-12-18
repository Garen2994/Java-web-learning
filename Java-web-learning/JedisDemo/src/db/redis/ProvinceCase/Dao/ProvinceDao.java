package db.redis.ProvinceCase.Dao;

import db.redis.ProvinceCase.Domain.Province;

import java.util.List;

public interface ProvinceDao {

    public List<Province> findAll();

}
