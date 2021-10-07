package com.dapeng.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class DBUtil {
    public DBUtil() {
    }

    /**
     * 工具类的构造方法都是私有的
     * 因为工具类的方法都是静态的，直接采用类型调用
     */

   // private DBUtil(){}
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接的方法
     * @return
     * @throws SQLException
     */
    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root","555");
    }
    //方法重载，
    public  Connection getConnection(HttpServletRequest request) throws SQLException {
        Connection conn = null;
        //1.先得到全局作用域对象
        ServletContext application = request.getServletContext();
        //2.从全局作用域对象获得map的20个连接对象
        Map map = (Map)application.getAttribute("key1");
        //3.从map中获取处于空闲的连接对象，（空拖鞋）
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            conn = (Connection)it.next();
            boolean flag = (boolean)map.get(conn);
            if (flag){
                map.put(conn,false);
                break;
            }
        }
        return conn;
    }
    /**
     * 关闭资源，
     * @param conn 连接对像
     * @param ps 数据库操作对象
     * @param rs 结果集
     */
    public  void close(Connection conn, Statement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭资源方法重载
    public  void close(HttpServletRequest request, Connection conn,Statement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Connection conn = null;
        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        map.put(conn,true);
    }
}
