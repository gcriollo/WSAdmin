/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.POrderDet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EMP
 */
@Stateless
public class POrderDetFacade extends AbstractFacade<POrderDet> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public POrderDetFacade() {
        super(POrderDet.class);
    }
    
    /*public POrderDet findByPOrder(String porder){
        try{
            return (POrderDet)em.createNamedQuery("POrder.findByPo").setParameter("po", porder).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }*/
    
}
