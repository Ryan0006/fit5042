/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository;

import fit5042.assign.repository.entities.Transaction;
import fit5042.assign.interfaces.TransactionRepository;
import fit5042.assign.interfaces.TransactionTypeManager;
import fit5042.assign.repository.entities.Account;
import fit5042.assign.repository.entities.User;
import java.util.List;
import javax.ejb.EJB;
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
public class JPATransRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Transaction searchTransactionByNo(int transNo) throws Exception {
        return entityManager.find(Transaction.class, transNo);
    }

    @Override
    public List<Transaction> getAllTransactions() throws Exception {
        return entityManager.createNamedQuery(Transaction.GET_ALL_TRANS).getResultList();
    }

    @Override
    public List<Transaction> searchTransactionByName(String transName) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> transaction = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(transaction)
                .where(criteriaBuilder
                        .like(criteriaBuilder
                                .lower(transaction.<String>get("transactionName")), "%" + transName.toLowerCase() + "%"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Transaction> searchTransactionByType(String transType) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> transactions = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(transactions)
                .where(criteriaBuilder
                        .equal(criteriaBuilder
                                .lower(transactions.<String>get("transactionType")), transType.toLowerCase()));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Transaction> searchTransactionByDescription(String description) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> transaction = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(transaction)
                .where(criteriaBuilder
                        .like(criteriaBuilder
                                .lower(transaction.<String>get("description")), "%" + description.toLowerCase() + "%"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Transaction> getTransactionByUser(User user) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> transaction = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(transaction).where(criteriaBuilder.equal(transaction.get("user"), user));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
