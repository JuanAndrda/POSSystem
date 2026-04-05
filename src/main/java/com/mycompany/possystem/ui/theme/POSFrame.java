/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.possystem.ui.theme;

/**
 *
 * @author juanr
 */
public class POSFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(POSFrame.class.getName());
    private java.util.List<com.mycompany.possystem.model.CartItem> cart =
        new java.util.ArrayList<>();
    private java.util.List<com.mycompany.possystem.model.Product> products =
    new java.util.ArrayList<>();
    private javax.swing.table.DefaultTableModel cartModel;
    private com.mycompany.possystem.dao.ProductDAO productDAO =
        new com.mycompany.possystem.dao.ProductDAO();

    /**
     * Creates new form POSFrame
     */
    public POSFrame() {
    initComponents();

    // ── Window settings ──────────────────────────────────────────────
    setTitle("RetailPro POS — POS Screen");
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // ── Fix main layout: 3 columns ───────────────────────────────────
    getContentPane().setLayout(new java.awt.GridLayout(1, 3, 4, 0));
    getContentPane().setBackground(AppTheme.BG_DARK);

    // ── Style all 3 panels ───────────────────────────────────────────
    leftPanel.setBackground(AppTheme.BG_PANEL);
    leftPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
    AppTheme.roundedBorder(12, AppTheme.BORDER),
    javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    leftPanel.setLayout(new java.awt.BorderLayout(0, 10));  

    centerPanel.setBackground(AppTheme.BG_DARK);
    centerPanel.setBorder(javax.swing.BorderFactory
    .createEmptyBorder(20, 16, 20, 16));
    centerPanel.setLayout(new java.awt.BorderLayout(0, 10));

    centerPanel1.setBackground(AppTheme.BG_PANEL);
    centerPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
    AppTheme.roundedBorder(12, AppTheme.BORDER),
    javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    centerPanel1.setLayout(new java.awt.BorderLayout(0, 10));

    // ══════════════════════════════════════════════════════════════════
    // LEFT PANEL — Product Search
    // ══════════════════════════════════════════════════════════════════

    // Section title
    javax.swing.JLabel lblProducts = new javax.swing.JLabel("Products");
    lblProducts.setFont(AppTheme.FONT_TITLE);
    lblProducts.setForeground(AppTheme.TEXT_PRI);

    // Search field
    AppTheme.styleField(txtSearch);
    txtSearch.setFont(AppTheme.FONT_BODY);

    // Top section of left panel
    javax.swing.JPanel leftTop = new javax.swing.JPanel(
        new java.awt.BorderLayout(0, 8));
    leftTop.setBackground(AppTheme.BG_PANEL);
    leftTop.add(lblProducts, java.awt.BorderLayout.NORTH);
    leftTop.add(txtSearch,   java.awt.BorderLayout.CENTER);

    // Products table
    tblProducts.setModel(new javax.swing.table.DefaultTableModel(
        new String[]{"Product", "Category", "Price", "Stock"}, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    });
    AppTheme.styleTable(tblProducts);
    scrollProducts.getViewport().setBackground(AppTheme.BG_CARD);
    scrollProducts.setBorder(javax.swing.BorderFactory
        .createLineBorder(AppTheme.BORDER));

    // Add to cart button
    AppTheme.stylePrimaryButton(btnAddToCart, "Add to Cart", AppTheme.SUCCESS);

    // Assemble left panel
    leftPanel.add(leftTop,        java.awt.BorderLayout.NORTH);
leftPanel.add(scrollProducts, java.awt.BorderLayout.CENTER);
leftPanel.add(btnAddToCart,   java.awt.BorderLayout.SOUTH);

    // ══════════════════════════════════════════════════════════════════
    // CENTER PANEL — Cart
    // ══════════════════════════════════════════════════════════════════

    javax.swing.JLabel lblOrder = new javax.swing.JLabel("Current Order");
    lblOrder.setFont(AppTheme.FONT_TITLE);
    lblOrder.setForeground(AppTheme.TEXT_PRI);

    // Cart table
    cartModel = new javax.swing.table.DefaultTableModel(
        new String[]{"Product", "Price", "Qty", "Subtotal"}, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    };
    tblCart.setModel(cartModel);
    AppTheme.styleTable(tblCart);
    scrollCart.getViewport().setBackground(AppTheme.BG_CARD);
    scrollCart.setBorder(javax.swing.BorderFactory
        .createLineBorder(AppTheme.BORDER));

    // Bottom buttons
    AppTheme.stylePrimaryButton(btnRemove,    "Remove Item", AppTheme.DANGER);
    AppTheme.stylePrimaryButton(btnClearCart, "Clear Cart",  AppTheme.BG_HOVER);

    javax.swing.JPanel cartButtons = new javax.swing.JPanel(
        new java.awt.GridLayout(1, 2, 8, 0));
    cartButtons.setBackground(AppTheme.BG_DARK);
    cartButtons.add(btnRemove);
    cartButtons.add(btnClearCart);

    // Assemble center panel
    centerPanel.add(lblOrder,    java.awt.BorderLayout.NORTH);
    centerPanel.add(scrollCart,  java.awt.BorderLayout.CENTER);
    centerPanel.add(cartButtons, java.awt.BorderLayout.SOUTH);

    // ══════════════════════════════════════════════════════════════════
    // RIGHT PANEL — Payment Summary
    // ══════════════════════════════════════════════════════════════════

    javax.swing.JLabel lblSummary = new javax.swing.JLabel("Order Summary");
    lblSummary.setFont(AppTheme.FONT_TITLE);
    lblSummary.setForeground(AppTheme.TEXT_PRI);

    // Summary rows panel
    javax.swing.JPanel summaryPanel = new javax.swing.JPanel(
        new java.awt.GridBagLayout());
    summaryPanel.setBackground(AppTheme.BG_CARD);
    summaryPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(16,16,16,16));

    java.awt.GridBagConstraints g = new java.awt.GridBagConstraints();
    g.fill = java.awt.GridBagConstraints.HORIZONTAL;
    g.insets = new java.awt.Insets(6, 4, 6, 4);

    // Subtotal row
    g.gridx = 0; g.gridy = 0; g.weightx = 1.0;
    summaryPanel.add(makeLabel("Subtotal:", AppTheme.TEXT_SEC, AppTheme.FONT_BODY), g);
    g.gridx = 1; g.weightx = 0;
    lblSubtotalVal.setText("₱0.00");
    lblSubtotalVal.setForeground(AppTheme.TEXT_PRI);
    lblSubtotalVal.setFont(AppTheme.FONT_BODY);
    lblSubtotalVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    summaryPanel.add(lblSubtotalVal, g);

    // Tax row
    g.gridx = 0; g.gridy = 1; g.weightx = 1.0;
    summaryPanel.add(makeLabel("Tax (12%):", AppTheme.TEXT_SEC, AppTheme.FONT_BODY), g);
    g.gridx = 1; g.weightx = 0;
    lblTaxVal.setText("₱0.00");
    lblTaxVal.setForeground(AppTheme.TEXT_SEC);
    lblTaxVal.setFont(AppTheme.FONT_BODY);
    lblTaxVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    summaryPanel.add(lblTaxVal, g);

    // Divider
    g.gridx = 0; g.gridy = 2; g.gridwidth = 2;
    javax.swing.JSeparator sep = new javax.swing.JSeparator();
    sep.setForeground(AppTheme.BORDER);
    summaryPanel.add(sep, g);
    g.gridwidth = 1;

    // Total row
    g.gridx = 0; g.gridy = 3; g.weightx = 1.0;
    summaryPanel.add(makeLabel("TOTAL:", AppTheme.TEXT_PRI, AppTheme.FONT_HEADING), g);
    g.gridx = 1; g.weightx = 0;
    lblTotalVal.setText("₱0.00");
    lblTotalVal.setForeground(AppTheme.ACCENT);
    lblTotalVal.setFont(AppTheme.FONT_TOTAL);
    lblTotalVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    summaryPanel.add(lblTotalVal, g);

    // Payment method
    javax.swing.JPanel paymentPanel = new javax.swing.JPanel(
        new java.awt.GridBagLayout());
    paymentPanel.setBackground(AppTheme.BG_PANEL);
    paymentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12,0,0,0));

    java.awt.GridBagConstraints p = new java.awt.GridBagConstraints();
    p.fill = java.awt.GridBagConstraints.HORIZONTAL;
    p.weightx = 1.0;
    p.insets = new java.awt.Insets(3, 0, 3, 0);

    // Payment method dropdown
    p.gridy = 0;
    paymentPanel.add(makeLabel("Payment Method:", AppTheme.TEXT_SEC, AppTheme.FONT_SMALL), p);
    p.gridy = 1;
    cboPayment.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[]{"Cash", "Card", "GCash"}));
    cboPayment.setBackground(AppTheme.BG_CARD);
    cboPayment.setForeground(AppTheme.TEXT_PRI);
    cboPayment.setRenderer(new javax.swing.DefaultListCellRenderer() {
    @Override
    public java.awt.Component getListCellRendererComponent(
            javax.swing.JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setBackground(isSelected ? AppTheme.BG_SELECTED : AppTheme.BG_CARD);
        setForeground(AppTheme.TEXT_PRI);
        return this;
    }
});
    cboPayment.setFont(AppTheme.FONT_BODY);
    paymentPanel.add(cboPayment, p);

    // Amount tendered
    p.gridy = 2;
    paymentPanel.add(makeLabel("Amount Tendered:", AppTheme.TEXT_SEC, AppTheme.FONT_SMALL), p);
    p.gridy = 3;
    AppTheme.styleField(txtTendered);
    paymentPanel.add(txtTendered, p);

    // Change
    p.gridy = 4;
    paymentPanel.add(makeLabel("Change:", AppTheme.TEXT_SEC, AppTheme.FONT_SMALL), p);
    p.gridy = 5;
    lblChangeVal.setText("₱0.00");
    lblChangeVal.setFont(AppTheme.FONT_PRICE);
    lblChangeVal.setForeground(AppTheme.SUCCESS);
    paymentPanel.add(lblChangeVal, p);

    // Buttons
    p.gridy = 6;
    p.insets = new java.awt.Insets(8, 0, 4, 0);
    AppTheme.stylePrimaryButton(btnCheckout, "PROCESS PAYMENT", AppTheme.SUCCESS);
    btnCheckout.setFont(AppTheme.FONT_HEADING);
    btnCheckout.setFont(AppTheme.FONT_HEADING);
    paymentPanel.add(btnCheckout, p);

    p.gridy = 7;
    p.insets = new java.awt.Insets(6, 0, 0, 0);
    AppTheme.stylePrimaryButton(btnDashboard, "Back to Dashboard", AppTheme.BG_HOVER);
    paymentPanel.add(btnDashboard, p);

    // Assemble right panel
    javax.swing.JPanel rightTop = new javax.swing.JPanel(
        new java.awt.BorderLayout(0, 12));
    rightTop.setBackground(AppTheme.BG_PANEL);
    rightTop.add(lblSummary,  java.awt.BorderLayout.NORTH);
    rightTop.add(summaryPanel, java.awt.BorderLayout.CENTER);

    centerPanel1.add(rightTop,     java.awt.BorderLayout.NORTH);
    centerPanel1.add(paymentPanel, java.awt.BorderLayout.CENTER);

    // ══════════════════════════════════════════════════════════════════
    // WIRE UP EVENTS
    // ══════════════════════════════════════════════════════════════════

    // Live search as user types
    txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {
            searchProducts();
        }
    });

    // Recalculate change when tendered amount changes
    txtTendered.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {
            updateChange();
        }
    });

    // Add to cart on button click
    btnAddToCart.addActionListener(e -> addSelectedToCart());

    // Also add to cart on double-click in product table
    tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (e.getClickCount() == 2) addSelectedToCart();
        }
    });

    // Remove item
    btnRemove.addActionListener(e -> removeSelectedFromCart());

    // Clear cart
    btnClearCart.addActionListener(e -> {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this, "Clear the entire cart?", "Confirm",
            javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            cart.clear();
            refreshCart();
        }
    });

    // Checkout
    btnCheckout.addActionListener(e -> processCheckout());

    // Back to dashboard
    btnDashboard.addActionListener(e -> {
        new DashboardFrame().setVisible(true);
        this.dispose();
    });

    // Load all products on startup
    loadAllProducts();
}
    
    // ── Style a table ─────────────────────────────────────────────────────
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

