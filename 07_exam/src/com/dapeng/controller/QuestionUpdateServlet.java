package com.dapeng.controller;

import com.dapeng.dao.QuestionDao;
import com.dapeng.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        Question question = null;
        int resulet = 0;
        //1.调用请求对象，读取当前请求头的参数信息
        String questionId = request.getParameter("questionId");
        String title = request.getParameter("title");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String answer = request.getParameter("answer");
        //2.掉用dao实现更新操作
        question = new Question(Integer.valueOf(questionId),title,optionA,optionB,optionC,optionD,answer);
        resulet = questionDao.questionUpdate(question,request);
        //3.调用info.jsp将结果写入响应体
        if (resulet == 1){
            request.setAttribute("info","试题修改成功");
        }else {
            request.setAttribute("info","试题修改失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
