/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author jerrychen
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "PUBLIC_MEMBER")
@DiscriminatorValue("public_member")
public class PublicMember extends User implements Serializable {
    
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Transaction> transactions;
    
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Account> accounts;
    
    public PublicMember() {
        super();
    }

    public PublicMember(String username, String lastName, String firstName, String email, 
            String password, String address, String phoneNumber) {
        super(username, lastName, firstName, email, password, address, phoneNumber);
    }

    public PublicMember(int userId, String username, String lastName, String firstName, String email, String password, 
            String address, String phoneNumber) {
        super(userId, username, lastName, firstName, email, password, address, phoneNumber);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Account getAccountByNo(int cardNo) {
        for (Account account : accounts) {
            if (account.getCardNo() == cardNo) {
                return account;
            }
        }
        return null;
    }
    
}
