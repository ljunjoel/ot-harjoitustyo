/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.domain;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author joel
 */
public class Collection {
    Map<String, Integer> collection = new HashMap<>();
    
    public void addItem(String name, int quantity) {
        if (this.collection.keySet().contains(name)) {
            int old = this.collection.get(name);
            this.collection.replace(name, old + 1);
        } else {
            this.collection.put(name, quantity);
        }
    }
    
    public void removeItem(String name) {
        this.collection.remove(name);
    }
    
    public void reduceItem(String name, int quantity) {
        int old = this.collection.get(name);
        if (old - quantity == 0) {
            removeItem(name);
        } else {
            this.collection.replace(name, old - quantity);
        }
    }
    
    public void printAll() {
        for (String name: this.collection.keySet()) {
            String key = name;
            printItem(key);
        }
    }
    
    public void printItem(String name) {
        String value = this.collection.get(name).toString();
        System.out.println("Name: " + name + " Quantity: " + value);
    }
}