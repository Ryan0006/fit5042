/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.UserRepository;
import fit5042.assign.repository.entities.PublicMember;
import fit5042.assign.repository.entities.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named
@RequestScoped
public class Login implements Serializable {

    @EJB
    private UserRepository userRepository;

    @Inject
    CurrentUser currentUser;

    private User user = null;
    private boolean login = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Login() {
    }

    @PostConstruct
    public void init() {
        try {
            user = userRepository.searchUserByUsername(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user == null) {
            return;
        }
        login = true;
        currentUser.setUser(user);
        currentUser.setLogin(true);
        currentUser.createMenu();
        if (PublicMember.class.isInstance(user)) {
            currentUser.setPublicMember(true);
            currentUser.setCurrentPage("/publicmember/account");
        } else {
            currentUser.setPublicMember(false);
            currentUser.setCurrentPage("/bankworker/users");
        }
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String userLogin() throws Exception {

        return currentUser.getCurrentPage() + "?faces-redirect=true";
    }

}
