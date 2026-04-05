package com.mycompany.possystem.dao;

import com.mycompany.possystem.db.DatabaseConnection;
import com.mycompany.possystem.model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /**
     * Checks username + password against the database.
     * Returns the User object if correct, null if wrong.
     */
    public User authenticate(String username, String password) {
    String sql = "SELECT * FROM users WHERE username = ? AND is_active = TRUE";
    try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String storedPassword = rs.getString("password");
            // Check if password is BCrypt hashed or plain text
            boolean valid;
            if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$")) {
                // BCrypt hash — use BCrypt check
                valid = org.mindrot.jbcrypt.BCrypt.checkpw(password, storedPassword);
            } else {
                // Plain text — direct comparison (for admin account)
                valid = password.equals(storedPassword);
            }
            if (valid) return mapRow(rs);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    /**
     * Returns every user in the database.
     * Used by the User Management screen.
     */
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY full_name";
        try (Statement st = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Creates a new user account.
     * The plain password is hashed with BCrypt before saving.
     * Returns true if successful.
     */
    public boolean createUser(String username, String plainPassword,
                              String fullName, String role) {
        // Never store plain passwords — BCrypt turns it into a safe hash
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
        String sql = "INSERT INTO users (username, password, full_name, role) "
                   + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, hashed);
            ps.setString(3, fullName);
            ps.setString(4, role);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing user's details.
     * If newPassword is empty, the password is left unchanged.
     */
    public boolean updateUser(int id, String fullName, String role, String newPassword) {
        try {
            if (newPassword != null && !newPassword.isEmpty()) {
                // Update everything including password
                String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt(10));
                String sql = "UPDATE users SET full_name=?, role=?, password=? WHERE id=?";
                try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                    ps.setString(1, fullName);
                    ps.setString(2, role);
                    ps.setString(3, hashed);
                    ps.setInt(4, id);
                    return ps.executeUpdate() > 0;
                }
            } else {
                // Update without touching the password
                String sql = "UPDATE users SET full_name=?, role=? WHERE id=?";
                try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                    ps.setString(1, fullName);
                    ps.setString(2, role);
                    ps.setInt(3, id);
                    return ps.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deactivates a user — they can no longer log in.
     * The account is NOT deleted so sales history is preserved.
     */
    public boolean deactivateUser(int userId) {
        String sql = "UPDATE users SET is_active = FALSE WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reactivates a previously deactivated user.
     */
    public boolean activateUser(int userId) {
        String sql = "UPDATE users SET is_active = TRUE WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a username is already taken.
     * Call this before createUser to show a friendly error.
     */
    public boolean usernameExists(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Converts a database row into a User object.
     * Called internally whenever we read from the users table.
     */
    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("full_name"),
            rs.getString("role"),
            rs.getBoolean("is_active")
        );
        u.setPassword(rs.getString("password"));
        return u;
    }
}