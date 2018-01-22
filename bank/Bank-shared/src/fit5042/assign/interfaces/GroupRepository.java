/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.interfaces;

import fit5042.assign.repository.entities.Groups;

/**
 *
 * @author jerrychen
 */
public interface GroupRepository {
    
    public void addGroup(String username, String groupname) throws Exception;
    
    public Groups searchByUsername(String username) throws Exception;
    
    public void deleteGroup(String username) throws Exception;
}