// ── Style a button ────────────────────────────────────────────────────
private void styleButton(javax.swing.JButton btn, String text, java.awt.Color bg) {
    btn.setText(text);
    btn.setBackground(bg);
    btn.setForeground(bg.equals(AppTheme.BG_HOVER) ? AppTheme.TEXT_PRI : AppTheme.BG_DARK);
    btn.setFont(AppTheme.FONT_BODY);
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);
    btn.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    btn.setPreferredSize(new java.awt.Dimension(0, 40));
}

// ── Make a simple label ───────────────────────────────────────────────
private javax.swing.JLabel makeLabel(String text, java.awt.Color color,
                                      java.awt.Font font) {
    javax.swing.JLabel lbl = new javax.swing.JLabel(text);
    lbl.setForeground(color);
    lbl.setFont(font);
    return lbl;
}

// ── Load all products into the product table ──────────────────────────
private void loadAllProducts() {
    javax.swing.table.DefaultTableModel model =
        (javax.swing.table.DefaultTableModel) tblProducts.getModel();
    model.setRowCount(0);
    for (com.mycompany.possystem.model.Product p : productDAO.getAllProducts()) {
        model.addRow(new Object[]{
            p.getName(),
            p.getCategoryName(),
            String.format("₱%.2f", p.getPrice()),
            p.getStock()
        });
    }
    // Store products list for reference
    this.products = productDAO.getAllProducts();
}

