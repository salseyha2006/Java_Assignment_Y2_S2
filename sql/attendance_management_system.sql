 -- =====================================================
-- Employee Attendance Management System Database
-- PostgreSQL
-- =====================================================

-- Create Database
CREATE DATABASE attendance_management_system;

-- Connect to Database
-- \c attendance_management_system;

-- =====================================================
-- Table: Users
-- =====================================================

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'Admin',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- Table: Departments
-- =====================================================

CREATE TABLE departments (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL
);

-- =====================================================
-- Table: Employees
-- =====================================================

CREATE TABLE employees (
    employee_id SERIAL PRIMARY KEY,
    employee_code VARCHAR(20) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender VARCHAR(10),
    phone VARCHAR(20),
    email VARCHAR(100),
    position VARCHAR(100),
    hire_date DATE,
    status VARCHAR(20) DEFAULT 'Active',

    department_id INT,

    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
        ON DELETE SET NULL
);

-- =====================================================
-- Table: Attendance
-- =====================================================

CREATE TABLE attendance (
    attendance_id SERIAL PRIMARY KEY,

    employee_id INT NOT NULL,

    attendance_date DATE NOT NULL,

    check_in TIME,

    check_out TIME,

    status VARCHAR(20),

    CONSTRAINT fk_employee
        FOREIGN KEY (employee_id)
        REFERENCES employees(employee_id)
        ON DELETE CASCADE
);

-- =====================================================
-- Sample Departments
-- =====================================================

INSERT INTO departments (department_name)
VALUES
('IT'),
('HR'),
('Finance'),
('Sales');

-- =====================================================
-- Sample Users
-- =====================================================

INSERT INTO users (username, password, full_name, role)
VALUES
('admin', 'admin123', 'System Administrator', 'Admin');

-- =====================================================
-- Sample Employees
-- =====================================================

INSERT INTO employees
(employee_code, first_name, last_name, gender, phone, email, position, hire_date, status, department_id)
VALUES
('EMP001','John','Doe','Male','012345678','john@gmail.com','Manager','2025-01-01','Active',1),

('EMP002','Jane','Smith','Female','098765432','jane@gmail.com','HR Officer','2025-02-10','Active',2),

('EMP003','David','Brown','Male','011223344','david@gmail.com','Sales','2025-03-15','Active',4);

-- =====================================================
-- Sample Attendance
-- =====================================================

INSERT INTO attendance
(employee_id, attendance_date, check_in, check_out, status)
VALUES
(1, CURRENT_DATE, '08:00:00', '17:00:00', 'Present'),

(2, CURRENT_DATE, '08:15:00', '17:00:00', 'Late'),

(3, CURRENT_DATE, NULL, NULL, 'Absent');

-- =====================================================
-- View Data
-- =====================================================

SELECT * FROM users;
SELECT * FROM departments;
SELECT * FROM employees;
SELECT * FROM attendance;