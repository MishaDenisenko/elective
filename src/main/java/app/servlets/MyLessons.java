package app.servlets;

import app.entities.Lesson;
import app.entities.LessonsInfo;
import connection.ConnectionManager;
import controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MyLessons extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<Lesson, Integer> lessons = null;
            try {
                lessons = new LessonsInfo(ConnectionManager.getConnection()).getMarks(Controller.getCurrentUser());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        req.setAttribute("lessons", lessons);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/my-lessons.jsp");
        requestDispatcher.forward(req, resp);
    }
}
