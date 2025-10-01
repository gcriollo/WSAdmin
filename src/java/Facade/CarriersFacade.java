/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Carriers;
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
public class CarriersFacade extends AbstractFacade<Carriers> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarriersFacade() {
        super(Carriers.class);
    }
    
    public List<Carriers> getActiveCarriers(){
        try{
            String s = "A";
            return (List<Carriers>)em.createNamedQuery("Carriers.findByStatus").setParameter("status",s).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
}
