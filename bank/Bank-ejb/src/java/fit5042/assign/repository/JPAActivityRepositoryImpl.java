/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository;

import fit5042.assign.interfaces.ActivityRepository;
import fit5042.assign.interfaces.UserRepository;
import fit5042.assign.repository.entities.Activity;
import fit5042.assign.repository.entities.BankWorker;
import fit5042.assign.repository.entities.User;
import fit5042.assign.utility.TransactionType;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jerrychen
 */
@Stateless
public class JPAActivityRepositoryImpl implements ActivityRepository {

    @EJB
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Activity> getActivityByUser(User user) throws Exception {
        Query query = entityManager.createNamedQuery(Activity.GET_ACTIVITY_BY_USER);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public void transactionTypeInsertion(String type, User user) throws Exception {
        Activity activity = new Activity("add transaction type",
                user.getUsername() + " add a new transaction type as " + type,
                user);
        List<Activity> activities = user.getBankWorkerActivities();
        activities.add(activity);
        entityManager.merge(user);

    }

    @Override
    public void transactionTypeDeletion(TransactionType type, User user) throws Exception {
        Activity activity = new Activity("delete transaction type",
                user.getUsername() + " remove the transaction type of " + type.getType(),
                user);
        List<Activity> activities = user.getBankWorkerActivities();
        activities.add(activity);
        entityManager.merge(user);

    }

    @Override
    public void userInsertion(User targetedUser, User user) throws Exception {
        User modifiedUser = userRepository.searchUserByUsername(targetedUser.getUsername());
        Activity activity = new Activity("add user",
                user.getUsername() + " add a new user (id=" + modifiedUser.getUserId() + ")",
                user);
        List<Activity> activities = user.getBankWorkerActivities();
        activities.add(activity);
        entityManager.merge(user);

    }

    @Override
    public void userDeletion(User targetedUser, User user) throws Exception {
        Activity activity = new Activity("delete user",
                user.getUsername() + " delete user (id=" + targetedUser.getUserId() + ")",
                user);
        List<Activity> activities = user.getBankWorkerActivities();
        activities.add(activity);
        entityManager.merge(user);

    }

    @Override
    public void userEdition(User targetedUser, User user) throws Exception {
        Activity activity = new Activity("edit user",
                user.getUsername() + " edit user (id=" + targetedUser.getUserId() + ")",
                user);
        List<Activity> activities = user.getBankWorkerActivities();
        activities.add(activity);
        entityManager.merge(user);

    }

    public void persist(Object object) {
        entityManager.persist(object);
    }
}
