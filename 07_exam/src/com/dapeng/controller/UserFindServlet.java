package com.dapeng.controller;

import com.dapeng.dao.UserDao;
import com.dapeng.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        //借用输出流
        PrintWriter out = null;
        //1.调用dao将查询命令推送到数据库服务器上，得到所有的用户信息List
        List<Users> userList = new ArrayList<>();
        userList = userDao.findAll();
       /* //索要当前用户的服务费HttpSession
        HttpSession session = request.getSession(false);
        if (session == null){
            response.sendRedirect("/myWeb/login_error.html");
            return;
        }*/
        //2、调用响应对象，将用户信息结合table标签，以二进制形式写入响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        out.print("<table>");
        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户密码</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td>操作</td>");
        out.print("</tr>");

        for (Users users : userList){
            out.print("<tr>");
            out.print("<td>" + users.getUserId() + "</td>");
            out.print("<td>" + users.getUserName() + "</td>");
            out.print("<td>" + users.getPassword() + "</td>");
            out.print("<td>" + users.getSex() + "</td>");
            out.print("<td>" + users.getEmail() + "</td>");
            out.print("<td><a href='/myWeb/user/delete?userId="+users.getUserId()+"'>删除用户</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
}
