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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "P_MAKELABELS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PMakelabels.findAll", query = "SELECT p FROM PMakelabels p"),
    @NamedQuery(name = "PMakelabels.findById", query = "SELECT p FROM PMakelabels p WHERE p.id = :id"),
    @NamedQuery(name = "PMakelabels.findByPo", query = "SELECT p FROM PMakelabels p WHERE p.po = :po"),
    @NamedQuery(name = "PMakelabels.findByPoandGID", query = "SELECT p FROM PMakelabels p WHERE p.po = :po AND p.gId = :gId"),
    @NamedQuery(name = "PMakelabels.findByStyle", query = "SELECT p FROM PMakelabels p WHERE p.style = :style"),
    @NamedQuery(name = "PMakelabels.findByNBox", query = "SELECT p FROM PMakelabels p WHERE p.nBox = :nBox"),
    @NamedQuery(name = "PMakelabels.findByDzBox", query = "SELECT p FROM PMakelabels p WHERE p.dzBox = :dzBox"),
    @NamedQuery(name = "PMakelabels.findByUnitsBox", query = "SELECT p FROM PMakelabels p WHERE p.unitsBox = :unitsBox"),
    @NamedQuery(name = "PMakelabels.findByOsgroup", query = "SELECT p FROM PMakelabels p WHERE p.osgroup = :osgroup"),
    @NamedQuery(name = "PMakelabels.findByShipto", query = "SELECT p FROM PMakelabels p WHERE p.shipto = :shipto")})
public class PMakelabels implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_BOX")
    private Integer nBox;
    @Column(name = "DZ_BOX")
    private Integer dzBox;
    @Column(name = "UNITS_BOX")
    private Integer unitsBox;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OSGROUP")
    private Integer osgroup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_BOX")
    private Integer totalBox;
    @Column(name = "G_ID")
    private Integer gId;
    @Column(name = "INVOICE")
    private Integer invoice;
    @Size(max = 20)
    @Column(name = "V_STYLE")
    private String vStyle;
    @Size(max = 20)
    @Column(name = "COLOR")
    private String color;
    @Column(name = "S_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sDate;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator( name = "SEC_MAKELABELS", sequenceName = "SEC_MAKELABELS", allocationSize = 1, initialValue = 1 )    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_MAKELABELS")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PO")
    private String po;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STYLE")
    private String style;
    @Size(max = 200)
    @Column(name = "SHIPTO")
    private String shipto;
    @JoinColumn(name = "CORP", referencedColumnName = "ID")
    @ManyToOne
    private Corp corp;

    public PMakelabels() {
    }

    public PMakelabels(BigDecimal id) {
        this.id = id;
    }

    public PMakelabels(String style,String po, Integer qtybox, Integer units, Integer dzperbox, String shipto, Integer totalbox, String color, Integer invoice, String vstyle){
        //this.id = id;
        this.style = style;
        this.totalBox = totalbox;
        this.unitsBox = units;
        this.dzBox = dzperbox;
        this.shipto = shipto;
        this.po = po;
        this.nBox = qtybox;
        this.color = color;
        this.invoice = invoice;
        this.vStyle = vstyle;
    }
    
    public PMakelabels(BigDecimal id, String po, String style, Integer nBox, Integer osgroup, Integer GId) {
        this.id = id;
        this.po = po;
        this.style = style;
        this.nBox = nBox;
        this.osgroup = osgroup;
        this.gId = GId;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getNBox() {
        return nBox;
    }

    public void setNBox(Integer nBox) {
        this.nBox = nBox;
    }

    public Integer getDzBox() {
        return dzBox;
    }

    public void setDzBox(Integer dzBox) {
        this.dzBox = dzBox;
    }

    public Integer getUnitsBox() {
        return unitsBox;
    }

    public void setUnitsBox(Integer unitsBox) {
        this.unitsBox = unitsBox;
    }


    public String getShipto() {
        return shipto;
    }

    public void setShipto(String shipto) {
        this.shipto = shipto;
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
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
        if (!(object instanceof PMakelabels)) {
            return false;
        }
        PMakelabels other = (PMakelabels) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PMakelabels[ id=" + id + " ]";
    }  

   

    public Integer getTotalBox() {
        return totalBox;
    }

    public void setTotalBox(Integer totalBox) {
        this.totalBox = totalBox;
    }
     


    
    public Integer getGId() {
        return gId;
    }

    public void setGId(Integer gId) {
        this.gId = gId;
    }

    
   

    public Date getSDate() {
        return sDate;
    }

    public void setSDate(Date sDate) {
        this.sDate = sDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getOsgroup() {
        return osgroup;
    }

    public void setOsgroup(Integer osgroup) {
        this.osgroup = osgroup;
    }
  
    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public String getVStyle() {
        return vStyle;
    }

    public void setVStyle(String vStyle) {
        this.vStyle = vStyle;
    }

    
}
