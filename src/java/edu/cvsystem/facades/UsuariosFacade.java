/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cvsystem.facades;

import edu.cvsystem.entidades.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jorge Amado Perdomo
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "CVSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    private List<Usuarios> informacion = new ArrayList<>();

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public EntityManager getEm() {
        return em;
    }

    public List<Usuarios> verUsuarios(){
         String consulta;
         try {
//                consulta = "SELECT * FROM usuarios u WHERE u.estado = 'activo'";
                Query query = em.createNativeQuery("SELECT * FROM usuarios u WHERE u.estado = 'activo'");
                List<Usuarios> lista = query.getResultList();
                if (!lista.isEmpty()) {
                 informacion = lista;
             }
         } catch (Exception e) {
             throw e;
         }
         return informacion;
     }

}
