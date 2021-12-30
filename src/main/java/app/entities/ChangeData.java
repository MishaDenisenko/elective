package app.entities;

import dao.sql.ChangeDataDaoImpl;
import dao.sql.LessonsDaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChangeData extends ChangeDataDaoImpl {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public ChangeData(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }
    @Override
    public boolean setMark(String[] studentName, int lessonId, int mark) throws SQLException {
        if (studentName.length != 3) return false;
        else {
            query = String.format("SELECT * FROM elective.users WHERE last_name=\"%s\" AND name=\"%s\" and second_name=\"%s\"", studentName[0].trim(), studentName[1].trim(), studentName[2].trim());
            int id = 0;
            try {
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) id = resultSet.getInt(1);
                resultSet.close();
            } catch (SQLException throwables) {
                System.err.println(throwables.getMessage());
            }

            return setMarkIntoDB(statement, id, lessonId, mark);
        }
    }

    @Override
    public User addTeacher(String[] teacherInfo) throws SQLException {
        try {
            query = "SELECT * FROM elective.users WHERE login=\"" + teacherInfo[3] + "\"";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("Такой пользователь уже существует");
                return null;
            }
            query = "INSERT INTO elective.users (last_name, name, second_name, login, password) VALUES (\"" + teacherInfo[0] + "\", \"" + teacherInfo[1] + "\", \"" + teacherInfo[2] + "\", \"" + teacherInfo[3] + "\", \"" + teacherInfo[4] + "\")";

            statement.execute(query);
            resultSet.close();
            query = "SELECT * FROM elective.users WHERE login=\"" + teacherInfo[3] + "\"" + " AND password=\"" + teacherInfo[4] + "\"";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                String lastName = resultSet.getString(2);
                String name = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String login = resultSet.getString(5);
                String password = resultSet.getString(6);

                return new User(id, lastName, name, firstName, login, password);
            }
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }
        finally {
            statement.close();
            resultSet.close();
        }
        return null;
    }

    @Override
    public Lesson addLesson(String lessonTheme, int teacher, String lessonName) throws SQLException {
        try {
            query = "SELECT * FROM elective.lessons WHERE lesson_theme=\"" + lessonTheme + "\"" +  " AND lesson_name=\"" + lessonName + "\"" ;
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("Такой предмет уже присутствует");
                return null;
            }
            query = "INSERT INTO elective.lessons (lesson_theme, teacher, count_of_students, lesson_name, status) VALUES (\"" + lessonTheme + "\", " + teacher + ", " + 0 + ", \"" + lessonName + "\", " + -1 + ")";

            statement.execute(query);
            resultSet.close();
            query = "SELECT * FROM elective.lessons WHERE lesson_theme=\"" + lessonTheme + "\"" +  " AND lesson_name=\"" + lessonName + "\"" ;
            resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                String theme = resultSet.getString(2);
                int teacherId = resultSet.getInt(3);
                int countStudents = resultSet.getInt(4);
                String name = resultSet.getString(5);
                int status = resultSet.getInt(6);

                return new Lesson(id, theme, teacherId, countStudents, name, status);
            }
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }
        finally {
            statement.close();
            resultSet.close();
        }

        return null;
    }

    @Override
    public boolean signUp(User user, String lesson) throws SQLException {
        query = "SELECT * FROM elective.student_lesson WHERE students_id=" + user.getUserId();
        ArrayList<Integer> lessonsId = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                lessonsId.add(resultSet.getInt(2));
            }
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }

        StringBuilder request = new StringBuilder("SELECT * FROM elective.lessons WHERE idlessons=" + lessonsId.get(0));
        for (int i = 1; i < lessonsId.size(); i++) {
            request.append(" or ").append("idlessons=").append(lessonsId.get(i));
        }

        List<Lesson> studentLessons = getLessonsFromDB(resultSet, statement, request.toString());
        for (Lesson studentLesson : studentLessons) {
            if (studentLesson.getLessonName().equalsIgnoreCase(lesson)) {
                System.out.println("Вы уже записаны на этот курс.");
                return false;
            }
        }
        List<Lesson> allLessons = getLessonsFromDB(resultSet, statement,"SELECT * FROM elective.lessons");
        for (Lesson l : allLessons) {
            if (l.getLessonName().equalsIgnoreCase(lesson) && l.getStatus() == -1){
                try {
                    query = "INSERT INTO elective.student_lesson (students_id, lesson_id, mark) VALUES (" + user.getUserId() + ", " + l.getLessonId() + ", " + 0 + ")";
                    statement.execute(query);
                    query = "UPDATE elective.lessons SET count_of_students=" + (l.getCountOfStudents()+1) + " WHERE idlessons=" + l.getLessonId();
                    statement.execute(query);

                    return true;
                } catch (SQLException throwables) {
                    System.err.println(throwables.getMessage());
                }
                finally {
                    statement.close();
                    resultSet.close();
                }
            }
            else if (l.getLessonName().equalsIgnoreCase(lesson) && l.getStatus() != -1)
                System.out.println("Невозможно записаться на курс");
        }

        return false;
    }
}
