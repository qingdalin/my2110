<%@ page import="java.util.List" %>
<%@ page import="com.dapeng.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/21
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Question> questionList = (List)session.getAttribute("rand");
%>
    <form action="/myWeb/exam" method="get">
        <table>
            <tr>
                <td>试题编号</td>
                <td>试题信息</td>
                <td>试题选项A</td>
                <td>试题选项B</td>
                <td>试题选项C</td>
                <td>试题选项D</td>
                <td>试题答案</td>
            </tr>
            <%
                for (Question q:questionList){
            %>
                    <tr>
                    <td><%=q.getQuestionId()%></td>
                    <td><%=q.getTitle()%></td>
                    <td><%=q.getOptionA()%></td>
                    <td><%=q.getOptionB()%></td>
                    <td><%=q.getOptionC()%></td>
                    <td><%=q.getOptionD()%></td>
                    <td>
                        <input type="radio" name="answer_<%=q.getQuestionId()%>" value="A"/>A
                        <input type="radio" name="answer_<%=q.getQuestionId()%>" value="B"/>B
                        <input type="radio" name="answer_<%=q.getQuestionId()%>" value="C"/>C
                        <input type="radio" name="answer_<%=q.getQuestionId()%>" value="D"/>D
                    </td>
                    </tr>
            <%
                }
            %>
            <tr>
                <td><input type="submit" value="交卷"></td>
                <td><input type="reset" value="重做"></td>
            </tr>
        </table>
    </form>