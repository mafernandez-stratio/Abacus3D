/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.util;

/**
 *
 * @author miguel
 */
public class StringUtil {
    
    public static String[] splitDCs(String text){
        String[] strArray = null;
        if(text.contains("dc=")){
            text=text.replace("dc=", "");
        }
        if(text.contains(".")){
            strArray = text.split(".");
        } else if(text.contains(",")){
            strArray = text.split(",");
        } else {
            strArray = text.split(" ");
        }
        for(int i=0; i<strArray.length; i++){
            strArray[i]=strArray[i].trim();
        }
        return strArray;
    }
    
}
