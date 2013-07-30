/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import javax.el.ELContext;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author miguel
 */
@FacesConverter("RolesConverter")
public class RolesConverter implements Converter {
    
    private RolesParser rolesParser;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(" === getAsObject === ");
        for (Role role : getRolesParser(context).getRolesList()) {
            if (role.getRoleName().equals(value)) {
                System.out.println(role.getRoleName());
                return role;
            }
        }
        System.out.println(" === /getAsObject === ");
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println(" === getAsString === ");
        System.out.println(((Role) value).getRoleName());
        System.out.println(" === /getAsString === ");
        if (value == null) return null;
        return ((Role) value).getRoleName();
    }
    
    private RolesParser getRolesParser(FacesContext facesContext) {
        System.out.println(" === getRolesParser === ");
        if (rolesParser == null) {
            ELContext elContext = facesContext.getELContext();
            rolesParser = (RolesParser) elContext.getELResolver().getValue(elContext, null, "rolesParser");
        }
        System.out.println(" === /getRolesParser === ");
        return rolesParser;
    }    
    
}
