/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "account")
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = Account.GET_ALL_ACCOUNT, query = "SELECT a FROM Account a")})

public class Account implements Serializable {

    public static final String GET_ALL_ACCOUNT = "Account.getAll";

    @Id
    @SequenceGenerator(name = "acc_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "card_no", unique = true, nullable = false)
    private int cardNo;

    @Column(name = "balance", nullable = false)
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void takeOut(double fund) {
        BigDecimal bd1 = new BigDecimal(Double.toString(balance));
        BigDecimal bd2 = new BigDecimal(Double.toString(fund));
        balance = bd1.subtract(bd2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void takeIn(double fund) {
        balance = balance + fund;
    }
}
