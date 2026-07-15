package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database connection parameters
    private static final String URL = "jdbc:postgresql://localhost:5432/attendance_management_system";
    private static final String USER = "postgres";    
    private static final String PASSWORD = "27082006";  

    private static Connection connection = null;

    // Constructor ធ្វើជា private ដើម្បីការពារកុំឱ្យ new class នេះដោយផ្ទាល់
    private DBConnection() {
    }

    // Method សម្រាប់ទាញយក Connection (Singleton Pattern)
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.postgresql.Driver"); // ត្រូវដាក់ postgresql JDBC driver (.jar) ក្នុង folder lib
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database Connected Successfully!");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found. សូមដាក់ postgresql jar ក្នុង folder lib ។", e);
        }
        return connection;
    }

    // Method សម្រាប់សាកល្បងតភ្ជាប់ដោយផ្ទាល់
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                System.out.println("Connection Test: SUCCESS");
            }
        } catch (SQLException e) {
            System.out.println("Connection Test: FAILED");
            e.printStackTrace();
        }
    }
}