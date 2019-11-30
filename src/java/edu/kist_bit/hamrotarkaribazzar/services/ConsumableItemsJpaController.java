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
import edu.kist_bit.hamrotarkaribazzar.entity.Productdetail;
import edu.kist_bit.hamrotarkaribazzar.services.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrator
 */
public class ConsumableItemsJpaController implements Serializable {

    public ConsumableItemsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConsumableItems consumableItems) {
        if (consumableItems.getProductdetailList() == null) {
            consumableItems.setProductdetailList(new ArrayList<Productdetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category catgId = consumableItems.getCatgId();
            if (catgId != null) {
                catgId = em.getReference(catgId.getClass(), catgId.getId());
                consumableItems.setCatgId(catgId);
            }
            Subcategory subcatgId = consumableItems.getSubcatgId();
            if (subcatgId != null) {
                subcatgId = em.getReference(subcatgId.getClass(), subcatgId.getId());
                consumableItems.setSubcatgId(subcatgId);
            }
            List<Productdetail> attachedProductdetailList = new ArrayList<Productdetail>();
            for (Productdetail productdetailListProductdetailToAttach : consumableItems.getProductdetailList()) {
                productdetailListProductdetailToAttach = em.getReference(productdetailListProductdetailToAttach.getClass(), productdetailListProductdetailToAttach.getId());
                attachedProductdetailList.add(productdetailListProductdetailToAttach);
            }
            consumableItems.setProductdetailList(attachedProductdetailList);
            em.persist(consumableItems);
            if (catgId != null) {
                catgId.getConsumableItemsList().add(consumableItems);
                catgId = em.merge(catgId);
            }
            if (subcatgId != null) {
                subcatgId.getConsumableItemsList().add(consumableItems);
                subcatgId = em.merge(subcatgId);
            }
            for (Productdetail productdetailListProductdetail : consumableItems.getProductdetailList()) {
                ConsumableItems oldConsIdOfProductdetailListProductdetail = productdetailListProductdetail.getConsId();
                productdetailListProductdetail.setConsId(consumableItems);
                productdetailListProductdetail = em.merge(productdetailListProductdetail);
                if (oldConsIdOfProductdetailListProductdetail != null) {
                    oldConsIdOfProductdetailListProductdetail.getProductdetailList().remove(productdetailListProductdetail);
                    oldConsIdOfProductdetailListProductdetail = em.merge(oldConsIdOfProductdetailListProductdetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConsumableItems consumableItems) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConsumableItems persistentConsumableItems = em.find(ConsumableItems.class, consumableItems.getId());
            Category catgIdOld = persistentConsumableItems.getCatgId();
            Category catgIdNew = consumableItems.getCatgId();
            Subcategory subcatgIdOld = persistentConsumableItems.getSubcatgId();
            Subcategory subcatgIdNew = consumableItems.getSubcatgId();
            List<Productdetail> productdetailListOld = persistentConsumableItems.getProductdetailList();
            List<Productdetail> productdetailListNew = consumableItems.getProductdetailList();
            if (catgIdNew != null) {
                catgIdNew = em.getReference(catgIdNew.getClass(), catgIdNew.getId());
                consumableItems.setCatgId(catgIdNew);
            }
            if (subcatgIdNew != null) {
                subcatgIdNew = em.getReference(subcatgIdNew.getClass(), subcatgIdNew.getId());
                consumableItems.setSubcatgId(subcatgIdNew);
            }
            List<Productdetail> attachedProductdetailListNew = new ArrayList<Productdetail>();
            for (Productdetail productdetailListNewProductdetailToAttach : productdetailListNew) {
                productdetailListNewProductdetailToAttach = em.getReference(productdetailListNewProductdetailToAttach.getClass(), productdetailListNewProductdetailToAttach.getId());
                attachedProductdetailListNew.add(productdetailListNewProductdetailToAttach);
            }
            productdetailListNew = attachedProductdetailListNew;
            consumableItems.setProductdetailList(productdetailListNew);
            consumableItems = em.merge(consumableItems);
            if (catgIdOld != null && !catgIdOld.equals(catgIdNew)) {
                catgIdOld.getConsumableItemsList().remove(consumableItems);
                catgIdOld = em.merge(catgIdOld);
            }
            if (catgIdNew != null && !catgIdNew.equals(catgIdOld)) {
                catgIdNew.getConsumableItemsList().add(consumableItems);
                catgIdNew = em.merge(catgIdNew);
            }
            if (subcatgIdOld != null && !subcatgIdOld.equals(subcatgIdNew)) {
                subcatgIdOld.getConsumableItemsList().remove(consumableItems);
                subcatgIdOld = em.merge(subcatgIdOld);
            }
            if (subcatgIdNew != null && !subcatgIdNew.equals(subcatgIdOld)) {
                subcatgIdNew.getConsumableItemsList().add(consumableItems);
                subcatgIdNew = em.merge(subcatgIdNew);
            }
            for (Productdetail productdetailListOldProductdetail : productdetailListOld) {
                if (!productdetailListNew.contains(productdetailListOldProductdetail)) {
                    productdetailListOldProductdetail.setConsId(null);
                    productdetailListOldProductdetail = em.merge(productdetailListOldProductdetail);
                }
            }
            for (Productdetail productdetailListNewProductdetail : productdetailListNew) {
                if (!productdetailListOld.contains(productdetailListNewProductdetail)) {
                    ConsumableItems oldConsIdOfProductdetailListNewProductdetail = productdetailListNewProductdetail.getConsId();
                    productdetailListNewProductdetail.setConsId(consumableItems);
                    productdetailListNewProductdetail = em.merge(productdetailListNewProductdetail);
                    if (oldConsIdOfProductdetailListNewProductdetail != null && !oldConsIdOfProductdetailListNewProductdetail.equals(consumableItems)) {
                        oldConsIdOfProductdetailListNewProductdetail.getProductdetailList().remove(productdetailListNewProductdetail);
                        oldConsIdOfProductdetailListNewProductdetail = em.merge(oldConsIdOfProductdetailListNewProductdetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consumableItems.getId();
                if (findConsumableItems(id) == null) {
                    throw new NonexistentEntityException("The consumableItems with id " + id + " no longer exists.");
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
            ConsumableItems consumableItems;
            try {
                consumableItems = em.getReference(ConsumableItems.class, id);
                consumableItems.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consumableItems with id " + id + " no longer exists.", enfe);
            }
            Category catgId = consumableItems.getCatgId();
            if (catgId != null) {
                catgId.getConsumableItemsList().remove(consumableItems);
                catgId = em.merge(catgId);
            }
            Subcategory subcatgId = consumableItems.getSubcatgId();
            if (subcatgId != null) {
                subcatgId.getConsumableItemsList().remove(consumableItems);
                subcatgId = em.merge(subcatgId);
            }
            List<Productdetail> productdetailList = consumableItems.getProductdetailList();
            for (Productdetail productdetailListProductdetail : productdetailList) {
                productdetailListProductdetail.setConsId(null);
                productdetailListProductdetail = em.merge(productdetailListProductdetail);
            }
            em.remove(consumableItems);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConsumableItems> findConsumableItemsEntities() {
        return findConsumableItemsEntities(true, -1, -1);
    }

    public List<ConsumableItems> findConsumableItemsEntities(int maxResults, int firstResult) {
        return findConsumableItemsEntities(false, maxResults, firstResult);
    }

    private List<ConsumableItems> findConsumableItemsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConsumableItems.class));
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

    public ConsumableItems findConsumableItems(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConsumableItems.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsumableItemsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConsumableItems> rt = cq.from(ConsumableItems.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
