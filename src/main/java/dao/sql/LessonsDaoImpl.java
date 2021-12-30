package dao.sql;

import app.entities.Lesson;
import app.entities.User;
import dao.LessonsDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LessonsDaoImpl implements LessonsDao {
    @Override
    public List<Lesson> getAllLessons() {
        return null;
    }

    @Override
    public List<Lesson> getLessons(String[] teacherName) {
        return null;
    }

    @Override
    public HashMap<Lesson, Integer> getMarks(User user) throws SQLException {
        return null;
    }

    protected List<Lesson> getLessonsFromDB(ResultSet resultSet, Statement statement, String query) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String theme = resultSet.getString(2);
                int teacherId = resultSet.getInt(3);
                int countStudents = resultSet.getInt(4);
                String lessonName = resultSet.getString(5);
                int status = resultSet.getInt(6);

                lessons.add(new Lesson(id, theme, teacherId, countStudents, lessonName, status));
            }
            return lessons;
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }
        return new ArrayList<>();
    }
}
