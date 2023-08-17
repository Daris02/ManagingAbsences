package hei.server.DataBase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConnection {
    @Value("${DB_URL}")
    private static String url;

    @Value("${DB_USERNAME}")
    private static String username;

    @Value("${DB_PASSWORD}")
    private static String password;

    @Bean
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                url,
                username,
                password
        );
    }
}
