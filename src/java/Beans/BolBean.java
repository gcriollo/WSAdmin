/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Corp;
import Entities.Customers;
import Entities.PBol;
import JWSAdmin.Jwsadmin;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author GCRIOLLO
 */
@ManagedBean(name = "BolBean")
@SessionScoped
public class BolBean implements Serializable {
    
    private PBol current;
    private Corp corp;
    private List<Customers> customerList;
    
    @EJB
    private Facade.CorpFacade CorpEjb;
    @EJB
    private Facade.CustomersFacade CustomerEjb;
    
    @PostConstruct
    public void init() {
        setCurrent(new PBol());
        customerList = CustomerEjb.getActiveCustomers();
    }

    /**
     * @return the current
     */
    public PBol getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(PBol current) {
        this.current = current;
    }
    
    public Corp getCorp(){
        if(corp ==null)
            corp = CorpEjb.findCorpbyId(jwsadminSesion().getCorp().getId());
        return corp;
    }
    
    public void addAction(){
    }
    
    public void saveBOL(){}
    
    public List<Customers> customersCmplt(String query){
        
        if(customerList !=null)
            return customerList;
        return new ArrayList();
    }
    
    private Jwsadmin jwsadminSesion(){
        ExternalContext contexto=FacesContext.getCurrentInstance().getExternalContext();
        Jwsadmin jwsadmin =(Jwsadmin)contexto.getSessionMap().get("jwsadmin");
        return jwsadmin;
    }
}

