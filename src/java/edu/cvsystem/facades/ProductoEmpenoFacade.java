/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cvsystem.facades;

import edu.cvsystem.entidades.ProductoEmpeno;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge Amado Perdomo
 */
@Stateless
public class ProductoEmpenoFacade extends AbstractFacade<ProductoEmpeno> {

    @PersistenceContext(unitName = "CVSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoEmpenoFacade() {
        super(ProductoEmpeno.class);
    }

    public EntityManager getEm() {
        return em;
    }
    
}
