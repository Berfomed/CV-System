package edu.cvsystem.facades;

import edu.cvsystem.entidades.SolicitudCompraventa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SolicitudCompraventaFacade extends AbstractFacade<SolicitudCompraventa> {

    @PersistenceContext(unitName = "CVSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudCompraventaFacade() {
        super(SolicitudCompraventa.class);
    }

    public EntityManager getEm() {
        return em;
    }
}
