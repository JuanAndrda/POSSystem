package com.mycompany.possystem.dao;

import com.mycompany.possystem.db.DatabaseConnection;
import com.mycompany.possystem.model.CartItem;
import com.mycompany.possystem.model.Sale;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    /**
     * Saves a completed sale to the database.
     * Uses a transaction — if anything fails, nothing is saved.
     * Returns the new sale ID, or -1 if it failed.
     */
    public int createSale(List<CartItem> cart, int cashierId,
                          String paymentMethod, double amountTendered,
                          Connection conn) throws SQLException {

        // Step 1: Insert the sale header
        String saleSql = """
                         INSERT INTO sales
                         (cashier_id, total_amount, discount, tax_amount,
                          payment_method, amount_tendered, change_due)
                         VALUES (?, ?, ?, ?, ?, ?, ?)
                         """;

        double subtotal = cart.stream()
                              .mapToDouble(CartItem::getSubtotal)
                              .sum();
        double tax      = subtotal * 0.12;
        double total    = subtotal + tax;
        double change   = amountTendered - total;

        int saleId = -1;

        PreparedStatement salePs = conn.prepareStatement(
                saleSql, Statement.RETURN_GENERATED_KEYS);
        salePs.setInt   (1, cashierId);
        salePs.setDouble(2, total);
        salePs.setDouble(3, 0.00);       // discount (can expand later)
        salePs.setDouble(4, tax);
        salePs.setString(5, paymentMethod);
        salePs.setDouble(6, amountTendered);
        salePs.setDouble(7, change);
        salePs.executeUpdate();

        // Get the generated sale ID
        ResultSet keys = salePs.getGeneratedKeys();
        if (keys.next()) {
            saleId = keys.getInt(1);
        }

        if (saleId == -1) {
            throw new SQLException("Failed to create sale record.");
        }

        // Step 2: Insert each item in the cart as a sale_item row
        String itemSql = """
                         INSERT INTO sale_items
                         (sale_id, product_id, quantity, unit_price, subtotal)
                         VALUES (?, ?, ?, ?, ?)
                         """;

        ProductDAO productDAO = new ProductDAO();

        for (CartItem item : cart) {
            // Insert the sale item
            PreparedStatement itemPs = conn.prepareStatement(itemSql);
            itemPs.setInt   (1, saleId);
            itemPs.setInt   (2, item.getProduct().getId());
            itemPs.setInt   (3, item.getQuantity());
            itemPs.setDouble(4, item.getProduct().getPrice());
            itemPs.setDouble(5, item.getSubtotal());
            itemPs.executeUpdate();

            // Reduce stock for this product
            boolean stockUpdated = productDAO.decrementStock(
                    item.getProduct().getId(),
                    item.getQuantity(),
                    conn);

            if (!stockUpdated) {
                throw new SQLException(
                    "Not enough stock for: " + item.getProduct().getName());
            }
        }

        return saleId;
    }

    /**
     * Returns all sales with cashier name joined.
     * Used by the Reports screen.
     */
    public List<Sale> getAllSales() {
        List<Sale> list = new ArrayList<>();
        String sql = """
                     SELECT s.*, u.full_name AS cashier_name
                     FROM sales s
                     LEFT JOIN users u ON s.cashier_id = u.id
                     ORDER BY s.sale_date DESC
                     """;
        try (Statement st = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Sale sale = mapRow(rs);
                sale.setCashierName(rs.getString("cashier_name"));
                list.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Returns sales filtered by date range.
     * Used by the Reports screen date filter.
     */
    public List<Sale> getSalesByDateRange(LocalDateTime from, LocalDateTime to) {
        List<Sale> list = new ArrayList<>();
        String sql = """
                     SELECT s.*, u.full_name AS cashier_name
                     FROM sales s
                     LEFT JOIN users u ON s.cashier_id = u.id
                     WHERE s.sale_date BETWEEN ? AND ?
                     ORDER BY s.sale_date DESC
                     """;
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(from));
            ps.setTimestamp(2, Timestamp.valueOf(to));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sale sale = mapRow(rs);
                sale.setCashierName(rs.getString("cashier_name"));
                list.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Returns today's total sales amount.
     * Used by the Dashboard stat card.
     */
    public double getTodaysTotalSales() {
        String sql = """
                     SELECT COALESCE(SUM(total_amount), 0) AS total
                     FROM sales
                     WHERE DATE(sale_date) = CURDATE()
                     """;
        try (Statement st = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Returns today's total number of transactions.
     * Used by the Dashboard stat card.
     */
    public int getTodaysTransactionCount() {
        String sql = """
                     SELECT COUNT(*) AS total
                     FROM sales
                     WHERE DATE(sale_date) = CURDATE()
                     """;
        try (Statement st = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Returns the most recent 10 sales.
     * Used by the Dashboard recent transactions table.
     */
    public List<Sale> getRecentSales(int limit) {
        List<Sale> list = new ArrayList<>();
        String sql = """
                     SELECT s.*, u.full_name AS cashier_name
                     FROM sales s
                     LEFT JOIN users u ON s.cashier_id = u.id
                     ORDER BY s.sale_date DESC
                     LIMIT ?
                     """;
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sale sale = mapRow(rs);
                sale.setCashierName(rs.getString("cashier_name"));
                list.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Converts a database row into a Sale object.
     */
    private Sale mapRow(ResultSet rs) throws SQLException {
        return new Sale(
            rs.getInt      ("id"),
            rs.getInt      ("cashier_id"),
            rs.getDouble   ("total_amount"),
            rs.getDouble   ("discount"),
            rs.getDouble   ("tax_amount"),
            rs.getString   ("payment_method"),
            rs.getDouble   ("amount_tendered"),
            rs.getDouble   ("change_due"),
            rs.getTimestamp("sale_date").toLocalDateTime()
        );
    }
}