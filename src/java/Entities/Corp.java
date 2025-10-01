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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "CORP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corp.findAll", query = "SELECT c FROM Corp c"),
    @NamedQuery(name = "Corp.findById", query = "SELECT c FROM Corp c WHERE c.id = :id"),
    @NamedQuery(name = "Corp.findByName", query = "SELECT c FROM Corp c WHERE c.name = :name"),
    @NamedQuery(name = "Corp.findByAddl1", query = "SELECT c FROM Corp c WHERE c.addl1 = :addl1"),
    @NamedQuery(name = "Corp.findByAddl2", query = "SELECT c FROM Corp c WHERE c.addl2 = :addl2"),
    @NamedQuery(name = "Corp.findByState", query = "SELECT c FROM Corp c WHERE c.state = :state"),
    @NamedQuery(name = "Corp.findByCity", query = "SELECT c FROM Corp c WHERE c.city = :city"),
    @NamedQuery(name = "Corp.findByZipcode", query = "SELECT c FROM Corp c WHERE c.zipcode = :zipcode"),
    @NamedQuery(name = "Corp.findByPhone", query = "SELECT c FROM Corp c WHERE c.phone = :phone")})
public class Corp implements Serializable {
    @OneToMany(mappedBy = "corp")
    private List<RptItemsCtlgDet> rptItemsCatalogList;
    @OneToMany(mappedBy = "corp")
    private List<Items> itemsList;
    @OneToMany(mappedBy = "corp")
    private List<ItemsCategory> itemsCategoryList;
    @OneToMany(mappedBy = "corp")
    private List<ItemsDet> itemsDetList;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(mappedBy = "corp")
    private List<Users> usersList;
    @OneToMany(mappedBy = "corpFk")
    private List<Customers> customersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corpFk")
    private List<PBol> pBolList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 100)
    @Column(name = "ADDL1")
    private String addl1;
    @Size(max = 100)
    @Column(name = "ADDL2")
    private String addl2;
    @Size(max = 20)
    @Column(name = "STATE")
    private String state;
    @Size(max = 20)
    @Column(name = "CITY")
    private String city;
    @Size(max = 20)
    @Column(name = "ZIPCODE")
    private String zipcode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 13)
    @Column(name = "PHONE")
    private String phone;
    @OneToMany(mappedBy = "corp")
    private List<PMakelabels> pMakelabelsList;
    @Size(max = 200)
    @Column(name = "URL_REPORTS")
    private String urlreports;
    @Size(max = 200)
    @Column(name = "URL_ITEM_PICS")
    private String urlitempics;
    
    public Corp() {
    }

    public Corp(BigDecimal id) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }
    
    public String geturlReports(){
        return urlreports;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void seturlReports(String url_reports) {
        this.urlreports = url_reports;
    }

    /**
     * @return the urlitempics
     */
    public String getUrlitempics() {
        return urlitempics;
    }

    /**
     * @param urlitempics the urlitempics to set
     */
    public void setUrlitempics(String urlitempics) {
        this.urlitempics = urlitempics;
    }
    
    @XmlTransient
    public List<PMakelabels> getPMakelabelsList() {
        return pMakelabelsList;
    }

    public void setPMakelabelsList(List<PMakelabels> pMakelabelsList) {
        this.pMakelabelsList = pMakelabelsList;
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
        if (!(object instanceof Corp)) {
            return false;
        }
        Corp other = (Corp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Corp[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Customers> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customers> customersList) {
        this.customersList = customersList;
    }

    @XmlTransient
    public List<PBol> getPBolList() {
        return pBolList;
    }

    public void setPBolList(List<PBol> pBolList) {
        this.pBolList = pBolList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @XmlTransient
    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    @XmlTransient
    public List<ItemsCategory> getItemsCategoryList() {
        return itemsCategoryList;
    }

    public void setItemsCategoryList(List<ItemsCategory> itemsCategoryList) {
        this.itemsCategoryList = itemsCategoryList;
    }

    @XmlTransient
    public List<ItemsDet> getItemsDetList() {
        return itemsDetList;
    }

    public void setItemsDetList(List<ItemsDet> itemsDetList) {
        this.itemsDetList = itemsDetList;
    }

    @XmlTransient
    public List<RptItemsCtlgDet> getRptItemsCatalogList() {
        return rptItemsCatalogList;
    }

    public void setRptItemsCatalogList(List<RptItemsCtlgDet> rptItemsCatalogList) {
        this.rptItemsCatalogList = rptItemsCatalogList;
    }

    
    
}
