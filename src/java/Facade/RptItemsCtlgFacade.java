/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.RptItemsCtlg;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EMP
 */
@Stateless
public class RptItemsCtlgFacade extends AbstractFacade<RptItemsCtlg> {
    @PersistenceContext(unitName = "WSAdminPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RptItemsCtlgFacade() {
        super(RptItemsCtlg.class);
    }
    
}
