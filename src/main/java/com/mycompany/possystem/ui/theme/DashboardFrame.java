/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.possystem.ui.theme;

/**
 *
 * @author juanr
 */
public class DashboardFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardFrame.class.getName());

    /**
     * Creates new form DashboardFrame
     */
   public DashboardFrame() {
    initComponents();

    // ── Window settings ──────────────────────────────────────────────
    setTitle("InterTech POS — Dashboard");
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // ── Backgrounds ──────────────────────────────────────────────────
    getContentPane().setBackground(AppTheme.BG_DARK);
    mainPanel.setBackground(AppTheme.BG_DARK);
    navBar.setBackground(AppTheme.BG_PANEL);
    navBar.setBorder(javax.swing.BorderFactory.createMatteBorder(
        0, 0, 2, 0, AppTheme.BORDER));

    // ── Nav buttons ──────────────────────────────────────────────────
    AppTheme.styleNavButton(btnPOS,       "POS");
    AppTheme.styleNavButton(btnInventory, "Inventory");
    AppTheme.styleNavButton(btnReports,   "Reports");
    AppTheme.styleNavButton(btnUsers,     "Users");
    AppTheme.styleNavButton(btnLogout,    "Logout");
    btnUsers.setVisible(com.mycompany.possystem.util.SessionManager.isAdmin());
    btnInventory.setVisible(com.mycompany.possystem.util.SessionManager.isAdmin());
    btnReports.setVisible(com.mycompany.possystem.util.SessionManager.isAdmin());

    // Style logout button distinctly
    btnLogout.setText("Logout");
    btnLogout.setBackground(new java.awt.Color(0x3B0011));
    btnLogout.setForeground(AppTheme.DANGER);
    btnLogout.setFont(AppTheme.FONT_NAV);
    btnLogout.setBorderPainted(false);
    btnLogout.setFocusPainted(false);
    btnLogout.setCursor(java.awt.Cursor.getPredefinedCursor(
        java.awt.Cursor.HAND_CURSOR));
    btnLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(
        0, 18, 0, 18));
    btnLogout.setPreferredSize(new java.awt.Dimension(100, 64));
    AppTheme.addHoverEffect(btnLogout,
        new java.awt.Color(0x3B0011), new java.awt.Color(0x5C0018));

    // ── Welcome label ────────────────────────────────────────────────
    String name = com.mycompany.possystem.util.SessionManager
                    .getCurrentUser() != null
                ? com.mycompany.possystem.util.SessionManager
                    .getCurrentUser().getFullName()
                : "User";
    lblWelcome.setText("Welcome back, " + name + "!");
    lblWelcome.setFont(AppTheme.FONT_TITLE);
    lblWelcome.setForeground(AppTheme.TEXT_PRI);
    
    // ── Recent table ─────────────────────────────────────────────────
    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new String[]{"Receipt #", "Cashier", "Total", "Payment", "Time"}, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    });
    AppTheme.styleTable(jTable1);
    AppTheme.styleScrollPane(scrollRecent);

    // ── Fix layout ───────────────────────────────────────────────────
    fixLayout();

    // ── Setup cards AFTER layout (order matters!) ─────────────────────
    setupCard(cardSales,        "TODAY'S SALES",    "₱0.00", AppTheme.ACCENT);
    setupCard(cardTransactions1,"TRANSACTIONS",     "0",     AppTheme.SUCCESS);
    setupCard(cardLowStock,     "LOW STOCK ALERTS", "0",     AppTheme.WARNING);
    setupCard(cardProducts,     "TOTAL PRODUCTS",   "0",     AppTheme.TEXT_SEC);

    // ── Load data LAST (after cards are ready) ────────────────────────
    loadDashboardData();

    // ── Nav actions ──────────────────────────────────────────────────
    btnLogout.addActionListener(e -> {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this, "Are you sure you want to logout?",
            "Logout", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            com.mycompany.possystem.util.SessionManager.logout();
            new LoginFrame().setVisible(true);
            this.dispose();
        }
    });
    btnPOS.addActionListener(e -> {
        new POSFrame().setVisible(true);
        this.dispose();
    });
    btnInventory.addActionListener(e -> {
        new InventoryFrame().setVisible(true);
        this.dispose();
    });
    btnReports.addActionListener(e -> {
        new ReportsFrame().setVisible(true);
        this.dispose();
    });
    btnUsers.addActionListener(e -> {
        new UserManagementFrame().setVisible(true);
        this.dispose();
    });

    // ── Load data ────────────────────────────────────────────────────
    
}

