package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/student_management";
        String user = "postgres";
        String password = "yourpassword";
        return DriverManager.getConnection(url, user, password);
    }
}