// ── Search products ───────────────────────────────────────────────────
private void searchProducts() {
    String query = txtSearch.getText().trim();
    javax.swing.table.DefaultTableModel model =
        (javax.swing.table.DefaultTableModel) tblProducts.getModel();
    model.setRowCount(0);

    java.util.List<com.mycompany.possystem.model.Product> results =
        query.isEmpty() ? productDAO.getAllProducts() : productDAO.search(query);

    this.products = results;
    for (com.mycompany.possystem.model.Product p : results) {
        model.addRow(new Object[]{
            p.getName(),
            p.getCategoryName(),
            String.format("₱%.2f", p.getPrice()),
            p.getStock()
        });
    }
}

// ── Add selected product to cart ──────────────────────────────────────
private void addSelectedToCart() {
    int row = tblProducts.getSelectedRow();
    if (row < 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Please select a product first.");
        return;
    }
    com.mycompany.possystem.model.Product selected = products.get(row);
    if (selected.getStock() <= 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "This product is out of stock!");
        return;
    }

    // Check if already in cart
    for (com.mycompany.possystem.model.CartItem item : cart) {
        if (item.getProduct().getId() == selected.getId()) {
            item.setQuantity(item.getQuantity() + 1);
            refreshCart();
            return;
        }
    }
    cart.add(new com.mycompany.possystem.model.CartItem(selected, 1));
    refreshCart();
}

