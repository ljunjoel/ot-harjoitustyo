/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Mock database until the real one is implemented.
 * 
 */
public class Collection {
    Map<String, Integer> collection = new HashMap<>();
    /**
     * Adds the given quantity to given item
     * @param name
     * @param quantity 
     */
    public void addItem(String name, int quantity) {
        if (this.collection.keySet().contains(name)) {
            int old = this.collection.get(name);
            this.collection.replace(name, old + quantity);
        } else {
            this.collection.put(name, quantity);
        }
    }
    /**
     * Completely removes item with said name
     * @param name 
     */
    public void removeItem(String name) {
        this.collection.remove(name);
    }
    /**
     * Reduces the given item by given amount. If the item goes to zero, removes it all together.
     * @param name
     * @param quantity 
     */
    public void reduceItem(String name, int quantity) {
        int old = this.collection.get(name);
        if (old - quantity == 0) {
            removeItem(name);
        } else {
            this.collection.replace(name, old - quantity);
        }
    }
    /**
     * Prints out all items and their quantities.
     */
    public void printAll() {
        for (String name: this.collection.keySet()) {
            String key = name;
            printItem(key);
        }
    }
    /**
     * Prints the name and quantity of a specific item.
     * @param name 
     */
    public void printItem(String name) {
        String value = this.collection.get(name).toString();
        System.out.println("Name: " + name + " Quantity: " + value);
    }
}