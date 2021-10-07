package com.dapeng.controller;

import com.dapeng.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        int result = 0;
        //PrintWriter out = null;
        //1、设置字符集
        request.setCharacterEncoding("utf-8");
        //2、调用请求对象得到参数
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        //3、调用dao将查询信息，发送到数据库
        result = userDao.login(userName,password);
        //4、调用响应对象，将验证结果将不同资源文件地址发送浏览器
        //out = response.getWriter();
        if (result == 1){
            //判断当前用户身份合法后，通过请求对象在tomcat申请一个HttpSession,
            HttpSession session = request.getSession();
            response.sendRedirect("/myWeb/index.html");
        }else {
            response.sendRedirect("/myWeb/login_error.html");
        }
    }
}
