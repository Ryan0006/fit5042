/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository.entities;


import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author jerrychen
 */
@Entity
@Table(name = "BANK_TRANS")
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = Transaction.GET_ALL_TRANS, query = "SELECT t FROM Transaction t")})
public class Transaction implements Serializable {
    
    public static final String GET_ALL_TRANS = "Transaction.getAll";
    
    @Id
    @SequenceGenerator(name = "trans_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq")
    @Column(name = "trans_no")
    private int transactionNo;
    
    @Column(name = "trans_name")
    private String transactionName;
    
    @Column(name = "trans_type", nullable = false)
    private String transactionType;
    
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public Transaction() {
    }

    public Transaction(String transactionName, String transactionType, String description, User user) {
        this.transactionName = transactionName;
        this.transactionType = transactionType;
        this.description = description;
        this.user = user;
    }


    public int getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(int transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   
    
    
}
