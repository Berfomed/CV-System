package edu.cvsystem.facades;

import edu.cvsystem.entidades.SolicitudDomicilio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SolicitudDomicilioFacade extends AbstractFacade<SolicitudDomicilio> {

    @PersistenceContext(unitName = "CVSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudDomicilioFacade() {
        super(SolicitudDomicilio.class);
    }

    public EntityManager getEm() {
        return em;
    }
}
