


package com.swcguild.capstoneproject.model;


public class User {

    private String username;
    private String password;
    private boolean active;
    private boolean admin;
    
    public User() {
        
    }
    
    public User(String name, String pass, boolean active, boolean admin) {
        this.active = active;
        this.admin = admin;
        username = name;
        password = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
}
