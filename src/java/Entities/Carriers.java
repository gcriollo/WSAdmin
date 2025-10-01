/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EMP
 */
@Entity
@Table(name = "CARRIERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carriers.findAll", query = "SELECT c FROM Carriers c WHERE c.status='A'"),
    @NamedQuery(name = "Carriers.findById", query = "SELECT c FROM Carriers c WHERE c.id = :id"),
    @NamedQuery(name = "Carriers.findByName", query = "SELECT c FROM Carriers c WHERE c.name = :name"),
    @NamedQuery(name = "Carriers.findByAddress", query = "SELECT c FROM Carriers c WHERE c.address = :address"),
    @NamedQuery(name = "Carriers.findByPhone", query = "SELECT c FROM Carriers c WHERE c.phone = :phone"),
    @NamedQuery(name = "Carriers.findByEmail", query = "SELECT c FROM Carriers c WHERE c.email = :email"),
    @NamedQuery(name = "Carriers.findByWeb", query = "SELECT c FROM Carriers c WHERE c.web = :web"),
    @NamedQuery(name = "Carriers.findByNotes", query = "SELECT c FROM Carriers c WHERE c.notes = :notes"),
    @NamedQuery(name = "Carriers.findByStatus", query = "SELECT c FROM Carriers c WHERE c.status = :status"),
    @NamedQuery(name = "Carriers.findBySdate", query = "SELECT c FROM Carriers c WHERE c.sdate = :sdate")})
public class Carriers implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "ADDRESS")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 13)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 50)
    @Column(name = "WEB")
    private String web;
    @Size(max = 200)
    @Column(name = "NOTES")
    private String notes;
    @Size(max = 1)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;
    @OneToMany(mappedBy = "carrierFk")
    private List<PBol> pBolList;

    public Carriers() {
    }

    public Carriers(BigDecimal id) {
        this.id = id;
    }

    public Carriers(BigDecimal id, Date sdate) {
        this.id = id;
        this.sdate = sdate;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    @XmlTransient
    public List<PBol> getPBolList() {
        return pBolList;
    }

    public void setPBolList(List<PBol> pBolList) {
        this.pBolList = pBolList;
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
        if (!(object instanceof Carriers)) {
            return false;
        }
        Carriers other = (Carriers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
