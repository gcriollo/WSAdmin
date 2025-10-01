/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EMP
 */
@Entity
@Table(name = "RPT_ITEMS_CTLG_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptItemsCtlgDet.findAll", query = "SELECT r FROM RptItemsCtlgDet r"),
    @NamedQuery(name = "RptItemsCtlgDet.findByQtyUnits", query = "SELECT r FROM RptItemsCtlgDet r WHERE r.qtyUnits = :qtyUnits"),
    @NamedQuery(name = "RptItemsCtlgDet.findByCorp", query = "SELECT r FROM RptItemsCtlgDet r WHERE r.corp = :corp"),
    @NamedQuery(name = "RptItemsCtlgDet.findById", query = "SELECT r FROM RptItemsCtlgDet r WHERE r.id = :id")})
public class RptItemsCtlgDet implements Serializable {
    @JoinColumn(name = "ID_RPT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RptItemsCtlg idRpt;
    @JoinColumn(name = "CORP", referencedColumnName = "ID")
    @ManyToOne
    private Corp corp;
    private static final long serialVersionUID = 1L;
    @Column(name = "QTY_UNITS")
    private BigInteger qtyUnits;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator( name = "SEC_RPTITEM_DET", sequenceName = "SEC_RPTITEM_DET", allocationSize = 1, initialValue = 1 )    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_RPTITEM_DET")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "ID_ITEM_DET", referencedColumnName = "ID")
    @ManyToOne
    private ItemsDet idItemDet;

    public RptItemsCtlgDet() {
        this.qtyUnits= new BigInteger("0");
    }

    public RptItemsCtlgDet(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getQtyUnits() {
        return qtyUnits;
    }

    public void setQtyUnits(BigInteger qtyUnits) {
        this.qtyUnits = qtyUnits;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public ItemsDet getIdItemDet() {
        return idItemDet;
    }

    public void setIdItemDet(ItemsDet idItemDet) {
        this.idItemDet = idItemDet;
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
        if (!(object instanceof RptItemsCtlgDet)) {
            return false;
        }
        RptItemsCtlgDet other = (RptItemsCtlgDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.RptItemsCtlgDet[ id=" + id + " ]";
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }

    public RptItemsCtlg getIdRpt() {
        return idRpt;
    }

    public void setIdRpt(RptItemsCtlg idRpt) {
        this.idRpt = idRpt;
    }
    
}
