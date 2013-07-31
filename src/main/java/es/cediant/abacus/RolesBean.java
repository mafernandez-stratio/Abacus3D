/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.Role;
import es.cediant.db.RoleHelper;
import es.cediant.db.UsersRole;
import es.cediant.db.UsersRoleHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class RolesBean implements Serializable {
    
    private static final long serialVersionUID = 2955806248343336613L;

    private List<Role> roles = new ArrayList<Role>();
    private List<Role> selectedRoles = new ArrayList<Role>();
    private List<Role> newRoles = new ArrayList<Role>();
    
    //@ManagedProperty(value="#{userBean.userId}")
    private String userId;
    private int currentIndex;
    
    /**
     * Creates a new instance of RolesBean
     */
    public RolesBean() {  
        //System.out.println("Roles Bean");
        // Get all available roles       
        RoleHelper rh = new RoleHelper();
        roles = rh.getRoles();
        /*for (Role role: roles) {
            System.out.println(role.getRoleName());
        }*/        
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }    
    
    public List<Role> getRoles() {        
        return roles;
    }

    public void setRoles(List<Role> roles) {
        /* Update DB and UserBean */
        this.roles = roles;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }    
    
    public void setCurrentIndex(int currentIndex){
        System.out.println("Current Index = "+currentIndex);
        this.currentIndex = currentIndex;
    }

    public List<Role> getNewRoles() {
        return newRoles;
    }

    public void setNewRoles(List<Role> newRoles) {
        this.newRoles = newRoles;
    }    
    
    public void addNewRole(Role role) {
        newRoles.add(role);
    }

    public void removeNewRoles() {
        newRoles.clear();
    }    
    
    public boolean contains(Role role) {
        Iterator iter = getNewRoles().iterator();
        while(iter.hasNext()){
            if(((Role) iter.next()).getRoleName().equalsIgnoreCase(role.getRoleName())){
                return true;
            }
        }
        return false;
    }
    
    public List<Role> getSelectedRoles() {
        // Get roles for current user
        selectedRoles = new ArrayList<Role>();
        UsersRoleHelper urh = new UsersRoleHelper();
        ArrayList<UsersRole> rolesUser = new ArrayList<UsersRole>();                        
        rolesUser = urh.getRoles(currentIndex+1);
        ArrayList<Integer> userRolesIds = new ArrayList<Integer>();
        //System.out.println("=============");
        for(UsersRole userRole: rolesUser){
            userRolesIds.add(userRole.getRole().getIdRole());
            //System.out.println(userRole.getRole().getRoleName());
        }
        //System.out.println(userRolesIds.toString());
        //System.out.println("=============");
        for (Role role: roles) {  
            //System.out.println(role.getRoleName());
            if(userRolesIds.contains(role.getIdRole())){
                selectedRoles.add(role);
                //System.out.print("   ==> Adding: "+role.getIdRole());
            }            
        }
        //System.out.println("=============");
        /*selectedRoles = new ArrayList<Role>();
        UsersRoleHelper urh = new UsersRoleHelper();
        ArrayList<UsersRole> rolesUser = new ArrayList<UsersRole>();  
        UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userBean");
        setUserId(userBean.getUserId());   
        System.out.println(userId);
        rolesUser = urh.getRoles(Integer.parseInt(userId));
        for(UsersRole roleUser: rolesUser){
            selectedRoles.add(roleUser.getRole());
            System.out.println(roleUser.getRole().getRoleName());
        }*/        
        return selectedRoles;
    }
    
    /*public List<SelectItem> getSelectedRoles() {  
        List<SelectItem> sItems = new ArrayList<SelectItem>(); 
        RoleHelper rh = new RoleHelper();
        List<Role> someRoles = rh.getRoles();
        someRoles.remove(1);
        for(Role sRole : someRoles){
            SelectItem sItem = new SelectItem(sRole, sRole.getRoleName());
            sItems.add(sItem);
        }
        return sItems;
    }*/

    public void setSelectedRoles(List<Role> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

}
