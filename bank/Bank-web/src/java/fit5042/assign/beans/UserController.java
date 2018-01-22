/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.beans;

import fit5042.assign.interfaces.ActivityRepository;
import fit5042.assign.interfaces.GroupRepository;
import fit5042.assign.repository.entities.User;
import fit5042.assign.interfaces.UserRepository;
import fit5042.assign.utility.UserType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jerrychen
 */
@Named(value = "userController")
@RequestScoped
public class UserController implements Serializable {

    @EJB
    private GroupRepository groupRepository;

    @EJB
    UserRepository userRepository;

    @Inject
    UserBean userBean;

    @Inject
    CurrentUser currentUser;

    @EJB
    ActivityRepository activityRepository;

    private List<User> users;
    private List<String> searchOptions;
    private String keyword;
    private boolean showForm;
    private String selectedOption;
    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private List<String> types;
    private String email;
    private String selectedType;

    public UserController() {
    }

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        types = new ArrayList<>();
        try {
            viewAll();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        searchOptions = new ArrayList<>();
        searchOptions.add("ID");
        searchOptions.add("first name");
        searchOptions.add("last name");
        searchOptions.add("Email");
        types.add("");
        types.add("public member");
        types.add("bank worker");
        showForm = true;
    }

    public List<String> getSearchOptions() {
        return searchOptions;
    }

    public void setSearchOptions(List<String> searchOptions) {
        this.searchOptions = searchOptions;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public List<User> getUsers() {
        return users;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String deleteUser(int userId) throws Exception {
        User user = userRepository.searchUserById(userId);
        activityRepository.userDeletion(user, currentUser.getUser());
        userRepository.removeUser(userId);
        groupRepository.deleteGroup(user.getUsername());
        return "/bankworker/users?faces-redirect=true";
    }

    public void viewAll() throws Exception {
        users = userRepository.getAllUsers();
        if (users.isEmpty()) {
            showForm = false;
        }
    }

    public void search() throws Exception {

        switch (selectedOption) {
            case "ID":
                User userById = userRepository.searchUserById(Integer.valueOf(keyword));
                if (userById == null) {
                    showForm = false;
                } else {
                    users.clear();
                    users.add(userById);
                }
                break;
            case "first name":
                users = userRepository.searchUserByFirstName(keyword);
                if (users.isEmpty()) {
                    showForm = false;
                }
                break;
            case "last name":
                users = userRepository.searchUserByLastName(keyword);
                if (users.isEmpty()) {
                    showForm = false;
                }
                break;
            case "Email":
                User userByEmail = userRepository.searchUserByEmail(keyword);
                if (userByEmail == null) {
                    showForm = false;
                } else {
                    users.add(userByEmail);
                }
                break;
        }
    }

    public void combinationSearch() throws Exception {

        switch (selectedType) {
            case "":
                users = userRepository.searchUserByCombination(userId, firstName, lastName, null, email);
                if (users.isEmpty()) {
                    showForm = false;
                }
                break;
            case "public member":
                users = userRepository.searchUserByCombination(userId, firstName, lastName, UserType.PUBLIC_MEMBER, email);
                if (users.isEmpty()) {
                    showForm = false;
                }
                break;
            case "bank worker":
                users = userRepository.searchUserByCombination(userId, firstName, lastName, UserType.BANK_WORKER, email);
                if (users.isEmpty()) {
                    showForm = false;
                }
                break;
        }
    }
}
