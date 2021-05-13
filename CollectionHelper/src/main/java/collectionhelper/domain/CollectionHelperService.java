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
            System.out.println("Käyttäjän nimi: "+user.getName());
            System.out.println("Annettu salasana: "+password.trim());
            System.out.println("Käyttäjän salasana: "+user.getPassword().trim());
            if((user.getPassword()).equals(password)) {
                System.out.println("Käyttäjän nimi ifin sisällä: "+user.getName());
                this.loggedIn = user.getName();
                System.out.println("Nyt kirjattuna: "+this.loggedIn);
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
    
    public String getLoggedIn() {
        return this.loggedIn;
    }
}
