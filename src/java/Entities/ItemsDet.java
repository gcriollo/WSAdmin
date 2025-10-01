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
 * @author EMP
 */
@Entity
@Table(name = "ITEMS_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemsDet.findAll", query = "SELECT i FROM ItemsDet i"),
    @NamedQuery(name = "ItemsDet.findAllActive", query = "SELECT i FROM ItemsDet i WHERE i.status = :status AND i.corp = :corp"),
    @NamedQuery(name = "ItemsDet.findById", query = "SELECT i FROM ItemsDet i WHERE i.id = :id"),
    @NamedQuery(name = "ItemsDet.findByColor", query = "SELECT i FROM ItemsDet i WHERE UPPER(i.color) LIKE UPPER(:color)"),
    @NamedQuery(name = "ItemsDet.findBySdate", query = "SELECT i FROM ItemsDet i WHERE i.sdate = :sdate"),
    @NamedQuery(name = "ItemsDet.findByStatus", query = "SELECT i FROM ItemsDet i WHERE i.status = :status AND i.corp = :corp")})
public class ItemsDet implements Serializable {
    @OneToMany(mappedBy = "idItemDet")
    private List<RptItemsCtlgDet> rptItemsCatalogList;
    @JoinColumn(name = "CORP", referencedColumnName = "ID")
    @ManyToOne
    private Corp corp;
    @Size(max = 2)
    @Column(name = "GENDER")
    private String gender;
    @Size(max = 60)
    @Column(name = "PIC_NAME")
    private String picName;
    @OneToMany(mappedBy = "item")
    private List<POrderDet> pOrderDetList;
    @Column(name = "PRICE")
    private BigInteger price;
    @Size(max = 20)
    @Column(name = "I_SIZE")
    private String iSize;
    @Column(name = "QTY_UNITS")
    private BigInteger qtyUnits;
    @Size(max = 200)
    @Column(name = "PIC")
    private String pic;
    @Size(max = 20)
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "QTY_ORIG")
    private BigInteger qtyOrig;
    @Size(max = 20)
    @Column(name = "SDATE_QTY")
    private String sdateQty;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator( name = "SEC_ITEM_DET", sequenceName = "SEC_ITEM_DET", allocationSize = 1, initialValue = 1)    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_ITEM_DET")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "COLOR")
    private String color;
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STATUS")
    private String status;
   
    @JoinColumn(name = "ITEM_FK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Items itemFk;

    public ItemsDet() {
    }

    public ItemsDet(BigDecimal id) {
        this.id = id;
    }

    public ItemsDet(BigDecimal id, String color, String status) {
        this.id = id;
        this.color = color;
        this.status = status;
        this.brand="-";
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  

    public Items getItemFk() {
        return itemFk;
    }

    public void setItemFk(Items itemFk) {
        this.itemFk = itemFk;
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
        if (!(object instanceof ItemsDet)) {
            return false;
        }
        ItemsDet other = (ItemsDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ItemsDet[ id=" + id + " ]";
    }

    public String getISize() {
        return iSize;
    }

    public void setISize(String iSize) {
        this.iSize = iSize;
    }

    public BigInteger getQtyUnits() {
        return qtyUnits;
    }

    public void setQtyUnits(BigInteger qtyUnits) {
        this.qtyUnits = qtyUnits;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigInteger getQtyOrig() {
        return qtyOrig;
    }

    public void setQtyOrig(BigInteger qtyOrig) {
        this.qtyOrig = qtyOrig;
    }

    public String getSdateQty() {
        return sdateQty;
    }

    public void setSdateQty(String sdateQty) {
        this.sdateQty = sdateQty;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    @XmlTransient
    public List<POrderDet> getPOrderDetList() {
        return pOrderDetList;
    }

    public void setPOrderDetList(List<POrderDet> pOrderDetList) {
        this.pOrderDetList = pOrderDetList;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }

    @XmlTransient
    public List<RptItemsCtlgDet> getRptItemsCatalogList() {
        return rptItemsCatalogList;
    }

    public void setRptItemsCatalogList(List<RptItemsCtlgDet> rptItemsCatalogList) {
        this.rptItemsCatalogList = rptItemsCatalogList;
    }
    
}
