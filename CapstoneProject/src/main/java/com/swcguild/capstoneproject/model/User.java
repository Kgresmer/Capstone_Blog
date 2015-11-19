


package com.swcguild.capstoneproject.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class User {

    private int userID;
    @NotEmpty(message = "You must supply a value for username.")
    @NotNull(message = "You must supply a value for password.")
    @Length(max = 50, message = "Username must be no more than 50 characters in length.")
    private String username;
    
    @NotEmpty(message = "You must supply a value for password.")
    @NotNull(message = "You must supply a value for password.")
    @Length(max = 50, message = "password must be no more than 50 characters in length.")
    private String password;
    private boolean active;
    private boolean admin;
    
    public User() {
        
    }
    
    public User(String name, String pass, boolean active) {
        this.active = active;
        username = name;
        password = pass;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
