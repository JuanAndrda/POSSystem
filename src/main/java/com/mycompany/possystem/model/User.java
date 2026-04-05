package com.mycompany.possystem.model;
 
public class User {
    private int id;
    private String username;
    private String password;   // stores the BCrypt hash
    private String fullName;
    private String role;       // "admin" or "cashier"
    private boolean active;
 
    // Constructor
    public User(int id, String username, String fullName, String role, boolean active) {
        this.id       = id;
        this.username = username;
        this.fullName = fullName;
        this.role     = role;
        this.active   = active;
    }
 
    // Getters
    public int     getId()       { return id; }
    public String  getUsername() { return username; }
    public String  getFullName() { return fullName; }
    public String  getRole()     { return role; }
    public boolean isActive()    { return active; }
    public String  getPassword() { return password; }
    public void    setPassword(String password) { this.password = password; }
}
