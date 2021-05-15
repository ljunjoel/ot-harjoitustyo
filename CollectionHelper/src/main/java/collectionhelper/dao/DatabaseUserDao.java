/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.dao;

import collectionhelper.domain.User;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class DatabaseUserDao implements UserDao {
    List<String> names = new ArrayList<>();
    
    @Override
    public User create(User user) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("INSERT INTO Users(Username, UserPassword) VALUES (?,?)");
            preps.setString(1, user.getName());
            preps.setString(2, user.getPassword());
            preps.executeUpdate();
        } catch (Exception e) {
            System.out.println("User creation failed");
        }
        
        return user;
    }

    @Override
    public User findByName(String username) throws Exception {
        User user = new User("", "");
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {      
            PreparedStatement preps = db.prepareStatement("SELECT * FROM Users WHERE Username=?");
            preps.setString(1, username);
            ResultSet r = preps.executeQuery();
            if (r.next()) {
                String name = r.getString("Username");
                String password = r.getString("UserPassword");
                user = new User(name, password);
            }
        } catch (Exception e) {
            System.out.println("Finding failed");
        }
        return user;
    }

    @Override
    public List<String> getAllNames() throws SQLException {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("SELECT Username FROM Users");
            ResultSet r = preps.executeQuery();
            if(r.next()) {
                String name = r.getString("Username");
                names.add(name);
                while(r.next()) {
                name = r.getString("Username");
                names.add(name);
                }
            } 
        } catch (Exception e) {
            System.out.println("Getting all names failed");
        }
        
        return this.names;
    }
    
}