/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.hamrotarkaribazzar.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "productdetail", catalog = "tarkaribazzar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productdetail.findAll", query = "SELECT p FROM Productdetail p")
    , @NamedQuery(name = "Productdetail.findById", query = "SELECT p FROM Productdetail p WHERE p.id = :id")
    , @NamedQuery(name = "Productdetail.findByDateProduct", query = "SELECT p FROM Productdetail p WHERE p.dateProduct = :dateProduct")
    , @NamedQuery(name = "Productdetail.findByQuantity", query = "SELECT p FROM Productdetail p WHERE p.quantity = :quantity")
    , @NamedQuery(name = "Productdetail.findByQtyreturn", query = "SELECT p FROM Productdetail p WHERE p.qtyreturn = :qtyreturn")
    , @NamedQuery(name = "Productdetail.findByBillno", query = "SELECT p FROM Productdetail p WHERE p.billno = :billno")
    , @NamedQuery(name = "Productdetail.findByRemark", query = "SELECT p FROM Productdetail p WHERE p.remark = :remark")
    , @NamedQuery(name = "Productdetail.findByRegisterby", query = "SELECT p FROM Productdetail p WHERE p.registerby = :registerby")})
public class Productdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date_product")
    @Temporal(TemporalType.DATE)
    private Date dateProduct;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "qtyreturn")
    private int qtyreturn;
    @Basic(optional = false)
    @Column(name = "billno")
    private String billno;
    @Basic(optional = false)
    @Column(name = "remark")
    private String remark;
    @Column(name = "registerby")
    private String registerby;
    @JoinColumn(name = "cons_id", referencedColumnName = "code")
    @ManyToOne
    private ConsumableItems consId;
    @JoinColumn(name = "supp_id", referencedColumnName = "id")
    @ManyToOne
    private Supplierinfo suppId;

    public Productdetail() {
    }

    public Productdetail(Integer id) {
        this.id = id;
    }

    public Productdetail(Integer id, Date dateProduct, int quantity, int qtyreturn, String billno, String remark) {
        this.id = id;
        this.dateProduct = dateProduct;
        this.quantity = quantity;
        this.qtyreturn = qtyreturn;
        this.billno = billno;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateProduct() {
        return dateProduct;
    }

    public void setDateProduct(Date dateProduct) {
        this.dateProduct = dateProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQtyreturn() {
        return qtyreturn;
    }

    public void setQtyreturn(int qtyreturn) {
        this.qtyreturn = qtyreturn;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRegisterby() {
        return registerby;
    }

    public void setRegisterby(String registerby) {
        this.registerby = registerby;
    }

    public ConsumableItems getConsId() {
        return consId;
    }

    public void setConsId(ConsumableItems consId) {
        this.consId = consId;
    }

    public Supplierinfo getSuppId() {
        return suppId;
    }

    public void setSuppId(Supplierinfo suppId) {
        this.suppId = suppId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productdetail)) {
            return false;
        }
        Productdetail other = (Productdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist_bit.hamrotarkaribazzar.entity.Productdetail[ id=" + id + " ]";
    }
    
}
