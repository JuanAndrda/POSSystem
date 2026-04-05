/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.possystem.ui.theme;

/**
 *
 * @author juanr
 */
public class UserManagementFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserManagementFrame.class.getName());
    private java.util.List<com.mycompany.possystem.model.User> allUsers =
    new java.util.ArrayList<>();
    /**
     * Creates new form UserManagementFrame
     */
    public UserManagementFrame() {
    initComponents();

    // ── Window settings ──────────────────────────────────────────────
    setTitle("RetailPro POS — User Management");
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // ── Backgrounds ──────────────────────────────────────────────────
    getContentPane().setBackground(AppTheme.BG_DARK);
    topBar.setBackground(AppTheme.BG_PANEL);
    topBar.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(10, 14, 10, 14));

    // ── Fix layout ───────────────────────────────────────────────────
    getContentPane().setLayout(new java.awt.BorderLayout(0, 0));
    topBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
    topBar.setBorder(javax.swing.BorderFactory.createMatteBorder(
        0, 0, 2, 0, AppTheme.BORDER));
    topBar.setPreferredSize(new java.awt.Dimension(0, 64));

    javax.swing.JLabel lblTitle = new javax.swing.JLabel("User Management");
    lblTitle.setFont(AppTheme.FONT_HEADING);
    lblTitle.setForeground(AppTheme.TEXT_PRI);
    lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 20));

    topBar.removeAll();
    topBar.add(lblTitle);
    topBar.add(btnDashboard);
    topBar.add(btnAddUser);
    topBar.add(btnToggleActive);
    topBar.add(btnResetPassword);
    getContentPane().add(topBar,       java.awt.BorderLayout.NORTH);
    javax.swing.JPanel tableContainer = new javax.swing.JPanel(
    new java.awt.BorderLayout());
    tableContainer.setBackground(AppTheme.BG_DARK);
    tableContainer.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(16, 20, 20, 20));
    tableContainer.add(scrollUsers, java.awt.BorderLayout.CENTER);
    getContentPane().add(tableContainer, java.awt.BorderLayout.CENTER);
    scrollUsers.setBorder(javax.swing.BorderFactory.createEmptyBorder());

    // ── Toolbar buttons ──────────────────────────────────────────────
    AppTheme.stylePrimaryButton(btnDashboard,     "Back",           AppTheme.BG_HOVER);
    AppTheme.stylePrimaryButton(btnAddUser,       "+ Add User",     AppTheme.SUCCESS);
    AppTheme.stylePrimaryButton(btnToggleActive,  "Toggle Active",  AppTheme.WARNING);
    AppTheme.stylePrimaryButton(btnResetPassword, "Reset Password", AppTheme.ACCENT_BTN);
    // ── Users table ──────────────────────────────────────────────────
    tblUsers.setModel(new javax.swing.table.DefaultTableModel(
        new String[]{"ID", "Username", "Full Name", "Role", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    });
    AppTheme.styleTable(tblUsers);
    tblUsers.setRowHeight(42);
    tblUsers.getColumnModel().getColumn(0).setMinWidth(0);
    tblUsers.getColumnModel().getColumn(0).setMaxWidth(0);
    tblUsers.getColumnModel().getColumn(0).setWidth(0);
    tblUsers.getColumnModel().getColumn(1).setPreferredWidth(150);
    tblUsers.getColumnModel().getColumn(2).setPreferredWidth(200);
    tblUsers.getColumnModel().getColumn(3).setPreferredWidth(100);
    tblUsers.getColumnModel().getColumn(4).setPreferredWidth(100);
    scrollUsers.getViewport().setBackground(AppTheme.BG_CARD);

    // Color code active/inactive rows
    tblUsers.setDefaultRenderer(Object.class,
        new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable t, Object val, boolean sel,
                    boolean foc, int row, int col) {
                super.getTableCellRendererComponent(
                    t, val, sel, foc, row, col);
                if (!sel) {
                    String status = t.getValueAt(row, 4).toString();
                    if ("Inactive".equals(status)) {
                        setBackground(new java.awt.Color(0x2A0A0A));
                        setForeground(AppTheme.DANGER);
                    } else {
                        setBackground(row % 2 == 0
                            ? AppTheme.BG_CARD : AppTheme.BG_PANEL);
                        setForeground(AppTheme.TEXT_PRI);
                    }
                }
                setBorder(javax.swing.BorderFactory
                    .createEmptyBorder(0, 8, 0, 8));
                return this;
            }
        });

    // ── Button actions ───────────────────────────────────────────────
    btnDashboard.addActionListener(e -> {
        new DashboardFrame().setVisible(true);
        this.dispose();
    });

    btnAddUser.addActionListener(e -> showAddUserDialog());

    btnToggleActive.addActionListener(e -> toggleActiveStatus());

    btnResetPassword.addActionListener(e -> resetPassword());

    // ── Load data ────────────────────────────────────────────────────
    loadUsers();
}

