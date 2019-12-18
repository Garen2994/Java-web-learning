package ee.demo.Utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.util.Properties;

/**
 * @Title : 使用DBCP连接池+数据源
 * @Author : Garen
 * @Date : 2019/12/9 21:40
 */
public class JDBCUtils {
    private static String DRIVERNAME = null;
    private static String URL = null;
    private static String USER = null;
    private static String PASSWORD = null;

    //获取数据源
    private static BasicDataSource ds = new BasicDataSource();
    static {
        try {
            Properties pro = new Properties();
            Reader r;
            r = new FileReader("F:\\workspace\\Java-web-learning\\Login_demo\\src\\ee\\demo\\Utils\\info.properties");
            pro.load(r);
            DRIVERNAME = pro.getProperty("driver");
            URL = pro.getProperty("url");
            USER = pro.getProperty("user");
            PASSWORD = pro.getProperty("password");

            ds.setDriverClassName(DRIVERNAME);
            ds.setUrl(URL);
            ds.setUsername(USER);
            ds.setPassword(PASSWORD);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException i){
            i.printStackTrace();
        }
    }
        public static DataSource getDataSource(){
            return ds;
        }
        //返回数据源供JDBCTemplate使用

}
