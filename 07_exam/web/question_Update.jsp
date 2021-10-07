<%@ page import="com.dapeng.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/21
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Question question = (Question) request.getAttribute("questionId");
%>
<form action="/myWeb/question/update" method="get">
    <table>
        <tr>
            <td>试题编号</td>
            <td><input type="text" name="questionId" value="<%=question.getQuestionId()%>" readonly/></td>
        </tr>
        <tr>
            <td>试题题目</td>
            <td><input type="text" name="title" value="<%=question.getTitle()%>"/></td>
        </tr>
        <tr>
            <td>试题选项A</td>
            <td><input type="text" name="optionA" value="<%=question.getOptionA()%>"/></td>
        </tr>
        <tr>
            <td>试题选项B</td>
            <td><input type="text" name="optionB"  value="<%=question.getOptionB()%>"/></td>
        </tr>
        <tr>
            <td>试题选项C</td>
            <td><input type="text" name="optionC"  value="<%=question.getOptionC()%>"/></td>
        </tr>
        <tr>
            <td>试题选项D</td>
            <td><input type="text" name="optionD"  value="<%=question.getOptionD()%>"/></td>
        </tr>
        <tr>
            <td>正确答案</td>
            <td>
                <input type="radio" name="answer" value="A" <%="A".equals(question.getAnswer()) ? "checked" : ""%>/>A
                <input type="radio" name="answer" value="B" <%="B".equals(question.getAnswer()) ? "checked" : ""%>/>B
                <input type="radio" name="answer" value="C" <%="C".equals(question.getAnswer()) ? "checked" : ""%>/>C
                <input type="radio" name="answer" value="D" <%="D".equals(question.getAnswer()) ? "checked" : ""%>/>D
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="修改试题"/></td>
            <td><input type="reset" value="清空"/></td>
        </tr>
    </table>
</form>