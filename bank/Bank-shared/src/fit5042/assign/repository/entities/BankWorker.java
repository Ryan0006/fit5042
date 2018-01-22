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
@Table(name = "BANK_WORKER")
@DiscriminatorValue("bank_worker")
public class BankWorker extends User implements Serializable {
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Activity> activities;
    
    public BankWorker() {
        super();
    }

    public BankWorker(String username, String lastName, String firstName, String email, 
            String password, String address, String phoneNumber) {
        super(username, lastName, firstName, email, password, address, phoneNumber);
    }

    public BankWorker(int userId, String username, String lastName, String firstName, String email, String password, String address, String phoneNumber) {
        super(userId, username, lastName, firstName, email, password, address, phoneNumber);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    
   
}
