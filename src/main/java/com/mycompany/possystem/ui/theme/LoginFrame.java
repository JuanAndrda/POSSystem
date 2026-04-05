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

    // ── Outer background ─────────────────────────────────────────────
    getContentPane().setBackground(AppTheme.BG_DARK);
    mainPanel.setBackground(AppTheme.BG_DARK);
    mainPanel.setLayout(new java.awt.GridBagLayout());

    // ── Floating card ────────────────────────────────────────────────
    javax.swing.JPanel card = new javax.swing.JPanel(
        new java.awt.GridBagLayout());
    card.setBackground(AppTheme.BG_PANEL);
    card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        AppTheme.roundedBorder(18, AppTheme.BORDER),
        javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)
    ));

    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
    gbc.gridx = 0;
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

    // ── Accent top bar on card ────────────────────────────────────────
    javax.swing.JPanel cardAccent = new javax.swing.JPanel();
    cardAccent.setBackground(AppTheme.ACCENT);
    cardAccent.setPreferredSize(new java.awt.Dimension(0, 4));
    cardAccent.setOpaque(true);
    gbc.gridy = 0;
    gbc.insets = new java.awt.Insets(0, 0, 0, 0);
    card.add(cardAccent, gbc);

    // ── Logo ─────────────────────────────────────────────────────────
    lblLogo.setText("⚡");
    lblLogo.setFont(new java.awt.Font("Segoe UI Emoji", java.awt.Font.PLAIN, 52));
    lblLogo.setForeground(AppTheme.ACCENT);
    lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 1;
    gbc.insets = new java.awt.Insets(36, 40, 4, 40);
    card.add(lblLogo, gbc);

    // ── Title ────────────────────────────────────────────────────────
    lblTitle.setText("InterTech POS");
    lblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
    lblTitle.setForeground(AppTheme.ACCENT);
    lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 2;
    gbc.insets = new java.awt.Insets(0, 40, 2, 40);
    card.add(lblTitle, gbc);

    // ── Subtitle ─────────────────────────────────────────────────────
    javax.swing.JLabel lblSubtitle = new javax.swing.JLabel(
        "Electronics & Gadgets Store");
    lblSubtitle.setFont(AppTheme.FONT_SMALL);
    lblSubtitle.setForeground(AppTheme.TEXT_SEC);
    lblSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 3;
    gbc.insets = new java.awt.Insets(0, 40, 28, 40);
    card.add(lblSubtitle, gbc);

    // ── Divider ───────────────────────────────────────────────────────
    javax.swing.JSeparator sep = new javax.swing.JSeparator();
    sep.setForeground(AppTheme.BORDER);
    sep.setBackground(AppTheme.BORDER);
    gbc.gridy = 4;
    gbc.insets = new java.awt.Insets(0, 0, 20, 0);
    card.add(sep, gbc);

    // ── Username label ───────────────────────────────────────────────
    lblUsername.setText("USERNAME");
    lblUsername.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
    lblUsername.setForeground(AppTheme.TEXT_SEC);
    gbc.gridy = 5;
    gbc.insets = new java.awt.Insets(0, 36, 5, 36);
    card.add(lblUsername, gbc);

    // ── Username field ───────────────────────────────────────────────
    AppTheme.styleField(txtUsername);
    txtUsername.setPreferredSize(new java.awt.Dimension(0, 44));
    gbc.gridy = 6;
    gbc.insets = new java.awt.Insets(0, 36, 16, 36);
    card.add(txtUsername, gbc);

    // ── Password label ───────────────────────────────────────────────
    lblPassword.setText("PASSWORD");
    lblPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
    lblPassword.setForeground(AppTheme.TEXT_SEC);
    gbc.gridy = 7;
    gbc.insets = new java.awt.Insets(0, 36, 5, 36);
    card.add(lblPassword, gbc);

    // ── Password field ───────────────────────────────────────────────
    AppTheme.styleField(txtPassword);
    txtPassword.setPreferredSize(new java.awt.Dimension(0, 44));
    gbc.gridy = 8;
    gbc.insets = new java.awt.Insets(0, 36, 24, 36);
    card.add(txtPassword, gbc);

    // ── Login button ─────────────────────────────────────────────────
    AppTheme.stylePrimaryButton(btnLogin, "LOG IN", AppTheme.ACCENT_BTN);
    btnLogin.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15));
    btnLogin.setForeground(java.awt.Color.WHITE);
    btnLogin.setPreferredSize(new java.awt.Dimension(0, 48));
    gbc.gridy = 9;
    gbc.insets = new java.awt.Insets(0, 36, 10, 36);
    card.add(btnLogin, gbc);

    // ── Status label ─────────────────────────────────────────────────
    lblStatus.setText(" ");
    lblStatus.setFont(AppTheme.FONT_SMALL);
    lblStatus.setForeground(AppTheme.DANGER);
    lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 10;
    gbc.insets = new java.awt.Insets(0, 36, 4, 36);
    card.add(lblStatus, gbc);

    // ── Version ───────────────────────────────────────────────────────
    javax.swing.JLabel lblVersion = new javax.swing.JLabel("v1.0.0");
    lblVersion.setFont(AppTheme.FONT_SMALL);
    lblVersion.setForeground(new java.awt.Color(0x2A4060));
    lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gbc.gridy = 11;
    gbc.insets = new java.awt.Insets(0, 36, 28, 36);
    card.add(lblVersion, gbc);

    // ── Add card to mainPanel centered ───────────────────────────────
    java.awt.GridBagConstraints outer = new java.awt.GridBagConstraints();
    outer.gridx = 0; outer.gridy = 0;
    outer.fill = java.awt.GridBagConstraints.NONE;
    outer.anchor = java.awt.GridBagConstraints.CENTER;
    outer.ipadx = 60;
    mainPanel.add(card, outer);

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