// ── Remove selected item from cart ───────────────────────────────────
private void removeSelectedFromCart() {
    int row = tblCart.getSelectedRow();
    if (row < 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Please select an item to remove.");
        return;
    }
    cart.remove(row);
    refreshCart();
}

// ── Refresh cart table and totals ─────────────────────────────────────
private void refreshCart() {
    cartModel.setRowCount(0);
    double subtotal = 0;
    for (com.mycompany.possystem.model.CartItem item : cart) {
        cartModel.addRow(new Object[]{
            item.getProduct().getName(),
            String.format("₱%.2f", item.getProduct().getPrice()),
            item.getQuantity(),
            String.format("₱%.2f", item.getSubtotal())
        });
        subtotal += item.getSubtotal();
    }
    double tax   = subtotal * 0.12;
    double total = subtotal + tax;
    lblSubtotalVal.setText(String.format("₱%.2f", subtotal));
    lblTaxVal.setText(String.format("₱%.2f", tax));
    lblTotalVal.setText(String.format("₱%.2f", total));
    updateChange();
}

// ── Update change amount ──────────────────────────────────────────────
private void updateChange() {
    try {
        double tendered = Double.parseDouble(txtTendered.getText().trim());
        double total    = Double.parseDouble(
            lblTotalVal.getText().replace("₱", "").replace(",", ""));
        double change   = tendered - total;
        lblChangeVal.setText(String.format("₱%.2f", change));
        lblChangeVal.setForeground(change >= 0 ? AppTheme.SUCCESS : AppTheme.DANGER);
    } catch (NumberFormatException e) {
        lblChangeVal.setText("₱0.00");
    }
}