private void fixLayout() {
    getContentPane().setLayout(new java.awt.BorderLayout());
    getContentPane().add(navBar, java.awt.BorderLayout.NORTH);
    getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

    // ── Nav bar: brand LEFT | buttons CENTER | user+logout RIGHT ─────
    navBar.setLayout(new java.awt.BorderLayout());
    navBar.setPreferredSize(new java.awt.Dimension(0, 64));

    // Brand (GridBagLayout so the 64px wrapper centers naturally)
    javax.swing.JPanel navLeft = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
    navLeft.setBackground(AppTheme.BG_PANEL);
    navLeft.setOpaque(true);
    navLeft.setPreferredSize(new java.awt.Dimension(210, 64));
    navLeft.add(AppTheme.makeNavBrand());

    // Center nav buttons
    javax.swing.JPanel navCenter = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
    navCenter.setBackground(AppTheme.BG_PANEL);
    navCenter.setOpaque(true);
    navCenter.add(btnPOS);
    navCenter.add(btnInventory);
    navCenter.add(btnReports);
    navCenter.add(btnUsers);

    // Right: user chip + logout
    com.mycompany.possystem.model.User currentSession =
        com.mycompany.possystem.util.SessionManager.getCurrentUser();
    String currentUser = currentSession != null
        ? currentSession.getFullName() : "User";
    String currentRole = currentSession != null
        ? currentSession.getRole().toUpperCase() : "";

    // Role badge
    javax.swing.JLabel lblRole = new javax.swing.JLabel(currentRole);
    lblRole.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10));
    lblRole.setForeground(AppTheme.BG_DARK);
    lblRole.setBackground(AppTheme.SUCCESS);
    lblRole.setOpaque(true);
    lblRole.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 6, 2, 6));

    // Name label
    javax.swing.JLabel lblUserName = new javax.swing.JLabel(currentUser);
    lblUserName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    lblUserName.setForeground(AppTheme.TEXT_PRI);

    // User chip (inner)
    javax.swing.JPanel userChip = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
    userChip.setBackground(AppTheme.BG_CARD);
    userChip.setOpaque(true);
    userChip.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        AppTheme.roundedBorder(8, AppTheme.BORDER),
        javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)
    ));
    userChip.add(lblUserName);
    userChip.add(lblRole);

    // Wrapper: 64px tall so it matches nav buttons height
    javax.swing.JPanel chipWrapper = new javax.swing.JPanel(
        new java.awt.GridBagLayout());
    chipWrapper.setBackground(AppTheme.BG_PANEL);
    chipWrapper.setOpaque(true);
    chipWrapper.setPreferredSize(new java.awt.Dimension(
        userChip.getPreferredSize().width + 28, 64));
    chipWrapper.add(userChip); // GridBagLayout centers it vertically

    javax.swing.JPanel navRight = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));
    navRight.setBackground(AppTheme.BG_PANEL);
    navRight.setOpaque(true);
    navRight.add(chipWrapper);
    navRight.add(btnLogout);

    navBar.add(navLeft,   java.awt.BorderLayout.WEST);
    navBar.add(navCenter, java.awt.BorderLayout.CENTER);
    navBar.add(navRight,  java.awt.BorderLayout.EAST);

    mainPanel.setLayout(new java.awt.BorderLayout(0, 0));
    mainPanel.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(28, 28, 28, 28));

    // Welcome row
    javax.swing.JPanel welcomeRow = new javax.swing.JPanel(
        new java.awt.BorderLayout());
    welcomeRow.setBackground(AppTheme.BG_DARK);
    welcomeRow.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(0, 0, 20, 0));

    javax.swing.JLabel lblDate = new javax.swing.JLabel();
    lblDate.setFont(AppTheme.FONT_SMALL);
    lblDate.setForeground(AppTheme.TEXT_SEC);
    lblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

    // Live clock
    javax.swing.Timer clock = new javax.swing.Timer(1000, e -> {
        lblDate.setText(java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter
                .ofPattern("EEEE, MMMM dd yyyy  |  hh:mm:ss a")));
    });
    clock.start();
    lblDate.setText(java.time.LocalDateTime.now().format(
        java.time.format.DateTimeFormatter
            .ofPattern("EEEE, MMMM dd yyyy  |  hh:mm:ss a")));

    welcomeRow.add(lblWelcome, java.awt.BorderLayout.WEST);
    welcomeRow.add(lblDate,    java.awt.BorderLayout.EAST);

    // Cards row
    javax.swing.JPanel cardsRow = new javax.swing.JPanel(
        new java.awt.GridLayout(1, 4, 16, 0));
    cardsRow.setBackground(AppTheme.BG_DARK);
    cardsRow.setPreferredSize(new java.awt.Dimension(0, 120));
    cardsRow.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(0, 0, 24, 0));
    cardsRow.add(cardSales);
    cardsRow.add(cardTransactions);
    cardsRow.add(cardLowStock);
    cardsRow.add(cardProducts);

    // Top section
    javax.swing.JPanel topSection = new javax.swing.JPanel(
        new java.awt.BorderLayout());
    topSection.setBackground(AppTheme.BG_DARK);
    topSection.add(welcomeRow, java.awt.BorderLayout.NORTH);
    topSection.add(cardsRow,   java.awt.BorderLayout.CENTER);

    // Recent section
    javax.swing.JLabel lblRecent = AppTheme.makeSectionHeader(
        "Recent Transactions");
    lblRecent.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(0, 2, 10, 0));

    javax.swing.JPanel bottomSection = new javax.swing.JPanel(
        new java.awt.BorderLayout(0, 0));
    bottomSection.setBackground(AppTheme.BG_DARK);
    bottomSection.add(lblRecent,    java.awt.BorderLayout.NORTH);
    bottomSection.add(scrollRecent, java.awt.BorderLayout.CENTER);

    mainPanel.add(topSection,    java.awt.BorderLayout.NORTH);
    mainPanel.add(bottomSection, java.awt.BorderLayout.CENTER);
}

