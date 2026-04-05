/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.possystem.ui.theme;

/**
 *
 * @author juanr
 */
public class ReportsFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReportsFrame.class.getName());

    /**
     * Creates new form ReportsFrame
     */
    public ReportsFrame() {
    initComponents();

    // ── Window settings ──────────────────────────────────────────────
    setTitle("InterTech POS — Sales Reports");
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // ── Backgrounds ──────────────────────────────────────────────────
    getContentPane().setBackground(AppTheme.BG_DARK);
    topBar.setBackground(AppTheme.BG_PANEL);
    topBar.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(10, 14, 10, 14));
    statsPanel.setBackground(AppTheme.BG_CARD);
    statsPanel.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(16, 20, 16, 20));

    // ── Fix layout ───────────────────────────────────────────────────
    getContentPane().setLayout(new java.awt.BorderLayout(0, 0));

    // Top bar
    topBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
    topBar.setBorder(javax.swing.BorderFactory.createMatteBorder(
        0, 0, 2, 0, AppTheme.BORDER));
    topBar.setPreferredSize(new java.awt.Dimension(0, 64));

    javax.swing.JLabel lblTitle = new javax.swing.JLabel("Sales Reports");
    lblTitle.setFont(AppTheme.FONT_HEADING);
    lblTitle.setForeground(AppTheme.TEXT_PRI);
    lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 20));

    topBar.setLayout(new java.awt.BorderLayout());
    topBar.removeAll();

    javax.swing.JPanel rptLeft = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
    rptLeft.setBackground(AppTheme.BG_PANEL);
    rptLeft.setOpaque(true);
    rptLeft.add(AppTheme.makeNavBrand());

    javax.swing.JPanel rptRight = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 12));
    rptRight.setBackground(AppTheme.BG_PANEL);
    rptRight.setOpaque(true);
    rptRight.add(btnDaily);
    rptRight.add(btnWeekly);
    rptRight.add(btnMonthly);
    rptRight.add(btnAll);
    rptRight.add(btnDashboard);

    topBar.add(rptLeft,  java.awt.BorderLayout.WEST);
    topBar.add(rptRight, java.awt.BorderLayout.EAST);
    getContentPane().add(topBar, java.awt.BorderLayout.NORTH);

    // Stats panel
    statsPanel.setLayout(new java.awt.GridLayout(1, 3, 20, 0));
    getContentPane().add(statsPanel, java.awt.BorderLayout.CENTER);

    // Table
    getContentPane().add(scrollReports, java.awt.BorderLayout.SOUTH);
    scrollReports.setPreferredSize(new java.awt.Dimension(0, 500));

    // Fix layout properly
    javax.swing.JPanel centerSection = new javax.swing.JPanel(
        new java.awt.BorderLayout(0, 0));
    centerSection.setBackground(AppTheme.BG_DARK);
    centerSection.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(16, 16, 16, 16));

    // Stats at top of center
    getContentPane().remove(statsPanel);
    getContentPane().remove(scrollReports);
    centerSection.add(statsPanel,    java.awt.BorderLayout.NORTH);
    centerSection.add(scrollReports, java.awt.BorderLayout.CENTER);
    getContentPane().add(centerSection, java.awt.BorderLayout.CENTER);

    // ── Toolbar buttons ──────────────────────────────────────────────
    AppTheme.stylePrimaryButton(btnDashboard, "Back",    AppTheme.BG_HOVER);
    AppTheme.stylePrimaryButton(btnDaily,     "Today",   AppTheme.ACCENT_BTN);
    AppTheme.stylePrimaryButton(btnWeekly,    "Weekly",  AppTheme.ACCENT_BTN);
    AppTheme.stylePrimaryButton(btnMonthly,   "Monthly", AppTheme.ACCENT_BTN);
    AppTheme.stylePrimaryButton(btnAll,       "All",     AppTheme.ACCENT_BTN);

    // ── Stats labels ─────────────────────────────────────────────────
    setupStatLabel(lblTotalSales,        "Total Sales",    "₱0.00");
    setupStatLabel(lblTotalTransactions, "Transactions",   "0");
    setupStatLabel(lblTotalRevenue,      "Avg per Sale",   "₱0.00");

    // ── Reports table ────────────────────────────────────────────────
    tblReports.setModel(new javax.swing.table.DefaultTableModel(
        new String[]{"#", "Cashier", "Total", "Tax",
                     "Discount", "Payment", "Date & Time"}, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    });
    AppTheme.styleTable(tblReports);
    AppTheme.styleScrollPane(scrollReports);

    // ── Button actions ───────────────────────────────────────────────
    btnDashboard.addActionListener(e -> {
        new DashboardFrame().setVisible(true);
        this.dispose();
    });

    btnDaily.addActionListener(e -> {
        java.time.LocalDateTime start = java.time.LocalDate.now()
            .atStartOfDay();
        java.time.LocalDateTime end = java.time.LocalDate.now()
            .atTime(23, 59, 59);
        loadSales(new com.mycompany.possystem.dao.SaleDAO()
            .getSalesByDateRange(start, end));
    });

    btnWeekly.addActionListener(e -> {
        java.time.LocalDateTime start = java.time.LocalDate.now()
            .minusDays(7).atStartOfDay();
        java.time.LocalDateTime end = java.time.LocalDate.now()
            .atTime(23, 59, 59);
        loadSales(new com.mycompany.possystem.dao.SaleDAO()
            .getSalesByDateRange(start, end));
    });

    btnMonthly.addActionListener(e -> {
        java.time.LocalDateTime start = java.time.LocalDate.now()
            .minusDays(30).atStartOfDay();
        java.time.LocalDateTime end = java.time.LocalDate.now()
            .atTime(23, 59, 59);
        loadSales(new com.mycompany.possystem.dao.SaleDAO()
            .getSalesByDateRange(start, end));
    });

    btnAll.addActionListener(e ->
        loadSales(new com.mycompany.possystem.dao.SaleDAO().getAllSales()));

    // ── Load today's data by default ─────────────────────────────────
    btnDaily.doClick();
}

