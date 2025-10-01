/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EMP
 */
@Entity
@Table(name = "P_BOL_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PBolDet.findAll", query = "SELECT p FROM PBolDet p"),
    @NamedQuery(name = "PBolDet.findById", query = "SELECT p FROM PBolDet p WHERE p.id = :id"),
    @NamedQuery(name = "PBolDet.findByPo", query = "SELECT p FROM PBolDet p WHERE p.po = :po"),
    @NamedQuery(name = "PBolDet.findByInvoice", query = "SELECT p FROM PBolDet p WHERE p.invoice = :invoice"),
    @NamedQuery(name = "PBolDet.findByCtns", query = "SELECT p FROM PBolDet p WHERE p.ctns = :ctns"),
    @NamedQuery(name = "PBolDet.findByWeight", query = "SELECT p FROM PBolDet p WHERE p.weight = :weight"),
    @NamedQuery(name = "PBolDet.findByPalletSlip1", query = "SELECT p FROM PBolDet p WHERE p.palletSlip1 = :palletSlip1"),
    @NamedQuery(name = "PBolDet.findByPalletSlip2", query = "SELECT p FROM PBolDet p WHERE p.palletSlip2 = :palletSlip2"),
    @NamedQuery(name = "PBolDet.findByAddShipinfo", query = "SELECT p FROM PBolDet p WHERE p.addShipinfo = :addShipinfo"),
    @NamedQuery(name = "PBolDet.findByStatus", query = "SELECT p FROM PBolDet p WHERE p.status = :status"),
    @NamedQuery(name = "PBolDet.findBySdate", query = "SELECT p FROM PBolDet p WHERE p.sdate = :sdate")})
public class PBolDet implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "PO")
    private String po;
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CTNS")
    private BigInteger ctns;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT")
    private BigInteger weight;
    @Size(max = 20)
    @Column(name = "PALLET_SLIP1")
    private String palletSlip1;
    @Size(max = 20)
    @Column(name = "PALLET_SLIP2")
    private String palletSlip2;
    @Size(max = 100)
    @Column(name = "ADD_SHIPINFO")
    private String addShipinfo;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;
    @JoinColumn(name = "BOL_FK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PBol bolFk;

    public PBolDet() {
    }

    public PBolDet(BigDecimal id) {
        this.id = id;
    }

    public PBolDet(BigDecimal id, BigInteger ctns, BigInteger weight) {
        this.id = id;
        this.ctns = ctns;
        this.weight = weight;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public BigInteger getCtns() {
        return ctns;
    }

    public void setCtns(BigInteger ctns) {
        this.ctns = ctns;
    }

    public BigInteger getWeight() {
        return weight;
    }

    public void setWeight(BigInteger weight) {
        this.weight = weight;
    }

    public String getPalletSlip1() {
        return palletSlip1;
    }

    public void setPalletSlip1(String palletSlip1) {
        this.palletSlip1 = palletSlip1;
    }

    public String getPalletSlip2() {
        return palletSlip2;
    }

    public void setPalletSlip2(String palletSlip2) {
        this.palletSlip2 = palletSlip2;
    }

    public String getAddShipinfo() {
        return addShipinfo;
    }

    public void setAddShipinfo(String addShipinfo) {
        this.addShipinfo = addShipinfo;
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

    public PBol getBolFk() {
        return bolFk;
    }

    public void setBolFk(PBol bolFk) {
        this.bolFk = bolFk;
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
        if (!(object instanceof PBolDet)) {
            return false;
        }
        PBolDet other = (PBolDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PBolDet[ id=" + id + " ]";
    }
    
}
