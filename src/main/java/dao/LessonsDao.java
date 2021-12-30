package dao;

import app.entities.Lesson;
import app.entities.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface LessonsDao {
    List<Lesson> getAllLessons();
    List<Lesson> getLessons(String[] teacherName);
    HashMap<Lesson, Integer> getMarks(User user) throws SQLException;

}
