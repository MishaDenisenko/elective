package app.servlets;

import app.entities.ChangeData;
import app.entities.Lesson;
import app.entities.User;
import connection.ConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/add-teacher.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            String[] teacherInfo = new String[]{
                    req.getParameter("last-name"),
                    req.getParameter("name"),
                    req.getParameter("second-name"),
                    req.getParameter("login"),
                    req.getParameter("password"),
            };
            User teacher = new ChangeData(ConnectionManager.getConnection()).addTeacher(teacherInfo);
            req.setAttribute("teacher", teacher);
            doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
