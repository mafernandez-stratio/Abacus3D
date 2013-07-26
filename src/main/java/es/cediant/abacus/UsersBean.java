/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.InventoryUserList;
import es.cediant.db.User;
import es.cediant.db.UserHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class UsersBean implements Serializable {
    
    private static final long serialVersionUID = 6848248887384709657L;
    
    private static final int DECIMALS = 1;
    private static final int CLIENT_ROWS_IN_AJAX_MODE = 15;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
    private List<User> allUsers = null;
    private List<InventoryUserList> inventoryUserLists = null;
    private int currentUserIndex;
    private User editedUser;
    private int page = 1;
 
    private int clientRows;

    /**
     * Creates a new instance of UsersBean
     */
    public UsersBean() {
    }
    
    public void switchAjaxLoading(ValueChangeEvent event) {
        this.clientRows = (Boolean) event.getNewValue() ? CLIENT_ROWS_IN_AJAX_MODE : 0;
    }
 
    public void remove() {
        allUsers.remove(allUsers.get(currentUserIndex));
    }
 
    public void store() {
        allUsers.set(currentUserIndex, editedUser);
    }
 
    /*
    public List<SelectItem> getUserOptions() {
        List<SelectItem> result = new ArrayList<SelectItem>();
        result.add(new SelectItem("", ""));
        for (InventoryUserList userList : getInventoryUserLists()) {
            result.add(new SelectItem(userList.getUser()));
        }
        return result;
    }
    */
 
    /*
    public List<String> getAllUsersStr() {
        List<String> result = new ArrayList<String>();
        for (InventoryUserList userList : getInventoryUserLists()) {
            result.add(userList.getUser());
        }
        return result;
    }
    */
 
    /*
    public List<InventoryUserList> getInventoryUserLists() {
        synchronized (this) {
            if (inventoryUserLists == null) {
                inventoryUserLists = new ArrayList<InventoryUserList>();
                List<User> inventoryItems = getAllUsers();
 
                Collections.sort(inventoryItems, new Comparator<User>() {
                    public int compare(User o1, User o2) {
                        return o1.getUserName().compareTo(o2.getUserName());
                    }
                });
                
                Iterator<User> iterator = inventoryItems.iterator();
                InventoryUserList userList = new InventoryUserList();
                userList.setUser(inventoryItems.get(0).getUserName());
                while (iterator.hasNext()) {
                    User item = iterator.next();
                    User newItem = new User();
                    itemToUserItem(item, newItem);
                    if (!item.getUserName().equals(userList.getUser())) {
                        inventoryUserLists.add(userList);
                        userList = new InventoryUserList();
                        userList.setUser(item.getUserName());
                    }
                    userList.getUserItems().add(newItem);
                }
                inventoryUserLists.add(userList);
            }
        }
        return inventoryUserLists;
    }
    */     
 
    public List<User> getAllUsers() {
        allUsers = new ArrayList<User>();
        UserHelper uh = new UserHelper();
        return uh.getUsers();        
    }     
 
    public int getCurrentUserIndex() {
        return currentUserIndex;
    }
 
    public void setCurrentUserIndex(int currentUserIndex) {
        this.currentUserIndex = currentUserIndex;
    }
 
    public User getEditedUser() {
        return editedUser;
    }
 
    public void setEditedUser(User editedUser) {
        this.editedUser = editedUser;
    }
 
    public int getPage() {
        return page;
    }
 
    public void setPage(int page) {
        this.page = page;
    }
 
    public int getClientRows() {
        return clientRows;
    }
 
    public void setClientRows(int clientRows) {
        this.clientRows = clientRows;
    }
}
