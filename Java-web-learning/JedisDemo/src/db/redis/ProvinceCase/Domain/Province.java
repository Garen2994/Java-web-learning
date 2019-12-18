package db.redis.ProvinceCase.Domain;
/**
 * @Title : 省份实体类
 * @Author : Garen
 * @Date : 2019/12/17 22:20
 */
public class Province {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  id +
                ": " + name;
    }
}
