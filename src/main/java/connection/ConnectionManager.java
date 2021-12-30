package connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class ConnectionManager {
    private static Connection connection = null;

    public ConnectionManager() {
    }

    public static void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = Constants.URL;
            String user_name = Constants.USER_NAME;
            String password = Constants.PASSWORD;
            connection = DriverManager.getConnection(url, user_name, password);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }

        return connection;
    }
}
