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
@Table(name = "CUSTOMERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c"),
    @NamedQuery(name = "Customers.findById", query = "SELECT c FROM Customers c WHERE c.id = :id"),
    @NamedQuery(name = "Customers.findByName", query = "SELECT c FROM Customers c WHERE c.name = :name"),
    @NamedQuery(name = "Customers.findByNameC", query = "SELECT c FROM Customers c WHERE UPPER(c.name) like UPPER(:name) OR UPPER(c.company) like UPPER(:company)"),
    @NamedQuery(name = "Customers.findByCO", query = "SELECT c FROM Customers c WHERE c.cO = :cO"),
    @NamedQuery(name = "Customers.findByAddl1", query = "SELECT c FROM Customers c WHERE c.addl1 = :addl1"),
    @NamedQuery(name = "Customers.findByAddl2", query = "SELECT c FROM Customers c WHERE c.addl2 = :addl2"),
    @NamedQuery(name = "Customers.findByCity", query = "SELECT c FROM Customers c WHERE c.city = :city"),
    @NamedQuery(name = "Customers.findByState", query = "SELECT c FROM Customers c WHERE c.state = :state"),
    @NamedQuery(name = "Customers.findByZip", query = "SELECT c FROM Customers c WHERE c.zip = :zip"),
    @NamedQuery(name = "Customers.findByStatus", query = "SELECT c FROM Customers c WHERE c.status = :status"),
    @NamedQuery(name = "Customers.findBySdate", query = "SELECT c FROM Customers c WHERE c.sdate = :sdate")})
public class Customers implements Serializable {
    @OneToMany(mappedBy = "customer")
    private List<POrder> pOrderList;
    @Size(max = 100)
    @Column(name = "COMPANY")
    private String company;
    @Size(max = 20)
    @Column(name = "PYMT_TERMS")
    private String pymtTerms;
    @Size(max = 300)
    @Column(name = "SHIP_ADD")
    private String shipAdd;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "FAX")
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator( name = "SEC_CUSTOMER", sequenceName = "SEC_CUSTOMER", allocationSize = 1, initialValue = 1 )    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_CUSTOMER")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "C_O")
    private String cO;
    @Size(max = 100)
    @Column(name = "ADDL1")
    private String addl1;
    @Size(max = 100)
    @Column(name = "ADDL2")
    private String addl2;
    @Size(max = 20)
    @Column(name = "CITY")
    private String city;
    @Size(max = 20)
    @Column(name = "STATE")
    private String state;
    @Size(max = 20)
    @Column(name = "ZIP")
    private String zip;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;
    @JoinColumn(name = "CORP_FK", referencedColumnName = "ID")
    @ManyToOne
    private Corp corpFk;
    @OneToMany(mappedBy = "customerFk")
    private List<PBol> pBolList;

    public Customers() {
    }

    public Customers(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCO() {
        return cO;
    }

    public void setCO(String cO) {
        this.cO = cO;
    }

    public String getAddl1() {
        return addl1;
    }

    public void setAddl1(String addl1) {
        this.addl1 = addl1;
    }

    public String getAddl2() {
        return addl2;
    }

    public void setAddl2(String addl2) {
        this.addl2 = addl2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    public Corp getCorpFk() {
        return corpFk;
    }

    public void setCorpFk(Corp corpFk) {
        this.corpFk = corpFk;
    }

    @XmlTransient
    public List<PBol> getPBolList() {
        return pBolList;
    }

    public void setPBolList(List<PBol> pBolList) {
        this.pBolList = pBolList;
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
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name+" "+addl1;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPymtTerms() {
        return pymtTerms;
    }

    public void setPymtTerms(String pymtTerms) {
        this.pymtTerms = pymtTerms;
    }

    public String getShipAdd() {
        return shipAdd;
    }

    public void setShipAdd(String shipAdd) {
        this.shipAdd = shipAdd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<POrder> getPOrderList() {
        return pOrderList;
    }

    public void setPOrderList(List<POrder> pOrderList) {
        this.pOrderList = pOrderList;
    }
    
}
