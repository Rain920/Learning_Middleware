package connection;

import java.sql.*;

public class DataBaseConnection{
    private final String DBDriver ="com.mysql.jdbc.Driver";
    private final String DBURL ="jdbc:mysql://localhost:3306/db_practice?serverTimezone=UTC";
    private final String DBUSER ="root";
    private final String DBPASSWORD ="123456";
    private Connection conn = null;
    //构造器
    public DataBaseConnection(){
        try{
            Class.forName(DBDriver);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //获得连接
    public Connection getConnection(){
        System.out.println("获得连接");
        return this.conn; }

    //关闭连接
    public void close() throws SQLException{
        System.out.println("关闭连接");
        this.conn.close(); }
} 
