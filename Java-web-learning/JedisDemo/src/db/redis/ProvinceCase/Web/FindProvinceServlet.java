package db.redis.ProvinceCase.Web;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.redis.ProvinceCase.Domain.Province;
import db.redis.ProvinceCase.Service.Impl.ProvinceServiceImpl;
import db.redis.ProvinceCase.Service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FindProvinceServlet")
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //调用Service查询
//        ProvinceService service = new ProvinceServiceImpl();
//        List<Province> list = service.findAll();
//        //将数据序列化为JSON
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(list);

        ProvinceService service = new ProvinceServiceImpl();
        String json = service.findAllJson();
        System.out.println(json);
        //响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
