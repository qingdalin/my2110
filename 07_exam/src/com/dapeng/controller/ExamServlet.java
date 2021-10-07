package com.dapeng.controller;

import com.dapeng.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ExamServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        List<Question> questionList = null;
        int score = 0;
        //1.从当前用户的私人储物柜得到系统的四道题目
        questionList = (List)session.getAttribute("rand");
        //2,从请求包得到四道题目的答案
        for (Question q: questionList){
            String answer = q.getAnswer();
            Integer questionId = q.getQuestionId();
            //得到用户答案
            String userAnswer = request.getParameter("answer_"+q.getQuestionId());
            //3.判断分数
            if (userAnswer.equals(answer)){
                score+=25;
            }
        }
        //4.将分数写入request，作为共享数据
        request.setAttribute("info","本次考试成绩得分为"+score);
        //5.调用请求转发将jsp将用户分数写入到响应体
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
