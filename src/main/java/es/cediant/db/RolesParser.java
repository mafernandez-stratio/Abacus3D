/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import es.cediant.abacus.RolesBean;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author miguel
 */
@ManagedBean
@ApplicationScoped
public class RolesParser {
    
    private List<Role> rolesList;

    /*@XmlRootElement(name = "roles")
    private static final class RolesHolder {
        
        private List<Role> roles;
        
        @XmlElement(name = "role")
        public List<Role> getRoles() {
            return roles;
        }
        
        @SuppressWarnings("unused")
        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }
    }*/
    
    
    public synchronized Iterable<Role> getRolesList() {
        System.out.println(" === /getRoleList ===");
        if (rolesList == null) {
            //RoleHelper ur = new RoleHelper();
            //rolesList = ur.getRoles();
                                    
            RolesBean rolesBean = (RolesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rolesBean");
            rolesList = rolesBean.getRoles();
                        
            rolesBean.removeNewRoles();
            
            Iterator iter = rolesList.iterator();            
            while(iter.hasNext()){
                Role role = ((Role) iter.next());
                System.out.println(role.getRoleName());
            }
            
            /*ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            URL resource = ccl.getResource("org/richfaces/demo/data/roles/roles.xml");
            JAXBContext context;
            try {
                context = JAXBContext.newInstance(RolesHolder.class);
                RolesHolder capitalsHolder = (RolesHolder) context.createUnmarshaller().unmarshal(resource);
                rolesList = capitalsHolder.getRoles();
            } catch (JAXBException e) {
                throw new FacesException(e.getMessage(), e);
            }*/
        }        
        System.out.println(" === /getRoleList ===");
        return rolesList;
    }
    
}
