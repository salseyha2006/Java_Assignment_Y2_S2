package ui;

import dao.AttendanceDAO;
import dao.EmployeeDAO;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Attendance;

public class DashboardPanel extends JPanel {


    // COLOR THEME - ពណ៌ដែលប្រើក្នុង Panel ទាំងអស់

    private final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private final Color PRIMARY_COLOR = new Color(30, 41, 59);
    private final Color CARD_BG = Color.WHITE;
    private final Color TEXT_MUTED = new Color(100, 116, 139);

    // COMPONENTS - Component ដែលត្រូវប្រើនៅច្រើន method

    private JLabel lblTotalEmployees;
    private JLabel lblPresentToday;
    private JLabel lblLateToday;
    private JLabel lblAbsentToday;

    private JTable attendanceTable;
    private DefaultTableModel tableModel;

    // CONSTRUCTOR - ចាប់ផ្តើម Setup Panel ហើយហៅ method រៀងៗខ្លួន

    public DashboardPanel() {
        setLayout(new BorderLayout(0, 25));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        add(buildHeaderPanel(), BorderLayout.NORTH);
        add(buildMainContentPanel(), BorderLayout.CENTER);

        loadDashboardData();
    }

    // ១. HEADER PANEL - ចំណងជើង + កាលបរិច្ឆេទថ្ងៃនេះ

