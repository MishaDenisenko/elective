<%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-16BE"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Set Mark</title>
</head>
<body>
    <%

        if (request.getAttribute("isSet") != null && (boolean) request.getAttribute("isSet")) {
            out.println("Оценка выставлена успешно");


        %>
        <button onclick="location.href='/user-info'">Вернуться</button>
        <%
        } else {
    %>
    <div>
        <h1>Введите ФИО ученика</h1>
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
                <label>Предмет №:
                    <input type="text" name="lesson"><br />
                </label>
                <label>Оценка:
                    <input type="text" name="mark"><br />
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
