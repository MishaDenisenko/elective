<%@ page import="app.entities.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-16BE"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add lesson</title>
</head>
<body>
    <%
        Lesson lesson = (Lesson) request.getAttribute("lesson");
        if (lesson != null) {
            out.println("Курс " + lesson.getLessonName() + " успешно добавлен");
    %>
    <button onclick="location.href='/user-info'">Вернуться</button>
    <%
    } else {
    %>
    <div>
        <h1>Введите данные курса</h1>
    </div>

    <div>

        <div>
            <form method="post">
                <label>Тема:
                    <input type="text" name="theme"><br />
                </label>
                <label>id учителя:
                    <input type="text" name="teacher"><br />
                </label>
                <label>Название:
                    <input type="text" name="lesson-name"><br />
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
