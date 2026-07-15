package ui;

import dao.AttendanceDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Attendance;
import model.Employee;

public class AttendancePanel extends JPanel {

    // កំណត់ Palette ពណ៌បែប Modern Flat UI
    private final Color PRIMARY_COLOR = new Color(44, 62, 80);    // Dark Navy
    private final Color SUCCESS_COLOR = new Color(46, 204, 113);  // Green (Check In)
    private final Color DANGER_COLOR = new Color(231, 76, 60);    // Red (Check Out)
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private final Color CARD_BG = Color.WHITE;
    private final Color TEXT_MUTED = new Color(120, 130, 140);

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtEmployeeCode; 
    private JLabel lblDate;

    private final AttendanceDAO attendanceDAO = new AttendanceDAO();

    public AttendancePanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        // រៀបចំសមាសភាគ UI តាមលំដាប់លំនឹង
        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildActionPanel(), BorderLayout.SOUTH);

        loadTodayAttendance();

        // ឱ្យវា Focus លើប្រអប់វាយកូដភ្លាមពេលបើកផ្ទាំងនេះមក
        SwingUtilities.invokeLater(() -> txtEmployeeCode.requestFocusInWindow());
    }

    // 1. ផ្នែកខាងលើ៖ ចំណងជើង និង កាលបរិច្ឆេទ 
    private JPanel buildTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel lblTitle = new JLabel("Daily Attendance Recorder");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(PRIMARY_COLOR);
        panel.add(lblTitle, BorderLayout.WEST);

        lblDate = new JLabel("Date: " + LocalDate.now().toString());
        lblDate.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblDate.setForeground(TEXT_MUTED);
        panel.add(lblDate, BorderLayout.EAST);

        return panel;
    }

    // 2. ផ្នែកកណ្តាល៖ តារាងបង្ហាញវត្តមានលក្ខណៈ Premium 
    private JPanel buildTablePanel() {
        JPanel tableCard = new JPanel(new BorderLayout(0, 12));
        tableCard.setBackground(CARD_BG);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel tableTitle = new JLabel("Today's Active Attendance Stream");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableTitle.setForeground(PRIMARY_COLOR);
        tableCard.add(tableTitle, BorderLayout.NORTH);

        String[] columns = {"Attendance ID", "Employee ID", "Employee Name", "Date", "Check In", "Check Out", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 251, 252));
                }
                return c;
            }
        };

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(40);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(240, 243, 246));
        table.setSelectionBackground(new Color(236, 240, 241));
        table.setSelectionForeground(PRIMARY_COLOR);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(236, 240, 241));
        header.setForeground(PRIMARY_COLOR);
        header.setPreferredSize(new Dimension(header.getWidth(), 42));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer nameRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.LEFT);
                setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
                return this;
            }
        };

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(nameRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(new StatusBadgeRenderer());

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(6).setPreferredWidth(130);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    // 3. ផ្នែកខាងក្រោម៖ ប្រអប់វាយលេខកូដ និងប៊ូតុងបញ្ជា
    private JPanel buildActionPanel() {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(CARD_BG);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        inputPanel.setBackground(CARD_BG);

        JLabel lblSelect = new JLabel("Enter Employee Code:");
        lblSelect.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSelect.setForeground(PRIMARY_COLOR);
        inputPanel.add(lblSelect);

        txtEmployeeCode = new JTextField();
        txtEmployeeCode.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtEmployeeCode.setPreferredSize(new Dimension(250, 38));
        txtEmployeeCode.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));

        // បើគាត់វាយកូដហើយចុច Enter ក៏ឱ្យវាស្មើនឹងការចុចប៊ូតុង Check In ដែរ
        txtEmployeeCode.addActionListener(this::onCheckIn);

        inputPanel.add(txtEmployeeCode);
        cardPanel.add(inputPanel, BorderLayout.WEST);

        JPanel buttonGroupPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        buttonGroupPanel.setBackground(CARD_BG);

        JButton btnCheckIn = createStyledButton("Check In", SUCCESS_COLOR);
        btnCheckIn.setPreferredSize(new Dimension(130, 38));
        btnCheckIn.addActionListener(this::onCheckIn);

        JButton btnCheckOut = createStyledButton("Check Out", DANGER_COLOR);
        btnCheckOut.setPreferredSize(new Dimension(130, 38));
        btnCheckOut.addActionListener(this::onCheckOut);

        buttonGroupPanel.add(btnCheckIn);
        buttonGroupPanel.add(btnCheckOut);
        cardPanel.add(buttonGroupPanel, BorderLayout.EAST);

        return cardPanel;
    }

    private JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(baseColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { button.setBackground(baseColor.brighter()); }
            public void mouseExited(java.awt.event.MouseEvent evt) { button.setBackground(baseColor); }
        });
        return button;
    }

    private void loadTodayAttendance() {
        tableModel.setRowCount(0);
        List<Attendance> list = attendanceDAO.getAttendanceByDate(LocalDate.now());
        for (Attendance a : list) {
            Object[] row = {
                a.getAttendanceId(),
                a.getEmployeeId(),
                a.getEmployeeName(),
                a.getAttendanceDate(),
                a.getCheckIn() != null ? a.getCheckIn() : "--:--",
                a.getCheckOut() != null ? a.getCheckOut() : "--:--",
                a.getStatus() != null ? a.getStatus().toUpperCase() : "PRESENT"
            };
            tableModel.addRow(row);
        }
    }

    // ព្រឹត្តិការណ៍ចុចប៊ូតុង Check In
    //  ព្រឹត្តិការណ៍ចុចប៊ូតុង Check In
    private void onCheckIn(ActionEvent e) {
        String code = txtEmployeeCode.getText().trim();
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employee Code!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ស្វែងរកផ្ទាល់ទៅកាន់ Database ដោយប្រើប្រាស់ Method សុវត្ថិភាពខ្ពស់ខាងក្រោម
        Employee emp = fetchEmployeeDirectByCode(code);
        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Employee Code '" + code + "' not found in database!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ការត្រួតពិនិត្យថ្មី៖ តើបុគ្គលិកនេះបាន Check In រួចហើយសម្រាប់ថ្ងៃនេះឬនៅ
        List<Attendance> todayRecords = attendanceDAO.getAttendanceByDate(LocalDate.now());
        for (Attendance a : todayRecords) {
            if (a.getEmployeeId() == emp.getEmployeeId()) {
                JOptionPane.showMessageDialog(this,
                    emp.getFirstName() + " " + emp.getLastName() + " has already checked in today!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                txtEmployeeCode.setText("");
                txtEmployeeCode.requestFocusInWindow();
                return;
            }
        }

        boolean success = attendanceDAO.checkIn(emp.getEmployeeId(), LocalDate.now(), LocalTime.now());
        if (success) {
            JOptionPane.showMessageDialog(this, emp.getFirstName() + " " + emp.getLastName() + " checked in successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTodayAttendance();
            txtEmployeeCode.setText(""); 
        } else {
            JOptionPane.showMessageDialog(this, "Failed to check in! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        txtEmployeeCode.requestFocusInWindow();
    }

    // ព្រឹត្តិការណ៍ចុចប៊ូតុង Check Out 
    private void onCheckOut(ActionEvent e) {
        String code = txtEmployeeCode.getText().trim();
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employee Code!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Employee emp = fetchEmployeeDirectByCode(code);
        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Employee Code '" + code + "' not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int attendanceIdToUpdate = -1;
        boolean alreadyCheckedOut = false;

        List<Attendance> todayRecords = attendanceDAO.getAttendanceByDate(LocalDate.now());
        for (Attendance a : todayRecords) {
            if (a.getEmployeeId() == emp.getEmployeeId()) {
                attendanceIdToUpdate = a.getAttendanceId();
                alreadyCheckedOut = (a.getCheckOut() != null); // ត្រួតពិនិត្យថាតើមាន Check Out Time រួចហើយឬនៅ
                break;
            }
        }

        if (attendanceIdToUpdate == -1) {
            JOptionPane.showMessageDialog(this, "This employee has not Checked In today yet!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ការត្រួតពិនិត្យថ្មី៖ ការពារកុំឱ្យ Check Out ជាន់លើគ្នា 
        if (alreadyCheckedOut) {
            JOptionPane.showMessageDialog(this, emp.getFirstName() + " " + emp.getLastName() + " has already checked out today!", "Warning", JOptionPane.WARNING_MESSAGE);
            txtEmployeeCode.setText("");
            txtEmployeeCode.requestFocusInWindow();
            return;
        }

        boolean success = attendanceDAO.checkOut(attendanceIdToUpdate, LocalTime.now());
        if (success) {
            JOptionPane.showMessageDialog(this, emp.getFirstName() + " " + emp.getLastName() + " checked out successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTodayAttendance();
            txtEmployeeCode.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to check out!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        txtEmployeeCode.requestFocusInWindow();
    }

    // វិធីសាស្ត្រថ្មី៖ រត់ទៅទាញរកទិន្នន័យពី Database ត្រង់ៗតាមរយៈ employee_code ការពារតម្លៃ null
    private Employee fetchEmployeeDirectByCode(String code) {
        String sql = "SELECT employee_id, first_name, last_name FROM employees WHERE employee_code = ?";
        try (Connection conn = config.DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Employee emp = new Employee();
                    emp.setEmployeeId(rs.getInt("employee_id"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setLastName(rs.getString("last_name"));
                    return emp;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // បើគ្មានកូដនេះក្នុង database
    }

    // Custom Renderer សម្រាប់ដេញពណ៌ Badge
    private static class StatusBadgeRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setOpaque(true);
            label.setFont(new Font("Segoe UI", Font.BOLD, 12));

            String status = value != null ? value.toString().trim() : "";

            if ("PRESENT".equalsIgnoreCase(status)) {
                label.setBackground(new Color(220, 252, 231));
                label.setForeground(new Color(21, 128, 61));
            } else if ("LATE".equalsIgnoreCase(status)) {
                label.setBackground(new Color(254, 243, 199));
                label.setForeground(new Color(180, 83, 9));
            } else if ("ABSENT".equalsIgnoreCase(status)) {
                label.setBackground(new Color(254, 226, 226));
                label.setForeground(new Color(185, 28, 28));
            } else {
                label.setBackground(new Color(241, 245, 249));
                label.setForeground(new Color(71, 85, 105));
            }

            if (isSelected) {
                label.setBackground(table.getSelectionBackground());
                label.setForeground(table.getSelectionForeground());
            }
            return label;
        }
    }
}