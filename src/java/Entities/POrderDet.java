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
@Table(name = "P_ORDER_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "POrderDet.findAll", query = "SELECT p FROM POrderDet p"),
    @NamedQuery(name = "POrderDet.findById", query = "SELECT p FROM POrderDet p WHERE p.id = :id"),
    @NamedQuery(name = "POrderDet.findByStyle", query = "SELECT p FROM POrderDet p WHERE p.style = :style"),
    @NamedQuery(name = "POrderDet.findByPcs", query = "SELECT p FROM POrderDet p WHERE p.pcs = :pcs"),
    @NamedQuery(name = "POrderDet.findByTotalPcs", query = "SELECT p FROM POrderDet p WHERE p.totalPcs = :totalPcs"),
    @NamedQuery(name = "POrderDet.findByDz", query = "SELECT p FROM POrderDet p WHERE p.dz = :dz")})
public class POrderDet implements Serializable {
    @Column(name = "UNIT_COST")
    private BigInteger unitCost;
    @Size(max = 40)
    @Column(name = "COLOR")
    private String color;
    @Column(name = "BOXES")
    private BigInteger boxes;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 20)
    @Column(name = "V_ITEM")
    private String vItem;
    @JoinColumn(name = "ITEM", referencedColumnName = "ID")
    @ManyToOne
    private ItemsDet item;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator( name = "SEC_PORDER_DET", sequenceName = "SEC_PORDER_DET", allocationSize = 1, initialValue = 1 )    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_PORDER_DET")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "STYLE")
    private String style;
    @Column(name = "PCS")
    private BigInteger pcs;
    @Column(name = "TOTAL_PCS")
    private BigInteger totalPcs;
    @Column(name = "DZ")
    private BigInteger dz;
    @JoinColumn(name = "P_ORDER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private POrder pOrder;

    public POrderDet() {
    }

    public POrderDet(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BigInteger getPcs() {
        return pcs;
    }

    public void setPcs(BigInteger pcs) {
        this.pcs = pcs;
    }

    public BigInteger getTotalPcs() {
        return totalPcs;
    }

    public void setTotalPcs(BigInteger totalPcs) {
        this.totalPcs = totalPcs;
    }

    public BigInteger getDz() {
        return dz;
    }

    public void setDz(BigInteger dz) {
        this.dz = dz;
    }

    public POrder getPOrder() {
        return pOrder;
    }

    public void setPOrder(POrder pOrder) {
        this.pOrder = pOrder;
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
        if (!(object instanceof POrderDet)) {
            return false;
        }
        POrderDet other = (POrderDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.POrderDet[ id=" + id + " ]";
    }

    public BigInteger getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigInteger unitCost) {
        this.unitCost = unitCost;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigInteger getBoxes() {
        return boxes;
    }

    public void setBoxes(BigInteger boxes) {
        this.boxes = boxes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVItem() {
        return vItem;
    }

    public void setVItem(String vItem) {
        this.vItem = vItem;
    }

    public ItemsDet getItem() {
        return item;
    }

    public void setItem(ItemsDet item) {
        this.item = item;
    }
    
}
