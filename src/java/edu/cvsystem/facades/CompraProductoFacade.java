package edu.cvsystem.facades;

import edu.cvsystem.entidades.CompraProducto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CompraProductoFacade extends AbstractFacade<CompraProducto> {

    @PersistenceContext(unitName = "CVSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraProductoFacade() {
        super(CompraProducto.class);
    }
    
    public EntityManager getEm() {
        return em;
    }
}
