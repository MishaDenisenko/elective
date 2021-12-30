package dao;

import app.entities.Lesson;
import app.entities.User;

import java.sql.SQLException;

public interface ChangeDataDao {
    boolean setMark(String[] studentName, int lessonId, int mark) throws SQLException;
    User addTeacher(String[] teacherInfo) throws SQLException;
    Lesson addLesson(String lessonTheme, int teacher, String lessonName) throws SQLException;
    boolean signUp(User user, String lesson) throws SQLException;
}
