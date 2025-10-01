/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.PMakelabels;
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
public class PMakelabelsFacade extends AbstractFacade<PMakelabels> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PMakelabelsFacade() {
        super(PMakelabels.class);
    }
    
    public List<PMakelabels> findLabelsByPONumberGID(String porder, Integer gid){
        try{
            return em.createNamedQuery("PMakelabels.findByPoandGID").setParameter("po", porder).setParameter("gId", gid).getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
}
