package ui;

import dao.AttendanceDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Attendance;

public class ReportPanel extends JPanel {

    // កំណត់ Palette ពណ៌បែប Modern Flat UI
    private final Color PRIMARY_COLOR = new Color(44, 62, 80);    // Dark Navy
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private final Color CARD_BG = Color.WHITE;

    private JTable table;
    private DefaultTableModel tableModel;

    // សមាសភាគសម្រាប់ធ្វើ Advanced Filter
    private JSpinner spinnerFromDate;
    private JSpinner spinnerToDate;
    private JTextField txtSearchEmployee;

    private final AttendanceDAO attendanceDAO = new AttendanceDAO();

    public ReportPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        // រៀបចំសមាសភាគ UI តាមលំដាប់លំនឹងលំហ
        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);

        // បង្ហាញរបាយការណ៍ទាំងអស់នៅពេលបើកដំបូង
        loadAllAttendance();
    }

    // --- 1. ផ្នែកខាងលើ៖ ចំណងជើង និង ផ្ទាំង Advanced Filter Controls ---
    private JPanel buildTopPanel() {
        JPanel mainTopPanel = new JPanel(new BorderLayout(0, 15));
        mainTopPanel.setBackground(BACKGROUND_COLOR);

        // ជួរទី១៖ ចំណងជើងធំ
        JLabel title = new JLabel("Attendance Reports");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(PRIMARY_COLOR);
        mainTopPanel.add(title, BorderLayout.WEST);

        // ជួរទី២៖ របារឧបករណ៍ Filter (Date Range + Employee Search)
        JPanel filterContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        filterContainer.setBackground(CARD_BG);
        filterContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                new EmptyBorder(10, 15, 10, 15)
        ));

        // កំណត់ទម្រង់ Date សម្រាប់ JSpinner
        SpinnerDateModel modelFrom = new SpinnerDateModel();
        SpinnerDateModel modelTo = new SpinnerDateModel();

        spinnerFromDate = new JSpinner(modelFrom);
        spinnerToDate = new JSpinner(modelTo);

        JSpinner.DateEditor editorFrom = new JSpinner.DateEditor(spinnerFromDate, "yyyy-MM-dd");
        JSpinner.DateEditor editorTo = new JSpinner.DateEditor(spinnerToDate, "yyyy-MM-dd");

        spinnerFromDate.setEditor(editorFrom);
        spinnerToDate.setEditor(editorTo);

        Dimension spinnerSize = new Dimension(130, 35);
        spinnerFromDate.setPreferredSize(spinnerSize);
        spinnerToDate.setPreferredSize(spinnerSize);
        spinnerFromDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        spinnerToDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // ប្រអប់វាយស្វែងរកបុគ្គលិក (ឈ្មោះ ឬ ID/Code)
        txtSearchEmployee = new JTextField();
        txtSearchEmployee.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearchEmployee.setPreferredSize(new Dimension(220, 35));
        txtSearchEmployee.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        // បើគាត់វាយហើយចុច Enter ក៏ឱ្យវា Filter ភ្លាមៗដែរ
        txtSearchEmployee.addActionListener(this::onApplyAdvancedFilter);

        // រៀបចំដាក់ចូលក្នុង Layout
        filterContainer.add(new JLabel("From:"));
        filterContainer.add(spinnerFromDate);
        filterContainer.add(new JLabel("To:"));
        filterContainer.add(spinnerToDate);

        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setPreferredSize(new Dimension(2, 25));
        filterContainer.add(sep);

        filterContainer.add(new JLabel("Employee (Name/Code):"));
        filterContainer.add(txtSearchEmployee);

        // ប៊ូតុង Filter ពណ៌ Navy លេចធ្លោ
        JButton btnFilter = new JButton("Filter");
        btnFilter.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnFilter.setForeground(Color.WHITE);
        btnFilter.setBackground(PRIMARY_COLOR);
        btnFilter.setFocusPainted(false);
        btnFilter.setPreferredSize(new Dimension(90, 35));
        btnFilter.setBorder(BorderFactory.createEmptyBorder());
        btnFilter.addActionListener(this::onApplyAdvancedFilter);

        btnFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btnFilter.setBackground(PRIMARY_COLOR.brighter()); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btnFilter.setBackground(PRIMARY_COLOR); }
        });
        filterContainer.add(btnFilter);

        // ប៊ូតុង Reset សម្រាប់សម្អាតទិន្នន័យនាំមកវិញទាំងអស់
        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReset.setForeground(PRIMARY_COLOR);
        btnReset.setBackground(Color.WHITE);
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(90, 35));
        btnReset.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        btnReset.addActionListener(this::onResetFilter);
        filterContainer.add(btnReset);

        mainTopPanel.add(filterContainer, BorderLayout.SOUTH);
        return mainTopPanel;
    }

    // --- 2. ផ្នែកកណ្តាល៖ តារាងបង្ហាញរបាយការណ៍លក្ខណៈ Premium ---
    private JPanel buildTablePanel() {
        JPanel tableCard = new JPanel(new BorderLayout(0, 12));
        tableCard.setBackground(CARD_BG);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel tableTitle = new JLabel("Filtered Historical Attendance Log");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableTitle.setForeground(PRIMARY_COLOR);
        tableCard.add(tableTitle, BorderLayout.NORTH);

        String[] columns = {"Attendance ID", "Employee Name", "Attendance Date", "Check In", "Check Out", "Status"};
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
        table.getColumnModel().getColumn(1).setCellRenderer(nameRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(new StatusBadgeRenderer());

        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(280);
        table.getColumnModel().getColumn(2).setPreferredWidth(160);
        table.getColumnModel().getColumn(5).setPreferredWidth(140);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    // --- ទាញយកទិន្នន័យវត្តមានទាំងអស់មកបង្ហាញដំបូង ---
    private void loadAllAttendance() {
        List<Attendance> list = attendanceDAO.getAllAttendance();
        fillTable(list);
    }

    // --- មុខងារ Filter ឆ្លាតវៃ (ទាញយកទិន្នន័យត្រង់ពី Database តាមលក្ខខណ្ឌចម្រោះ) ---
    private void onApplyAdvancedFilter(ActionEvent e) {
        // ១. បំប្លែងកាលបរិច្ឆេទពី JSpinner ទៅជា LocalDate
        java.util.Date dateFromRaw = (java.util.Date) spinnerFromDate.getValue();
        java.util.Date dateToRaw = (java.util.Date) spinnerToDate.getValue();

        LocalDate fromDate = dateFromRaw.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate toDate = dateToRaw.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // ការពារករណីអ្នកប្រើជ្រើសរើសថ្ងៃចាប់ផ្តើម ធំជាង ថ្ងៃបញ្ចប់
        if (fromDate.isAfter(toDate)) {
            JOptionPane.showMessageDialog(this, "'From Date' cannot be after 'To Date'!", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ២. ទាញពាក្យគន្លឹះស្វែងរកឈ្មោះ ឬលេខកូដបុគ្គលិក
        String searchKeyword = txtSearchEmployee.getText().trim();

        // ៣. ហៅទៅកាន់ DAO ដើម្បីទាញទិន្នន័យចម្រោះចេញពី Database ផ្ទាល់
        List<Attendance> filteredList = attendanceDAO.getAttendanceReport(fromDate, toDate, searchKeyword);

        // ៤. បោះទិន្នន័យដែលចម្រាញ់រួចចូលតារាង
        fillTable(filteredList);

        // === ការត្រួតពិនិត្យ៖ ជូនដំណឹងបើ Filter រកមិនឃើញលទ្ធផលអ្វីសោះ ===
        if (filteredList.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No attendance records found for the selected filter.",
                "No Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // --- ព្រឹត្តិការណ៍ចុច Reset ដើម្បីសម្អាតលក្ខខណ្ឌ Filter ទាំងអស់ ---
    private void onResetFilter(ActionEvent e) {
        // កំណត់ From Date ឱ្យទៅ ១ ខែមុន (ដើម្បីងាយស្រួលមើលឃើញទិន្នន័យចាស់ៗឡើងវិញភ្លាមៗ)
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        java.util.Date fromDate = java.util.Date.from(oneMonthAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        spinnerFromDate.setValue(fromDate);
        spinnerToDate.setValue(new java.util.Date()); // ថ្ងៃនេះ
        txtSearchEmployee.setText("");
        
        loadAllAttendance(); // បង្ហាញទិន្នន័យទាំងអស់ឡើងវិញ
    }

    // --- Method ជំនួយសម្រាប់ចាក់បំពេញទិន្នន័យទៅក្នុងតារាង ---
    private void fillTable(List<Attendance> list) {
        tableModel.setRowCount(0);
        for (Attendance a : list) {
            tableModel.addRow(new Object[]{
                    a.getAttendanceId(),
                    a.getEmployeeName(),
                    a.getAttendanceDate(),
                    a.getCheckIn() != null ? a.getCheckIn() : "--:--",
                    a.getCheckOut() != null ? a.getCheckOut() : "--:--",
                    a.getStatus() != null ? a.getStatus().toUpperCase() : "PRESENT"
            });
        }
    }

    // --- Custom Renderer សម្រាប់ដេញពណ៌ Badge ស្ថានភាព ---
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