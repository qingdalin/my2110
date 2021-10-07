package com.dapeng.controller;

import com.dapeng.dao.QuestionDao;
import com.dapeng.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionFindByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        //1.调用请求对象得到请求参数
        String questionId = request.getParameter("questionId");
        //2.调用dao推送查询命令得到试题编号对应的信息
        Question question = questionDao.questionFindById(questionId);
        //3,调用question_Update.jsp,将试题信息写入响应体
        request.setAttribute("questionId",question);
        request.getRequestDispatcher("/question_Update.jsp").forward(request,response);
    }
}
