/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.utility.UserType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named(value = "userTypeBean")
@ApplicationScoped
public class UserTypeBean {
    
    public UserType[] getUserTypes() {
        return UserType.values();
    }
    
}
