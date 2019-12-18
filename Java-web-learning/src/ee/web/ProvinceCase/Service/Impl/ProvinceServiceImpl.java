package ee.web.ProvinceCase.Service.Impl;

import ee.web.ProvinceCase.Dao.Impl.ProvinceDaoImpl;
import ee.web.ProvinceCase.Dao.ProvinceDao;
import ee.web.ProvinceCase.Domain.Province;
import ee.web.ProvinceCase.Service.ProvinceService;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    ProvinceDao dao = new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }
}
