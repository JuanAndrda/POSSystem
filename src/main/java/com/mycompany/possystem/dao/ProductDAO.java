package com.mycompany.possystem.dao;

import com.mycompany.possystem.db.DatabaseConnection;
import com.mycompany.possystem.model.Category;
import com.mycompany.possystem.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    /**
     * Returns all active products joined with their category name.
     * Used by the Inventory screen and POS search.
     */
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = """
                     SELECT p.*, c.name AS category_name
                     FROM products p
                     LEFT JOIN categories c ON p.category_id = c.id
                     WHERE p.is_active = TRUE
                     ORDER BY p.name
                     """;
        try (Statement st = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Product p = mapRow(rs);
                p.setCategoryName(rs.getString("category_name"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Searches products by name or barcode.
     * Used in the POS screen search bar.
     */
    public List<Product> search(String query) {
        List<Product> list = new ArrayList<>();
        String sql = """
                     SELECT p.*, c.name AS category_name
                     FROM products p
                     LEFT JOIN categories c ON p.category_id = c.id
                     WHERE p.is_active = TRUE
                     AND (p.name LIKE ? OR p.barcode = ?)
                     ORDER BY p.name LIMIT 50
                     """;
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + query + "%");
            ps.setString(2, query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = mapRow(rs);
                p.setCategoryName(rs.getString("category_name"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Returns products that are at or below their low stock threshold.
     * Used by the Dashboard for alerts.
     */
    public List<Product> getLowStockProducts() {
        List<Product> list = new ArrayList<>();
        String sql = """
                     SELECT p.*, c.name AS category_name
                     FROM products p
                     LEFT JOIN categories c ON p.category_id = c.id
                     WHERE p.is_active = TRUE
                     AND p.stock <= p.low_stock_threshold
                     """;
        try (Statement st = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Product p = mapRow(rs);
                p.setCategoryName(rs.getString("category_name"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Returns all categories.
     * Used by dropdowns in the Inventory and POS screens.
     */
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories ORDER BY name";
        try (Statement st = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Inserts a new product into the database.
     * Returns the generated ID, or -1 if it failed.
     */
    public int addProduct(Product product) {
        String sql = """
                     INSERT INTO products
                     (name, category_id, price, cost, stock, low_stock_threshold, barcode)
                     VALUES (?, ?, ?, ?, ?, ?, ?)
                     """;
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setInt   (2, product.getCategoryId());
            ps.setDouble(3, product.getPrice());
            ps.setDouble(4, product.getCost());
            ps.setInt   (5, product.getStock());
            ps.setInt   (6, product.getLowStockThreshold());
            ps.setString(7, product.getBarcode());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Updates an existing product's details.
     * Returns true if successful.
     */
    public boolean updateProduct(Product product) {
        String sql = """
                     UPDATE products
                     SET name=?, category_id=?, price=?, cost=?,
                         stock=?, low_stock_threshold=?, barcode=?
                     WHERE id=?
                     """;
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setInt   (2, product.getCategoryId());
            ps.setDouble(3, product.getPrice());
            ps.setDouble(4, product.getCost());
            ps.setInt   (5, product.getStock());
            ps.setInt   (6, product.getLowStockThreshold());
            ps.setString(7, product.getBarcode());
            ps.setInt   (8, product.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Soft deletes a product — marks it inactive instead of removing it.
     * This preserves historical sales data that reference this product.
     */
    public boolean deleteProduct(int productId) {
        String sql = "UPDATE products SET is_active = FALSE WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reduces stock after a sale is processed.
     * Must be called inside a transaction (conn is passed in).
     * Returns false if there is not enough stock.
     */
    public boolean decrementStock(int productId, int quantity, Connection conn)
            throws SQLException {
        String sql = """
                     UPDATE products
                     SET stock = stock - ?
                     WHERE id = ? AND stock >= ?
                     """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, quantity);
        ps.setInt(2, productId);
        ps.setInt(3, quantity);
        return ps.executeUpdate() > 0;
    }

    /**
     * Converts a database row into a Product object.
     */
    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt   ("id"),
            rs.getString("name"),
            rs.getInt   ("category_id"),
            rs.getDouble("price"),
            rs.getDouble("cost"),
            rs.getInt   ("stock"),
            rs.getInt   ("low_stock_threshold"),
            rs.getString("barcode")
        );
    }
}