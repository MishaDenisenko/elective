package app.servlets;

import app.entities.ChangeData;
import app.entities.Lesson;
import connection.ConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddLesson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/add-lesson.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            Lesson lesson = new ChangeData(ConnectionManager.getConnection()).addLesson(req.getParameter("theme"), Integer.parseInt(req.getParameter("teacher")), req.getParameter("lesson-name"));
            System.out.println(lesson);
            req.setAttribute("lesson", lesson);
            doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
