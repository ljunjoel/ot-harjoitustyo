/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.domain;

import collectionhelper.dao.CollectibleDao;
import collectionhelper.dao.UserDao;
import java.util.List;
/**
 *
 * Component for the logic of the application.
 */
public class CollectionHelperService {
    CollectibleDao collectibleDao;
    UserDao userDao;
    String loggedIn="";
    List<String> names;
    List<Collectible> items;
    
    public CollectionHelperService(CollectibleDao collectibleDao, UserDao userDao) {
        this.collectibleDao = collectibleDao;
        this.userDao = userDao;
    }
    
    public boolean createUser(String name, String password) {
        User user = new User(name, password);
        try {
            this.userDao.create(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean login(String username, String password) {
        try {
            User user = this.userDao.findByName(username);
            if((user.getPassword()).equals(password)) {
                this.loggedIn = user.getName();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public List<String> getNames() {
        try {
            names = userDao.getAllNames();
        } catch (Exception e) {
            System.out.println("No names");
        }
        return names;
    }
    
    public void logout() {
        this.loggedIn="";
    }
    
    public String getLoggedIn() {
        return this.loggedIn;
    }
    
    public List<Collectible> searchItems(String search) {
        try {
            items = collectibleDao.search(search, getLoggedIn());
        } catch (Exception e) {
            System.out.println("No items");
        }
        return items;
    }
    
    public boolean createItem(String name, int quantity, String owner) {
        Collectible collectible = new Collectible(name, quantity, owner);
        try {
            this.collectibleDao.create(collectible);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public List<Collectible> getAllItems() {
        try {
            items = collectibleDao.getAll(getLoggedIn());
        } catch (Exception e) {
            System.out.println("Kaikkien hakeminen kusi");
        }
        return items;
    }
    
    public boolean addToItem(Collectible item, int add) {
        try {
            this.collectibleDao.add(item, add);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean reduceFromItem(Collectible item, int reducer) {
        try {
            this.collectibleDao.reduce(item, reducer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean removeItem(Collectible item) {
        try{
            this.collectibleDao.remove(item);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
