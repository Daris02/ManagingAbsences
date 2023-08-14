package hei.server.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String databaseName = "jdbc:postgresql://localhost/" + "hei";
    private static final String user = "postgres";
    private static final String password = "postgres";
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
