/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.repository.entities.Transaction;
import fit5042.assign.interfaces.TransactionRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named(value = "transController")
@RequestScoped
public class TransController implements Serializable {

    @EJB
    TransactionRepository transactionRepository;

    @Inject
    CurrentUser currentUser;
    
    private List<Transaction> transactions;
    private List<String> searchOptions;
    private String keyword;
    private boolean showForm;
    private String selectedOption;

    public TransController() {
    }

    @PostConstruct
    public void init() {
        try {
            viewAllTransactions();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        searchOptions = new ArrayList<>();
        searchOptions.add("transaction no");
        searchOptions.add("transaction name");
        searchOptions.add("transaction type");
        searchOptions.add("description");
        showForm = true;
    }
    
    

    public List<String> getSearchOptions() {
        return searchOptions;
    }

    public void setSearchOptions(List<String> searchOptions) {
        this.searchOptions = searchOptions;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void viewAllTransactions() throws Exception {
        transactions = transactionRepository.getAllTransactions();
        if (transactions.isEmpty()) {
            showForm = false;
        }
    }

    
    public void search() throws Exception {
        
        switch (selectedOption) {
            case "transaction no":
                Transaction transaction = transactionRepository.searchTransactionByNo(Integer.valueOf(keyword));
                if (transaction == null) {
                    showForm = false;
                } else {
                    transactions.clear();
                    transactions.add(transaction);
                }
                break;
            case "transaction name":
                transactions = transactionRepository.searchTransactionByName(keyword);
                if (transactions.isEmpty()) {
                    showForm = false;
                }
                break;
            case "transaction type":
                transactions = transactionRepository.searchTransactionByType(keyword);
                if (transactions.isEmpty()) {
                    showForm = false;
                }
                break;
            case "description":
                transactions = transactionRepository.searchTransactionByDescription(keyword);
                if (transactions.isEmpty()) {
                    showForm = false;
                }
                break;
        }
    }
}
