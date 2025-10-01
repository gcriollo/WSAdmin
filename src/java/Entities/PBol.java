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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "P_BOL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PBol.findAll", query = "SELECT p FROM PBol p"),
    @NamedQuery(name = "PBol.findById", query = "SELECT p FROM PBol p WHERE p.id = :id"),
    @NamedQuery(name = "PBol.findBySdate", query = "SELECT p FROM PBol p WHERE p.sdate = :sdate"),
    @NamedQuery(name = "PBol.findBySeq", query = "SELECT p FROM PBol p WHERE p.seq = :seq"),
    @NamedQuery(name = "PBol.findByCCommodity", query = "SELECT p FROM PBol p WHERE p.cCommodity = :cCommodity"),
    @NamedQuery(name = "PBol.findBySkids", query = "SELECT p FROM PBol p WHERE p.skids = :skids"),
    @NamedQuery(name = "PBol.findByWeight", query = "SELECT p FROM PBol p WHERE p.weight = :weight"),
    @NamedQuery(name = "PBol.findByNfc", query = "SELECT p FROM PBol p WHERE p.nfc = :nfc"),
    @NamedQuery(name = "PBol.findByClass1", query = "SELECT p FROM PBol p WHERE p.class1 = :class1"),
    @NamedQuery(name = "PBol.findByFChargeTerms", query = "SELECT p FROM PBol p WHERE p.fChargeTerms = :fChargeTerms"),
    @NamedQuery(name = "PBol.findByConfirmation", query = "SELECT p FROM PBol p WHERE p.confirmation = :confirmation"),
    @NamedQuery(name = "PBol.findByDValue", query = "SELECT p FROM PBol p WHERE p.dValue = :dValue"),
    @NamedQuery(name = "PBol.findByCodAmount", query = "SELECT p FROM PBol p WHERE p.codAmount = :codAmount"),
    @NamedQuery(name = "PBol.findByCClass", query = "SELECT p FROM PBol p WHERE p.cClass = :cClass"),
    @NamedQuery(name = "PBol.findByCNmfc", query = "SELECT p FROM PBol p WHERE p.cNmfc = :cNmfc"),
    @NamedQuery(name = "PBol.findByCHm", query = "SELECT p FROM PBol p WHERE p.cHm = :cHm"),
    @NamedQuery(name = "PBol.findByFeeTerms", query = "SELECT p FROM PBol p WHERE p.feeTerms = :feeTerms"),
    @NamedQuery(name = "PBol.findByTrailerLoaded", query = "SELECT p FROM PBol p WHERE p.trailerLoaded = :trailerLoaded"),
    @NamedQuery(name = "PBol.findByFreightCounted", query = "SELECT p FROM PBol p WHERE p.freightCounted = :freightCounted"),
    @NamedQuery(name = "PBol.findByFob", query = "SELECT p FROM PBol p WHERE p.fob = :fob"),
    @NamedQuery(name = "PBol.findBySid", query = "SELECT p FROM PBol p WHERE p.sid = :sid"),
    @NamedQuery(name = "PBol.findByTrailerNo", query = "SELECT p FROM PBol p WHERE p.trailerNo = :trailerNo"),
    @NamedQuery(name = "PBol.findBySealNo", query = "SELECT p FROM PBol p WHERE p.sealNo = :sealNo"),
    @NamedQuery(name = "PBol.findByScac", query = "SELECT p FROM PBol p WHERE p.scac = :scac"),
    @NamedQuery(name = "PBol.findByProNo", query = "SELECT p FROM PBol p WHERE p.proNo = :proNo"),
    @NamedQuery(name = "PBol.findByInstructions", query = "SELECT p FROM PBol p WHERE p.instructions = :instructions"),
    @NamedQuery(name = "PBol.findByMbolAtt", query = "SELECT p FROM PBol p WHERE p.mbolAtt = :mbolAtt"),
    @NamedQuery(name = "PBol.findByCodTerms", query = "SELECT p FROM PBol p WHERE p.codTerms = :codTerms")})
