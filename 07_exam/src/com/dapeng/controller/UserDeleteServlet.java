package com.dapeng.controller;

import com.dapeng.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        int result = 0;
        PrintWriter out = null;
        //1、调用请求对象，读取请求头的用户编号，
        String userId = request.getParameter("userId");
        //2、调用dao将用户编号填充到delete命令，发送数据库服务器
        result = userDao.delete(userId);
        //3、调用响应对象将处理结果交给浏览器
        //设置字符集
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if (result == 1){
            out.print("<font style = 'color : red; font-size : 15px'>用户删除成功</font>");
        }else {
            out.print("<font style = 'color : red; font-size : 15px'>用户删除失败</font>");
        }
    }
}
