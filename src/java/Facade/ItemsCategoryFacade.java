/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.ItemsCategory;
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
public class ItemsCategoryFacade extends AbstractFacade<ItemsCategory> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemsCategoryFacade() {
        super(ItemsCategory.class);
    }
    
    public List<ItemsCategory> findItemsCategory(){
        try{
            return (List<ItemsCategory>)em.createNamedQuery("ItemsCategory.findByStatus").setParameter("status", "A").getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
}
