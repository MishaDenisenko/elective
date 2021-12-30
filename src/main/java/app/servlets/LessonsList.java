package app.servlets;

import app.entities.Lesson;
import app.entities.LessonsInfo;
import com.sun.jdi.connect.spi.Connection;
import connection.ConnectionManager;
import controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LessonsList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = null;
        if (Controller.getSortedLessons() == null){
            try {
                lessons = new LessonsInfo(ConnectionManager.getConnection()).getAllLessons();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else lessons = Controller.getSortedLessons();
        req.setAttribute("lessons", lessons);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/all-lessons.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = null;
        try {
            lessons = new LessonsInfo(ConnectionManager.getConnection()).getAllLessons();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (req.getParameter("order").equals("none") || req.getParameter("order") == null) req.setAttribute("lessons", lessons);
        else {
            Controller.sortLessons(lessons, req.getParameter("order"));
            lessons = Controller.getSortedLessons();
            req.setAttribute("lessons", lessons);
        }
        doGet(req, resp);
    }
}
