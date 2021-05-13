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
 * @author joel
 */
public interface UserDao {
    User create (User user) throws Exception;
    
    User findByName(String username) throws Exception;
    
    List<String> getAllNames() throws Exception;
}
