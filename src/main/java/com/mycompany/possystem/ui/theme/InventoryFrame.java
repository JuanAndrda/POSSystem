/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.possystem.ui.theme;

/**
 *
 * @author juanr
 */
public class InventoryFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InventoryFrame.class.getName());
    private java.util.List<com.mycompany.possystem.model.Product> allProducts =
    new java.util.ArrayList<>();
    /**
     * Creates new form InventoryFrame
     */
    public InventoryFrame() {
    initComponents();

    // ── Window settings ──────────────────────────────────────────────
    setTitle("RetailPro POS — Inventory");
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // ── Backgrounds ──────────────────────────────────────────────────
    getContentPane().setBackground(AppTheme.BG_DARK);
    topBar.setBackground(AppTheme.BG_PANEL);
    topBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));

    // ── Toolbar buttons ──────────────────────────────────────────────
    AppTheme.stylePrimaryButton(btnAdd,      "+ Add Product", AppTheme.SUCCESS);
    AppTheme.stylePrimaryButton(btnEdit,     "Edit",          AppTheme.ACCENT_BTN);
    AppTheme.stylePrimaryButton(btnDelete,   "Delete",        AppTheme.DANGER);
    AppTheme.stylePrimaryButton(btnRefresh,  "Refresh",       AppTheme.BG_HOVER);
    AppTheme.stylePrimaryButton(btnDashboard,"Back",          AppTheme.BG_HOVER);

    // ── Filter field ─────────────────────────────────────────────────
    AppTheme.styleField(txtFilter);
    txtFilter.setPreferredSize(new java.awt.Dimension(200, 34));

    // ── Fix layout ───────────────────────────────────────────────────
    getContentPane().setLayout(new java.awt.BorderLayout(0, 0));
    getContentPane().add(topBar,           java.awt.BorderLayout.NORTH);
    // ── Wrap table in padded container ───────────────────────────────
    javax.swing.JPanel tableContainer = new javax.swing.JPanel(
        new java.awt.BorderLayout());
    tableContainer.setBackground(AppTheme.BG_DARK);
    tableContainer.setBorder(javax.swing.BorderFactory
        .createEmptyBorder(16, 20, 20, 20));
    tableContainer.add(scrollInventory, java.awt.BorderLayout.CENTER);
    getContentPane().add(scrollInventory,  java.awt.BorderLayout.CENTER);

    // Fix topBar layout
    topBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
    topBar.setBorder(javax.swing.BorderFactory.createMatteBorder(
        0, 0, 2, 0, AppTheme.BORDER));
    topBar.setPreferredSize(new java.awt.Dimension(0, 64));

    // Add a title label to the left
    javax.swing.JLabel lblTitle = new javax.swing.JLabel("Inventory Management");
    lblTitle.setFont(AppTheme.FONT_HEADING);
    lblTitle.setForeground(AppTheme.TEXT_PRI);
    lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 20));

    // Re-add all components in correct order
    topBar.removeAll();
    topBar.add(lblTitle);
    topBar.add(btnAdd);
    topBar.add(btnEdit);
    topBar.add(btnDelete);
    topBar.add(btnRefresh);
    topBar.add(btnDashboard);
    topBar.add(txtFilter);

    // ── Inventory table ──────────────────────────────────────────────
    tblInventory.setModel(new javax.swing.table.DefaultTableModel(
        new String[]{"ID", "Name", "Category", "Price", "Cost", "Stock", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    });
    AppTheme.styleTable(tblInventory);
    // ── Column widths ─────────────────────────────────────────────────
    tblInventory.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
    tblInventory.getColumnModel().getColumn(1).setPreferredWidth(220);  // Name
    tblInventory.getColumnModel().getColumn(2).setPreferredWidth(120);  // Category
    tblInventory.getColumnModel().getColumn(3).setPreferredWidth(110);  // Price
    tblInventory.getColumnModel().getColumn(4).setPreferredWidth(110);  // Cost
    tblInventory.getColumnModel().getColumn(5).setPreferredWidth(70);   // Stock
    tblInventory.getColumnModel().getColumn(6).setPreferredWidth(90);   // Status

    // ── Add padding around the table ─────────────────────────────────
    scrollInventory.setBorder(javax.swing.BorderFactory.createEmptyBorder(
        12, 16, 16, 16));
    getContentPane().setBackground(AppTheme.BG_DARK);

    // ── Taller row height for modern look ─────────────────────────────
    tblInventory.setRowHeight(42);

    // ── Hide the ID column (not needed for users to see) ──────────────
    tblInventory.getColumnModel().getColumn(0)
        .setMinWidth(0);
    tblInventory.getColumnModel().getColumn(0)
        .setMaxWidth(0);
    tblInventory.getColumnModel().getColumn(0)
        .setWidth(0);
    scrollInventory.getViewport().setBackground(AppTheme.BG_CARD);
    scrollInventory.setBorder(javax.swing.BorderFactory.createEmptyBorder(
    16, 20, 20, 20));

    // Color code rows by stock level
    tblInventory.setDefaultRenderer(Object.class,
    new javax.swing.table.DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(
                javax.swing.JTable t, Object val, boolean sel,
                boolean foc, int row, int col) {
            super.getTableCellRendererComponent(t, val, sel, foc, row, col);
            if (!sel) {
                try {
                    int stock   = Integer.parseInt(
                        t.getValueAt(row, 5).toString());
                    String status = t.getValueAt(row, 6).toString();
                    if (stock == 0) {
                        setBackground(new java.awt.Color(0x3B0011));
                        setForeground(AppTheme.DANGER);
                    } else if ("Low Stock".equals(status)) {
                        // ← uses real threshold from DB now
                        setBackground(new java.awt.Color(0x2A1800));
                        setForeground(AppTheme.WARNING);
                    } else {
                        setBackground(row % 2 == 0
                            ? AppTheme.BG_CARD : AppTheme.BG_PANEL);
                        setForeground(AppTheme.TEXT_PRI);
                    }
                } catch (Exception e) {
                    setBackground(AppTheme.BG_CARD);
                    setForeground(AppTheme.TEXT_PRI);
                }
            }
            setBorder(javax.swing.BorderFactory
                .createEmptyBorder(0, 8, 0, 8));
            return this;
        }
    });

    // ── Wire up events ───────────────────────────────────────────────
    // Live filter as user types
    txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {
            filterTable();
        }
    });

    btnRefresh.addActionListener(e -> loadProducts());

    btnAdd.addActionListener(e -> showAddDialog());

    btnDelete.addActionListener(e -> deleteSelected());

    btnEdit.addActionListener(e -> showEditDialog());
    
    btnDashboard.addActionListener(e -> {
    new DashboardFrame().setVisible(true);
    this.dispose();
});

    // ── Load data ────────────────────────────────────────────────────
    loadProducts();
}

