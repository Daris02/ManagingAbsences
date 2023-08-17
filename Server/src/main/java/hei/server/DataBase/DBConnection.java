package hei.server.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String databaseName = System.getenv("DB_URL");
    private static final String user = System.getenv("DB_USERNAME");
    private static final String password = System.getenv("DB_PASSWORD");
    private static Connection connection;

    // Public Method to create connection
    public static Connection createConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        databaseName, user, password);
                return connection;
            }
            return connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