public class PBol implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;
    @Column(name = "SEQ")
    private BigInteger seq;
    @Size(max = 20)
    @Column(name = "C_COMMODITY")
    private String cCommodity;
    @Column(name = "SKIDS")
    private BigInteger skids;
    @Column(name = "WEIGHT")
    private BigInteger weight;
    @Size(max = 20)
    @Column(name = "NFC")
    private String nfc;
    @Size(max = 20)
    @Column(name = "CLASS")
    private String class1;
    @Size(max = 20)
    @Column(name = "F_CHARGE_TERMS")
    private String fChargeTerms;
    @Size(max = 50)
    @Column(name = "CONFIRMATION")
    private String confirmation;
    @Column(name = "D_VALUE")
    private BigInteger dValue;
    @Column(name = "COD_AMOUNT")
    private BigInteger codAmount;
    @Size(max = 20)
    @Column(name = "C_CLASS")
    private String cClass;
    @Size(max = 20)
    @Column(name = "C_NMFC")
    private String cNmfc;
    @Size(max = 20)
    @Column(name = "C_HM")
    private String cHm;
    @Size(max = 20)
    @Column(name = "FEE_TERMS")
    private String feeTerms;
    @Size(max = 20)
    @Column(name = "TRAILER_LOADED")
    private String trailerLoaded;
    @Size(max = 20)
    @Column(name = "FREIGHT_COUNTED")
    private String freightCounted;
    @Size(max = 20)
    @Column(name = "FOB")
    private String fob;
    @Size(max = 20)
    @Column(name = "SID")
    private String sid;
    @Column(name = "TRAILER_NO")
    private BigInteger trailerNo;
    @Column(name = "SEAL_NO")
    private BigInteger sealNo;
    @Size(max = 20)
    @Column(name = "SCAC")
    private String scac;
    @Size(max = 20)
    @Column(name = "PRO_NO")
    private String proNo;
    @Size(max = 300)
    @Column(name = "INSTRUCTIONS")
    private String instructions;
    @Size(max = 1)
    @Column(name = "MBOL_ATT")
    private String mbolAtt;
    @Size(max = 2)
    @Column(name = "COD_TERMS")
    private String codTerms;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bolFk")
    private List<PBolDet> pBolDetList;
    @JoinColumn(name = "CUSTOMER_FK", referencedColumnName = "ID")
    @ManyToOne
    private Customers customerFk;
    @JoinColumn(name = "CORP_FK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Corp corpFk;
    @JoinColumn(name = "CARRIER_FK", referencedColumnName = "ID")
    @ManyToOne
    private Carriers carrierFk;

    public PBol() {
    }

    public PBol(BigDecimal id) {
        this.id = id;
    }

    public PBol(BigDecimal id, Date sdate) {
        this.id = id;
        this.sdate = sdate;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public BigInteger getSeq() {
        return seq;
    }

    public void setSeq(BigInteger seq) {
        this.seq = seq;
    }

    public String getCCommodity() {
        return cCommodity;
    }

    public void setCCommodity(String cCommodity) {
        this.cCommodity = cCommodity;
    }

    public BigInteger getSkids() {
        return skids;
    }

    public void setSkids(BigInteger skids) {
        this.skids = skids;
    }

    public BigInteger getWeight() {
        return weight;
    }

    public void setWeight(BigInteger weight) {
        this.weight = weight;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getFChargeTerms() {
        return fChargeTerms;
    }

    public void setFChargeTerms(String fChargeTerms) {
        this.fChargeTerms = fChargeTerms;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public BigInteger getDValue() {
        return dValue;
    }

    public void setDValue(BigInteger dValue) {
        this.dValue = dValue;
    }

    public BigInteger getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(BigInteger codAmount) {
        this.codAmount = codAmount;
    }

    public String getCClass() {
        return cClass;
    }

    public void setCClass(String cClass) {
        this.cClass = cClass;
    }

    public String getCNmfc() {
        return cNmfc;
    }

    public void setCNmfc(String cNmfc) {
        this.cNmfc = cNmfc;
    }

    public String getCHm() {
        return cHm;
    }

    public void setCHm(String cHm) {
        this.cHm = cHm;
    }

    public String getFeeTerms() {
        return feeTerms;
    }

    public void setFeeTerms(String feeTerms) {
        this.feeTerms = feeTerms;
    }

    public String getTrailerLoaded() {
        return trailerLoaded;
    }

    public void setTrailerLoaded(String trailerLoaded) {
        this.trailerLoaded = trailerLoaded;
    }

    public String getFreightCounted() {
        return freightCounted;
    }

    public void setFreightCounted(String freightCounted) {
        this.freightCounted = freightCounted;
    }

    public String getFob() {
        return fob;
    }

    public void setFob(String fob) {
        this.fob = fob;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public BigInteger getTrailerNo() {
        return trailerNo;
    }

    public void setTrailerNo(BigInteger trailerNo) {
        this.trailerNo = trailerNo;
    }

    public BigInteger getSealNo() {
        return sealNo;
    }

    public void setSealNo(BigInteger sealNo) {
        this.sealNo = sealNo;
    }

    public String getScac() {
        return scac;
    }

    public void setScac(String scac) {
        this.scac = scac;
    }

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMbolAtt() {
        return mbolAtt;
    }

    public void setMbolAtt(String mbolAtt) {
        this.mbolAtt = mbolAtt;
    }

    public String getCodTerms() {
        return codTerms;
    }

    public void setCodTerms(String codTerms) {
        this.codTerms = codTerms;
    }

    @XmlTransient
    public List<PBolDet> getPBolDetList() {
        return pBolDetList;
    }

    public void setPBolDetList(List<PBolDet> pBolDetList) {
        this.pBolDetList = pBolDetList;
    }

    public Customers getCustomerFk() {
        return customerFk;
    }

    public void setCustomerFk(Customers customerFk) {
        this.customerFk = customerFk;
    }

    public Corp getCorpFk() {
        return corpFk;
    }

    public void setCorpFk(Corp corpFk) {
        this.corpFk = corpFk;
    }

    public Carriers getCarrierFk() {
        return carrierFk;
    }

    public void setCarrierFk(Carriers carrierFk) {
        this.carrierFk = carrierFk;
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
        if (!(object instanceof PBol)) {
            return false;
        }
        PBol other = (PBol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PBol[ id=" + id + " ]";
    }
    
}