private void setupCard(javax.swing.JPanel card, String label,
                        String value, java.awt.Color accent) {
    card.setBackground(AppTheme.BG_CARD);
    card.setLayout(new java.awt.BorderLayout());
    card.setBorder(AppTheme.roundedBorder(12, AppTheme.BORDER));

    // Colored accent bar at top
    javax.swing.JPanel accentBar = new javax.swing.JPanel();
    accentBar.setBackground(accent);
    accentBar.setPreferredSize(new java.awt.Dimension(0, 4));
    accentBar.setOpaque(true);

    // Content area
    javax.swing.JPanel inner = new javax.swing.JPanel(
        new java.awt.BorderLayout(0, 10));
    inner.setBackground(AppTheme.BG_CARD);
    inner.setBorder(javax.swing.BorderFactory.createEmptyBorder(
        16, 20, 18, 20));

    javax.swing.JLabel lblLabel = new javax.swing.JLabel(label);
    lblLabel.setForeground(AppTheme.TEXT_SEC);
    lblLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));

    javax.swing.JLabel lblValue = new javax.swing.JLabel(value);
    lblValue.setForeground(accent);
    lblValue.setFont(AppTheme.FONT_PRICE);

    inner.add(lblLabel, java.awt.BorderLayout.NORTH);
    inner.add(lblValue, java.awt.BorderLayout.CENTER);

    card.add(accentBar, java.awt.BorderLayout.NORTH);
    card.add(inner,     java.awt.BorderLayout.CENTER);
    card.putClientProperty("valueLabel", lblValue);
}

private void loadDashboardData() {
    try {
        com.mycompany.possystem.dao.SaleDAO saleDAO =
            new com.mycompany.possystem.dao.SaleDAO();
        com.mycompany.possystem.dao.ProductDAO productDAO =
            new com.mycompany.possystem.dao.ProductDAO();

        updateCard(cardSales,
            String.format("₱%.2f", saleDAO.getTodaysTotalSales()));
        updateCard(cardTransactions1,
            String.valueOf(saleDAO.getTodaysTransactionCount()));
        updateCard(cardLowStock,
            String.valueOf(productDAO.getLowStockProducts().size()));
        updateCard(cardProducts,
            String.valueOf(productDAO.getAllProducts().size()));

        javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        java.time.format.DateTimeFormatter fmt =
            java.time.format.DateTimeFormatter
                .ofPattern("MMM dd, hh:mm a");

        for (com.mycompany.possystem.model.Sale sale :
                saleDAO.getRecentSales(10)) {
            model.addRow(new Object[]{
                "#" + sale.getId(),
                sale.getCashierName(),
                String.format("₱%.2f", sale.getTotalAmount()),
                sale.getPaymentMethod().toUpperCase(),
                sale.getSaleDate().format(fmt)
            });
        }
    } catch (Exception e) {
        System.err.println("Dashboard load error: " + e.getMessage());
    }
}

