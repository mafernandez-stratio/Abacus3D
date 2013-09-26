/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.Node;
import es.cediant.db.NodeHelper;
import java.util.List;

/**
 *
 * @author miguel
 */
public class NodesBean {

    /**
     * Creates a new instance of NodesBean
     */
    public NodesBean() {
    }
    
    public List<Node> getLastNodes(int num){
        NodeHelper nh = new NodeHelper();
        return nh.getLastNodes(num);        
    }
    
}
