/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.ActivityRepository;
import fit5042.assign.interfaces.GroupRepository;
import fit5042.assign.repository.entities.BankWorker;
import fit5042.assign.repository.entities.PublicMember;
import fit5042.assign.interfaces.UserRepository;
import fit5042.assign.repository.entities.User;
import fit5042.assign.utility.UserType;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@RequestScoped
@Named("addUser")
public class AddUser {

    @EJB
    private GroupRepository groupRepository;

    @Inject
    CurrentUser currentUser;

    @EJB
    ActivityRepository activityRepository;

    @EJB
    UserRepository userRepository;

    @Inject
    UserBean userBean;

    public AddUser() {
    }

    public String addNewUser() throws Exception {
        User user = null;
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
        if (userBean.getUserType() == UserType.BANK_WORKER) {
            user = new BankWorker(userBean.getUsername(), userBean.getLastName(), userBean.getFirstName(), userBean.getEmail(),
                    hexString.toString(), userBean.getAddress(), userBean.getPhoneNumber());
            userRepository.addUser(user);
            groupRepository.addGroup(userBean.getUsername(), "bankworker");
        } else if (userBean.getUserType() == UserType.PUBLIC_MEMBER) {
            user = new PublicMember(userBean.getUsername(), userBean.getLastName(), userBean.getFirstName(), userBean.getEmail(),
                    hexString.toString(), userBean.getAddress(), userBean.getPhoneNumber());
            userRepository.addUser(user);
            groupRepository.addGroup(userBean.getUsername(), "publicmember");
        }
        activityRepository.userInsertion(user, currentUser.getUser());
        return "/bankworker/users?faces-redirect=true";
    }
}