// ── Load all users into table ─────────────────────────────────────────
private void loadUsers() {
    javax.swing.table.DefaultTableModel model =
        (javax.swing.table.DefaultTableModel) tblUsers.getModel();
    model.setRowCount(0);
    allUsers = new com.mycompany.possystem.dao.UserDAO().getAllUsers();
    for (com.mycompany.possystem.model.User u : allUsers) {
        model.addRow(new Object[]{
            u.getId(),
            u.getUsername(),
            u.getFullName(),
            u.getRole().toUpperCase(),
            u.isActive() ? "Active" : "Inactive"
        });
    }
}

// ── Show Add User dialog ──────────────────────────────────────────────
private void showAddUserDialog() {
    javax.swing.JTextField fUsername = new javax.swing.JTextField();
    javax.swing.JTextField fFullName = new javax.swing.JTextField();
    javax.swing.JTextField fPassword = new javax.swing.JTextField();
    javax.swing.JComboBox<String> cboRole = new javax.swing.JComboBox<>(
        new String[]{"cashier", "admin"});

    // Style fields
    for (javax.swing.JTextField f : new javax.swing.JTextField[]{
            fUsername, fFullName, fPassword}) {
        f.setBackground(java.awt.Color.WHITE);
        f.setForeground(java.awt.Color.BLACK);
        f.setCaretColor(java.awt.Color.BLACK);
        f.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0x0088AA), 1),
            javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        f.setPreferredSize(new java.awt.Dimension(220, 30));
    }

    // Style dropdown
    cboRole.setRenderer(new javax.swing.DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(
                javax.swing.JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
            setBackground(isSelected
                ? AppTheme.BG_SELECTED : AppTheme.BG_CARD);
            setForeground(AppTheme.TEXT_PRI);
            return this;
        }
    });

    Object[] fields = {
        "Username:",  fUsername,
        "Full Name:", fFullName,
        "Password:",  fPassword,
        "Role:",      cboRole
    };

    int result = javax.swing.JOptionPane.showConfirmDialog(
        this, fields, "Add New User",
        javax.swing.JOptionPane.OK_CANCEL_OPTION);

    if (result == javax.swing.JOptionPane.OK_OPTION) {
        String username = fUsername.getText().trim();
        String fullName = fFullName.getText().trim();
        String password = fPassword.getText().trim();
        String role     = cboRole.getSelectedItem().toString();

        if (username.isEmpty() || fullName.isEmpty() || password.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "All fields are required.");
            return;
        }

        com.mycompany.possystem.dao.UserDAO dao =
            new com.mycompany.possystem.dao.UserDAO();

        if (dao.usernameExists(username)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Username already exists!");
            return;
        }

        if (dao.createUser(username, password, fullName, role)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "User created successfully!");
            loadUsers();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Failed to create user.");
        }
    }
}

