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
 * @author EMP
 */
@Entity
@Table(name = "ITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"),
    @NamedQuery(name = "Items.findById", query = "SELECT i FROM Items i WHERE i.id = :id"),
    @NamedQuery(name = "Items.findByCode", query = "SELECT i FROM Items i WHERE UPPER(i.code) = UPPER(:code)"),
    @NamedQuery(name = "Items.findByDescription", query = "SELECT i FROM Items i WHERE i.description = :description"),
    @NamedQuery(name = "Items.findBySdate", query = "SELECT i FROM Items i WHERE i.sdate = :sdate"),
    @NamedQuery(name = "Items.findByStatus", query = "SELECT i FROM Items i WHERE i.status = :status"),
    @NamedQuery(name = "Items.findByPrice", query = "SELECT i FROM Items i WHERE i.price = :price"),
    @NamedQuery(name = "Items.findByType", query = "SELECT i FROM Items i WHERE i.type = :type")})
public class Items implements Serializable {
    @JoinColumn(name = "CORP", referencedColumnName = "ID")
    @ManyToOne
    private Corp corp;
    @JoinColumn(name = "CATEGORYFK", referencedColumnName = "ID")
    @ManyToOne
    private ItemsCategory categoryfk;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false) 
    @SequenceGenerator( name = "SEC_ITEM", sequenceName = "SEC_ITEM", allocationSize = 1, initialValue = 1573 )    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_ITEM")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PRICE")
    private BigInteger price;
    @Size(max = 20)
    @Column(name = "TYPE")
    private String type;
    @OneToMany(mappedBy = "itemfk")
    private List<Items> itemsList;
    @JoinColumn(name = "ITEMFK", referencedColumnName = "ID")
    @ManyToOne
    private Items itemfk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemFk")
    private List<ItemsDet> itemsDetList;

    public Items() {
    }

    public Items(BigDecimal id) {
        this.id = id;
    }

    public Items(BigDecimal id, String code, String description, String status) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public Items getItemfk() {
        return itemfk;
    }

    public void setItemfk(Items itemfk) {
        this.itemfk = itemfk;
    }

    @XmlTransient
    public List<ItemsDet> getItemsDetList() {
        return itemsDetList;
    }

    public void setItemsDetList(List<ItemsDet> itemsDetList) {
        this.itemsDetList = itemsDetList;
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
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Items[ id=" + id + " ]";
    }

    public ItemsCategory getCategoryfk() {
        return categoryfk;
    }

    public void setCategoryfk(ItemsCategory categoryfk) {
        this.categoryfk = categoryfk;
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }
    
}
