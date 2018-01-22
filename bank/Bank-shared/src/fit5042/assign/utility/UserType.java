/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.utility;

/**
 *
 * @author jerrychen
 */
public enum UserType {

    PUBLIC_MEMBER("public memeber"),
    BANK_WORKER("bank worker");
    
    private String label;
    
    private UserType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
    
}