<%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 02:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-16BE" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>ADD</title>
</head>
    <body>
    <div>
        <h1>Super app!</h1>
    </div>

    <div>
        <%
            if (request.getAttribute("userName") != null) {
                out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>");
            }
        %>
        <div>
            <div>
                <h2>Add user</h2>
            </div>

            <form method="post">
                <label>Name:
                    <input type="text" name="name"><br />
                </label>
                <label>Password:
                    <input type="password" name="pass"><br />
                </label>
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>
</body>
</html>
