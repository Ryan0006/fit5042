/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository;

import com.google.common.base.Predicates;
import fit5042.assign.repository.entities.User;
import fit5042.assign.interfaces.UserRepository;
import fit5042.assign.repository.entities.Activity;
import fit5042.assign.repository.entities.BankWorker;
import fit5042.assign.repository.entities.PublicMember;
import fit5042.assign.utility.UserType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author jerrychen
 */
@Stateless
public class JPAUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public User searchUserById(int userId) throws Exception {
        return entityManager.find(User.class, userId);
    }
    
    @Override
    public User searchUserByUsername(String username) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.select(user).where(criteriaBuilder.equal(user.get("username"), username));
        List<User> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return entityManager.createNamedQuery(User.GET_ALL_USER).getResultList();
    }

    @Override
    public void removeUser(int userId) throws Exception {
        User user = searchUserById(userId);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    /**
     *
     * @param user
     * @throws Exception
     */
    @Override
    public void editUser(User user) throws Exception {
        entityManager.merge(user);
    }

    /**
     *
     * @param firstName
     * @return
     * @throws Exception
     */
    @Override
    public List<User> searchUserByFirstName(String firstName) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.select(user)
                .where(criteriaBuilder
                        .equal(criteriaBuilder
                                .lower(user.<String>get("firstName")), firstName.toLowerCase()));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     *
     * @param lastName
     * @return
     * @throws Exception
     */
    @Override
    public List<User> searchUserByLastName(String lastName) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.select(user)
                .where(criteriaBuilder
                        .equal(criteriaBuilder
                                .lower(user.<String>get("lastName")), lastName.toLowerCase()));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public User searchUserByEmail(String email) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.select(user).where(criteriaBuilder.equal(user.get("email"), email));
        List<User> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<User> searchUserByCombination(int userId, String firstName, String lastName, UserType type, String email) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        Predicate pId = criteriaBuilder.equal(user.get("userId"), userId);
        Predicate pFirstName = criteriaBuilder.like(criteriaBuilder.lower(user.<String>get("firstName")), "%" + firstName.toLowerCase() + "%");
        Predicate pLastName = criteriaBuilder.like(criteriaBuilder.lower(user.<String>get("lastName")), "%" + lastName.toLowerCase() + "%");
        Predicate pEmail = criteriaBuilder.like(user.<String>get("email"), "%" + email + "%");
        List<Predicate> predicates = new ArrayList<>();
        if (userId != 0) {
            predicates.add(pId);
        }
        if (!firstName.isEmpty()) {
            predicates.add(pFirstName);
        }
        if (!lastName.isEmpty()) {
            predicates.add(pLastName);
        }
        if (!email.isEmpty()) {
            predicates.add(pEmail);
        }
        criteriaQuery.select(user).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        List<User> initialResult = entityManager.createQuery(criteriaQuery).getResultList();
        if (type == null) {
            return initialResult;
        }
        List<User> finalResult = new ArrayList<>();
        if (type == UserType.PUBLIC_MEMBER) {
            for (User u : initialResult) {
                if (PublicMember.class.isInstance(u)) {
                    finalResult.add(u);
                }
            }
        }
        if (type == UserType.BANK_WORKER) {
            for (User u : initialResult) {
                if (BankWorker.class.isInstance(u)) {
                    finalResult.add(u);
                }
            }
        }
        return finalResult;
    }
    
    
}
