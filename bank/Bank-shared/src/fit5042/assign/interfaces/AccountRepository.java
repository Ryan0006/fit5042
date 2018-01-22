/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.interfaces;

import fit5042.assign.repository.entities.Account;
import fit5042.assign.repository.entities.User;
import java.util.List;

/**
 *
 * @author jerrychen
 */
public interface AccountRepository {
    
    public Account searchAccountByNo(int cardNo) throws Exception;
    
    public Account searchAccountById(int accountId) throws Exception;
    
    public List<Account> getAllAccounts() throws Exception;
    
    public List<Account> getAccountByOwner(User owner) throws Exception;
}
