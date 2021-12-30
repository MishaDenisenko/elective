package app.servlets;

import app.entities.ChangeData;
import app.entities.Lesson;
import app.entities.LessonsInfo;
import connection.ConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SetMark extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/set-mark.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            boolean isSet = new ChangeData(ConnectionManager.getConnection()).setMark(new String[]{req.getParameter("last-name"), req.getParameter("name"), req.getParameter("second-name"),}, Integer.parseInt(req.getParameter("lesson")), Integer.parseInt(req.getParameter("mark")));

            req.setAttribute("isSet", isSet);
            doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
