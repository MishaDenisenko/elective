<%@ page import="app.entities.User" %>
<%@ page import="enums.UserCategory" %><%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 04:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
    if (user != null){
        out.println("<h1>Добро пожаловать, " + user.getName() + " " + user.getSecondName() + " (" + user.getUserCategory() + ")" + "<h1>");
%>
    <div>
        <a href="/all-lessons">Все предметы</a>
        <br>
        <a href="/lessons-theme">Предметы по темам</a>
        <br>
        <a href="/lessons-teacher">Предметы преподавателя</a>

        <%
        if (user.getUserCategory() == UserCategory.STUDENT){
        %>
            <br>
            <a href="/my-lessons">Мои предметы</a>
            <br>
            <a href="/sign-up">Записаться</a>
        <%
        }
        %>

        <%
        if (user.getUserCategory() == UserCategory.TEACHER){
        %>
            <br>
            <a href="/set-mark">Выставить оценку</a>
        <%
        }
        %>

        <%
        if (user.getUserCategory() == UserCategory.ADMIN){
        %>
            <br>
            <a href="/add-lesson">Добавить предмет</a>
            <br>
            <a href="/add-teacher">Добавить преподавателя</a>
        <%
        }
        %>
    </div>
<%
    }
%>
</body>
</html>
