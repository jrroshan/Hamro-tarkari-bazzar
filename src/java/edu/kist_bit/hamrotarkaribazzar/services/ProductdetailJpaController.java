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
import edu.kist_bit.hamrotarkaribazzar.entity.ConsumableItems;
import edu.kist_bit.hamrotarkaribazzar.entity.Productdetail;
import edu.kist_bit.hamrotarkaribazzar.entity.Supplierinfo;
import edu.kist_bit.hamrotarkaribazzar.services.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrator
 */
public class ProductdetailJpaController implements Serializable {

    public ProductdetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productdetail productdetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConsumableItems consId = productdetail.getConsId();
            if (consId != null) {
                consId = em.getReference(consId.getClass(), consId.getId());
                productdetail.setConsId(consId);
            }
            Supplierinfo suppId = productdetail.getSuppId();
            if (suppId != null) {
                suppId = em.getReference(suppId.getClass(), suppId.getId());
                productdetail.setSuppId(suppId);
            }
            em.persist(productdetail);
            if (consId != null) {
                consId.getProductdetailList().add(productdetail);
                consId = em.merge(consId);
            }
            if (suppId != null) {
                suppId.getProductdetailList().add(productdetail);
                suppId = em.merge(suppId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productdetail productdetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productdetail persistentProductdetail = em.find(Productdetail.class, productdetail.getId());
            ConsumableItems consIdOld = persistentProductdetail.getConsId();
            ConsumableItems consIdNew = productdetail.getConsId();
            Supplierinfo suppIdOld = persistentProductdetail.getSuppId();
            Supplierinfo suppIdNew = productdetail.getSuppId();
            if (consIdNew != null) {
                consIdNew = em.getReference(consIdNew.getClass(), consIdNew.getId());
                productdetail.setConsId(consIdNew);
            }
            if (suppIdNew != null) {
                suppIdNew = em.getReference(suppIdNew.getClass(), suppIdNew.getId());
                productdetail.setSuppId(suppIdNew);
            }
            productdetail = em.merge(productdetail);
            if (consIdOld != null && !consIdOld.equals(consIdNew)) {
                consIdOld.getProductdetailList().remove(productdetail);
                consIdOld = em.merge(consIdOld);
            }
            if (consIdNew != null && !consIdNew.equals(consIdOld)) {
                consIdNew.getProductdetailList().add(productdetail);
                consIdNew = em.merge(consIdNew);
            }
            if (suppIdOld != null && !suppIdOld.equals(suppIdNew)) {
                suppIdOld.getProductdetailList().remove(productdetail);
                suppIdOld = em.merge(suppIdOld);
            }
            if (suppIdNew != null && !suppIdNew.equals(suppIdOld)) {
                suppIdNew.getProductdetailList().add(productdetail);
                suppIdNew = em.merge(suppIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productdetail.getId();
                if (findProductdetail(id) == null) {
                    throw new NonexistentEntityException("The productdetail with id " + id + " no longer exists.");
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
            Productdetail productdetail;
            try {
                productdetail = em.getReference(Productdetail.class, id);
                productdetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productdetail with id " + id + " no longer exists.", enfe);
            }
            ConsumableItems consId = productdetail.getConsId();
            if (consId != null) {
                consId.getProductdetailList().remove(productdetail);
                consId = em.merge(consId);
            }
            Supplierinfo suppId = productdetail.getSuppId();
            if (suppId != null) {
                suppId.getProductdetailList().remove(productdetail);
                suppId = em.merge(suppId);
            }
            em.remove(productdetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productdetail> findProductdetailEntities() {
        return findProductdetailEntities(true, -1, -1);
    }

    public List<Productdetail> findProductdetailEntities(int maxResults, int firstResult) {
        return findProductdetailEntities(false, maxResults, firstResult);
    }

    private List<Productdetail> findProductdetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productdetail.class));
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

    public Productdetail findProductdetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productdetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductdetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productdetail> rt = cq.from(Productdetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
