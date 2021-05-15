/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.dao;

import collectionhelper.domain.Collectible;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * An implementation of the interface CollectibleDao, specifically designed to interact with the database.
 */
public class DatabaseCollectibleDao implements CollectibleDao {
    List<Collectible> items = new ArrayList<>();
    
    @Override
    public Collectible create(Collectible collectible) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("INSERT INTO Collection(CollectibleName, CollectibleQuantity, CollectibleUser) VALUES (?,?,?)");
            preps.setString(1, collectible.getName());
            preps.setInt(2, collectible.getQuantity());
            preps.setString(3, collectible.getOwner());
            preps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Esineen lisääminen ei onnistunut");
        }
        return collectible;
    }

    @Override
    public Collectible add(Collectible collectible, int add) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("UPDATE Collection SET CollectibleQuantity =? WHERE CollectibleName=? AND CollectibleUser=?");
            preps.setInt(1, collectible.getQuantity() + add);
            preps.setString(2, collectible.getName());
            preps.setString(3, collectible.getOwner());
            preps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Esineeseen lisääminen ei onnistunut");
        }
        Collectible newCollectible = new Collectible(collectible.getName(), collectible.getQuantity() + add, collectible.getOwner());
        return newCollectible;
    }

    @Override
    public Collectible reduce(Collectible collectible, int reducer) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("UPDATE Collection SET CollectibleQuantity =? WHERE CollectibleName=? AND CollectibleUser=?");
            preps.setInt(1, collectible.getQuantity() - reducer);
            preps.setString(2, collectible.getName());
            preps.setString(3, collectible.getOwner());
            preps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Esineestä vähentäminen ei onnistunut");
        }
        Collectible newCollectible = new Collectible(collectible.getName(), collectible.getQuantity() - reducer, collectible.getOwner());
        return newCollectible;
    }

    @Override
    public Collectible remove(Collectible collectible) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("DELETE FROM Collection WHERE CollectibleName=? AND CollectibleUser=?");
            preps.setString(1, collectible.getName());
            preps.setString(2, collectible.getOwner());
            preps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Poistaminen epäonnistui");
        }
        return collectible;
    }

    @Override
    public List<Collectible> search(String search, String username) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("SELECT * FROM Collection WHERE CollectibleName LIKE ? AND CollectibleUser=?;");
            preps.setString(1, "%" + search + "%");
            preps.setString(2, username);
            ResultSet r = preps.executeQuery();
            if (r.next()) {
                String name = r.getString("CollectibleName");
                int quantity = r.getInt("CollectibleQuantity");
                String owner = r.getString("CollectibleUser");
                Collectible item = new Collectible(name, quantity, owner);
                items.add(item);
                while (r.next()) {
                    name = r.getString("CollectibleName");
                    quantity = r.getInt("CollectibleQuantity");
                    owner = r.getString("CollectibleUser");
                    item = new Collectible(name, quantity, owner);
                    items.add(item);
                }
            } 
        } catch (SQLException e) {
            System.out.println("searchItems(): No items?");
        }
        return this.items;
    }

    @Override
    public List<Collectible> getAll(String username) throws Exception {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
            PreparedStatement preps = db.prepareStatement("SELECT * FROM Collection WHERE CollectibleUser=?;");
            preps.setString(1, username);
            ResultSet r = preps.executeQuery();
            if (r.next()) {
                String name = r.getString("CollectibleName");
                int quantity = r.getInt("CollectibleQuantity");
                String owner = r.getString("CollectibleUser");
                Collectible item = new Collectible(name, quantity, owner);
                items.add(item);
                while (r.next()) {
                    name = r.getString("CollectibleName");
                    quantity = r.getInt("CollectibleQuantity");
                    owner = r.getString("CollectibleUser");
                    item = new Collectible(name, quantity, owner);
                    items.add(item);
                }
            } 
        } catch (SQLException e) {
            System.out.println("getAllItems(): No items?");
        }
        return this.items;
    }
}
