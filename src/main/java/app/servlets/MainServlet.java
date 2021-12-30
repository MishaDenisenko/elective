package app.servlets;

import app.entities.User;
import app.entities.UserLogin;
import connection.ConnectionManager;
import controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        if (req.getAttribute("user") != null) requestDispatcher = req.getRequestDispatcher("jsp/user-info.jsp");
        else requestDispatcher = req.getRequestDispatcher("jsp/main-page.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = new UserLogin(ConnectionManager.getConnection()).logUserInto(req.getParameter("login"), req.getParameter("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Controller.setCurrentUser(user);
        req.setAttribute("user", user);
        doGet(req, resp);
    }
}
