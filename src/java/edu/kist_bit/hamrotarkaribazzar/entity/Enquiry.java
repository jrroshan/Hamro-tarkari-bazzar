/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.hamrotarkaribazzar.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "enquiry", catalog = "tarkaribazaar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enquiry.findAll", query = "SELECT e FROM Enquiry e")
    , @NamedQuery(name = "Enquiry.findById", query = "SELECT e FROM Enquiry e WHERE e.id = :id")
    , @NamedQuery(name = "Enquiry.findByName", query = "SELECT e FROM Enquiry e WHERE e.name = :name")
    , @NamedQuery(name = "Enquiry.findByEmail", query = "SELECT e FROM Enquiry e WHERE e.email = :email")
    , @NamedQuery(name = "Enquiry.findBySubject", query = "SELECT e FROM Enquiry e WHERE e.subject = :subject")
    , @NamedQuery(name = "Enquiry.findByMessage", query = "SELECT e FROM Enquiry e WHERE e.message = :message")})
public class Enquiry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @Column(name = "message")
    private String message;

    public Enquiry() {
    }

    public Enquiry(Integer id) {
        this.id = id;
    }

    public Enquiry(Integer id, String name, String email, String subject, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        if (!(object instanceof Enquiry)) {
            return false;
        }
        Enquiry other = (Enquiry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist_bit.hamrotarkaribazzar.entity.Enquiry[ id=" + id + " ]";
    }
    
}