// ── Load all products into table ──────────────────────────────────────
private void loadProducts() {
    javax.swing.table.DefaultTableModel model =
        (javax.swing.table.DefaultTableModel) tblInventory.getModel();
    model.setRowCount(0);
    allProducts = new com.mycompany.possystem.dao.ProductDAO().getAllProducts();
    for (com.mycompany.possystem.model.Product p : allProducts) {
        model.addRow(new Object[]{
            p.getId(),
            p.getName(),
            p.getCategoryName(),
            String.format("₱%.2f", p.getPrice()),
            String.format("₱%.2f", p.getCost()),
            p.getStock(),
            p.isLowStock() ? "Low Stock" : "OK"
        });
    }
}

// ── Filter table by search text ───────────────────────────────────────
private void filterTable() {
    String query = txtFilter.getText().trim().toLowerCase();
    javax.swing.table.DefaultTableModel model =
        (javax.swing.table.DefaultTableModel) tblInventory.getModel();
    model.setRowCount(0);
    for (com.mycompany.possystem.model.Product p : allProducts) {
        if (p.getName().toLowerCase().contains(query)
                || p.getCategoryName().toLowerCase().contains(query)) {
            model.addRow(new Object[]{
                p.getId(),
                p.getName(),
                p.getCategoryName(),
                String.format("₱%.2f", p.getPrice()),
                String.format("₱%.2f", p.getCost()),
                p.getStock(),
                p.isLowStock() ? "Low Stock" : "OK"
            });
        }
    }
}

