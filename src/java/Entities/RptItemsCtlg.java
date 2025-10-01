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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "RPT_ITEMS_CTLG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptItemsCtlg.findAll", query = "SELECT r FROM RptItemsCtlg r"),
    @NamedQuery(name = "RptItemsCtlg.findById", query = "SELECT r FROM RptItemsCtlg r WHERE r.id = :id"),
    @NamedQuery(name = "RptItemsCtlg.findByTitle", query = "SELECT r FROM RptItemsCtlg r WHERE r.title = :title"),
    @NamedQuery(name = "RptItemsCtlg.findByNameFor", query = "SELECT r FROM RptItemsCtlg r WHERE r.nameFor = :nameFor"),
    @NamedQuery(name = "RptItemsCtlg.findByNote", query = "SELECT r FROM RptItemsCtlg r WHERE r.note = :note"),
    @NamedQuery(name = "RptItemsCtlg.findBySdate", query = "SELECT r FROM RptItemsCtlg r WHERE r.sdate = :sdate")})
public class RptItemsCtlg implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator( name = "SEC_RPTITEM", sequenceName = "SEC_RPTITEM", allocationSize = 1, initialValue = 1 )    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_RPTITEM")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 200)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 200)
    @Column(name = "NAME_FOR")
    private String nameFor;
    @Size(max = 200)
    @Column(name = "NOTE")
    private String note;
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRpt")
    private List<RptItemsCtlgDet> rptItemsCtlgDetList;

    public RptItemsCtlg() {
        this.title="";
        this.nameFor="";
        this.note="";
    }

    public RptItemsCtlg(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameFor() {
        return nameFor;
    }

    public void setNameFor(String nameFor) {
        this.nameFor = nameFor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    @XmlTransient
    public List<RptItemsCtlgDet> getRptItemsCtlgDetList() {
        return rptItemsCtlgDetList;
    }

    public void setRptItemsCtlgDetList(List<RptItemsCtlgDet> rptItemsCtlgDetList) {
        this.rptItemsCtlgDetList = rptItemsCtlgDetList;
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
        if (!(object instanceof RptItemsCtlg)) {
            return false;
        }
        RptItemsCtlg other = (RptItemsCtlg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.RptItemsCtlg[ id=" + id + " ]";
    }
    
}
