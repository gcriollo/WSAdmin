/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Items;
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
public class ItemsFacade extends AbstractFacade<Items> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemsFacade() {
        super(Items.class);
    }
    
    public List<Items> getItems(){
        try{
            String s = "A";
            return (List<Items>)em.createNamedQuery("Items.findByStatus").setParameter("status",s).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public Items findItembyCode(String code){
        try{
            String s = "A";
            return (Items)em.createNamedQuery("Items.findByCode").setParameter("code",code).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
