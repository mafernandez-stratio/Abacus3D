/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguel
 */
public class InventoryUserList implements Serializable {
    
    private static final long serialVersionUID = 6353091267712183309L;

    private String user;
    private List<User> userItems;
    
    public InventoryUserList() {
        userItems = new ArrayList<User>();
    }

    public long getCount() {
        if (userItems != null) {
            return userItems.size();
        } else {
            return 0;
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<User> getUserItems() {
        return userItems;
    }

    public void setUserItems(List<User> userItems) {
        this.userItems = userItems;
    }
    
}
