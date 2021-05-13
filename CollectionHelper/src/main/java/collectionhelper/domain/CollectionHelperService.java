/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.domain;

import collectionhelper.dao.CollectibleDao;
import collectionhelper.dao.UserDao;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * Component for the logic of the application.
 */
public class CollectionHelperService {
    CollectibleDao collectibleDao;
    UserDao userDao;
    User loggedIn;
    List<String> names;
    
    public CollectionHelperService(CollectibleDao collectibleDao, UserDao userDao) {
        this.collectibleDao = collectibleDao;
        this.userDao = userDao;
    }
    
    public boolean createUser(String name, String password) {
        User user = new User(name, password);
        System.out.println("Name and password going into system: "+user.getName()+", "+user.getPassword());
        try {
            System.out.println("Tapahtuuko tätä ollenkaan? (service-create)");
            System.out.println(this.userDao);
            this.userDao.create(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Jokin meni vikaan (service-create)");
            return false;
        }
        return true;
    }
    
    public boolean login(String username, String password) {
        return true;
    }
    
    public List<String> getNames() {
        try {
            names = userDao.getAllNames();
        } catch (Exception e) {
            System.out.println("No names");
        }
        return names;
    }
}
