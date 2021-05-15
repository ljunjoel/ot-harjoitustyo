/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.domain;


/**
 * 
 * Class representing a collectible.
 */
public class Collectible {
    String name;
    Integer quantity;
    String owner;
    
    /**
     * A constructor with all needed parameters.
     * @param name The name you want to give.
     * @param quantity The amount you have of it.
     * @param owner Username of the owner.
     */
    public Collectible(String name, int quantity, String owner) {
        this.name = name;
        this.quantity = quantity;
        this.owner = owner;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public String getOwner() {
        return this.owner;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
}