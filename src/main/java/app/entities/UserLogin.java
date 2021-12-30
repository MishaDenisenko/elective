package app.entities;

import dao.sql.UsersDaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLogin extends UsersDaoImpl {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public UserLogin(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }

    @Override
    public User logUserInto(String login, String password) throws SQLException {
        query = "SELECT * FROM elective.users WHERE login=" + "\"" + login + "\"";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            String userPassword = resultSet.getString(6);
            if (userPassword.equals(password)) {
                int id = resultSet.getInt(1);
                String lastName = resultSet.getString(2);
                String name = resultSet.getString(3);
                String secondName = resultSet.getString(4);
                return new User(id, lastName, name, secondName, login, password);
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
}
