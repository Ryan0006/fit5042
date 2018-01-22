/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository;

import fit5042.assign.interfaces.GroupRepository;
import fit5042.assign.repository.entities.Groups;
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
public class JPAGroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addGroup(String username, String groupname) throws Exception {
        entityManager.persist(new Groups(username, groupname));
    }

    @Override
    public Groups searchByUsername(String username) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Groups> criteriaQuery = criteriaBuilder.createQuery(Groups.class);
        Root<Groups> groups = criteriaQuery.from(Groups.class);
        criteriaQuery.select(groups).where(criteriaBuilder.equal(groups.get("username"), username));
        List<Groups> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void deleteGroup(String username) throws Exception {
        Groups groups = searchByUsername(username);
        if (groups != null) {
            entityManager.remove(groups);
        }
    }
}
