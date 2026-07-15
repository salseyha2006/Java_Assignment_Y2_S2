package ui;

import dao.UserDAO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class LoginFrame extends JFrame {

    // COLOR
    private final Color PRIMARY_COLOR = new Color(44, 62, 80);
    private final Color ACCENT_COLOR = new Color(52, 152, 219);
    private final Color BG_COLOR = new Color(245, 245, 245);
    private final Color ERROR_COLOR = new Color(231, 76, 60);

    // COMPONENTS
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblStatus;

    // CONSTRUCTOR
    public LoginFrame() {
        setTitle("System Login - Attendance Management System");
        setSize(450, 610);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        mainPanel.add(buildHeaderPanel(), BorderLayout.NORTH); // Header Panel
        mainPanel.add(buildFormPanel(), BorderLayout.CENTER); // Form Panel
        mainPanel.add(buildBottomPanel(), BorderLayout.SOUTH); // Bottom Panel

        add(mainPanel);
    }

    // 1. HEADER PANEL
    private JPanel buildHeaderPanel() {

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        headerPanel.setBackground(BG_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JLabel lblTitle = new JLabel("WELCOME BACK");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblSub = new JLabel("Sign in to continue to system");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(lblTitle); // Add title label to header panel
        headerPanel.add(lblSub); // Add subtitle label to header panel

        return headerPanel;
    }

    // 2. FORM PANEL
    private JPanel buildFormPanel() {

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 0, 8));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);

        // Username
        JLabel lblUsername = new JLabel("Username / Email");
        lblUsername.setFont(labelFont);
        lblUsername.setForeground(PRIMARY_COLOR);

        txtUsername = new JTextField();
        styleInputField(txtUsername);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(labelFont);
        lblPassword.setForeground(PRIMARY_COLOR);

        txtPassword = new JPasswordField();
        styleInputField(txtPassword);

        // Status Label (បង្ហាញកំហុស)
        lblStatus = new JLabel(" ");
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblStatus.setForeground(ERROR_COLOR);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        // Add to form panel
        formPanel.add(lblUsername);  
        formPanel.add(txtUsername); 
        formPanel.add(lblPassword); 
        formPanel.add(txtPassword); 
        formPanel.add(lblStatus); 

        // Enter key shortcut
        txtUsername.addActionListener(e -> txtPassword.requestFocus());
        txtPassword.addActionListener(e -> performLogin());

        return formPanel;
    }

    // 3. BOTTOM PANEL - LOGIN + Link to Sign Up
    private JPanel buildBottomPanel() {

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(BG_COLOR);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        bottomPanel.add(buildLoginButton()); // add login button to bottom panel
        bottomPanel.add(buildSignUpPanel()); // add sign up link panel to bottom panel

        return bottomPanel;
    }

    // Button LOGIN
    private JButton buildLoginButton() {

        btnLogin = new JButton("LOGIN");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(PRIMARY_COLOR);
        btnLogin.setFocusable(false);
        btnLogin.setBorderPainted(false);

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                btnLogin.setBackground(ACCENT_COLOR);
                btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnLogin.setBackground(PRIMARY_COLOR);
            }
        });

        btnLogin.addActionListener(e -> performLogin());

        return btnLogin;
    }

    // Link To Sign Up 
    private JPanel buildSignUpPanel() {

        JPanel signUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        signUpPanel.setBackground(BG_COLOR);

        JLabel lblNoAccount = new JLabel("Don't have an account?");
        lblNoAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblNoAccount.setForeground(Color.GRAY);

        JButton btnGoToSignUp = new JButton("Sign Up");
        btnGoToSignUp.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGoToSignUp.setForeground(ACCENT_COLOR);
        btnGoToSignUp.setBackground(BG_COLOR);
        btnGoToSignUp.setBorderPainted(false);
        btnGoToSignUp.setContentAreaFilled(false);
        btnGoToSignUp.setFocusable(false);
        btnGoToSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGoToSignUp.addActionListener(e -> {
            new RegisterFrame().setVisible(true);
            this.dispose();
        });

        signUpPanel.add(lblNoAccount);
        signUpPanel.add(btnGoToSignUp);

        return signUpPanel;
    }

    // HELPER - ការតុបតែង Input Field ឲ្យដូចគ្នា
    private void styleInputField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(0, 38));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }


    // LOGIN FUNCTION 
   
   private void performLogin() {
    lblStatus.setText(" ");

    String username = txtUsername.getText().trim();
    String password = new String(txtPassword.getPassword()).trim();

    // Validate input fields
    if (username.isEmpty() || password.isEmpty()) {
        lblStatus.setText("Please enter both Username and Password!");
        return;
    }

    btnLogin.setEnabled(false);
    btnLogin.setText("Signing in...");

    SwingWorker<Boolean, Void> worker = new SwingWorker<>() {

        @Override
        protected Boolean doInBackground() {
            UserDAO userDAO = new UserDAO();
            return userDAO.login(username, password); // ហៅ UserDAO ជំនួសសរសេរ SQL ដោយផ្ទាល់
        }

        @Override
        protected void done() {
            btnLogin.setEnabled(true);
            btnLogin.setText("LOGIN");

            try {
                boolean success = get();
                if (success) {
                    new MainFrame().setVisible(true);
                    LoginFrame.this.dispose();
                } else {
                    lblStatus.setText("Invalid Username or Password!");
                    txtPassword.setText("");
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
            new LoginFrame().setVisible(true);
        });
    }
}