/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.jpa;

import fit5042.assign.webservices.entity.TransactionType;
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
public class JPATransactionType implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean addType(String type) {
        if (searchTypeByName(type).isEmpty()) {
            entityManager.persist(new TransactionType(type));
        }
        return true;
    }

    public TransactionType searchTypeById(int typeId) {
        return entityManager.find(TransactionType.class, typeId);
    }

    public List<TransactionType> getAllTypes() {
        return entityManager.createNamedQuery(TransactionType.GET_ALL_TYPES).getResultList();
    }

    public boolean removeType(String type) {
        List<TransactionType> types = searchTypeByName(type);
        if (types.isEmpty()) {
            return false;
        }
        entityManager.remove(types.get(0));
        return true;
    }

    public void editType(TransactionType type) {
        entityManager.merge(type);
    }

    public List<TransactionType> searchTypeByName(String type) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TransactionType> query = builder.createQuery(TransactionType.class);
        Root<TransactionType> types = query.from(TransactionType.class);
        query.select(types).where(builder.equal(types.get("type"), type));
        return entityManager.createQuery(query).getResultList();
    }
}
