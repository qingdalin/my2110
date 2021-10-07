<%@ page import="java.util.List" %>
<%@ page import="com.dapeng.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/21
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Question> questionList = (List) request.getAttribute("question");
%>
    <table>
        <tr align="center">
            <td>试题编号</td>
            <td>试题题目</td>
            <td>试题选项A</td>
            <td>试题选项B</td>
            <td>试题选项C</td>
            <td>试题选项D</td>
            <td>试题答案</td>
            <td colspan="2">操作</td>
        </tr>
        <%
            for(Question q:questionList){
        %>
        <tr>
            <td><%=q.getQuestionId()%></td>
            <td><%=q.getTitle()%></td>
            <td><%=q.getOptionA()%></td>
            <td><%=q.getOptionB()%></td>
            <td><%=q.getOptionC()%></td>
            <td><%=q.getOptionD()%></td>
            <td><%=q.getAnswer()%></td>
            <td><a href="/myWeb/question/delete?questionId=<%=q.getQuestionId()%>">删除试题</a></td>
            <td><a href="/myWeb/question/findById?questionId=<%=q.getQuestionId()%>">试题信息</a></td>
        </tr>
        <%
            }
        %>

    </table>