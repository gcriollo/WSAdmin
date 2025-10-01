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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author GCRIOLLO
 */
@Entity
@Table(name = "P_ORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "POrder.findAll", query = "SELECT p FROM POrder p"),
    @NamedQuery(name = "POrder.findById", query = "SELECT p FROM POrder p WHERE p.id = :id"),
    @NamedQuery(name = "POrder.findByPo", query = "SELECT p FROM POrder p WHERE p.po = :po"),
    @NamedQuery(name = "POrder.findBySdate", query = "SELECT p FROM POrder p WHERE p.sdate = :sdate"),
    @NamedQuery(name = "POrder.findByInvoice", query = "SELECT p FROM POrder p WHERE p.invoice = :invoice"),
    @NamedQuery(name = "POrder.findByShipOn", query = "SELECT p FROM POrder p WHERE p.shipOn = :shipOn"),
    @NamedQuery(name = "POrder.findByShipOnRange", query = "SELECT p FROM POrder p WHERE p.shipOn >= :shipOnStart and p.shipOn <= :shipOnEnd"),
    @NamedQuery(name = "POrder.findByCancelAfter", query = "SELECT p FROM POrder p WHERE p.cancelAfter = :cancelAfter"),
    @NamedQuery(name = "POrder.findByOrdered", query = "SELECT p FROM POrder p WHERE p.ordered = :ordered"),
    @NamedQuery(name = "POrder.findByTotalPcs", query = "SELECT p FROM POrder p WHERE p.totalPcs = :totalPcs")})
public class POrder implements Serializable {
    @Column(name = "TOTAL_COST")
    private BigDecimal totalCost;
    @JoinColumn(name = "PYMT_TERMS", referencedColumnName = "ID")
    @ManyToOne
    private PymtTerms pymtTerms;
    @JoinColumn(name = "CUSTOMER", referencedColumnName = "ID")
    @ManyToOne
    private Customers customer;
    @Size(max = 30)
    @Column(name = "PACK_REQ")
    private String packReq;
    @Column(name = "PS_LBL_DONE")
    private Character psLblDone;
    @Column(name = "WEIGHT")
    private BigInteger weight;    
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Size(max = 30)
    @Column(name = "CARRIER")
    private String carrier;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator( name = "SEC_PORDER", sequenceName = "SEC_PORDER", allocationSize = 1, initialValue = 1 )    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_PORDER")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PO")
    private String po;
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;
    @Size(max = 10)
    @Column(name = "INVOICE")
    private String invoice;
    @Column(name = "SHIP_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipOn;
    @Column(name = "CANCEL_AFTER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelAfter;
    @Column(name = "ORDERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordered;
    @Column(name = "TOTAL_PCS")
    private BigInteger totalPcs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pOrder")
    private List<POrderDet> pOrderDetList;

    public POrder() {
    }

    public POrder(BigDecimal id) {
        this.id = id;
    }

    public POrder(BigDecimal id, String po) {
        this.id = id;
        this.po = po;
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

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Date getShipOn() {
        return shipOn;
    }

    public void setShipOn(Date shipOn) {
        this.shipOn = shipOn;
    }

    public Date getCancelAfter() {
        return cancelAfter;
    }

    public void setCancelAfter(Date cancelAfter) {
        this.cancelAfter = cancelAfter;
    }

    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public BigInteger getTotalPcs() {
        return totalPcs;
    }

    public void setTotalPcs(BigInteger totalPcs) {
        this.totalPcs = totalPcs;
    }

    @XmlTransient
    public List<POrderDet> getPOrderDetList() {
        return pOrderDetList;
    }

    public void setPOrderDetList(List<POrderDet> pOrderDetList) {
        this.pOrderDetList = pOrderDetList;
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
        if (!(object instanceof POrder)) {
            return false;
        }
        POrder other = (POrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " - "+po;
    }

   
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getPackReq() {
        return packReq;
    }

    public void setPackReq(String packReq) {
        this.packReq = packReq;
    }


    public Character getPsLblDone() {
        return psLblDone;
    }

    public void setPsLblDone(Character psLblDone) {
        this.psLblDone = psLblDone;
    }

    public BigInteger getWeight() {
        return weight;
    }

    public void setWeight(BigInteger weight) {
        this.weight = weight;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public PymtTerms getPymtTerms() {
        return pymtTerms;
    }

    public void setPymtTerms(PymtTerms pymtTerms) {
        this.pymtTerms = pymtTerms;
    }

    
}
