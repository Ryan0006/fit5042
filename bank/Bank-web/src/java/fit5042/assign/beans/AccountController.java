/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.AccountRepository;
import fit5042.assign.repository.entities.Account;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named(value = "accountController")
@RequestScoped
public class AccountController implements Serializable {

    @EJB
    private AccountRepository accountRepository;
    
    @Inject
    private CurrentUser currentUser;
    
    private List<Account> accounts;
    private boolean showForm = true;

    public AccountController() {
    }
    
    public List<Account> getAccounts() throws Exception {
        accounts = accountRepository.getAccountByOwner(currentUser.getUser());
        if (accounts.isEmpty()) {
            showForm = false;
        }
        showForm = true;
        return accounts;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }
    
    
}
