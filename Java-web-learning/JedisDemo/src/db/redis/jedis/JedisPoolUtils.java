package db.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Title : 获取Jedis连接池工具类
 * @Author : Garen
 * @Date : 2019/12/17 17:21
 */
public class JedisPoolUtils {
    private static JedisPool jedisPool;


    static {
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
        String host = pro.getProperty("host");
        int port = Integer.parseInt(pro.getProperty("port"));
        jedisPool = new JedisPool(config,host,port);

    }

    /**
     * @description 获取Jedis连接的方法
     * @return
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}

