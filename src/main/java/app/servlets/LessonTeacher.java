package app.servlets;

import app.entities.Lesson;
import app.entities.LessonsInfo;
import app.entities.User;
import connection.ConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/lessons-teacher.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            List<Lesson> lessons = new LessonsInfo(ConnectionManager.getConnection()).getLessons(new String[]{req.getParameter("last-name"), req.getParameter("name"), req.getParameter("second-name"), });

            req.setAttribute("lessons", lessons);
            doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
