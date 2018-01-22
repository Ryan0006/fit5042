/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.utility;

import java.io.Serializable;

/**
 *
 * @author jerrychen
 */
public class TransactionType implements Serializable {
    
    public static final String NAME_DEPOSIT = "Deposit Money";
    public static final String TYPE_DEPOSIT = "Deposit";
    public static final String NAME_WITHDRAW = "Withdraw Money";
    public static final String TYPE_WITHDRAW = "Withdraw";
    public static final String NAME_TRANSFER = "Transfer Money";
    public static final String TYPE_TRANSFER = "Transfer";

    private int transTypeId;

    private String type;

    public TransactionType() {
    }

    public TransactionType(String type) {
        this.type = type;
    }

    public int getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(int transTypeId) {
        this.transTypeId = transTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
