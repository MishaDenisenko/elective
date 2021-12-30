<%@ page import="app.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 03:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>

    <div>
        <h1>Введите логин и пароль</h1>
    </div>

    <div>

        <div>


            <form method="post">
                <label>Логин:
                    <input type="text" name="login"><br />
                </label>
                <label>Password:
                    <input type="password" name="password"><br />
                </label>
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
</body>
</html>
