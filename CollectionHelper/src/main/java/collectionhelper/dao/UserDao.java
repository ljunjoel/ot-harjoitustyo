/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.dao;

import collectionhelper.domain.User;
import java.util.List;
/**
 *
 * An interface for users.
 */
public interface UserDao {
    /**
     * A method for creating Users.
     * @param user User to be created.
     * @return Returns a User.
     * @throws Exception 
     */
    User create(User user) throws Exception;
    /**
     * A method for searching for a specific user by username.
     * @param username Username of the user one wants to find.
     * @return Returns the User found or empty user.
     * @throws Exception 
     */
    User findByName(String username) throws Exception;
    /**
     * A method for fetching all usernames.
     * @return Returns a list of all usernames in the database.
     * @throws Exception 
     */
    List<String> getAllNames() throws Exception;
}