// ── Show Add Product dialog ───────────────────────────────────────────
private void showAddDialog() {
    // Simple input dialog for adding a product
    javax.swing.JTextField fName     = new javax.swing.JTextField();
    javax.swing.JTextField fPrice    = new javax.swing.JTextField();
    javax.swing.JTextField fCost     = new javax.swing.JTextField();
    javax.swing.JTextField fStock    = new javax.swing.JTextField();
    javax.swing.JTextField fBarcode  = new javax.swing.JTextField();
    
    for (javax.swing.JTextField f : new javax.swing.JTextField[]{
        fName, fPrice, fCost, fStock, fBarcode}) {
    f.setBackground(java.awt.Color.WHITE);
    f.setForeground(java.awt.Color.BLACK);
    f.setCaretColor(java.awt.Color.BLACK);
    f.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(
            new java.awt.Color(0x0088AA), 1),
        javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 8)
    ));
    f.setPreferredSize(new java.awt.Dimension(220, 30));
}

    // Category dropdown
    java.util.List<com.mycompany.possystem.model.Category> cats =
        new com.mycompany.possystem.dao.ProductDAO().getAllCategories();
    javax.swing.JComboBox<com.mycompany.possystem.model.Category> cboCat =
        new javax.swing.JComboBox<>(cats.toArray(
            new com.mycompany.possystem.model.Category[0]));
    
    cboCat.setRenderer(new javax.swing.DefaultListCellRenderer() {
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

    Object[] fields = {
        "Name:",     fName,
        "Category:", cboCat,
        "Price:",    fPrice,
        "Cost:",     fCost,
        "Stock:",    fStock,
        "Barcode:",  fBarcode
    };

    int result = javax.swing.JOptionPane.showConfirmDialog(
        this, fields, "Add New Product",
        javax.swing.JOptionPane.OK_CANCEL_OPTION);

    if (result == javax.swing.JOptionPane.OK_OPTION) {
        try {
            com.mycompany.possystem.model.Category selectedCat =
                (com.mycompany.possystem.model.Category) cboCat.getSelectedItem();
            com.mycompany.possystem.model.Product p =
                new com.mycompany.possystem.model.Product(
                    0,
                    fName.getText().trim(),
                    selectedCat.getId(),
                    Double.parseDouble(fPrice.getText().trim()),
                    Double.parseDouble(fCost.getText().trim()),
                    Integer.parseInt(fStock.getText().trim()),
                    5,
                    fBarcode.getText().trim()
                );
            int newId = new com.mycompany.possystem.dao.ProductDAO().addProduct(p);
            if (newId > 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Product added successfully!");
                loadProducts();
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please enter valid numbers for Price, Cost, and Stock.");
        }
    }
}

// ── Show Edit Product dialog ──────────────────────────────────────────
private void showEditDialog() {
    // First check if a row is selected
    int row = tblInventory.getSelectedRow();
    if (row < 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Please select a product to edit.");
        return;
    }

    // Get the selected product
    com.mycompany.possystem.model.Product p = allProducts.get(row);

    // Create fields with existing values pre-filled
    javax.swing.JTextField fName  = new javax.swing.JTextField(p.getName());
    javax.swing.JTextField fPrice = new javax.swing.JTextField(
        String.valueOf(p.getPrice()));
    javax.swing.JTextField fCost  = new javax.swing.JTextField(
        String.valueOf(p.getCost()));
    javax.swing.JTextField fStock = new javax.swing.JTextField(
        String.valueOf(p.getStock()));

    // Style all fields
    for (javax.swing.JTextField f : new javax.swing.JTextField[]{
            fName, fPrice, fCost, fStock}) {
        f.setBackground(java.awt.Color.WHITE);
        f.setForeground(java.awt.Color.BLACK);
        f.setCaretColor(java.awt.Color.BLACK);
        f.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0x0088AA), 1),
            javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        f.setPreferredSize(new java.awt.Dimension(220, 30));
    }

    Object[] fields = {
        "Name:",  fName,
        "Price:", fPrice,
        "Cost:",  fCost,
        "Stock:", fStock
    };

    int result = javax.swing.JOptionPane.showConfirmDialog(
        this, fields, "Edit Product",
        javax.swing.JOptionPane.OK_CANCEL_OPTION);

    if (result == javax.swing.JOptionPane.OK_OPTION) {
        try {
            com.mycompany.possystem.model.Product updated =
                new com.mycompany.possystem.model.Product(
                    p.getId(),
                    fName.getText().trim(),
                    p.getCategoryId(),
                    Double.parseDouble(fPrice.getText().trim()),
                    Double.parseDouble(fCost.getText().trim()),
                    Integer.parseInt(fStock.getText().trim()),
                    p.getLowStockThreshold(),
                    p.getBarcode()
                );
            if (new com.mycompany.possystem.dao.ProductDAO()
                    .updateProduct(updated)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Product updated successfully!");
                loadProducts();
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please enter valid numbers.");
        }
    }
}

// ── Delete selected product ───────────────────────────────────────────
private void deleteSelected() {
    int row = tblInventory.getSelectedRow();
    if (row < 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Please select a product to delete.");
        return;
    }
    com.mycompany.possystem.model.Product p = allProducts.get(row);
    int confirm = javax.swing.JOptionPane.showConfirmDialog(
        this, "Delete \"" + p.getName() + "\"?",
        "Confirm Delete", javax.swing.JOptionPane.YES_NO_OPTION);
    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        if (new com.mycompany.possystem.dao.ProductDAO()
                .deleteProduct(p.getId())) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Product deleted.");
            loadProducts();
        }
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

        topBar = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        txtFilter = new javax.swing.JTextField();
        btnDashboard = new javax.swing.JButton();
        scrollInventory = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdd.setText("jButton1");

        btnEdit.setText("jButton1");

        btnDelete.setText("jButton1");

        btnRefresh.setText("jButton1");

        txtFilter.addActionListener(this::txtFilterActionPerformed);

        btnDashboard.setText("jButton1");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnRefresh)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDashboard))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollInventory.setViewportView(tblInventory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollInventory)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollInventory, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFilterActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new InventoryFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane scrollInventory;
    private javax.swing.JTable tblInventory;
    private javax.swing.JPanel topBar;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
