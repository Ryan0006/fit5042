/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.interfaces;

import fit5042.assign.utility.TransactionType;
import java.util.List;

/**
 *
 * @author jerrychen
 */
public interface TransactionTypeManager {
    
    public boolean createTransactionType(String type);
    
    public boolean deleteTransactionType(String type);
    
    public List<TransactionType> findAllTypes();
}
