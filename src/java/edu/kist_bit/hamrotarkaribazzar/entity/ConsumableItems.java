/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.hamrotarkaribazzar.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "consumable_items", catalog = "tarkaribazzar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsumableItems.findAll", query = "SELECT c FROM ConsumableItems c")
    , @NamedQuery(name = "ConsumableItems.findById", query = "SELECT c FROM ConsumableItems c WHERE c.id = :id")
    , @NamedQuery(name = "ConsumableItems.findByCode", query = "SELECT c FROM ConsumableItems c WHERE c.code = :code")
    , @NamedQuery(name = "ConsumableItems.findByParticular", query = "SELECT c FROM ConsumableItems c WHERE c.particular = :particular")
    , @NamedQuery(name = "ConsumableItems.findByRemarks", query = "SELECT c FROM ConsumableItems c WHERE c.remarks = :remarks")})
public class ConsumableItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "particular")
    private String particular;
    @Basic(optional = false)
    @Column(name = "remarks")
    private String remarks;
    @OneToMany(mappedBy = "consId")
    private List<Productdetail> productdetailList;
    @JoinColumn(name = "catg_id", referencedColumnName = "id")
    @ManyToOne
    private Category catgId;
    @JoinColumn(name = "subcatg_id", referencedColumnName = "id")
    @ManyToOne
    private Subcategory subcatgId;

    public ConsumableItems() {
    }

    public ConsumableItems(Integer id) {
        this.id = id;
    }

    public ConsumableItems(Integer id, String code, String particular, String remarks) {
        this.id = id;
        this.code = code;
        this.particular = particular;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @XmlTransient
    public List<Productdetail> getProductdetailList() {
        return productdetailList;
    }

    public void setProductdetailList(List<Productdetail> productdetailList) {
        this.productdetailList = productdetailList;
    }

    public Category getCatgId() {
        return catgId;
    }

    public void setCatgId(Category catgId) {
        this.catgId = catgId;
    }

    public Subcategory getSubcatgId() {
        return subcatgId;
    }

    public void setSubcatgId(Subcategory subcatgId) {
        this.subcatgId = subcatgId;
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
        if (!(object instanceof ConsumableItems)) {
            return false;
        }
        ConsumableItems other = (ConsumableItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist_bit.hamrotarkaribazzar.entity.ConsumableItems[ id=" + id + " ]";
    }
    
}
