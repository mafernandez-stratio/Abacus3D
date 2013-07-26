/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.Role;
import es.cediant.db.RoleHelper;
import es.cediant.db.UsersRole;
import es.cediant.db.UsersRoleHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class RolesBean {

    private List<Role> roles;
    private List<Role> selectedRoles;
    
    @ManagedProperty(value="#{userBean.username}")
    private String username;
    
    /**
     * Creates a new instance of RolesBean
     */
    public RolesBean() {  
        System.out.println("Roles Bean");
        // Get all available roles
        roles = new ArrayList<Role>();        
        RoleHelper rh = new RoleHelper();
        roles = rh.getRoles();
        for (Role role: roles) {
            System.out.println(role.getRoleName());
        }
        // Get roles for current user
        selectedRoles = new ArrayList<Role>();
        UsersRoleHelper urh = new UsersRoleHelper();
        ArrayList<UsersRole> rolesUser = new ArrayList<UsersRole>();  
        int userId = 3;
        rolesUser = urh.getRoles(userId);
        for(UsersRole roleUser: rolesUser){
            selectedRoles.add(roleUser.getRole());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }    
    
    public List<Role> getRoles() {        
        return roles;
    }

    public void setRoles(List<Role> roles) {
        /* Update DB and UserBean */
        this.roles = roles;
    }

    public List<Role> getSelectedRoles() {        
        return selectedRoles;
    }

    public void setSelectedRoles(List<Role> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }
}
