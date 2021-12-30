<%@ page import="app.entities.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 09:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-16BE"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Lesson teacher</title>
</head>
<body>
    <%
        List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
        if (lessons != null && !lessons.isEmpty() ) {
            out.println("<ui>");
            for (Lesson lesson : lessons) {
                out.println("<li>" + lesson + "</li>");
            }
            out.println("</ui>");
    %>
    <button onclick="location.href='/user-info'">Вернуться</button>
    <%
        } else {
    %>

    <div>
        <h1>Введите ФИО преподавателя</h1>
    </div>

    <div>

        <div>
            <form method="post">
                <label>Фамилия:
                    <input type="text" name="last-name"><br />
                </label>
                <label>Имя:
                    <input type="text" name="name"><br />
                </label>
                <label>Отчество:
                    <input type="text" name="second-name"><br />
                </label>
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
    <%
        }
    %>
</body>
</html>