// ── Toggle active/inactive status ────────────────────────────────────
private void toggleActiveStatus() {
    int row = tblUsers.getSelectedRow();
    if (row < 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Please select a user first.");
        return;
    }

    com.mycompany.possystem.model.User u = allUsers.get(row);

    // Prevent deactivating yourself
    if (u.getId() == com.mycompany.possystem.util.SessionManager
            .getCurrentUser().getId()) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "You cannot deactivate your own account!");
        return;
    }

    com.mycompany.possystem.dao.UserDAO dao =
        new com.mycompany.possystem.dao.UserDAO();
    boolean success;

    if (u.isActive()) {
        success = dao.deactivateUser(u.getId());
        if (success) javax.swing.JOptionPane.showMessageDialog(this,
            u.getFullName() + " has been deactivated.");
    } else {
        success = dao.activateUser(u.getId());
        if (success) javax.swing.JOptionPane.showMessageDialog(this,
            u.getFullName() + " has been activated.");
    }

    if (success) loadUsers();
}

// ── Reset password ────────────────────────────────────────────────────
private void resetPassword() {
    int row = tblUsers.getSelectedRow();
    if (row < 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Please select a user first.");
        return;
    }

    com.mycompany.possystem.model.User u = allUsers.get(row);

    javax.swing.JTextField fNewPass = new javax.swing.JTextField();
    fNewPass.setBackground(java.awt.Color.WHITE);
    fNewPass.setForeground(java.awt.Color.BLACK);
    fNewPass.setCaretColor(java.awt.Color.BLACK);
    fNewPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(
            new java.awt.Color(0x0088AA), 1),
        javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 8)));
    fNewPass.setPreferredSize(new java.awt.Dimension(220, 30));

    Object[] fields = {
        "New password for " + u.getUsername() + ":", fNewPass
    };

    int result = javax.swing.JOptionPane.showConfirmDialog(
        this, fields, "Reset Password",
        javax.swing.JOptionPane.OK_CANCEL_OPTION);

    if (result == javax.swing.JOptionPane.OK_OPTION) {
        String newPass = fNewPass.getText().trim();
        if (newPass.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Password cannot be empty.");
            return;
        }
        if (new com.mycompany.possystem.dao.UserDAO()
                .updateUser(u.getId(), u.getFullName(),
                            u.getRole(), newPass)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Password reset successfully!");
        }
    }
}

// ── Style table ───────────────────────────────────────────────────────
private void styleTable(javax.swing.JTable table) {
    table.setBackground(AppTheme.BG_CARD);
    table.setForeground(AppTheme.TEXT_PRI);
    table.setFont(AppTheme.FONT_BODY);
    table.setRowHeight(38);
    table.setGridColor(AppTheme.BORDER);
    table.setSelectionBackground(AppTheme.BG_SELECTED);
    table.setSelectionForeground(AppTheme.TEXT_PRI);
    table.setShowVerticalLines(false);
    table.getTableHeader().setBackground(AppTheme.BG_PANEL);
    table.getTableHeader().setForeground(AppTheme.TEXT_SEC);
    table.getTableHeader().setFont(AppTheme.FONT_SMALL);
}

// ── Style button ──────────────────────────────────────────────────────
private void styleButton(javax.swing.JButton btn, String text,
                          java.awt.Color bg) {
    btn.setText(text);
    btn.setBackground(bg);
    btn.setForeground(bg.equals(AppTheme.BG_HOVER)
        ? AppTheme.TEXT_PRI : AppTheme.BG_DARK);
    btn.setFont(AppTheme.FONT_BODY);
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);
    btn.setCursor(java.awt.Cursor
        .getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    btn.setPreferredSize(new java.awt.Dimension(150, 36));
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topBar = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        btnToggleActive = new javax.swing.JButton();
        btnResetPassword = new javax.swing.JButton();
        scrollUsers = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnDashboard.setText("jButton1");

        btnAddUser.setText("jButton1");

        btnToggleActive.setText("jButton1");

        btnResetPassword.setText("jButton1");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(btnDashboard)
                .addGap(18, 18, 18)
                .addComponent(btnAddUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnToggleActive)
                .addGap(18, 18, 18)
                .addComponent(btnResetPassword)
                .addGap(32, 32, 32))
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDashboard)
                    .addComponent(btnAddUser)
                    .addComponent(btnToggleActive)
                    .addComponent(btnResetPassword))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollUsers.setViewportView(tblUsers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UserManagementFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JButton btnToggleActive;
    private javax.swing.JScrollPane scrollUsers;
    private javax.swing.JTable tblUsers;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
