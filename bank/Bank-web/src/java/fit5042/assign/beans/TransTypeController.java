/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.ActivityRepository;
import fit5042.assign.interfaces.TransactionTypeManager;
import fit5042.assign.utility.TransactionType;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named(value = "typeController")
@RequestScoped
public class TransTypeController implements Serializable {
    
    @EJB
    TransactionTypeManager transactionTypeManager;
    
    @EJB
    private ActivityRepository activityRepository;
    
    @Inject
    CurrentUser currentUser;
    
    private String type;
    private boolean addSuccess = false;

    public TransTypeController() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAddSuccess() {
        return addSuccess;
    }

    public void setAddSuccess(boolean addSuccess) {
        this.addSuccess = addSuccess;
    }
    
    public List<TransactionType> getTypes() {
        return transactionTypeManager.findAllTypes();
    }
    
    public void addType() throws Exception {
        if (transactionTypeManager.createTransactionType(type)) {
            activityRepository.transactionTypeInsertion(type, currentUser.getUser());
            addSuccess = true;
        }
        addSuccess = false;
    }
   
    public void deleteType(TransactionType type) throws Exception {
        transactionTypeManager.deleteTransactionType(type.getType());
        activityRepository.transactionTypeDeletion(type, currentUser.getUser());
    }
    
    public void validateAddType(FacesContext context, UIComponent toValidate, Object value) {
        String newType = (String) value;
        List<TransactionType> types = transactionTypeManager.findAllTypes();
        boolean found = false;
        for (TransactionType transType : types) {
            if (transType.getType().toLowerCase().equals(newType.toLowerCase())) {
                found = true;
            }
        }
        if (found) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Transaction type already exists");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
}
