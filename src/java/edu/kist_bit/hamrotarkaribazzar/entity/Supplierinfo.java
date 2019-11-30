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
@Table(name = "supplierinfo", catalog = "tarkaribazzar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplierinfo.findAll", query = "SELECT s FROM Supplierinfo s")
    , @NamedQuery(name = "Supplierinfo.findById", query = "SELECT s FROM Supplierinfo s WHERE s.id = :id")
    , @NamedQuery(name = "Supplierinfo.findByName", query = "SELECT s FROM Supplierinfo s WHERE s.name = :name")
    , @NamedQuery(name = "Supplierinfo.findByAddress", query = "SELECT s FROM Supplierinfo s WHERE s.address = :address")
    , @NamedQuery(name = "Supplierinfo.findByContact", query = "SELECT s FROM Supplierinfo s WHERE s.contact = :contact")
    , @NamedQuery(name = "Supplierinfo.findByEmail", query = "SELECT s FROM Supplierinfo s WHERE s.email = :email")})
public class Supplierinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "suppId")
    private List<Productdetail> productdetailList;

    public Supplierinfo() {
    }

    public Supplierinfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Productdetail> getProductdetailList() {
        return productdetailList;
    }

    public void setProductdetailList(List<Productdetail> productdetailList) {
        this.productdetailList = productdetailList;
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
        if (!(object instanceof Supplierinfo)) {
            return false;
        }
        Supplierinfo other = (Supplierinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist_bit.hamrotarkaribazzar.entity.Supplierinfo[ id=" + id + " ]";
    }
    
}
