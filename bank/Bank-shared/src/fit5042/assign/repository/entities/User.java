/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository.entities;

import fit5042.assign.utility.UserType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author jerrychen
 */
@Entity
@Table(name = "BANKUSER")
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@DiscriminatorValue("user")
@NamedQueries({
    @NamedQuery(name = User.GET_ALL_USER, query = "SELECT u FROM User u")})
public abstract class User implements Serializable {

    public static final String GET_ALL_USER = "User.getAll";

    @Id
    @SequenceGenerator(name = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "user_id")
    private int userId;
    @Column(unique = true)
    private String username;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(nullable = false)
    private String password;
    private String address;
    @Column(name = "phone_no")
    private String phoneNumber;

    public User() {
    }

    public User(String username, String lastName, String firstName, String email, String password, String address, String phoneNumber) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User(int userId, String username, String lastName, String firstName, String email, String password, String address, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        if (PublicMember.class.isInstance(this)) {
            return UserType.PUBLIC_MEMBER.getLabel();
        } else if (BankWorker.class.isInstance(this)) {
            return UserType.BANK_WORKER.getLabel();
        }
        return null;
    }
    
    public List<Account> getPublicMemberBankAccounts() {
        if (PublicMember.class.isInstance(this)) {
            PublicMember publicMember = (PublicMember)this;
            return publicMember.getAccounts();
        }
        return null;
    }
    
    public List<Activity> getBankWorkerActivities() {
        if (BankWorker.class.isInstance(this)) {
            BankWorker bankWorker = (BankWorker)this;
            return bankWorker.getActivities();
        }
        return null;
    }
    
    
}
