/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.domain;

import java.util.HashMap;
import java.util.Map;
/**
 * Class representing a user.
 * 
 */
public class User {
    String username;
    String password;
    /**
     * A constructor with two parameters.
     * @param username The username you would like to use.
     * @param password The password you would like to use.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setName(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
