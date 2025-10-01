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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ITEMS_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemsCategory.findAll", query = "SELECT i FROM ItemsCategory i"),
    @NamedQuery(name = "ItemsCategory.findById", query = "SELECT i FROM ItemsCategory i WHERE i.id = :id"),
    @NamedQuery(name = "ItemsCategory.findByCategory", query = "SELECT i FROM ItemsCategory i WHERE i.category = :category"),
    @NamedQuery(name = "ItemsCategory.findByStatus", query = "SELECT i FROM ItemsCategory i WHERE i.status = :status")})
public class ItemsCategory implements Serializable {
    @JoinColumn(name = "CORP", referencedColumnName = "ID")
    @ManyToOne
    private Corp corp;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 1)
    @Column(name = "STATUS")
    private String status;
    @OneToMany(mappedBy = "categoryfk")
    private List<Items> itemsList;

    public ItemsCategory() {
    }

    public ItemsCategory(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
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
        if (!(object instanceof ItemsCategory)) {
            return false;
        }
        ItemsCategory other = (ItemsCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ItemsCategory[ id=" + id + " ]";
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }
    
}
