/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.hamrotarkaribazzar.services;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.kist_bit.hamrotarkaribazzar.entity.Category;
import edu.kist_bit.hamrotarkaribazzar.entity.ConsumableItems;
import edu.kist_bit.hamrotarkaribazzar.entity.Subcategory;
import edu.kist_bit.hamrotarkaribazzar.services.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrator
 */
public class SubcategoryJpaController implements Serializable {

    public SubcategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Subcategory subcategory) {
        if (subcategory.getConsumableItemsList() == null) {
            subcategory.setConsumableItemsList(new ArrayList<ConsumableItems>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category categoryId = subcategory.getCategoryId();
            if (categoryId != null) {
                categoryId = em.getReference(categoryId.getClass(), categoryId.getId());
                subcategory.setCategoryId(categoryId);
            }
            List<ConsumableItems> attachedConsumableItemsList = new ArrayList<ConsumableItems>();
            for (ConsumableItems consumableItemsListConsumableItemsToAttach : subcategory.getConsumableItemsList()) {
                consumableItemsListConsumableItemsToAttach = em.getReference(consumableItemsListConsumableItemsToAttach.getClass(), consumableItemsListConsumableItemsToAttach.getId());
                attachedConsumableItemsList.add(consumableItemsListConsumableItemsToAttach);
            }
            subcategory.setConsumableItemsList(attachedConsumableItemsList);
            em.persist(subcategory);
            if (categoryId != null) {
                categoryId.getSubcategoryList().add(subcategory);
                categoryId = em.merge(categoryId);
            }
            for (ConsumableItems consumableItemsListConsumableItems : subcategory.getConsumableItemsList()) {
                Subcategory oldSubcatgIdOfConsumableItemsListConsumableItems = consumableItemsListConsumableItems.getSubcatgId();
                consumableItemsListConsumableItems.setSubcatgId(subcategory);
                consumableItemsListConsumableItems = em.merge(consumableItemsListConsumableItems);
                if (oldSubcatgIdOfConsumableItemsListConsumableItems != null) {
                    oldSubcatgIdOfConsumableItemsListConsumableItems.getConsumableItemsList().remove(consumableItemsListConsumableItems);
                    oldSubcatgIdOfConsumableItemsListConsumableItems = em.merge(oldSubcatgIdOfConsumableItemsListConsumableItems);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Subcategory subcategory) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subcategory persistentSubcategory = em.find(Subcategory.class, subcategory.getId());
            Category categoryIdOld = persistentSubcategory.getCategoryId();
            Category categoryIdNew = subcategory.getCategoryId();
            List<ConsumableItems> consumableItemsListOld = persistentSubcategory.getConsumableItemsList();
            List<ConsumableItems> consumableItemsListNew = subcategory.getConsumableItemsList();
            if (categoryIdNew != null) {
                categoryIdNew = em.getReference(categoryIdNew.getClass(), categoryIdNew.getId());
                subcategory.setCategoryId(categoryIdNew);
            }
            List<ConsumableItems> attachedConsumableItemsListNew = new ArrayList<ConsumableItems>();
            for (ConsumableItems consumableItemsListNewConsumableItemsToAttach : consumableItemsListNew) {
                consumableItemsListNewConsumableItemsToAttach = em.getReference(consumableItemsListNewConsumableItemsToAttach.getClass(), consumableItemsListNewConsumableItemsToAttach.getId());
                attachedConsumableItemsListNew.add(consumableItemsListNewConsumableItemsToAttach);
            }
            consumableItemsListNew = attachedConsumableItemsListNew;
            subcategory.setConsumableItemsList(consumableItemsListNew);
            subcategory = em.merge(subcategory);
            if (categoryIdOld != null && !categoryIdOld.equals(categoryIdNew)) {
                categoryIdOld.getSubcategoryList().remove(subcategory);
                categoryIdOld = em.merge(categoryIdOld);
            }
            if (categoryIdNew != null && !categoryIdNew.equals(categoryIdOld)) {
                categoryIdNew.getSubcategoryList().add(subcategory);
                categoryIdNew = em.merge(categoryIdNew);
            }
            for (ConsumableItems consumableItemsListOldConsumableItems : consumableItemsListOld) {
                if (!consumableItemsListNew.contains(consumableItemsListOldConsumableItems)) {
                    consumableItemsListOldConsumableItems.setSubcatgId(null);
                    consumableItemsListOldConsumableItems = em.merge(consumableItemsListOldConsumableItems);
                }
            }
            for (ConsumableItems consumableItemsListNewConsumableItems : consumableItemsListNew) {
                if (!consumableItemsListOld.contains(consumableItemsListNewConsumableItems)) {
                    Subcategory oldSubcatgIdOfConsumableItemsListNewConsumableItems = consumableItemsListNewConsumableItems.getSubcatgId();
                    consumableItemsListNewConsumableItems.setSubcatgId(subcategory);
                    consumableItemsListNewConsumableItems = em.merge(consumableItemsListNewConsumableItems);
                    if (oldSubcatgIdOfConsumableItemsListNewConsumableItems != null && !oldSubcatgIdOfConsumableItemsListNewConsumableItems.equals(subcategory)) {
                        oldSubcatgIdOfConsumableItemsListNewConsumableItems.getConsumableItemsList().remove(consumableItemsListNewConsumableItems);
                        oldSubcatgIdOfConsumableItemsListNewConsumableItems = em.merge(oldSubcatgIdOfConsumableItemsListNewConsumableItems);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subcategory.getId();
                if (findSubcategory(id) == null) {
                    throw new NonexistentEntityException("The subcategory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subcategory subcategory;
            try {
                subcategory = em.getReference(Subcategory.class, id);
                subcategory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subcategory with id " + id + " no longer exists.", enfe);
            }
            Category categoryId = subcategory.getCategoryId();
            if (categoryId != null) {
                categoryId.getSubcategoryList().remove(subcategory);
                categoryId = em.merge(categoryId);
            }
            List<ConsumableItems> consumableItemsList = subcategory.getConsumableItemsList();
            for (ConsumableItems consumableItemsListConsumableItems : consumableItemsList) {
                consumableItemsListConsumableItems.setSubcatgId(null);
                consumableItemsListConsumableItems = em.merge(consumableItemsListConsumableItems);
            }
            em.remove(subcategory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Subcategory> findSubcategoryEntities() {
        return findSubcategoryEntities(true, -1, -1);
    }

    public List<Subcategory> findSubcategoryEntities(int maxResults, int firstResult) {
        return findSubcategoryEntities(false, maxResults, firstResult);
    }

    private List<Subcategory> findSubcategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subcategory.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Subcategory findSubcategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Subcategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubcategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subcategory> rt = cq.from(Subcategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
