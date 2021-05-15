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
    String loggedIn = "";
    List<String> names;
    List<Collectible> items;
    /**
     * A constructor with parameters.
     * @param collectibleDao An implementation of the CollectibleDao interface.
     * @param userDao  An implementation of the UserDao interface.
     */
    public CollectionHelperService(CollectibleDao collectibleDao, UserDao userDao) {
        this.collectibleDao = collectibleDao;
        this.userDao = userDao;
    }
    /**
     * A method for creating a user using the UserDao implementation.
     * @param name Username for the user.
     * @param password Password for the user.
     * @return Returns true if successful and false if something went wrong.
     */
    public boolean createUser(String name, String password) {
        User user = new User(name, password);
        User newUser = new User("", "");
        try {
            newUser = this.userDao.create(user);
        } catch (Exception e) {
            return false;
        }
        if (newUser.getName().isBlank()) {
            return false;
        }
        return true;
    }
    /**
     * A method for logging in using the UserDao implementation.
     * @param username Username of the user you're trying to log in.
     * @param password Password of the user you're trying to log in.
     * @return Returns true if credentials are correct, otherwise false.
     */
    public boolean login(String username, String password) {
        try {
            User user = this.userDao.findByName(username);
            if ((user.getPassword()).equals(password)) {
                this.loggedIn = user.getName();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    /**
     * Getter to fetch all usernames in the database.
     * @return Returns a list of the usernames in the database.
     */
    public List<String> getNames() {
        try {
            names = userDao.getAllNames();
        } catch (Exception e) {
            System.out.println("No names");
        }
        return names;
    }
    /**
     * Method for logging out. Makes the loggedIn attribute blank.
     */
    public void logout() {
        this.loggedIn = "";
    }
    
    public String getLoggedIn() {
        return this.loggedIn;
    }
    /**
     * Method for doing a search in the database.
     * @param search Search criteria
     * @return Returns a list of Collectibles that matched the criteria
     */
    public List<Collectible> searchItems(String search) {
        try {
            items = collectibleDao.search(search, getLoggedIn());
        } catch (Exception e) {
            System.out.println("No items");
        }
        return items;
    }
    /**
     * Method for creating a Collectible in the database.
     * @param name The name you want the item to have.
     * @param quantity The amount one has of it.
     * @param owner Username of whoever owns it
     * @return Returns true if successful, otherwise false.
     */
    public boolean createItem(String name, int quantity, String owner) {
        Collectible collectible = new Collectible(name, quantity, owner);
        try {
            this.collectibleDao.create(collectible);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * Method for fetching all items the currently logged in user has.
     * @return Returns a list of Collectibles that are owned by the user currently logged in.
     */
    public List<Collectible> getAllItems() {
        try {
            items = collectibleDao.getAll(getLoggedIn());
        } catch (Exception e) {
            System.out.println("Kaikkien hakeminen kusi");
        }
        return items;
    }
    /**
     * Method for adding an amount to an already existing item in the database.
     * @param item The Collectible form of the pre-existing item.
     * @param add How many of the item you'd want to add to the database.
     * @return Returns true if the adding was successful, otherwise false.
     */
    public boolean addToItem(Collectible item, int add) {
        try {
            this.collectibleDao.add(item, add);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * Method for reducing the amount of an already existing item in the database.
     * @param item The Collectible form of the pre-existing item.
     * @param reducer How many of the item you'd want to take away from the database.
     * @return Returns true if the reduction was successful, otherwise false.
     */
    public boolean reduceFromItem(Collectible item, int reducer) {
        try {
            this.collectibleDao.reduce(item, reducer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * Method for removing an item.
     * @param item Collectible form of the item you would like to delete.
     * @return Returns true if the removal was successful, otherwise false.
     */
    public boolean removeItem(Collectible item) {
        try {
            this.collectibleDao.remove(item);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
