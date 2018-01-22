/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.UserRepository;
import fit5042.assign.repository.entities.Account;
import fit5042.assign.repository.entities.Activity;
import fit5042.assign.repository.entities.BankWorker;
import fit5042.assign.repository.entities.PublicMember;
import fit5042.assign.repository.entities.Transaction;
import fit5042.assign.repository.entities.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named(value = "userDetail")
@RequestScoped
public class UserDetail {

    @EJB
    private UserRepository userRepository;

    private User user;
    private boolean publicMember;
    private List<Account> accounts;
    private List<Activity> activities;
    private List<Transaction> transactions;
    
    public UserDetail() {
        publicMember = false;
    }

    @PostConstruct
    public void init() {
        int userId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("userId"));
       try {
            user = userRepository.searchUserById(userId);
       } catch (Exception ex) {
            Logger.getLogger(UserDetail.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        accounts = null;
        activities = null;
        transactions = null;
        if (PublicMember.class.isInstance(user)) {
            accounts = ((PublicMember) user).getAccounts();
            transactions = ((PublicMember) user).getTransactions();
            publicMember = true;
        } else {
            activities = ((BankWorker) user).getActivities();
            publicMember = false;
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Activity> getActivities() {
        return activities;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean isPublicMember() {
        return publicMember;
    }


    public User getUser() {
        return user;
    }
    

}
