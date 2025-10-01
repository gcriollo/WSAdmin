/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Corp;
import Entities.ItemsDet;
import java.math.BigDecimal;
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
public class ItemsDetFacade extends AbstractFacade<ItemsDet> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemsDetFacade() {
        super(ItemsDet.class);
    }
    
    public List<ItemsDet> findItemsDetActive(BigDecimal c){
        try{
            return (List<ItemsDet>)em.createNamedQuery("ItemsDet.findAllActive").setParameter("status", "A").setParameter("corp", c).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public List<ItemsDet> findItemsDetbyColor(String color, BigDecimal c){
        try{
            return (List<ItemsDet>)em.createNamedQuery("ItemsDet.findAllActive").setParameter("status", "A").setParameter("corp", c).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
}