private void updateCard(javax.swing.JPanel card, String newValue) {
    javax.swing.JLabel lbl =
        (javax.swing.JLabel) card.getClientProperty("valueLabel");
    if (lbl != null) lbl.setText(newValue);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navBar = new javax.swing.JPanel();
        btnPOS = new javax.swing.JButton();
        btnInventory = new javax.swing.JButton();
        btnReports = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        cardSales = new javax.swing.JPanel();
        cardTransactions = new javax.swing.JPanel();
        cardTransactions1 = new javax.swing.JPanel();
        cardLowStock = new javax.swing.JPanel();
        cardProducts = new javax.swing.JPanel();
        scrollRecent = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPOS.setText("jButton1");

        btnInventory.setText("jButton1");

        btnReports.setText("jButton1");

        btnUsers.setText("jButton1");

        btnLogout.setText("jButton1");

        javax.swing.GroupLayout navBarLayout = new javax.swing.GroupLayout(navBar);
        navBar.setLayout(navBarLayout);
        navBarLayout.setHorizontalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPOS)
                .addGap(18, 18, 18)
                .addComponent(btnInventory)
                .addGap(18, 18, 18)
                .addComponent(btnReports)
                .addGap(18, 18, 18)
                .addComponent(btnUsers)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navBarLayout.setVerticalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navBarLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPOS)
                    .addComponent(btnInventory)
                    .addComponent(btnReports)
                    .addComponent(btnUsers)
                    .addComponent(btnLogout))
                .addGap(35, 35, 35))
        );

        lblWelcome.setText("jLabel1");

        javax.swing.GroupLayout cardSalesLayout = new javax.swing.GroupLayout(cardSales);
        cardSales.setLayout(cardSalesLayout);
        cardSalesLayout.setHorizontalGroup(
            cardSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );
        cardSalesLayout.setVerticalGroup(
            cardSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cardTransactions1Layout = new javax.swing.GroupLayout(cardTransactions1);
        cardTransactions1.setLayout(cardTransactions1Layout);
        cardTransactions1Layout.setHorizontalGroup(
            cardTransactions1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );
        cardTransactions1Layout.setVerticalGroup(
            cardTransactions1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cardTransactionsLayout = new javax.swing.GroupLayout(cardTransactions);
        cardTransactions.setLayout(cardTransactionsLayout);
        cardTransactionsLayout.setHorizontalGroup(
            cardTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardTransactions1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardTransactionsLayout.setVerticalGroup(
            cardTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardTransactions1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cardLowStockLayout = new javax.swing.GroupLayout(cardLowStock);
        cardLowStock.setLayout(cardLowStockLayout);
        cardLowStockLayout.setHorizontalGroup(
            cardLowStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        cardLowStockLayout.setVerticalGroup(
            cardLowStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cardProductsLayout = new javax.swing.GroupLayout(cardProducts);
        cardProducts.setLayout(cardProductsLayout);
        cardProductsLayout.setHorizontalGroup(
            cardProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        cardProductsLayout.setVerticalGroup(
            cardProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollRecent.setViewportView(jTable1);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollRecent, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cardSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(cardTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cardLowStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(cardProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cardProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardLowStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardTransactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollRecent, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        java.awt.EventQueue.invokeLater(() -> new DashboardFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInventory;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPOS;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton btnUsers;
    private javax.swing.JPanel cardLowStock;
    private javax.swing.JPanel cardProducts;
    private javax.swing.JPanel cardSales;
    private javax.swing.JPanel cardTransactions;
    private javax.swing.JPanel cardTransactions1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navBar;
    private javax.swing.JScrollPane scrollRecent;
    // End of variables declaration//GEN-END:variables
}
