package ui;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Department;
import model.Employee;

public class EmployeePanel extends JPanel {

    // COLOR THEME - Palette ពណ៌បែប Modern Flat UI ឱ្យស៊ីគ្នាជាមួយ MainFrame

    private final Color PRIMARY_COLOR = new Color(44, 62, 80);    // Dark Navy
    private final Color SUCCESS_COLOR = new Color(46, 204, 113);  // Green
    private final Color WARNING_COLOR = new Color(241, 196, 15);  // Yellow
    private final Color DANGER_COLOR = new Color(231, 76, 60);    // Red
    private final Color INFO_COLOR = new Color(52, 152, 219);     // Blue
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private final Color CARD_BG = Color.WHITE;

    // COMPONENTS - Component ដែលត្រូវប្រើនៅច្រើន method

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtSearch, txtEmployeeCode, txtFirstName, txtLastName, txtPhone, txtEmail, txtPosition;
    private JComboBox<String> cbGender, cbStatus;
    private JComboBox<Department> cbDepartment;

    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    private int selectedEmployeeId = -1;

    // CONSTRUCTOR - ចាប់ផ្តើម Setup Panel ហើយហៅ method រៀងៗខ្លួន

    public EmployeePanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        // ផ្ទុកផ្នែកខាងលើ (Title + Search) និងផ្នែកកណ្តាល (Form + Table)
        add(buildTopPanel(), BorderLayout.NORTH);

        JPanel mainContent = new JPanel(new BorderLayout(20, 0));
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.add(buildFormPanel(), BorderLayout.WEST);
        mainContent.add(buildTablePanel(), BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);

