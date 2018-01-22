/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.interfaces;

import fit5042.assign.repository.entities.Account;
import fit5042.assign.repository.entities.Transaction;
import fit5042.assign.repository.entities.User;
import java.util.List;

/**
 *
 * @author jerrychen
 */
public interface TransactionRepository {
    
    public Transaction searchTransactionByNo(int transNo) throws Exception;
    
    public List<Transaction> getAllTransactions() throws Exception;
    
    public List<Transaction> getTransactionByUser(User user) throws Exception;
    
    public List<Transaction> searchTransactionByName(String transName) throws Exception;
    
    public List<Transaction> searchTransactionByType(String transType) throws Exception;
    
    public List<Transaction> searchTransactionByDescription(String description) throws Exception;
}
