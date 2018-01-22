/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository;

import fit5042.assign.interfaces.AccountRepository;
import fit5042.assign.repository.entities.Account;
import fit5042.assign.repository.entities.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jerrychen
 */
@Stateless
public class JPAAccountRepositoryImpl implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account searchAccountByNo(int cardNo) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> account = criteriaQuery.from(Account.class);
        criteriaQuery.select(account).where(criteriaBuilder.equal(account.get("cardNo"), cardNo));
        List<Account> results = entityManager.createQuery(criteriaQuery).getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    @Override
    public Account searchAccountById(int accountId) throws Exception {
        return entityManager.find(Account.class, accountId);
    }

    @Override
    public List<Account> getAllAccounts() throws Exception {
        return entityManager.createNamedQuery(Account.GET_ALL_ACCOUNT).getResultList();
    }

    @Override
    public List<Account> getAccountByOwner(User owner) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> query = builder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.select(root).where(builder.equal(root.get("owner"), owner));
        return entityManager.createQuery(query).getResultList();
    }
}
