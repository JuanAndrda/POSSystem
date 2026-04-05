package com.mycompany.possystem.util;
 
import com.mycompany.possystem.model.User;
 
/**
 * SessionManager — stores the currently logged-in user.
 * Call SessionManager.login(user) after successful authentication.
 * Call SessionManager.getCurrentUser() anywhere to get their info.
 */
public class SessionManager {
 
    private static User currentUser = null;
 
    public static void login(User user) {
        currentUser = user;
    }
 
    public static void logout() {
        currentUser = null;
    }
 
    public static User getCurrentUser() {
        return currentUser;
    }
 
    public static boolean isAdmin() {
        return currentUser != null && "admin".equals(currentUser.getRole());
    }
}
