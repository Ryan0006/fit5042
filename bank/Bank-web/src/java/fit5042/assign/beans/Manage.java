/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.AccountRepository;
import fit5042.assign.repository.entities.Account;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import fit5042.assign.interfaces.MoneyManager;
import fit5042.assign.interfaces.TransactionTypeManager;
import fit5042.assign.utility.TransactionType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author jerrychen
 */
@Named(value = "manage")
@SessionScoped
public class Manage implements Serializable {

    @EJB
    TransactionTypeManager webServiceTransactionType;

    @EJB
    AccountRepository accountRepository;

    @EJB
    MoneyManager moneyManager;

    private Account account;
    private int receiverNo;
    private double amount;
    private boolean success;
    private static Map<String, String> typeMap;
    private String option = "Deposit";

    public Manage() {
    }

    public String init() {
        typeMap = new HashMap<>();
        success = false;
        int cardNo = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("cardNo"));
        try {
            account = accountRepository.searchAccountByNo(cardNo);
        } catch (Exception ex) {
            Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<TransactionType> transTypes = webServiceTransactionType.findAllTypes();
        for (TransactionType transType : transTypes) {
            typeMap.put(transType.getType(), transType.getType());
        }
        return "/publicmember/manage?faces-redirect=true";
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getReceiverNo() {
        return receiverNo;
    }

    public void setReceiverNo(int receiverNo) {
        this.receiverNo = receiverNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, String> getTypeMap() {
        return typeMap;
    }


    public void setOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public String commitTransaction() throws Exception {
        String result = null;
        switch (option) {
            case "Deposit":
                result = depositMoney();
                break;
            case "Withdraw":
                result = withdrawMoney();
                break;
            case "Transfer":
                result = transferMoney();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }
    
    public String depositMoney() throws Exception {
        moneyManager.deposit(account, amount);
        return "/publicmember/success?faces-redirect=true";
    }

    public String withdrawMoney() throws Exception {
        if (moneyManager.withdraw(account, amount)) {
            return "/publicmember/success?faces-redirect=true";
        }
        return "/publicmember/failfund?faces-redirect=true";
    }

    public String transferMoney() throws Exception {
        if (accountRepository.searchAccountByNo(receiverNo) == null) {
            success = false;
            return "/publicmember/failcardno?faces-redirect=true";
        }
        success = moneyManager.transferMoney(account.getCardNo(), receiverNo, amount);
        if (success) {
            return "/publicmember/success?faces-redirect=true";
        }
        return "/publicmember/failfund?faces-redirect=true";
    }
}
