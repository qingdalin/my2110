package com.dapeng.dao;

import com.dapeng.entity.Users;
import com.dapeng.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private DBUtil util = new DBUtil();
    //用户注册方法
    public int add(Users users){

        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            //获取连接
            conn = util.getConnection();
            String sql = "insert into users(userName,password,sex,email)" + "values(?,?,?,?)";
            //获取预编译的数据库对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,users.getUserName());
            ps.setString(2,users.getPassword());
            ps.setString(3,users.getSex());
            ps.setString(4,users.getEmail());
            //执行sql语句
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            util.close(conn,ps,null);
        }
        return result;
    }
    //用户查新方法
    public List findAll(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明一个集合，放查询到的用户信息
        List<Users> userList = new ArrayList<>();
        try {
            //获取连接
            conn = util.getConnection();
            String sql = "select * from users";
            //获取预编译对象
            ps = conn.prepareStatement(sql);

            //执行sql语句
            rs = ps.executeQuery();
            //查询结果集
            while (rs.next()){
                Integer userId = rs.getInt("userId");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                //将数据封装为user对象
                Users user = new Users(userId,userName,password,sex,email);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(conn,ps,rs);
        }
        return userList;
    }
    //根据用户编号删除用户信息
    public int delete(String userID){
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            //获取连接
            conn = util.getConnection();
            String sql = "delete from users where userId = ?";
            //获取预编译的对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setInt(1,Integer.valueOf(userID));
            //执行sql语句
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            util.close(conn,ps,null);
        }
        return result;
    }
    //登录验证
    public int login(String userName,String password){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            //获取连接
            conn = util.getConnection();
            String sql = "select count(*) from users where userName = ? and password = ?";
            //获取预编译对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,userName);
            ps.setString(2,password);
            //执行sql语句
            rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(conn,ps,null);
        }
        return result;
    }
    //方法重载
    public int add(Users users,HttpServletRequest requset){

        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            //获取连接
            conn = util.getConnection(requset);
            String sql = "insert into users(userName,password,sex,email)" + "values(?,?,?,?)";
            //获取预编译的数据库对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,users.getUserName());
            ps.setString(2,users.getPassword());
            ps.setString(3,users.getSex());
            ps.setString(4,users.getEmail());
            //执行sql语句
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            util.close(requset,conn,ps,null);
        }
        return result;
    }
}
