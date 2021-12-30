<%@ page import="java.util.List" %>
<%@ page import="app.entities.Lesson" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="controller.Controller" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="app.servlets.ListServlet" %><%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 05:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-16BE"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>All Lessons</title>
</head>
<body>

    <div>
        <%
            List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
            System.out.println(lessons);
            if (lessons != null && !lessons.isEmpty()){
                out.println("<ui>");
                for (Lesson lesson : lessons) {
                    out.println("<li>" + lesson.toString() + "</li>");
                }
                out.println("<ui>");
        %>
        <%
            } else

        %>
    </div>

    <form action="/all-lessons" method="post">
        <label>Отсортировать по:
            <input type="text" name="order"><br />
        </label>
        <button type="Submit">Submit</button>
    </form>

    <button onclick="location.href='/user-info'">Вернуться</button>
</body>
</html>