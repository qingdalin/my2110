<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/21
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String result = (String) request.getAttribute("info");
%>
<font style="color: red;font-size: 15px">
    <%=result%>
</font>