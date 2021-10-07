package com.dapeng.controller;

import com.dapeng.dao.QuestionDao;
import com.dapeng.entity.Question;
import com.dapeng.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        Question question = null;
        int resulet = 0;
        //1、调用请求对象，读取请求头的参数信息
        String title = request.getParameter("title");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String answer = request.getParameter("answer");
        //2、调用QuestionDao,将试题信息添加到数据库
        question = new Question(null,title,optionA,optionB,optionC,optionD,answer);
        resulet = questionDao.add(question,request);
        //3、请求转发jsp，将结果输入到响应体
        if (resulet == 1){
            request.setAttribute("info","试题添加成功");
        }else {
            request.setAttribute("info","试题添加失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
