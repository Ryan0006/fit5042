/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.ActivityRepository;
import fit5042.assign.repository.entities.BankWorker;
import fit5042.assign.repository.entities.PublicMember;
import fit5042.assign.repository.entities.User;
import fit5042.assign.interfaces.UserRepository;
import fit5042.assign.utility.UserType;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@SessionScoped
@Named("editUser")
public class EditUser implements Serializable {

    @Inject
    CurrentUser currentUser;

    @EJB
    ActivityRepository activityRepository;

    @EJB
    UserRepository userRepository;

    @Inject
    UserBean userBean;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public EditUser() {
    }


    public String initId() {
        int userId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("userId"));
        try {
            user = userRepository.searchUserById(userId);
        } catch (Exception ex) {
            Logger.getLogger(EditUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/bankworker/edituser?faces-redirect=true";
    }

    public String editUser() throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(userBean.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        user.setFirstName(userBean.getFirstName());
        user.setLastName(userBean.getLastName());
        user.setEmail(userBean.getEmail());
        user.setPassword(hexString.toString());
        user.setAddress(userBean.getAddress());
        user.setPhoneNumber(userBean.getPhoneNumber());
        userRepository.editUser(user);
        activityRepository.userEdition(user, currentUser.getUser());
        return "/bankworker/users?faces-redirect=true";
    }

}
