/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Corp;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EMP
 */
@Stateless
public class CorpFacade extends AbstractFacade<Corp> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorpFacade() {
        super(Corp.class);
    }
    
    public Corp findCorpbyId(BigDecimal i){
        try{
            return (Corp)em.createNamedQuery("Corp.findById").setParameter("id", i).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