    private JPanel buildHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);

        JLabel title = new JLabel("Dashboard Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(PRIMARY_COLOR);
        headerPanel.add(title, BorderLayout.WEST);

        JLabel lblDate = new JLabel(LocalDate.now().toString());
        lblDate.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDate.setForeground(TEXT_MUTED);
        headerPanel.add(lblDate, BorderLayout.EAST);

        return headerPanel;
    }

    // ២. MAIN CONTENT PANEL - Stats Cards (លើ) + Attendance Table (ក្រោម)

    private JPanel buildMainContentPanel() {
        JPanel mainContentPanel = new JPanel(new BorderLayout(0, 25));
        mainContentPanel.setBackground(BACKGROUND_COLOR);

        mainContentPanel.add(buildStatsCardsPanel(), BorderLayout.NORTH);
        mainContentPanel.add(buildTableCardPanel(), BorderLayout.CENTER);

        return mainContentPanel;
    }

    // ២.១ Stats Cards (Total Employees, Present, Late, Absent)

    private JPanel buildStatsCardsPanel() {
        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        cardsPanel.setBackground(BACKGROUND_COLOR);

        lblTotalEmployees = new JLabel("0");
        lblPresentToday = new JLabel("0");
        lblLateToday = new JLabel("0");
        lblAbsentToday = new JLabel("0");

        cardsPanel.add(createStatCard("Total Employees", lblTotalEmployees, new Color(14, 165, 233)));
        cardsPanel.add(createStatCard("Present Today", lblPresentToday, new Color(34, 197, 94)));
        cardsPanel.add(createStatCard("Late Today", lblLateToday, new Color(234, 179, 8)));
        cardsPanel.add(createStatCard("Absent Today", lblAbsentToday, new Color(239, 68, 68)));

        return cardsPanel;
    }

    // Helper បង្កើត Stat Card មួយសន្លឹក (ប្រើម្តងៗសម្រាប់ Card ទាំង ៤)

    private JPanel createStatCard(String label, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 6, 0, 0, accentColor),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                        new EmptyBorder(18, 20, 18, 20)
                )
        ));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(CARD_BG);

        JLabel captionLabel = new JLabel(label);
        captionLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        captionLabel.setForeground(TEXT_MUTED);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        valueLabel.setForeground(PRIMARY_COLOR);

        textPanel.add(captionLabel);
        textPanel.add(Box.createVerticalStrut(6));
        textPanel.add(valueLabel);

        card.add(textPanel, BorderLayout.CENTER);
        return card;
    }

    // ២.២ Table Card (ចំណងជើង + Table បង្ហាញ Attendance ថ្ងៃនេះ) 

    private JPanel buildTableCardPanel() {
        JPanel tableCardPanel = new JPanel(new BorderLayout(0, 15));
        tableCardPanel.setBackground(CARD_BG);
        tableCardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                new EmptyBorder(22, 22, 22, 22)
        ));

        JLabel tableTitle = new JLabel("Today's Attendance Stream");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableTitle.setForeground(PRIMARY_COLOR);
        tableCardPanel.add(tableTitle, BorderLayout.NORTH);

        attendanceTable = buildAttendanceTable();

        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        tableCardPanel.add(scrollPane, BorderLayout.CENTER);

        return tableCardPanel;
    }

    // បង្កើត Table + Style + Column Renderer ទាំងអស់

    private JTable buildAttendanceTable() {
        // កំណត់ឈ្មោះក្បាល Column ឱ្យសមរម្យ
        String[] columns = {"Roll ID", "Employee Name", "Date", "Check In", "Check Out", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Custom Zebra Stripes Table
        JTable table = new JTable(tableModel) {
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
        table.setRowHeight(44); // បង្កើនកម្ពស់ជួរដេកឱ្យសមស្របនឹងទំហំ Frame ធំ
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(241, 245, 249));
        table.setSelectionBackground(new Color(241, 245, 249));
        table.setSelectionForeground(PRIMARY_COLOR);

        // កំណត់ឱ្យ Column ទាំងអស់ពង្រីកខ្លួនសមាមាត្រគ្នាពេលពង្រីក Frame មេ
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // រចនា TableHeader (ក្បាលតារាង) ឱ្យធំទូលាយ និងមានពណ៌ Flat ទំនើប
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(241, 245, 249));
        header.setForeground(PRIMARY_COLOR);
        header.setPreferredSize(new Dimension(header.getWidth(), 44));
        header.setReorderingAllowed(false);

        styleTableColumns(table);

        return table;
    }

    // កំណត់ Renderer និងទទឹង Column នីមួយៗ
    private void styleTableColumns(JTable table) {
        // 1. Renderer សម្រាប់តម្រឹមអក្សរចំកណ្តាល (Roll ID, Date, In, Out)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // 2. Renderer ពិសេសសម្រាប់ Employee Name (តម្រឹមខាងឆ្វេង និងមានគម្លាតគែម)
        DefaultTableCellRenderer nameRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.LEFT);
                setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // ហែកគម្លាតអក្សរពីបន្ទាត់ខាងឆ្វេង 15px
                return this;
            }
        };

        // ដាក់ការកំណត់ទៅតាម Column នីមួយៗ
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Roll ID
        table.getColumnModel().getColumn(1).setCellRenderer(nameRenderer);   // Employee Name
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Date
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Check In
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Check Out
        table.getColumnModel().getColumn(5).setCellRenderer(new StatusBadgeRenderer()); // Status Badge

        // កំណត់ទំហំទទឹងចន្លោះសមាមាត្ររវាង Column (ទំហំសរុបនឹងរត់បត់បែនតាម MainFrame)
        table.getColumnModel().getColumn(0).setPreferredWidth(90);   // Roll ID
        table.getColumnModel().getColumn(1).setPreferredWidth(260);  // Employee Name (ធំជាងគេ)
        table.getColumnModel().getColumn(2).setPreferredWidth(120);  // Date
        table.getColumnModel().getColumn(3).setPreferredWidth(110);  // Check In
        table.getColumnModel().getColumn(4).setPreferredWidth(110);  // Check Out
        table.getColumnModel().getColumn(5).setPreferredWidth(140);  // Status Badge
    }

    // មុខងារចម្បង៖ ទាញទិន្នន័យពី Database មកបង្ហាញលើ Card និង Table

    private void loadDashboardData() {
        EmployeeDAO empDAO = new EmployeeDAO();
        AttendanceDAO attDAO = new AttendanceDAO();

        int totalEmployees = empDAO.getAllEmployees().size();
        lblTotalEmployees.setText(String.valueOf(totalEmployees));

        List<Attendance> todayList = attDAO.getAttendanceByDate(LocalDate.now());
        int present = 0, late = 0, absent = 0;

        tableModel.setRowCount(0);

        for (Attendance a : todayList) {
            if ("Present".equalsIgnoreCase(a.getStatus())) present++;
            else if ("Late".equalsIgnoreCase(a.getStatus())) late++;
            else if ("Absent".equalsIgnoreCase(a.getStatus())) absent++;

            Object[] rowData = {
                a.getAttendanceId(),
                a.getEmployeeName(),
                a.getAttendanceDate(),
                a.getCheckIn() != null ? a.getCheckIn() : "--:--",
                a.getCheckOut() != null ? a.getCheckOut() : "--:--",
                a.getStatus() != null ? a.getStatus().toUpperCase() : "UNKNOWN"
            };
            tableModel.addRow(rowData);
        }

        lblPresentToday.setText(String.valueOf(present));
        lblLateToday.setText(String.valueOf(late));
        lblAbsentToday.setText(String.valueOf(absent));
    }

    // =========================================================================
    // PUBLIC METHOD - ត្រូវហៅ Method នេះពី MainFrame រាល់ពេល User ចុចមក Dashboard
    // ដើម្បីធ្វើបច្ចុប្បន្នភាពលេខ Stat Card និង Table ជាថ្មី
    // =========================================================================
    public void refreshDashboard() {
        loadDashboardData();
    }

    // =========================================================================
    // Status Badge Renderer - តុបតែង Column "Status" ជា Badge ពណ៌តាមស្ថានភាព
    // =========================================================================
    private static class StatusBadgeRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setOpaque(true);
            label.setFont(new Font("Segoe UI", Font.BOLD, 12));

            // កំណត់ Border ឱ្យដូចប្រអប់ Badge បន្តិច
            label.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));

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