package com.dapeng.controller;

import com.dapeng.dao.QuestionDao;
import com.dapeng.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList = new ArrayList<>();
        //1、调用Dao，将查询命令推送到服务器上，得到list几集合
        questionList = questionDao.questionFindAll();
        //2、调用请求对象，转发给jsp，将查询结果输出到响应体
        request.setAttribute("question",questionList);
        request.getRequestDispatcher("/question_Find.jsp").forward(request,response);
    }
}
