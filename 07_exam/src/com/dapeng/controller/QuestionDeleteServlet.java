package com.dapeng.controller;

import com.dapeng.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        int result = 0;
        //1.调用请求对象得到用户编号
        String questionID = request.getParameter("questionId");
        //2.调用Dao，将删除信息发送数据库
        result = questionDao.deleteQuestion(questionID);
        //3、将删除信息放在请求作用域对象中
        if (result == 1){
            request.setAttribute("info","试题删除成功");
        }else {
            request.setAttribute("info","试题删除失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
