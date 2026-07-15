package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private int attendanceId;
    private int employeeId;
    private String employeeName; 
    private LocalDate attendanceDate;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String status;

    public Attendance() {
    }

    public Attendance(int attendanceId, int employeeId, LocalDate attendanceDate,
                       LocalTime checkIn, LocalTime checkOut, String status) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.attendanceDate = attendanceDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    // ==== Getters & Setters ====
    public int getAttendanceId() { return attendanceId; }
    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }

    public LocalTime getCheckIn() { return checkIn; }
    public void setCheckIn(LocalTime checkIn) { this.checkIn = checkIn; }

    public LocalTime getCheckOut() { return checkOut; }
    public void setCheckOut(LocalTime checkOut) { this.checkOut = checkOut; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}