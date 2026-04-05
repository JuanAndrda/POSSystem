package com.mycompany.possystem.ui.theme;

import com.mycompany.possystem.dao.UserDAO;
import com.mycompany.possystem.model.User;
import com.mycompany.possystem.util.SessionManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
    initComponents();

    // ── Window settings ──────────────────────────────────────────────
    setTitle("InterTech POS — Login");
    setSize(420, 580);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);

    // ── Override layout with clean centered GridBagLayout ────────────
    mainPanel.setLayout(new java.awt.GridBagLayout());
    mainPanel.setBackground(AppTheme.BG_DARK);
    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
    gbc.gridx = 0;
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gbc.insets = new java.awt.Insets(6, 10, 6, 10);

    // ── Logo ─────────────────────────────────────────────────────────
    lblLogo.setText("⚡");
    lblLogo.setFont(new java.awt.Font("Segoe UI Emoji", java.awt.Font.PLAIN, 56));
    lblLogo.setForeground(AppTheme.ACCENT);
    lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 0;
    gbc.insets = new java.awt.Insets(40, 30, 6, 30);
    mainPanel.add(lblLogo, gbc);

    // ── Title ────────────────────────────────────────────────────────
    lblTitle.setText("InterTech POS");
    lblTitle.setFont(AppTheme.FONT_TITLE);
    lblTitle.setForeground(AppTheme.ACCENT);
    lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 1;
    gbc.insets = new java.awt.Insets(0, 30, 4, 30);
    mainPanel.add(lblTitle, gbc);

    // ── Subtitle ─────────────────────────────────────────────────────
    javax.swing.JLabel lblSubtitle = new javax.swing.JLabel(
        "Electronics & Gadgets Store");
    lblSubtitle.setFont(AppTheme.FONT_SMALL);
    lblSubtitle.setForeground(AppTheme.TEXT_SEC);
    lblSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 2;
    gbc.insets = new java.awt.Insets(0, 30, 30, 30);
    mainPanel.add(lblSubtitle, gbc);

    // ── Username label ───────────────────────────────────────────────
    lblUsername.setText("Username");
    lblUsername.setFont(AppTheme.FONT_SMALL);
    lblUsername.setForeground(AppTheme.TEXT_SEC);
    gbc.gridy = 3;
    gbc.insets = new java.awt.Insets(0, 30, 4, 30);
    mainPanel.add(lblUsername, gbc);

    // ── Username field ───────────────────────────────────────────────
    AppTheme.styleField(txtUsername);
    txtUsername.setPreferredSize(new java.awt.Dimension(0, 44));
    gbc.gridy = 4;
    gbc.insets = new java.awt.Insets(0, 30, 16, 30);
    mainPanel.add(txtUsername, gbc);

    // ── Password label ───────────────────────────────────────────────
    lblPassword.setText("Password");
    lblPassword.setFont(AppTheme.FONT_SMALL);
    lblPassword.setForeground(AppTheme.TEXT_SEC);
    gbc.gridy = 5;
    gbc.insets = new java.awt.Insets(0, 30, 4, 30);
    mainPanel.add(lblPassword, gbc);

    // ── Password field ───────────────────────────────────────────────
    AppTheme.styleField(txtPassword);
    txtPassword.setPreferredSize(new java.awt.Dimension(0, 44));
    gbc.gridy = 6;
    gbc.insets = new java.awt.Insets(0, 30, 24, 30);
    mainPanel.add(txtPassword, gbc);

    // ── Login button ─────────────────────────────────────────────────
    AppTheme.stylePrimaryButton(btnLogin, "LOG IN", AppTheme.ACCENT_BTN);
    btnLogin.setFont(AppTheme.FONT_HEADING);
    btnLogin.setPreferredSize(new java.awt.Dimension(0, 48));
    gbc.gridy = 7;
    gbc.insets = new java.awt.Insets(0, 30, 12, 30);
    mainPanel.add(btnLogin, gbc);

    // ── Status label ─────────────────────────────────────────────────
    lblStatus.setText(" ");
    lblStatus.setFont(AppTheme.FONT_SMALL);
    lblStatus.setForeground(AppTheme.DANGER);
    lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 8;
    gbc.insets = new java.awt.Insets(0, 30, 40, 30);
    mainPanel.add(lblStatus, gbc);

    // ── Bottom version label ──────────────────────────────────────────
    javax.swing.JLabel lblVersion = new javax.swing.JLabel("v1.0.0");
    lblVersion.setFont(AppTheme.FONT_SMALL);
    lblVersion.setForeground(new java.awt.Color(0x2A4060));
    lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 9;
    gbc.insets = new java.awt.Insets(0, 30, 16, 30);
    mainPanel.add(lblVersion, gbc);

    // ── Press Enter to login ──────────────────────────────────────────
    getRootPane().setDefaultButton(btnLogin);
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLogo.setText("⚡");

        lblTitle.setText("Intertech POS");

        lblUsername.setText("Username:");

        txtUsername.addActionListener(this::txtUsernameActionPerformed);

        lblPassword.setText("Password:");

        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        btnLogin.setText("Login");
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        lblStatus.setText("Message");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin)
                    .addComponent(lblTitle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addGap(18, 18, 18)
                .addComponent(lblStatus)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String username = txtUsername.getText().trim();
String password = txtPassword.getText();

    if (username.isEmpty() || password.isEmpty()) {
        lblStatus.setText("Please enter username and password.");
        return;
    }

    UserDAO dao  = new UserDAO();
    User    user = dao.authenticate(username, password);

if (user != null) {
    SessionManager.login(user);
    new DashboardFrame().setVisible(true);  // ← open dashboard
    this.dispose();                          // ← close login
} else {
        lblStatus.setText("Incorrect username or password.");
        txtPassword.setText("");
    }
    }//GEN-LAST:event_btnLoginActionPerformed

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
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