// ── Load sales into table ─────────────────────────────────────────────
private void loadSales(java.util.List<com.mycompany.possystem.model.Sale> sales) {
    javax.swing.table.DefaultTableModel model =
        (javax.swing.table.DefaultTableModel) tblReports.getModel();
    model.setRowCount(0);

    double totalRevenue = 0;
    java.time.format.DateTimeFormatter fmt =
        java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");

    for (com.mycompany.possystem.model.Sale s : sales) {
        model.addRow(new Object[]{
            "#" + s.getId(),
            s.getCashierName(),
            String.format("₱%.2f", s.getTotalAmount()),
            String.format("₱%.2f", s.getTaxAmount()),
            String.format("₱%.2f", s.getDiscount()),
            s.getPaymentMethod().toUpperCase(),
            s.getSaleDate().format(fmt)
        });
        totalRevenue += s.getTotalAmount();
    }

    // Update stat labels
    updateStatLabel(lblTotalSales,
        String.format("₱%.2f", totalRevenue));
    updateStatLabel(lblTotalTransactions,
        String.valueOf(sales.size()));
    updateStatLabel(lblTotalRevenue,
        sales.isEmpty() ? "₱0.00"
            : String.format("₱%.2f", totalRevenue / sales.size()));
}

// ── Setup a stat label card ───────────────────────────────────────────
private void setupStatLabel(javax.swing.JLabel lbl,
                             String title, String value) {
    lbl.setLayout(new java.awt.BorderLayout());

    javax.swing.JPanel card = new javax.swing.JPanel(
        new java.awt.BorderLayout());
    card.setBackground(AppTheme.BG_PANEL);
    card.setBorder(javax.swing.BorderFactory.createEmptyBorder(12,16,12,16));

    javax.swing.JLabel lblTitle = new javax.swing.JLabel(title);
    lblTitle.setForeground(AppTheme.TEXT_SEC);
    lblTitle.setFont(AppTheme.FONT_SMALL);

    javax.swing.JLabel lblValue = new javax.swing.JLabel(value);
    lblValue.setForeground(AppTheme.ACCENT);
    lblValue.setFont(AppTheme.FONT_PRICE);
    lblValue.setName("value");

    card.add(lblTitle, java.awt.BorderLayout.NORTH);
    card.add(lblValue, java.awt.BorderLayout.CENTER);

    lbl.setOpaque(false);
    lbl.setPreferredSize(new java.awt.Dimension(0, 70));

    // Replace lbl content with card
    lbl.setLayout(new java.awt.BorderLayout());
    statsPanel.remove(lbl);
    statsPanel.add(card);
    lbl.putClientProperty("valueLabel", lblValue);
}

// ── Update a stat label value ─────────────────────────────────────────
private void updateStatLabel(javax.swing.JLabel lbl, String newValue) {
    javax.swing.JLabel val =
        (javax.swing.JLabel) lbl.getClientProperty("valueLabel");
    if (val != null) val.setText(newValue);
}

// ── Style table ───────────────────────────────────────────────────────
private void styleTable(javax.swing.JTable table) {
    table.setBackground(AppTheme.BG_CARD);
    table.setForeground(AppTheme.TEXT_PRI);
    table.setFont(AppTheme.FONT_BODY);
    table.setRowHeight(34);
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
    btn.setPreferredSize(new java.awt.Dimension(100, 36));
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
        btnDaily = new javax.swing.JButton();
        btnWeekly = new javax.swing.JButton();
        btnMonthly = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        statsPanel = new javax.swing.JPanel();
        lblTotalSales = new javax.swing.JLabel();
        lblTotalTransactions = new javax.swing.JLabel();
        lblTotalRevenue = new javax.swing.JLabel();
        scrollReports = new javax.swing.JScrollPane();
        tblReports = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnDashboard.setText("jButton1");

        btnDaily.setText("jButton1");

        btnWeekly.setText("jButton1");

        btnMonthly.setText("jButton1");

        btnAll.setText("jButton1");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDaily)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnWeekly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMonthly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAll)
                .addGap(12, 12, 12))
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDashboard)
                    .addComponent(btnDaily)
                    .addComponent(btnWeekly)
                    .addComponent(btnMonthly)
                    .addComponent(btnAll))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblTotalSales.setText("jLabel1");

        lblTotalTransactions.setText("jLabel1");

        lblTotalRevenue.setText("jLabel1");

        javax.swing.GroupLayout statsPanelLayout = new javax.swing.GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTotalSales, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(lblTotalRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalSales)
                    .addComponent(lblTotalTransactions)
                    .addComponent(lblTotalRevenue))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tblReports.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollReports.setViewportView(tblReports);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollReports, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollReports, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
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
        java.awt.EventQueue.invokeLater(() -> new ReportsFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnDaily;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnMonthly;
    private javax.swing.JButton btnWeekly;
    private javax.swing.JLabel lblTotalRevenue;
    private javax.swing.JLabel lblTotalSales;
    private javax.swing.JLabel lblTotalTransactions;
    private javax.swing.JScrollPane scrollReports;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JTable tblReports;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
