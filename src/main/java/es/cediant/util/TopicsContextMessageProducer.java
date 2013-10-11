/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.richfaces.application.push.MessageException;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;

/**
 *
 * @author miguel
 */
public class TopicsContextMessageProducer implements MessageProducer {    
    
    public static final String PUSH_TOPICS_CONTEXT_TOPIC = "pushTopicsContext";
    
    private TopicsContextMessageProducer() {
    }        
    
    @Override
    public void sendMessage() {
        try {
            TopicKey topicKey = new TopicKey(PUSH_TOPICS_CONTEXT_TOPIC);
            TopicsContext topicsContext = TopicsContext.lookup();
            topicsContext.publish(topicKey, "message");
        } catch (MessageException ex) {
            Logger.getLogger(TopicsContextMessageProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
