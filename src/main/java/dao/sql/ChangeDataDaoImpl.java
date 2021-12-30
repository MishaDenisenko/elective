package dao.sql;

import app.entities.Lesson;
import app.entities.User;
import dao.ChangeDataDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChangeDataDaoImpl implements ChangeDataDao {
    @Override
    public boolean setMark(String[] studentName, int lessonId, int mark) throws SQLException {
        return false;
    }

    @Override
    public User addTeacher(String[] teacherInfo) throws SQLException {
        return null;
    }

    @Override
    public Lesson addLesson(String lessonTheme, int teacher, String lessonName) throws SQLException {
        return null;
    }

    @Override
    public boolean signUp(User user, String lesson) throws SQLException {
        return false;
    }

    protected boolean setMarkIntoDB(Statement statement, int id, int lessonId, int mark) throws SQLException {
        if (mark < 0 || mark > 100) return false;
        String query = "UPDATE elective.student_lesson SET mark=" + mark + " WHERE students_id=" + id + " AND lesson_id=" + lessonId;
        try {
            statement.execute(query);
            return true;
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }
        return false;
    }

    protected List<Lesson> getLessonsFromDB(ResultSet resultSet, Statement statement, String query) throws SQLException {
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
