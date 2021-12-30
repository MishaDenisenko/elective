package app.entities;

import dao.sql.LessonsDaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LessonsInfo extends LessonsDaoImpl {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public LessonsInfo(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }

    @Override
    public List<Lesson> getAllLessons() {
        query = "SELECT * FROM elective.lessons";
        return getLessonsFromDB(resultSet, statement, query);
    }

    @Override
    public HashMap<Lesson, Integer> getMarks(User user) throws SQLException {
        HashMap<Lesson, Integer> lessonsMarks = new HashMap<>();
        query = "SELECT * FROM elective.student_lesson WHERE students_id=" + user.getUserId();
        ArrayList<Integer> lessons = new ArrayList<>();
        ArrayList<Integer> marks = new ArrayList<>();

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                lessons.add(resultSet.getInt(2));
                marks.add(resultSet.getInt(3));
            }

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }

        StringBuilder request = new StringBuilder("SELECT * FROM elective.lessons WHERE idlessons=" + lessons.get(0));
        for (int i = 1; i < lessons.size(); i++) {
            request.append(" or ").append("idlessons=").append(lessons.get(i));
        }
        List<Lesson> lessonsName = getLessonsFromDB(resultSet, statement, request.toString());

        for (int i = 0; i < lessons.size(); i++) {
            lessonsMarks.put(lessonsName.get(i), marks.get(i));
        }

        return lessonsMarks;
    }

    @Override
    public List<Lesson> getLessons(String[] teacherName) {
        if (teacherName.length != 3) {
            System.out.println("Некорректные данные");
            return new ArrayList<>();
        }
        else {
            int id = 0;
            query = String.format("SELECT * FROM elective.users WHERE last_name=\"%s\" AND name=\"%s\" and second_name=\"%s\"", teacherName[0], teacherName[1], teacherName[2]);
            try {
                resultSet = statement.executeQuery(query);
                if (resultSet.next()) id = resultSet.getInt(1);
                resultSet.close();
            } catch (SQLException throwables) {
                System.err.println(throwables.getMessage());
            }

            query = "SELECT * FROM elective.lessons WHERE teacher=" + id;
            return getLessonsFromDB(resultSet, statement, query);
        }
    }
}
