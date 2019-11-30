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
import edu.kist_bit.hamrotarkaribazzar.entity.Productdetail;
import edu.kist_bit.hamrotarkaribazzar.entity.Supplierinfo;
import edu.kist_bit.hamrotarkaribazzar.services.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrator
 */
public class SupplierinfoJpaController implements Serializable {

    public SupplierinfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Supplierinfo supplierinfo) {
        if (supplierinfo.getProductdetailList() == null) {
            supplierinfo.setProductdetailList(new ArrayList<Productdetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Productdetail> attachedProductdetailList = new ArrayList<Productdetail>();
            for (Productdetail productdetailListProductdetailToAttach : supplierinfo.getProductdetailList()) {
                productdetailListProductdetailToAttach = em.getReference(productdetailListProductdetailToAttach.getClass(), productdetailListProductdetailToAttach.getId());
                attachedProductdetailList.add(productdetailListProductdetailToAttach);
            }
            supplierinfo.setProductdetailList(attachedProductdetailList);
            em.persist(supplierinfo);
            for (Productdetail productdetailListProductdetail : supplierinfo.getProductdetailList()) {
                Supplierinfo oldSuppIdOfProductdetailListProductdetail = productdetailListProductdetail.getSuppId();
                productdetailListProductdetail.setSuppId(supplierinfo);
                productdetailListProductdetail = em.merge(productdetailListProductdetail);
                if (oldSuppIdOfProductdetailListProductdetail != null) {
                    oldSuppIdOfProductdetailListProductdetail.getProductdetailList().remove(productdetailListProductdetail);
                    oldSuppIdOfProductdetailListProductdetail = em.merge(oldSuppIdOfProductdetailListProductdetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Supplierinfo supplierinfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Supplierinfo persistentSupplierinfo = em.find(Supplierinfo.class, supplierinfo.getId());
            List<Productdetail> productdetailListOld = persistentSupplierinfo.getProductdetailList();
            List<Productdetail> productdetailListNew = supplierinfo.getProductdetailList();
            List<Productdetail> attachedProductdetailListNew = new ArrayList<Productdetail>();
            for (Productdetail productdetailListNewProductdetailToAttach : productdetailListNew) {
                productdetailListNewProductdetailToAttach = em.getReference(productdetailListNewProductdetailToAttach.getClass(), productdetailListNewProductdetailToAttach.getId());
                attachedProductdetailListNew.add(productdetailListNewProductdetailToAttach);
            }
            productdetailListNew = attachedProductdetailListNew;
            supplierinfo.setProductdetailList(productdetailListNew);
            supplierinfo = em.merge(supplierinfo);
            for (Productdetail productdetailListOldProductdetail : productdetailListOld) {
                if (!productdetailListNew.contains(productdetailListOldProductdetail)) {
                    productdetailListOldProductdetail.setSuppId(null);
                    productdetailListOldProductdetail = em.merge(productdetailListOldProductdetail);
                }
            }
            for (Productdetail productdetailListNewProductdetail : productdetailListNew) {
                if (!productdetailListOld.contains(productdetailListNewProductdetail)) {
                    Supplierinfo oldSuppIdOfProductdetailListNewProductdetail = productdetailListNewProductdetail.getSuppId();
                    productdetailListNewProductdetail.setSuppId(supplierinfo);
                    productdetailListNewProductdetail = em.merge(productdetailListNewProductdetail);
                    if (oldSuppIdOfProductdetailListNewProductdetail != null && !oldSuppIdOfProductdetailListNewProductdetail.equals(supplierinfo)) {
                        oldSuppIdOfProductdetailListNewProductdetail.getProductdetailList().remove(productdetailListNewProductdetail);
                        oldSuppIdOfProductdetailListNewProductdetail = em.merge(oldSuppIdOfProductdetailListNewProductdetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = supplierinfo.getId();
                if (findSupplierinfo(id) == null) {
                    throw new NonexistentEntityException("The supplierinfo with id " + id + " no longer exists.");
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
            Supplierinfo supplierinfo;
            try {
                supplierinfo = em.getReference(Supplierinfo.class, id);
                supplierinfo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The supplierinfo with id " + id + " no longer exists.", enfe);
            }
            List<Productdetail> productdetailList = supplierinfo.getProductdetailList();
            for (Productdetail productdetailListProductdetail : productdetailList) {
                productdetailListProductdetail.setSuppId(null);
                productdetailListProductdetail = em.merge(productdetailListProductdetail);
            }
            em.remove(supplierinfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Supplierinfo> findSupplierinfoEntities() {
        return findSupplierinfoEntities(true, -1, -1);
    }

    public List<Supplierinfo> findSupplierinfoEntities(int maxResults, int firstResult) {
        return findSupplierinfoEntities(false, maxResults, firstResult);
    }

    private List<Supplierinfo> findSupplierinfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Supplierinfo.class));
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

    public Supplierinfo findSupplierinfo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Supplierinfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSupplierinfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Supplierinfo> rt = cq.from(Supplierinfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
