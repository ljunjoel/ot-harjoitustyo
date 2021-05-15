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
 * Interface for collectibles.
 */
public interface CollectibleDao {
    /**
     * A method for creating Collectibles.
     * @param collectible to be created.
     * @return Returns a collectible.
     * @throws Exception
     */
    Collectible create(Collectible collectible) throws Exception;
    /**
     * A method for adding to the quantity of a Collectible.
     * @param collectible to be added to.
     * @param add How much is getting added.
     * @return Returns a collectible.
     * @throws Exception
     */
    Collectible add(Collectible collectible, int add) throws Exception;
    /**
     * A method for taking away from the quantity of a Collectible.
     * @param collectible What is taken away from.
     * @param reducer By how much it's getting reduced.
     * @return Return a collectible.
     * @throws Exception 
     */
    Collectible reduce(Collectible collectible, int reducer) throws Exception;
    /**
     * A method for removing Collectibles.
     * @param collectible To be removed.
     * @return Returns a collectible
     * @throws Exception 
     */
    Collectible remove(Collectible collectible) throws Exception;
    /**
     * A method for doing a search for items the user owns.
     * @param search Search term given.
     * @param username User who's stuff we're going through.
     * @return Returns a list with collectibles that match the search given.
     * @throws Exception 
     */
    List<Collectible> search(String search, String username) throws Exception;
    /**
     * A method for getting all the Collectibles a user owns.
     * @param username User who's stuff we're going through.
     * @return Returns a list of all the items the user has.
     * @throws Exception 
     */
    List<Collectible> getAll(String username) throws Exception;
}
