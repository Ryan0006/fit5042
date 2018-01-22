/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.utility.UserType;
import java.io.Serializable;
import java.util.regex.Pattern;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    private int userId;
    private String username;
    private String lastName;
    private String firstName;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private UserType userType;

    public UserBean() {
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    public void validatePhoneNumber(FacesContext context, UIComponent toValidate, Object value) {
        Pattern pLandline = Pattern.compile("9[0-9]{7}");
        Pattern pMobile = Pattern.compile("0[0-9]{9}");
        String phoneNo = (String) value;
        if (!(pLandline.matcher(phoneNo).matches() || pMobile.matcher(phoneNo).matches())) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Format: 9XXXXXXX or 0XXXXXXXXX");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
}
