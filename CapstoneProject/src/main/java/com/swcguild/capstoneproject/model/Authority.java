/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.model;

/**
 *
 * @author apprentice
 */
public class Authority {
    
    private String username;
    private String authority;

    public Authority(){}
    public Authority(String username, String authority){
        this.username = username;
        this.authority = authority;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
