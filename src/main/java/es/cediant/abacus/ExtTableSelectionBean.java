/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.App;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.richfaces.component.UIExtendedDataTable;

/**
 *
 * @author miguel
 */
public class ExtTableSelectionBean {
    private Collection<Object> selection;
    //@ManagedProperty(value = "#{carsBean.allApps}")
    private List<App> inventoryItems;
    private List<App> selectionItems = new ArrayList<App>();

    public ExtTableSelectionBean() {        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AppsBean appsBean = (AppsBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "appsBean");
        inventoryItems = appsBean.getAllTests();
    }
         
    public void selectionListener(AjaxBehaviorEvent event) {
        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        selectionItems.clear();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                selectionItems.add((App) dataTable.getRowData());
            }
        }
        dataTable.setRowKey(originalKey);
    }
 
    public Collection<Object> getSelection() {
        return selection;
    }
 
    public void setSelection(Collection<Object> selection) {
        this.selection = selection;
    }
 
    public List<App> getApps() {
        return inventoryItems;
    }
 
    public void setApps(List<App> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
 
    public App getSelectionItem() {
        if (selectionItems == null || selectionItems.isEmpty()) {
            return null;
        }
        return selectionItems.get(0);
    }
 
    public List<App> getSelectionItems() {
        return selectionItems;
    }
 
    public void setSelectionItems(List<App> selectionItems) {
        this.selectionItems = selectionItems;
    }
}
