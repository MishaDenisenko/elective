package app.servlets;

import app.entities.ChangeData;
import connection.ConnectionManager;
import controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/sign-up.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            boolean isSigned = new ChangeData(ConnectionManager.getConnection()).signUp(Controller.getCurrentUser(), req.getParameter("lesson"));

            req.setAttribute("isSigned", isSigned);
            doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
