package model;

import java.time.LocalDate;

public class Employee {
    private int employeeId;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    private String position;
    private LocalDate hireDate;
    private String status;
    private int departmentId;
    private String departmentName; // ប្រើសម្រាប់បង្ហាញលើតារាង (JOIN ជាមួយ departments)

    public Employee() {
    }

    public Employee(int employeeId, String employeeCode, String firstName, String lastName,
                     String gender, String phone, String email, String position,
                     LocalDate hireDate, String status, int departmentId) {
        this.employeeId = employeeId;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.hireDate = hireDate;
        this.status = status;
        this.departmentId = departmentId;
    }

    // ==== Getters & Setters ====
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    // ប្រើសម្រាប់ Full Name បង្ហាញលើតារាង
    public String getFullName() {
        return firstName + " " + lastName;
    }
}