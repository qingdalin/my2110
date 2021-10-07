package com.dapeng.controller;

import com.dapeng.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionRandServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        List questionList = null;
        HttpSession session = request.getSession(false);
        //1.调用dao，从表中得到四道题目
        questionList = questionDao.findRand();
        //2.将四道题目添加的请求作用域对象
        session.setAttribute("rand",questionList);
        //3.通过请求转发调用exam.jsp,将四道题目写入响应体
        request.getRequestDispatcher("/exam.jsp").forward(request,response);
    }
}
