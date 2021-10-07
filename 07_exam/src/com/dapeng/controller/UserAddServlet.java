package com.dapeng.controller;

import com.dapeng.dao.UserDao;
import com.dapeng.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,password,sex,email;
        UserDao userDao = new UserDao();
        Users user = null;
        int result = 0;
        PrintWriter out = null;

        //1、调用请求对象，读取参数,得到用户注册信息
        userName = request.getParameter("username");
        password = request.getParameter("password");
        sex = request.getParameter("sex");
        email = request.getParameter("email");

        //2、调用userDao,将用户信息添加到insert命令，并借助JDBC规范发送到数据库当中
        user = new Users(null,userName,password,sex,email);
        Date startDate = new Date();
        result = userDao.add(user,request);
        Date endDate = new Date();
        System.out.println("本次添加用时：" + (endDate.getTime()-startDate.getTime()) + "毫秒");
        //3、调用响应对象，将处理结果，以二进制形式写入响应体当中
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if (result == 1){
            out.print("<font style = 'color : red; font-size : 15px'>用户注册成功</font>");
        }else {
            out.print("<font style = 'color : red; font-size : 15px'>用户注册失败</font>");
        }
    }
}
