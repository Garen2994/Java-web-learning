package db.redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Title : Jedis测试类
 * @Author : Garen
 * @Date : 2019/12/17 15:44
 */
public class JedisTest {

    //创建连接
    Jedis jedis = new Jedis();   //本地默认值 : "localhost",6379

    @Test
    public void Test1() {

        //1. String 类型
        jedis.set("username", "ariana");
        String name = jedis.get("username");
        System.out.println(name);
        jedis.del("username");

        // *可以使用setex()方法存储可以指定过期时间的 key value
        jedis.setex("activecode", 20, "hehe");    //20秒 这个"activecode" 键值都消失

        jedis.close();
    }

    @Test
    public void Test2() {
        //2. Hash 类型
        jedis.hset("myhash", "name", "skylar");
        jedis.hset("myhash", "age", "14");

//        String s = jedis.hget("myhash", "name");
//        System.out.println("name："+ s);
        Map<String, String> map = jedis.hgetAll("myhash");
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String value = map.get(key);
            System.out.println(key + " : " + value);
        }

        jedis.del("myhash");
        jedis.close();
    }

    @Test
    public void Test3() {


        //3. list 类型
        jedis.lpush("mylist", "a", "b", "c");
        jedis.rpush("mylist", ",", "is", " ", "suck");

        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist.toString());
        jedis.del("mylist");
    }

    @Test
    public void Test4() {
        //4. set 类型
        jedis.sadd("myset", "a", "b", "b", "c");
        Set<String> myset = jedis.smembers("myset");

        System.out.println(myset);

        jedis.del("myset");
        jedis.close();

    }

    @Test
    public void Test5() {
        //5. sorted set 类型
        jedis.zadd("mysortedset", 80, "Dragon");
        jedis.zadd("mysortedset", 50, "Dimash");
        jedis.zadd("mysortedset", 90, "Garen");

        Set<String> zrange = jedis.zrange("mysortedset", 0, -1);
        System.out.println(zrange);

        jedis.close();
    }
}
