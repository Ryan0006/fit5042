/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.TransactionRepository;
import fit5042.assign.repository.entities.BankWorker;
import fit5042.assign.repository.entities.PublicMember;
import fit5042.assign.repository.entities.Transaction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jerrychen
 */
@Named(value = "transactionDetail")
@RequestScoped
public class TransactionDetail {

    @EJB
    private TransactionRepository transRepository;

    private Transaction transaction;
    
    public TransactionDetail() {
        
    }

    public Transaction getTransaction() {
        return transaction;
    }
    
    @PostConstruct
    public void init() {
        int transactionNo = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("transactionNo"));
     
        try {
            transaction = transRepository.searchTransactionByNo(transactionNo);
        } catch (Exception ex) {
            Logger.getLogger(TransactionDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
