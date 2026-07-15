package model;

public class Department {
    private int departmentId;
    private String departmentName;

    public Department() {
    }

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // ==== Getters & Setters ====
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    // ប្រើសម្រាប់បង្ហាញក្នុង JComboBox ដោយផ្ទាល់
    @Override
    public String toString() {
        return departmentName;
    }
}