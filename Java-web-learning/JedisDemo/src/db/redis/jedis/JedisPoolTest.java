package db.redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title : Jedis连接池的使用
 * @Author : Garen
 * @Date : 2019/12/17 16:25
 */
public class JedisPoolTest {
    @Test
    public void PoolTest() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);
        //获取连接池对象
        JedisPool pool = new JedisPool(config, "localhost", 6379);
        Jedis jedis = pool.getResource();
        jedis.set("smile", "hehe");
        String s = jedis.get("smile");
        System.out.println(s);

        jedis.close();
    }

    @Test
    public void JedisUtilsTest() {
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("myname", "Garen Hou");
        String s = jedis.get("myname");
        System.out.println(s);

        jedis.close();
    }
}
