package hei.server.DataBase;

import java.sql.Statement;

import static hei.server.DataBase.DBConnection.createConnection;

public class StatementDB {
    private static Statement statement;

    // Public Method to create statement
    public static Statement createStatement() {
        try {
            if (statement == null) {
                statement = createConnection().createStatement();
                return statement;
            }
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return statement;
    }
}
