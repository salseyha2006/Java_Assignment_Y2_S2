package ui;

import dao.UserDAO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class RegisterFrame extends JFrame {

    // =========================================================================
    // COLOR THEME
    // =========================================================================
    private final Color PRIMARY_COLOR = new Color(44, 62, 80);
    private final Color ACCENT_COLOR = new Color(52, 152, 219);
    private final Color BG_COLOR = new Color(245, 245, 245);
    private final Color ERROR_COLOR = new Color(231, 76, 60);

    // =========================================================================
    // COMPONENTS
    // =========================================================================
    private JTextField txtFullName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnRegister;
    private JLabel lblStatus;

    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================
    public RegisterFrame() {
        setTitle("Create Account - Attendance Management System");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        mainPanel.add(buildHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(buildFormPanel(), BorderLayout.CENTER);
        mainPanel.add(buildBottomPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    // =========================================================================
    // ១. HEADER PANEL
    // =========================================================================
    private JPanel buildHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        headerPanel.setBackground(BG_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JLabel lblTitle = new JLabel("CREATE ACCOUNT");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblSub = new JLabel("Register a new account to get started");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(lblTitle);
        headerPanel.add(lblSub);

        return headerPanel;
    }

    // =========================================================================
    // ២. FORM PANEL - កែសម្រួលថ្មី៖ ខ្លី ងាយយល់ និងលុបចោល GridBagLayout ញ៉េរញ៉ៃ
    // =========================================================================
    private JPanel buildFormPanel() {
        // ប្ដូរមកប្រើ GridLayout (៩ ជួរដេក ធ្លាក់ចុះមកក្រោមត្រង់ៗ)
        JPanel formPanel = new JPanel(new GridLayout(9, 1, 0, 6));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);

        // --- Full Name ---
        JLabel lblFullName = new JLabel("Full Name");
        lblFullName.setFont(labelFont);
        lblFullName.setForeground(PRIMARY_COLOR);
        txtFullName = new JTextField();
        styleInputField(txtFullName);

        // --- Username ---
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(labelFont);
        lblUsername.setForeground(PRIMARY_COLOR);
        txtUsername = new JTextField();
        styleInputField(txtUsername);

        // --- Password ---
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(labelFont);
        lblPassword.setForeground(PRIMARY_COLOR);
        txtPassword = new JPasswordField();
        styleInputField(txtPassword);

        // --- Confirm Password ---
        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setFont(labelFont);
        lblConfirmPassword.setForeground(PRIMARY_COLOR);
        txtConfirmPassword = new JPasswordField();
        styleInputField(txtConfirmPassword);

        // --- Status Label (បង្ហាញកំហុស) ---
        lblStatus = new JLabel(" ");
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblStatus.setForeground(ERROR_COLOR);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        // ញាត់ចូលទៅក្នុង Panel តាមលំដាប់លំដោយពីលើចុះក្រោម
        formPanel.add(lblFullName);
        formPanel.add(txtFullName);
        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);
        formPanel.add(lblConfirmPassword);
        formPanel.add(txtConfirmPassword);
        formPanel.add(lblStatus);

        // Shortcut key Enter លោតពីប្រអប់មួយទៅប្រអប់មួយទៀត
        txtFullName.addActionListener(e -> txtUsername.requestFocus());
        txtUsername.addActionListener(e -> txtPassword.requestFocus());
        txtPassword.addActionListener(e -> txtConfirmPassword.requestFocus());
        txtConfirmPassword.addActionListener(e -> performRegister());

        return formPanel;
    }

    // =========================================================================
    // ៣. BOTTOM PANEL
    // =========================================================================
    private JPanel buildBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(BG_COLOR);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        bottomPanel.add(buildRegisterButton());
        bottomPanel.add(buildBackToLoginPanel());

        return bottomPanel;
    }

    // --- ប៊ូតុង SIGN UP ---
    private JButton buildRegisterButton() {
        btnRegister = new JButton("SIGN UP");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(PRIMARY_COLOR);
        btnRegister.setFocusable(false);
        btnRegister.setBorderPainted(false);

        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                btnRegister.setBackground(ACCENT_COLOR);
                btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnRegister.setBackground(PRIMARY_COLOR);
            }
        });

        btnRegister.addActionListener(e -> performRegister());

        return btnRegister;
    }

    // --- តំណភ្ជាប់ត្រឡប់ទៅ Login Frame ---
    private JPanel buildBackToLoginPanel() {
        JPanel backToLoginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        backToLoginPanel.setBackground(BG_COLOR);

        JLabel lblHaveAccount = new JLabel("Already have an account?");
        lblHaveAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblHaveAccount.setForeground(Color.GRAY);

        JButton btnGoToLogin = new JButton("Login");
        btnGoToLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGoToLogin.setForeground(ACCENT_COLOR);
        btnGoToLogin.setBackground(BG_COLOR);
        btnGoToLogin.setBorderPainted(false);
        btnGoToLogin.setContentAreaFilled(false);
        btnGoToLogin.setFocusable(false);
        btnGoToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGoToLogin.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        backToLoginPanel.add(lblHaveAccount);
        backToLoginPanel.add(btnGoToLogin);

        return backToLoginPanel;
    }

    // =========================================================================
    // HELPER - ការតុបតែង Input Field
    // =========================================================================
    private void styleInputField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(0, 38));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    // =========================================================================
    // មុខងារចម្បង៖ Validate + បង្កើតគណនីថ្មីទៅក្នុង Database (រក្សាទុកដដែល មិនប៉ះពាល់)
    // =========================================================================
    private void performRegister() {
    lblStatus.setText(" ");

    String fullName = txtFullName.getText().trim();
    String username = txtUsername.getText().trim();
    String password = new String(txtPassword.getPassword()).trim();
    String confirmPassword = new String(txtConfirmPassword.getPassword()).trim();

    if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
        lblStatus.setText("Please fill in all fields!");
        return;
    }

    if (!username.matches("^[a-zA-Z0-9_]+$")) {
        lblStatus.setText("Username may only contain letters, numbers, and underscore (no spaces/symbols)!");
        return;
    }

    if (username.length() < 4) {
        lblStatus.setText("Username must be at least 4 characters!");
        return;
    }

    if (password.length() < 6) {
        lblStatus.setText("Password must be at least 6 characters!");
        return;
    }

    if (!password.equals(confirmPassword)) {
        lblStatus.setText("Passwords do not match!");
        return;
    }

    btnRegister.setEnabled(false);
    btnRegister.setText("Creating account...");

    SwingWorker<Integer, Void> worker = new SwingWorker<>() {

        @Override
        protected Integer doInBackground() {
            UserDAO userDAO = new UserDAO();

            // ជំហានទី 1: ពិនិត្យមើលថា Username មានរួចហើយឬអត់
            if (userDAO.isUsernameExist(username)) {
                return 0; // 0 = Username ស្ទួន
            }

            // ជំហានទី 2: បើមិនស្ទួន -> បង្កើត User ថ្មី
            boolean success = userDAO.register(username, password, fullName);
            return success ? 1 : -1; // 1 = ជោគជ័យ, -1 = បរាជ័យ
        }

        @Override
        protected void done() {
            btnRegister.setEnabled(true);
            btnRegister.setText("SIGN UP");

            try {
                int result = get();
                if (result == 1) {
                    new LoginFrame().setVisible(true);
                    RegisterFrame.this.dispose();
                } else if (result == 0) {
                    lblStatus.setText("Username already exists. Please choose another.");
                } else {
                    lblStatus.setText("Failed to create account!");
                }
            } catch (Exception ex) {
                lblStatus.setText("Unexpected error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    };

    worker.execute();
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegisterFrame().setVisible(true);
        });
    }
}