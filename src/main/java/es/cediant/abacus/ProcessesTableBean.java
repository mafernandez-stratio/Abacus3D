/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import org.richfaces.component.UIExtendedDataTable;
import es.cediant.db.Process;
import javax.servlet.http.Part;

/**
 *
 * @author miguel
 */
public class ProcessesTableBean implements Serializable {
    
    private static final long serialVersionUID = 7873279418020992633L;
    private String selectionMode = "single";
    private Collection<Object> selection;
    @ManagedProperty(value = "#{processesBean.allProcesses}")
    private List<Process> inventoryItems;
    private List<Process> selectionItems = new ArrayList<Process>();
    private Process selectedProcess;
    private Part uploadedFile;

    public void selectionListener(AjaxBehaviorEvent event) {
        System.out.println("selectionListener");
        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        selectionItems.clear();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                selectedProcess = (Process) dataTable.getRowData();
                selectionItems.add(selectedProcess);
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
 
    public List<Process> getProcesss() {
        return inventoryItems;
    }
 
    public void setProcesss(List<Process> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
 
    public Process getSelectionItem() {
        if (selectionItems == null || selectionItems.isEmpty()) {
            return null;
        }
        return selectionItems.get(0);
    }
 
    public List<Process> getSelectionItems() {
        return selectionItems;
    }
 
    public void setSelectionItems(List<Process> selectionItems) {
        this.selectionItems = selectionItems;
    }
 
    public String getSelectionMode() {
        return selectionMode;
    }
 
    public void setSelectionMode(String selectionMode) {
        this.selectionMode = selectionMode;
    }

    public Process getSelectedProcess() {
        return selectedProcess;
    }

    public void setSelectedProcess(Process selectedProcess) {
        this.selectedProcess = selectedProcess;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }            
    
    public void upload(){
        System.out.println("Uploaded file size = "+uploadedFile.getSize());
    }
    
}
