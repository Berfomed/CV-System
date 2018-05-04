package edu.cvsystem.facades;

import edu.cvsystem.entidades.ProductoInventario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductoInventarioFacade extends AbstractFacade<ProductoInventario> {

    @PersistenceContext(unitName = "CVSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoInventarioFacade() {
        super(ProductoInventario.class);
    }

    public EntityManager getEm() {
        return em;
    }
}
