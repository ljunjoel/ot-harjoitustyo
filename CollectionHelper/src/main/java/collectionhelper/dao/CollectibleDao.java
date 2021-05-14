/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.dao;

import collectionhelper.domain.Collectible;
import java.util.List;
/**
 *
 * @author joel
 */
public interface CollectibleDao {
    Collectible create(Collectible collectible) throws Exception;
    
    Collectible add(Collectible collectible, int add) throws Exception;
    
    Collectible reduce (Collectible collectible, int reducer) throws Exception;
    
    Collectible remove (Collectible collectible) throws Exception;
    
    List <Collectible> search(String search, String username) throws Exception;
    
    List <Collectible> getAll(String username) throws Exception;
}
