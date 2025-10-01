/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.POrder;
import java.util.Date;
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
public class POrderFacade extends AbstractFacade<POrder> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public POrderFacade() {
        super(POrder.class);
    }
    
    public List<POrder> getPOrder(){
        try{
            return (List<POrder>)em.createNamedQuery("POrder.findAll").getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public POrder findByPOrder(String porder){
        try{
            return (POrder)em.createNamedQuery("POrder.findByPo").setParameter("po", porder).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public List<POrder> findByInvoice(String invoice){
        try{
            return (List<POrder>)em.createNamedQuery("POrder.findByInvoice").setParameter("invoice", invoice).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public List<POrder> findByShipOn(Date shipon){
        try{
            return (List<POrder>)em.createNamedQuery("POrder.findByShipOn").setParameter("shipOn", shipon).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public List<POrder> findByCancelAfter(Date cancelAfter){
        try{
            return (List<POrder>)em.createNamedQuery("POrder.findByCancelAfter").setParameter("cancelAfter", cancelAfter).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public List<POrder> findByShipOnRange(Date shipOnStart, Date shipOnEnd){
        try{
            return (List<POrder>)em.createNamedQuery("POrder.findByShipOnRange").setParameter("shipOnStart", shipOnStart).setParameter("shipOnEnd", shipOnEnd).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
}
