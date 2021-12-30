package dao;

import app.entities.User;

import java.sql.SQLException;

public interface UsersDao {
    User logUserInto(String login, String password) throws SQLException;
}
