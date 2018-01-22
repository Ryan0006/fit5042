/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.webservices.entity;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author jerrychen
 */
@Entity
@Table(name = "TRANS_TYPE")
@Access(AccessType.FIELD)
@NamedQuery(name = TransactionType.GET_ALL_TYPES, query = "SELECT t FROM TransactionType t")
public class TransactionType implements Serializable {
    
    public static final String GET_ALL_TYPES = "TransactionType.getAll";
    
    @Id
    @SequenceGenerator(name = "transtype_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transtype_seq")
    @Column(name = "transtype_id")
    private int transTypeId;
    
    @Column(name = "type", nullable = false)
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
