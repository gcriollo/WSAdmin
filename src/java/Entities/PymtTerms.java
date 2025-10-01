/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EMP
 */
@Entity
@Table(name = "PYMT_TERMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PymtTerms.findAll", query = "SELECT p FROM PymtTerms p"),
    @NamedQuery(name = "PymtTerms.findById", query = "SELECT p FROM PymtTerms p WHERE p.id = :id"),
    @NamedQuery(name = "PymtTerms.findByTerms", query = "SELECT p FROM PymtTerms p WHERE p.terms = :terms"),
    @NamedQuery(name = "PymtTerms.findByStatus", query = "SELECT p FROM PymtTerms p WHERE p.status = :status")})
public class PymtTerms implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "TERMS")
    private String terms;
    @Column(name = "STATUS")
    private Character status;
    @OneToMany(mappedBy = "pymtTerms")
    private List<POrder> pOrderList;

    public PymtTerms() {
    }

    public PymtTerms(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    @XmlTransient
    public List<POrder> getPOrderList() {
        return pOrderList;
    }

    public void setPOrderList(List<POrder> pOrderList) {
        this.pOrderList = pOrderList;
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
        if (!(object instanceof PymtTerms)) {
            return false;
        }
        PymtTerms other = (PymtTerms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PymtTerms[ id=" + id + " ]";
    }
    
}
