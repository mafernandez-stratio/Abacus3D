package es.cediant.db;
// Generated Aug 29, 2013 4:45:28 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role  implements java.io.Serializable {
    private static final long serialVersionUID = -1073516299568581649L;


     private Integer idRole;
     private String roleName;
     private Set<UsersRole> usersRoles = new HashSet<UsersRole>(0);

    public Role() {
    }

	
    public Role(String roleName) {
        this.roleName = roleName;
    }
    public Role(String roleName, Set<UsersRole> usersRoles) {
       this.roleName = roleName;
       this.usersRoles = usersRoles;
    }
   
    public Integer getIdRole() {
        return this.idRole;
    }
    
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public Set<UsersRole> getUsersRoles() {
        return this.usersRoles;
    }
    
    public void setUsersRoles(Set<UsersRole> usersRoles) {
        this.usersRoles = usersRoles;
    }




}


