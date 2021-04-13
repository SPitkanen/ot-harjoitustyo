
package wbapp.domain;

import java.util.ArrayList;

/**
 *
 * @author santeripitkanen
 */
public class Aircraft {
    private Integer acId;
    private String acType;
    private String acRegister;
    
    public Aircraft(int acId, String acType, String acRegister, Double BasicWeight) {
        this.acId = acId;
        this.acType = acType;
        this.acRegister = acRegister;
    }
    
    public int getAcId() {
        return this.acId;
    }
    
    public String getAcType() {
        return this.acType;
    }
    
    public String getAcRegister() {
        return this.acRegister;
    }
}

