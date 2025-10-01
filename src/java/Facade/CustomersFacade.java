/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Customers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EMP
 */
@Stateless
public class CustomersFacade extends AbstractFacade<Customers> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomersFacade() {
        super(Customers.class);
    }
    
    public List<Customers> getActiveCustomers(){
        try{
            return (List<Customers>)em.createNamedQuery("Customers.findByStatus").setParameter("status","A").getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public List<Customers> getCustomersSearch(String text){
        try{
            return (List<Customers>)em.createNamedQuery("Customers.findByNameC").setParameter("name","%".concat(text.concat("%"))).setParameter("company", text).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
}