// ── Process checkout ──────────────────────────────────────────────────
private void processCheckout() {
    if (cart.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Cart is empty!", "Error",
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    try {
        double tendered = Double.parseDouble(txtTendered.getText().trim());
        double total    = Double.parseDouble(
            lblTotalVal.getText().replace("₱", "").replace(",", ""));
        if (tendered < total) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Insufficient amount tendered!", "Error",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.sql.Connection conn =
            com.mycompany.possystem.db.DatabaseConnection.getConnection();
        conn.setAutoCommit(false);
        try {
            com.mycompany.possystem.dao.SaleDAO saleDAO =
                new com.mycompany.possystem.dao.SaleDAO();
            int saleId = saleDAO.createSale(
                cart,
                com.mycompany.possystem.util.SessionManager.getCurrentUser().getId(),
                cboPayment.getSelectedItem().toString(),
                tendered,
                conn
            );
            conn.commit();
            javax.swing.JOptionPane.showMessageDialog(this,
                "Sale #" + saleId + " completed!\nChange: ₱" +
                String.format("%.2f", tendered - total),
                "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            cart.clear();
            refreshCart();
            txtTendered.setText("");
            loadAllProducts(); // refresh stock
        } catch (Exception ex) {
            conn.rollback();
            javax.swing.JOptionPane.showMessageDialog(this,
                "Sale failed: " + ex.getMessage(), "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        } finally {
            conn.setAutoCommit(true);
        }
    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Please enter a valid amount.", "Error",
            javax.swing.JOptionPane.WARNING_MESSAGE);
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        scrollProducts = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        btnAddToCart = new javax.swing.JButton();
        centerPanel = new javax.swing.JPanel();
        scrollCart = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        btnClearCart = new javax.swing.JButton();
        centerPanel1 = new javax.swing.JPanel();
        lblSubtotalVal = new javax.swing.JLabel();
        lblTaxVal = new javax.swing.JLabel();
        lblTotalVal = new javax.swing.JLabel();
        cboPayment = new javax.swing.JComboBox<>();
        txtTendered = new javax.swing.JTextField();
        lblChangeVal = new javax.swing.JLabel();
        btnCheckout = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtSearch.addActionListener(this::txtSearchActionPerformed);

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollProducts.setViewportView(tblProducts);

        btnAddToCart.setText("jButton1");

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(scrollProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnAddToCart))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddToCart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollCart.setViewportView(tblCart);

        btnRemove.setText("jButton1");

        btnClearCart.setText("jButton1");

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(scrollCart, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClearCart)
                            .addComponent(btnRemove))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(scrollCart, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClearCart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblSubtotalVal.setText("jLabel1");

        lblTaxVal.setText("jLabel1");

        lblTotalVal.setText("jLabel1");

        cboPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblChangeVal.setText("jLabel1");

        btnCheckout.setText("jButton1");

        btnDashboard.setText("jButton1");

        javax.swing.GroupLayout centerPanel1Layout = new javax.swing.GroupLayout(centerPanel1);
        centerPanel1.setLayout(centerPanel1Layout);
        centerPanel1Layout.setHorizontalGroup(
            centerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanel1Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(centerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTendered, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(centerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanel1Layout.createSequentialGroup()
                                .addGroup(centerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTotalVal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTaxVal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSubtotalVal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanel1Layout.createSequentialGroup()
                                .addComponent(cboPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)))
                        .addComponent(btnCheckout)
                        .addComponent(btnDashboard))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanel1Layout.createSequentialGroup()
                        .addComponent(lblChangeVal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        centerPanel1Layout.setVerticalGroup(
            centerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblSubtotalVal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTaxVal)
                .addGap(18, 18, 18)
                .addComponent(lblTotalVal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTendered, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblChangeVal)
                .addGap(18, 18, 18)
                .addComponent(btnCheckout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDashboard)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(centerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(centerPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new POSFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnClearCart;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox<String> cboPayment;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel centerPanel1;
    private javax.swing.JLabel lblChangeVal;
    private javax.swing.JLabel lblSubtotalVal;
    private javax.swing.JLabel lblTaxVal;
    private javax.swing.JLabel lblTotalVal;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JScrollPane scrollCart;
    private javax.swing.JScrollPane scrollProducts;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTendered;
    // End of variables declaration//GEN-END:variables
}