        loadEmployeeData();
        loadDepartmentData();
    }

    // =========================================================================
    // ១. TOP PANEL - Title & Search Box (មាន Auto-Refresh + Enter to Search)
    // =========================================================================
    private JPanel buildTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel lblTitle = new JLabel("Employee Management");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(PRIMARY_COLOR);
        panel.add(lblTitle, BorderLayout.WEST);

        panel.add(buildSearchPanel(), BorderLayout.EAST);
        return panel;
    }

    // --- Search Bar Section ---
    private JPanel buildSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        searchPanel.setBackground(BACKGROUND_COLOR);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSearch.setForeground(PRIMARY_COLOR);
        searchPanel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(240, 35));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));

        // === ចាប់ព្រឹត្តិការណ៍លុបអក្សរអស់ ឱ្យ Auto-Refresh ===
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (txtSearch.getText().trim().isEmpty()) {
                    loadEmployeeData(); // បើលុបអក្សរអស់ ឱ្យបង្ហាញទិន្នន័យទាំងអស់មកវិញភ្លាមៗ
                }
            }
        });

        // --- Enter key: ចុច Enter ក្នុង Search Box ស្មើនឹងចុចប៊ូតុង Search ---
        txtSearch.addActionListener(e -> performSearch());

        searchPanel.add(txtSearch);

        JButton btnSearch = createStyledButton("Search", INFO_COLOR);
        btnSearch.setPreferredSize(new Dimension(90, 35));
        btnSearch.addActionListener(e -> performSearch());
        searchPanel.add(btnSearch);

        return searchPanel;
    }

    // =========================================================================
    // ២. FORM PANEL - Form បញ្ចូលទិន្នន័យ (Left Sidebar Form)
    // =========================================================================
    private JPanel buildFormPanel() {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(CARD_BG);
        cardPanel.setPreferredSize(new Dimension(380, 0));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        cardPanel.add(buildFormGrid(), BorderLayout.CENTER);
        cardPanel.add(buildActionsPanel(), BorderLayout.SOUTH);

        return cardPanel;
    }

    // --- Grid នៃ Field ទាំងអស់ (Emp Code, Name, Gender, Phone, Email...) ---
    private JPanel buildFormGrid() {
        JPanel formGrid = new JPanel(new GridLayout(9, 2, 10, 15));
        formGrid.setBackground(CARD_BG);

        txtEmployeeCode = createStyledTextField();
        txtFirstName = createStyledTextField();
        txtLastName = createStyledTextField();
        txtPhone = createStyledTextField();
        txtEmail = createStyledTextField();
        txtPosition = createStyledTextField();

        cbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        styleComboBox(cbGender);

        cbStatus = new JComboBox<>(new String[]{"Active", "Inactive", "Terminated"});
        styleComboBox(cbStatus);

        cbDepartment = new JComboBox<>();
        styleComboBox(cbDepartment);

        addFormRow(formGrid, "Emp Code:", txtEmployeeCode);
        addFormRow(formGrid, "First Name:", txtFirstName);
        addFormRow(formGrid, "Last Name:", txtLastName);
        addFormRow(formGrid, "Gender:", cbGender);
        addFormRow(formGrid, "Phone:", txtPhone);
        addFormRow(formGrid, "Email:", txtEmail);
        addFormRow(formGrid, "Position:", txtPosition);
        addFormRow(formGrid, "Department:", cbDepartment);
        addFormRow(formGrid, "Status:", cbStatus);

        return formGrid;
    }

    // --- ផ្នែកប៊ូតុងបញ្ជា (Add / Update / Delete / Clear) ---
    private JPanel buildActionsPanel() {
        JPanel actionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        actionsPanel.setBackground(CARD_BG);
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton btnAdd = createStyledButton("Add New", SUCCESS_COLOR);
        JButton btnUpdate = createStyledButton("Update", WARNING_COLOR);
        JButton btnDelete = createStyledButton("Delete", DANGER_COLOR);
        JButton btnClear = createStyledButton("Clear Form", PRIMARY_COLOR);

        actionsPanel.add(btnAdd);
        actionsPanel.add(btnUpdate);
        actionsPanel.add(btnDelete);
        actionsPanel.add(btnClear);

        btnAdd.addActionListener(e -> addEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnClear.addActionListener(e -> clearForm());

        return actionsPanel;
    }

    // =========================================================================
    // ៣. TABLE PANEL - តារាងបង្ហាញបញ្ជីបុគ្គលិក (Premium JTable)
    // =========================================================================
    private JPanel buildTablePanel() {
        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(CARD_BG);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        table = buildEmployeeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    // --- បង្កើត Table + Style + Renderer ទាំងអស់ ---
    private JTable buildEmployeeTable() {
        String[] columns = {"ID", "Code", "First Name", "Last Name", "Gender", "Phone", "Position", "Department", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Custom Zebra Table ពណ៌ឆ្លាស់គ្នា
        JTable tbl = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 251, 252));
                }
                return c;
            }
        };

        tbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tbl.setRowHeight(40);
        tbl.setShowVerticalLines(false);
        tbl.setGridColor(new Color(240, 243, 246));
        tbl.setSelectionBackground(new Color(236, 240, 241));
        tbl.setSelectionForeground(PRIMARY_COLOR);
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // ពង្រីកសមាមាត្រស្វ័យប្រវត្តិជាមួយ MainFrame

        // រចនា TableHeader
        JTableHeader header = tbl.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(236, 240, 241));
        header.setForeground(PRIMARY_COLOR);
        header.setPreferredSize(new Dimension(header.getWidth(), 42));
        header.setReorderingAllowed(false);

        styleTableColumns(tbl);

        tbl.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow >= 0) {
                populateFormFromSelectedRow(selectedRow);
            }
        });

        return tbl;
    }

    // --- កំណត់ Renderer + ទទឹង Column នីមួយៗ ---
    private void styleTableColumns(JTable tbl) {
        // តម្រឹមអក្សរ និងដាក់ Padding ឆ្វេងស្តាំ
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer leftPaddingRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.LEFT);
                setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0)); // គម្លាតពីបន្ទាត់ឆ្វេង 12px
                return this;
            }
        };

        // ដាក់ Renderer តាម Column
        tbl.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(2).setCellRenderer(leftPaddingRenderer);
        tbl.getColumnModel().getColumn(3).setCellRenderer(leftPaddingRenderer);
        tbl.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(6).setCellRenderer(leftPaddingRenderer);
        tbl.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(110);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(110);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(110);
    }

    // =========================================================================
    // HELPERS - ជំនួយការតុបតែង UI Components
    // =========================================================================
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 225), 1, true),
                BorderFactory.createEmptyBorder(0, 8, 0, 8)
        ));
        return textField;
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
    }

    private void addFormRow(JPanel panel, String labelText, Component comp) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(PRIMARY_COLOR);
        panel.add(label);
        panel.add(comp);
    }

    private JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(baseColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor);
            }
        });
        return button;
    }

    // =========================================================================
    // CORE METHODS - Logic ចម្បងសម្រាប់ដោះស្រាយទិន្នន័យ
    // =========================================================================
    private void loadEmployeeData() {
        tableModel.setRowCount(0);
        List<Employee> list = employeeDAO.getAllEmployees();
        for (Employee e : list) {
            addEmployeeToTable(e);
        }
    }

    private void addEmployeeToTable(Employee e) {
        Object[] row = {
            e.getEmployeeId(),
            e.getEmployeeCode(),
            e.getFirstName(),
            e.getLastName(),
            e.getGender(),
            e.getPhone(),
            e.getPosition(),
            e.getDepartmentName(),
            e.getStatus()
        };
        tableModel.addRow(row);
    }

    private void loadDepartmentData() {
        cbDepartment.removeAllItems();
        List<Department> list = departmentDAO.getAllDepartments();
        for (Department d : list) {
            cbDepartment.addItem(d);
        }
    }

    // === Search: បើប្រអប់ទំនេរ ឱ្យ Refresh មកវិញទាំងអស់ ===
    private void performSearch() {
        String keyword = txtSearch.getText().trim();

        if (keyword.isEmpty()) {
            loadEmployeeData(); // បើចុច Search ទាំងអក្សរទទេ ឱ្យវា Refresh មកវិញទាំងអស់
            return;
        }

        tableModel.setRowCount(0);
        List<Employee> list = employeeDAO.searchEmployees(keyword);
        for (Employee e : list) {
            addEmployeeToTable(e);
        }
    }

    private void populateFormFromSelectedRow(int row) {
        selectedEmployeeId = (int) table.getValueAt(row, 0);
        txtEmployeeCode.setText((String) table.getValueAt(row, 1));
        txtFirstName.setText((String) table.getValueAt(row, 2));
        txtLastName.setText((String) table.getValueAt(row, 3));
        cbGender.setSelectedItem(table.getValueAt(row, 4));
        txtPhone.setText((String) table.getValueAt(row, 5));
        txtPosition.setText((String) table.getValueAt(row, 6));

        String deptName = (String) table.getValueAt(row, 7);
        for (int i = 0; i < cbDepartment.getItemCount(); i++) {
            if (cbDepartment.getItemAt(i).getDepartmentName().equals(deptName)) {
                cbDepartment.setSelectedIndex(i);
                break;
            }
        }
        cbStatus.setSelectedItem(table.getValueAt(row, 8));
    }

    private void addEmployee() {
        if (!validateForm()) return;
        Employee emp = buildEmployeeFromForm();
        if (employeeDAO.addEmployee(emp)) {
            JOptionPane.showMessageDialog(this, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEmployeeData();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add employee.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployee() {
        if (selectedEmployeeId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validateForm()) return;
        Employee emp = buildEmployeeFromForm();
        emp.setEmployeeId(selectedEmployeeId);
        if (employeeDAO.updateEmployee(emp)) {
            JOptionPane.showMessageDialog(this, "Employee updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEmployeeData();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update employee.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployee() {
        if (selectedEmployeeId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            if (employeeDAO.deleteEmployee(selectedEmployeeId)) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadEmployeeData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // =========================================================================
    // VALIDATION - ត្រួតពិនិត្យទិន្នន័យមុនពេល Add/Update
    // =========================================================================
    private boolean validateForm() {
        if (txtEmployeeCode.getText().trim().isEmpty() ||
            txtFirstName.getText().trim().isEmpty() ||
            txtLastName.getText().trim().isEmpty() ||
            txtPhone.getText().trim().isEmpty() ||
            txtPosition.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // === ត្រួតពិនិត្យទម្រង់ Email (បើមានវាយបញ្ចូល) ===
        // Email មិនតម្រូវអោយបំពេញទេ ប៉ុន្តែបើវាយបញ្ចូល ត្រូវមានទម្រង់ត្រឹមត្រូវ (មាន @ និង . បន្ទាប់ពី @)
        String email = txtEmail.getText().trim();
        if (!email.isEmpty() && !email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address (e.g. name@example.com).", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private Employee buildEmployeeFromForm() {
        Employee emp = new Employee();
        emp.setEmployeeCode(txtEmployeeCode.getText().trim());
        emp.setFirstName(txtFirstName.getText().trim());
        emp.setLastName(txtLastName.getText().trim());
        emp.setGender((String) cbGender.getSelectedItem());
        emp.setPhone(txtPhone.getText().trim());
        emp.setEmail(txtEmail.getText().trim());
        emp.setPosition(txtPosition.getText().trim());
        emp.setHireDate(LocalDate.now());
        emp.setStatus((String) cbStatus.getSelectedItem());

        Department selectedDept = (Department) cbDepartment.getSelectedItem();
        if (selectedDept != null) {
            emp.setDepartmentId(selectedDept.getDepartmentId());
        }
        return emp;
    }

    // === Clear Form: Reset Field ទាំងអស់ ហើយ Refresh Table ===
    private void clearForm() {
        selectedEmployeeId = -1;
        txtSearch.setText("");
        txtEmployeeCode.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtPosition.setText("");
        cbGender.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        if (cbDepartment.getItemCount() > 0) cbDepartment.setSelectedIndex(0);
        table.clearSelection();

        loadEmployeeData(); // បង្ហាញបញ្ជីឈ្មោះពេញលេញឡើងវិញ
    }
}