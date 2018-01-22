/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.repository.entities.BankWorker;
import fit5042.assign.repository.entities.PublicMember;
import fit5042.assign.repository.entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named
@SessionScoped
public class CurrentUser implements Serializable {

//    @EJB
//    private UserRepository userRepository;

    private List<String> menuOptions;
    private User user;
    private boolean login = false;
    private boolean publicMember;
    private String currentPage;
    private Map<String, String> currentMap;

    public CurrentUser() {
    }

    @PostConstruct
    public void init() {
        currentMap = new HashMap<>();
        menuOptions = new ArrayList<>();
        user = null;
        login = false;
    }

    public boolean isPublicMember() {
        return publicMember;
    }

    public void setPublicMember(boolean publicMember) {
        this.publicMember = publicMember;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(List<String> menuOptions) {
        this.menuOptions = menuOptions;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String logout() {
        setUser(null);
        setLogin(false);
        menuOptions.clear();
        currentMap.clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    public String returnFromTransDetail() {
        if (PublicMember.class.isInstance(user)) {
            return "/publicmember/publictrans?faces-redirect=true";
        }
        return "/bankworker/transactions?faces-redirect=true";
    }

    public void createMenu() {
        if (PublicMember.class.isInstance(user)) {
            currentMap.put("/publicmember/account", "View accounts");
            currentMap.put("/publicmember/publictrans", "View transactions");
            menuOptions.add("View accounts");
            menuOptions.add("View transactions");
        } else if (BankWorker.class.isInstance(user)) {
            currentMap.put("/bankworker/users", "View all users");
            currentMap.put("/bankworker/transactions", "View transactions");
            currentMap.put("/bankworker/transtype", "Manage transaction types");
            menuOptions.add("View all users");
            menuOptions.add("View transactions");
            menuOptions.add("Manage transaction types");
        }
    }

    public String navigate(String option) {
        for (Entry<String, String> entry : currentMap.entrySet()) {
            if (option.equals(entry.getValue())) {
                currentPage = entry.getKey();
                return currentPage + "?faces-redirect=true";
            }
        }
        return null;
    }

    public String redirect() {
        return currentPage + "?faces-redirect=true"; 
    }
}
