package dao;

import config.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Attendance;

public class AttendanceDAO {

    /**
     * ទាញយករបាយការណ៍វត្តមានដោយចម្រោះ (Filter) តាមចន្លោះថ្ងៃខែ និង ឈ្មោះ ឬកូដបុគ្គលិក ពី Database ផ្ទាល់
     */
    public List<Attendance> getAttendanceReport(LocalDate fromDate, LocalDate toDate, String searchKeyword) {
        List<Attendance> list = new ArrayList<>();
        
        // SQL Query សម្រាប់ទាញទិន្នន័យចន្លោះថ្ងៃខែ (BETWEEN)
        String sql = "SELECT a.*, e.first_name, e.last_name FROM attendance a " +
                     "JOIN employees e ON a.employee_id = e.employee_id " +
                     "WHERE a.attendance_date BETWEEN ? AND ? ";

        // ប្រសិនបើមានការវាយបញ្ចូលឈ្មោះ ឬកូដបុគ្គលិក ត្រូវបន្ថែមលក្ខខណ្ឌចម្រោះ
        boolean hasKeyword = searchKeyword != null && !searchKeyword.trim().isEmpty();
        if (hasKeyword) {
            sql += "AND (CONCAT(e.first_name, ' ', e.last_name) LIKE ? OR e.employee_code = ?) ";
        }
        
        sql += "ORDER BY a.attendance_date DESC, a.attendance_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // បញ្ចូលតម្លៃថ្ងៃចាប់ផ្ដើម និងថ្ងៃបញ្ចប់
            pstmt.setDate(1, Date.valueOf(fromDate));
            pstmt.setDate(2, Date.valueOf(toDate));

            // បញ្ចូលតម្លៃសម្រាប់ស្វែងរកឈ្មោះ ឬកូដបុគ្គលិក
            if (hasKeyword) {
                String searchPattern = "%" + searchKeyword.trim() + "%";
                pstmt.setString(3, searchPattern);      // ស្វែងរកឈ្មោះ (LIKE %keyword%)
                pstmt.setString(4, searchKeyword.trim()); // ស្វែងរកតាម Code ផ្ទាល់ (ឧ. EMP001)
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToAttendance(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ទាញយក Attendance ទាំងអស់ (JOIN ជាមួយ employees ដើម្បីបានឈ្មោះបុគ្គលិក)
    public List<Attendance> getAllAttendance() {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT a.*, e.first_name, e.last_name FROM attendance a " +
                     "JOIN employees e ON a.employee_id = e.employee_id " +
                     "ORDER BY a.attendance_date DESC, a.attendance_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToAttendance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ទាញយក Attendance តាមកាលបរិច្ឆេទជាក់លាក់ (សម្រាប់ Check-in ប្រចាំថ្ងៃ)
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT a.*, e.first_name, e.last_name FROM attendance a " +
                     "JOIN employees e ON a.employee_id = e.employee_id " +
                     "WHERE a.attendance_date = ? " +
                     "ORDER BY a.attendance_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToAttendance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // កត់ត្រា Check-in ថ្មី
    public boolean checkIn(int employeeId, LocalDate date, java.time.LocalTime time) {
        String sql = "INSERT INTO attendance (employee_id, attendance_date, check_in, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employeeId);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.setTime(3, Time.valueOf(time));
            pstmt.setString(4, "Present");

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // កត់ត្រា Check-out (Update លើ record ដែលមាន Check-in ស្រាប់)
    public boolean checkOut(int attendanceId, java.time.LocalTime time) {
        String sql = "UPDATE attendance SET check_out = ? WHERE attendance_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTime(1, Time.valueOf(time));
            pstmt.setInt(2, attendanceId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method ជំនួយសម្រាប់បំលែង ResultSet ទៅជា Attendance Object
    private Attendance mapResultSetToAttendance(ResultSet rs) throws SQLException {
        Attendance att = new Attendance();
        att.setAttendanceId(rs.getInt("attendance_id"));
        att.setEmployeeId(rs.getInt("employee_id"));
        att.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));

        Date date = rs.getDate("attendance_date");
        att.setAttendanceDate(date != null ? date.toLocalDate() : null);

        Time checkIn = rs.getTime("check_in");
        att.setCheckIn(checkIn != null ? checkIn.toLocalTime() : null);

        Time checkOut = rs.getTime("check_out");
        att.setCheckOut(checkOut != null ? checkOut.toLocalTime() : null);

        att.setStatus(rs.getString("status"));
        return att;
    }

    public model.Employee getEmployeeByCode(String code) {
        String sql = "SELECT * FROM employees WHERE employee_code = ? AND status = 'Active'";
        try (Connection conn = config.DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, code);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    model.Employee emp = new model.Employee();
                    emp.setEmployeeId(rs.getInt("employee_id"));
                    emp.setEmployeeCode(rs.getString("employee_code"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setLastName(rs.getString("last_name"));
                    return emp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}