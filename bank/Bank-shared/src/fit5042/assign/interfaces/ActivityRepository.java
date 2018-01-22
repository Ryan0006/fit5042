/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.interfaces;

import fit5042.assign.repository.entities.Activity;
import fit5042.assign.repository.entities.User;
import fit5042.assign.utility.TransactionType;
import java.util.List;

/**
 *
 * @author jerrychen
 */
public interface ActivityRepository {
    
    public List<Activity> getActivityByUser(User user) throws Exception;
    
    public void transactionTypeInsertion(String type, User user) throws Exception;
    
    public void transactionTypeDeletion(TransactionType type, User user) throws Exception;
    
    public void userInsertion(User targetedUser, User user) throws Exception;
    
    public void userDeletion(User targetedUser, User user) throws Exception;
    
    public void userEdition(User targetedUser, User user) throws Exception;
}
