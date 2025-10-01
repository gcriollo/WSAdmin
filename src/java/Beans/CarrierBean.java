/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Carriers;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author GCRIOLLO
 */
@ManagedBean(name = "CarrierBean")
@SessionScoped
public class CarrierBean {

    @EJB
    private Facade.CarriersFacade CarriersEjb;
    private List<Carriers> carriers;
    
    
    public List<Carriers> carriersCmpltTxt(){
        carriers = CarriersEjb.getActiveCarriers();
        if(carriers !=null)
            return carriers;
        return new ArrayList();
    }
}
