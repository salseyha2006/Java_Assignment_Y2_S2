package ui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    // =========================================================================
    // COLOR THEME - ពណ៌ដែលប្រើក្នុង Frame ទាំងអស់
    // =========================================================================
    private final Color PRIMARY_COLOR = new Color(44, 62, 80);
    private final Color ACTIVE_COLOR = new Color(52, 152, 219); // ពណ៌សម្រាប់ប៊ូតុងកំពុងជ្រើសរើស
    private final Color BG_COLOR = new Color(245, 245, 245);

    // =========================================================================
    // COMPONENTS - Component ដែលត្រូវប្រើនៅច្រើន method
    // =========================================================================
    private CardLayout cardLayout;
    private JPanel centerPanel;
    private JButton[] menuButtons; // ប្រើសម្រាប់ Highlight ប៊ូតុងកំពុងសកម្ម
    private DashboardPanel dashboardScreen; // ត្រូវទុកជា Field ដើម្បីអោយ Refresh បាន

    // =========================================================================
    // CONSTRUCTOR - ចាប់ផ្តើម Setup Frame ហើយហៅ method រៀងៗខ្លួន
    // =========================================================================
    public MainFrame() {
        setTitle("Attendance Management System");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(3, 3));

        add(buildHeaderPanel(), BorderLayout.NORTH);
        add(buildSidebarPanel(), BorderLayout.WEST);
        add(buildCenterPanel(), BorderLayout.CENTER);
    }

    // =========================================================================
    // ១. HEADER PANEL - របារខាងលើបង្ហាញឈ្មោះប្រព័ន្ធ
    // =========================================================================
    private JPanel buildHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 65));
        header.setBackground(PRIMARY_COLOR);

        JLabel titleLabel = new JLabel("ATTENDANCE MANAGEMENT SYSTEM");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(BG_COLOR);
        header.add(titleLabel, BorderLayout.CENTER);

        return header;
    }

    // =========================================================================
    // ២. SIDEBAR PANEL - Menu ខាងឆ្វេង (Title + Menu Buttons + Logout)
    // =========================================================================
    private JPanel buildSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBackground(Color.LIGHT_GRAY);

        sidebar.add(buildSidebarTitle(), BorderLayout.NORTH);
        sidebar.add(buildMenuButtonsPanel(), BorderLayout.CENTER);
        sidebar.add(buildLogoutPanel(), BorderLayout.SOUTH);

        return sidebar;
    }

    // --- ចំណងជើងផ្នែកខាងលើ Sidebar ---
    private JLabel buildSidebarTitle() {
        JLabel sidebarTitle = new JLabel("MAIN MENU");
        sidebarTitle.setHorizontalAlignment(SwingConstants.CENTER);
        sidebarTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarTitle.setFont(new Font("Arial", Font.BOLD, 18));
        sidebarTitle.setForeground(PRIMARY_COLOR);
        sidebarTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        return sidebarTitle;
    }

    // --- ប៊ូតុង Menu កណ្តាល (Dashboard, Employee, Attendance, Reports) ---
    private JPanel buildMenuButtonsPanel() {
        JPanel menuButtonsPanel = new JPanel();
        menuButtonsPanel.setLayout(new BoxLayout(menuButtonsPanel, BoxLayout.Y_AXIS));
        menuButtonsPanel.setBackground(Color.LIGHT_GRAY);
        menuButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        JButton dashboardButton = new JButton("Dashboard");
        JButton employeeButton = new JButton("Employee");
        JButton attendanceButton = new JButton("Attendance");
        JButton reportsButton = new JButton("Reports");

        menuButtons = new JButton[]{dashboardButton, employeeButton, attendanceButton, reportsButton};
        Dimension midBtnSize = new Dimension(170, 40);

        for (JButton button : menuButtons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(midBtnSize);
            button.setFocusable(false);
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(PRIMARY_COLOR);
            button.setBorderPainted(false);
            button.setContentAreaFilled(true);
            menuButtonsPanel.add(button);
            menuButtonsPanel.add(Box.createVerticalStrut(10));
        }

        // --- ភ្ជាប់ Action ជាមួយ Card Layout ដើម្បីប្តូរ Panel ---
        // ហើយ Highlight ប៊ូតុងណាមួយកំពុងជ្រើសរើស (Active State)
        dashboardButton.addActionListener(e -> {
            dashboardScreen.refreshDashboard(); // ធ្វើបច្ចុប្បន្នភាពទិន្នន័យរាល់ពេលចុច
            cardLayout.show(centerPanel, "DASHBOARD");
            setActiveButton(dashboardButton);
        });
        employeeButton.addActionListener(e -> {
            cardLayout.show(centerPanel, "EMPLOYEE");
            setActiveButton(employeeButton);
        });
        attendanceButton.addActionListener(e -> {
            cardLayout.show(centerPanel, "ATTENDANCE");
            setActiveButton(attendanceButton);
        });
        reportsButton.addActionListener(e -> {
            cardLayout.show(centerPanel, "REPORT");
            setActiveButton(reportsButton);
        });

        return menuButtonsPanel;
    }

    // --- ផ្នែកខាងក្រោម Sidebar (ប៊ូតុង Logout) ---
    private JPanel buildLogoutPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(170, 40));
        logoutButton.setFocusable(false);
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(PRIMARY_COLOR);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(true);
        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        bottomPanel.add(logoutButton);

        return bottomPanel;
    }

    // =========================================================================
    // ៣. CENTER PANEL - កន្លែងបង្ហាញ Screen នីមួយៗ (ប្តូរដោយ CardLayout)
    // =========================================================================
    private JPanel buildCenterPanel() {
        centerPanel = new JPanel();
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.setBackground(BG_COLOR);

        // --- បង្កើត Instance នៃ Panel មាតិកានីមួយៗ ---
        dashboardScreen = new DashboardPanel();
        EmployeePanel employeeScreen = new EmployeePanel();
        AttendancePanel attendanceScreen = new AttendancePanel();
        ReportPanel reportScreen = new ReportPanel();

        // --- បន្ថែម Panel ចូល centerPanel ដោយកំណត់ឈ្មោះសម្គាល់ជាក់លាក់ ---
        centerPanel.add(dashboardScreen, "DASHBOARD");
        centerPanel.add(employeeScreen, "EMPLOYEE");
        centerPanel.add(attendanceScreen, "ATTENDANCE");
        centerPanel.add(reportScreen, "REPORT");

        // --- បង្ហាញ Dashboard ជា Default នៅពេលកម្មវិធីចាប់ផ្តើម ---
        cardLayout.show(centerPanel, "DASHBOARD");

        return centerPanel;
    }

    // =========================================================================
    // HELPER - Highlight ប៊ូតុងកំពុងជ្រើសរើស ហើយប្តូរប៊ូតុងផ្សេងទៀតត្រឡប់ពណ៌ដើម
    // =========================================================================
    private void setActiveButton(JButton activeButton) {
        for (JButton button : menuButtons) {
            if (button == activeButton) {
                button.setBackground(ACTIVE_COLOR);
            } else {
                button.setBackground(PRIMARY_COLOR);
            }
        }
    }
}