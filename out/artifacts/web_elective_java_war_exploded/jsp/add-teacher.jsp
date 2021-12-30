<%@ page import="app.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add teacher</title>
</head>
<body>
    <%
        User teacher = (User) request.getAttribute("teacher");
        if (teacher != null) {
            out.println("Преподаватель " + teacher.getName() + " успешно добавлен");
    %>
    <button onclick="location.href='/user-info'">Вернуться</button>
    <%
    } else {
    %>
    <div>
        <h1>Введите данные преподавателя</h1>
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
                <br>
                <label>логин:
                    <input type="text" name="login"><br />
                </label>
                <label>пароль:
                    <input type="text" name="password"><br />
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
