/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.interfaces;

import fit5042.assign.repository.entities.Activity;
import fit5042.assign.repository.entities.User;
import fit5042.assign.utility.UserType;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jerrychen
 */
@Remote
public interface UserRepository {

    public void addUser(User user) throws Exception;

    public User searchUserById(int userId) throws Exception;
    
    public User searchUserByUsername(String username) throws Exception;

    public List<User> getAllUsers() throws Exception;
    
    public void removeUser(int userId) throws Exception;

    public void editUser(User user) throws Exception;

    public List<User> searchUserByFirstName(String firstName) throws Exception;
    
    public List<User> searchUserByLastName(String lastName) throws Exception;
    
    public User searchUserByEmail(String email) throws Exception;
    
    public List<User> searchUserByCombination(int userId, String firstName, String lastName, UserType type, String email) throws Exception;
    
    
}
