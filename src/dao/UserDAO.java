package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // ពិនិត្យ Username + Password ថាត្រឹមត្រូវអត់ (សម្រាប់ Login)
    // បើត្រូវ -> return true, បើខុស -> return false
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            // rs.next() ប្រាប់ថាមាន Row ត្រូវគ្នាដែរឬអត់
            if (rs.next()) {
                return true;  // Login ត្រូវ
            } else {
                return false; // Login ខុស
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            // បិទអ្វីៗដែលបានបើក (ធម្មតាត្រូវធ្វើរាល់ពេលប្រើ Database)
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
        }
    }

    // ពិនិត្យ Username មានរួចហើយឬនៅ (សម្រាប់ Register)
    public boolean isUsernameExist(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            rs = pstmt.executeQuery();
            return rs.next(); // ថ្ថ true បើមាន Row មួយត្រូវគ្នា

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
        }
    }

    // បង្កើត User ថ្មី (សម្រាប់ Register)
    public boolean register(String username, String password, String fullName) {
        String sql = "INSERT INTO users (username, password, full_name, role) VALUES (?, ?, ?, 'Admin')";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullName);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0; // true បើបញ្ចូលបានជោគជ័យ

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
        }
    }
}