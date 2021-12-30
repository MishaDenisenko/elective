<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Денисенко Михаил
  Date: 30.12.2021
  Time: 02:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-16" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <h1>Super app!</h1>
    </div>

    <div>
        <div>
            <div>
                <h2>Users</h2>
            </div>
            <%
                List<String> names = (List<String>) request.getAttribute("userNames");

                if (names != null && !names.isEmpty()) {
                    out.println("<ui>");
                    for (String s : names) {
                        out.println("<li>" + s + "</li>");
                    }
                    out.println("</ui>");
                } else out.println("<p>There are no users eee!</p>");
            %>
        </div>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>
</body>
</html>
