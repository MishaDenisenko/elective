<%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-16BE"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sign up</title>
</head>
<body>
    <%

        if (request.getAttribute("isSigned") != null && (boolean) request.getAttribute("isSigned")) {
            out.println("Вы успешно записались на курс");


    %>
    <button onclick="location.href='/user-info'">Вернуться</button>
    <%
    } else {
    %>
    <div>
        <h1>Введите данные курса, на который хотите записаться</h1>
    </div>

    <div>

        <div>
            <form method="post">
                <label>Название:
                    <input type="text" name="lesson"><br />
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
