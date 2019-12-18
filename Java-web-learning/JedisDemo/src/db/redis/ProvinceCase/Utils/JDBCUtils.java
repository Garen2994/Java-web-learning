package db.redis.ProvinceCase.Utils;


import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

//import javax.xml.crypto.Data;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.SQLException;

public class JDBCUtils {
    private static String DRIVERNAME = null;
    private static String URL = null;
    private static String USER = null;
    private static String PASSWORD = null;

    //    private static final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://localhost:3306/day28_demo?serverTimezone=UTC&characterEncoding" +
//            "=utf-8";
//    private static final String USER = "root";
//    private static final String PASSWORD = "hjl1115326";
    public static BasicDataSource dataSource = new BasicDataSource();

    static {

        try {
            Properties pro = new Properties();
            Reader r;
            r = new FileReader("F:\\workspace\\Java-web-learning\\JedisDemo\\src\\info.properties");
            pro.load(r);
            DRIVERNAME = pro.getProperty("driver");
            URL = pro.getProperty("url");
            USER = pro.getProperty("user");
            PASSWORD = pro.getProperty("password");

            dataSource.setDriverClassName(DRIVERNAME);
            dataSource.setUrl(URL);
            dataSource.setUsername(USER);
            dataSource.setPassword(PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource() {
        return dataSource;
    }
}
