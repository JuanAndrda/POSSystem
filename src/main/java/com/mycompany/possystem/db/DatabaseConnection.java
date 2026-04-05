package com.mycompany.possystem.db;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * DatabaseConnection — Singleton pattern.
 * Only one connection is created for the whole app.
 * Change DB_NAME, USER, or PASSWORD here if your setup differs.
 */
public class DatabaseConnection {
 
    // ── Connection settings ─────────────────────────────────────────────
    private static final String HOST     = "localhost";
    private static final String PORT     = "3307";      // slave/master setup uses 3307
    private static final String DB_NAME  = "pos_system";
    private static final String USER     = "root";      // XAMPP default
    private static final String PASSWORD = "";           // XAMPP default (blank)
 
    private static final String URL =
        "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
        + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Manila";
 
    // ── Singleton instance ──────────────────────────────────────────────
    private static Connection instance = null;
 
    /**
     * Returns the shared database connection.
     * Creates a new one only if it does not exist yet or was closed.
     */
    public static Connection getConnection() throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return instance;
    }
 
    /** Call this when the app closes to release the connection. */
    public static void close() {
        try {
            if (instance != null && !instance.isClosed()) {
                instance.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
