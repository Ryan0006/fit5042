/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jerrychen
 */
@Entity
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = Activity.GET_ALL_ACTIVITY, query = "SELECT a FROM Activity a"),
    @NamedQuery(name = Activity.GET_ACTIVITY_BY_USER, query = "SELECT a FROM Activity a WHERE a.user=:user")})
public class Activity implements Serializable{
    
    public static final String GET_ACTIVITY_BY_USER = "Activity.getByUser";
    public static final String GET_ALL_ACTIVITY = "Activity.getAll";
    
    @Id
    @SequenceGenerator(name = "act_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "act_seq")
    @Column(name = "activity_id")
    private int activityId;
    
    @Column(name = "act_type")
    private String activityType;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "act_time")
    private Date time;
    
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Activity() {
    }

    public Activity(String activityType, String description, User user) {
        this.activityType = activityType;
        this.time = new Date();
        this.description = description;
        this.user = user;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
