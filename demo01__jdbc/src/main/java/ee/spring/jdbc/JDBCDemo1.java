package ee.spring.jdbc;

import java.sql.*;

/**
 * @Title : 程序的耦合
 * @Author : Garen
 * @Date : 2019/12/19 15:04
 */
public class JDBCDemo1 {
    /**
     * 注册驱动
     *  获取连接
     *  获取操作数据库的预处理对象
     *  执行sql 得到结果集
     *  遍历结果集
     *  释放资源
     **/
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_demo?serverTimezone=UTC" +
                    "&characterEncoding=utf-8", "root", "hjl1115326");
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM account");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                System.out.println(set.getString("name"));
            }
            set.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
