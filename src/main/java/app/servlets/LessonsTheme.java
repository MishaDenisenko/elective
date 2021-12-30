package app.servlets;

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
import java.util.ArrayList;
import java.util.List;

public class LessonsTheme extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/lessons-theme.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            List<Lesson> lessons = new LessonsInfo(ConnectionManager.getConnection()).getAllLessons();
            List<Lesson> newLessons = new ArrayList<>();
            for (Lesson lesson : lessons) {
                if (lesson.getLessonTheme().equalsIgnoreCase(req.getParameter("theme"))) newLessons.add(lesson);
            }

            req.setAttribute("lessons", newLessons);
            doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
