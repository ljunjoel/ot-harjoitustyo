/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.dao;

import collectionhelper.domain.User;
import java.util.List;
import java.sql.*;

/**
 *
 * @author joel
 */
public class DatabaseUserDao implements UserDao {
    List<String> names;
    
    @Override
    public User create(User user) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("INSERT INTO Users(Username, UserPassword) VALUES (?,?)");
            preps.setString(1, user.getName());
            preps.setString(2, user.getPassword());
            preps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Käyttäjän lisääminen ei onnistunut");
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
        } catch (SQLException e) {
            System.out.println("Käyttäjää ei löydy");
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
        } catch (SQLException e) {
            System.out.println("getAllNames(): No users?");
        }
        return this.names;
    }
    
}