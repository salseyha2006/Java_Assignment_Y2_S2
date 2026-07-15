package dao;

import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class EmployeeDAO {

    // ទាញយក Employee ទាំងអស់ (JOIN ជាមួយ departments ដើម្បីបានឈ្មោះ Department)
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.*, d.department_name FROM employees e " +
                     "LEFT JOIN departments d ON e.department_id = d.department_id " +
                     "ORDER BY e.employee_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ស្វែងរក Employee តាមឈ្មោះ ឬលេខកូដ (សម្រាប់ Search Box)
    public List<Employee> searchEmployees(String keyword) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.*, d.department_name FROM employees e " +
                     "LEFT JOIN departments d ON e.department_id = d.department_id " +
                     "WHERE e.employee_code ILIKE ? OR e.first_name ILIKE ? OR e.last_name ILIKE ? " +
                     "ORDER BY e.employee_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String kw = "%" + keyword + "%";
            pstmt.setString(1, kw);
            pstmt.setString(2, kw);
            pstmt.setString(3, kw);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // បន្ថែម Employee ថ្មី
    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employees " +
                     "(employee_code, first_name, last_name, gender, phone, email, position, hire_date, status, department_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, emp.getEmployeeCode());
            pstmt.setString(2, emp.getFirstName());
            pstmt.setString(3, emp.getLastName());
            pstmt.setString(4, emp.getGender());
            pstmt.setString(5, emp.getPhone());
            pstmt.setString(6, emp.getEmail());
            pstmt.setString(7, emp.getPosition());
            pstmt.setDate(8, emp.getHireDate() != null ? Date.valueOf(emp.getHireDate()) : null);
            pstmt.setString(9, emp.getStatus());
            pstmt.setInt(10, emp.getDepartmentId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // កែប្រែ Employee
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET employee_code=?, first_name=?, last_name=?, gender=?, " +
                     "phone=?, email=?, position=?, hire_date=?, status=?, department_id=? " +
                     "WHERE employee_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, emp.getEmployeeCode());
            pstmt.setString(2, emp.getFirstName());
            pstmt.setString(3, emp.getLastName());
            pstmt.setString(4, emp.getGender());
            pstmt.setString(5, emp.getPhone());
            pstmt.setString(6, emp.getEmail());
            pstmt.setString(7, emp.getPosition());
            pstmt.setDate(8, emp.getHireDate() != null ? Date.valueOf(emp.getHireDate()) : null);
            pstmt.setString(9, emp.getStatus());
            pstmt.setInt(10, emp.getDepartmentId());
            pstmt.setInt(11, emp.getEmployeeId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // លុប Employee
    public boolean deleteEmployee(int employeeId) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method ជំនួយសម្រាប់បំលែង ResultSet ទៅជា Employee Object
    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getInt("employee_id"));
        emp.setEmployeeCode(rs.getString("employee_code"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setGender(rs.getString("gender"));
        emp.setPhone(rs.getString("phone"));
        emp.setEmail(rs.getString("email"));
        emp.setPosition(rs.getString("position"));

        Date hireDate = rs.getDate("hire_date");
        emp.setHireDate(hireDate != null ? hireDate.toLocalDate() : null);

        emp.setStatus(rs.getString("status"));
        emp.setDepartmentId(rs.getInt("department_id"));
        emp.setDepartmentName(rs.getString("department_name"));
        return emp;
    }
}