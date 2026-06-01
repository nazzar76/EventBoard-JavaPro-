package eventBoard.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/event_board_db";

    private static final String USER = "postgres";

    private static final String PASSWORD = "20yfpfhxbr03";

    public static Connection getConnection() {

        try {

            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}