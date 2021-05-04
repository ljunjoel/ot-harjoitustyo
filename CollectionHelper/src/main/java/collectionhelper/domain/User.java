/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.domain;

import java.util.HashMap;
import java.util.Map;
/**
 * Mock user database until the real one is implemented.
 * 
 */
public class User {
    Map<String, String> users = new HashMap<>();
    /**
     * 
     * @param username
     * @param password
     * @return Returns whether name was unique and - therefore - was added
     */
    public boolean addUser(String username, String password) {
        if (this.users.keySet().contains(username)) {
            return false;
        }
        this.users.put(username, password);
        return true;
    }
    /**
     * 
     * @param username
     * @return Returns whether a user by the given name exists
     */
    public boolean getUser(String username) {
        if (!this.users.keySet().contains(username)) {
            return false;
        }
        return true;
    }
    /**
     * 
     * @return Gives a list of usernames
     */
    public String getUsers() {
        return this.users.keySet().toString();
    }
    /**
     * 
     * @param username
     * @return Returns the password of said user
     */
    public String getPassword(String username) {
        return this.users.get(username);
    }
}
