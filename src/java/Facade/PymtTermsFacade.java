/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.PymtTerms;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EMP
 */
@Stateless
public class PymtTermsFacade extends AbstractFacade<PymtTerms> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PymtTermsFacade() {
        super(PymtTerms.class);
    }
    
}
