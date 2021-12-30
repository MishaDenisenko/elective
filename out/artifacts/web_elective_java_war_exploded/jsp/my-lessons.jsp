<%@ page import="java.util.HashMap" %>
<%@ page import="app.entities.Lesson" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 08:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My lessons</title>
</head>
<body>
    <%
        HashMap<Lesson, Integer> myLessons = (HashMap<Lesson, Integer>) request.getAttribute("lessons");
        if (myLessons != null && !myLessons.isEmpty()) {
            out.println("<ui>");
            for (Map.Entry<Lesson, Integer> entry : myLessons.entrySet()) {
                out.println("<li>" + entry.getKey() + "; Оцека: " + entry.getValue() + "</li>");
            }
            out.println("</ui>");
    %>
    <button onclick="location.href='/user-info'">Вернуться</button>
    <%
        } else out.println("<p>Ничего не удалось найти</p>\n");
    %>
</body>
</html>
