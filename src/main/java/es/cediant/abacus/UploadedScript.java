/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;

public class UploadedScript implements Serializable {
    
    private static final long serialVersionUID = 1290841891103212522L;
    
    private String name;
    private String mime;
    private long length;
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        int extDot = name.lastIndexOf('.');
        if (extDot > 0) {
            String extension = name.substring(extDot + 1);
            mime = "script/"+extension;
        } else {
            mime = "script/unknown";
        }
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getMime() {
        return mime;
    }
}
