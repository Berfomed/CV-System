package edu.cvsystem.beans;

import javax.ejb.EJB;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import edu.cvsystem.entidades.Compraventa;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.CompraventaFacade;
import edu.cvsystem.facades.UsuariosFacade;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import edu.cvsystem.clases.Mailler;

@Named(value = "compraventasBean")
@RequestScoped
public class CompraventasBean implements Serializable {


    public CompraventasBean() {
        usuarios = new Usuarios();
        compraventa = new Compraventa();
    }
  
    @EJB
    private CompraventaFacade compraventaFacade;
    
    @EJB
    private UsuariosFacade usuariosFacade;

    public List<Compraventa> listarCompraventas() {
        return compraventaFacade.findAll();
    }

    public void registrarCompraventa() {
//        compraventa.setIdUsuario(usuariosFacade.find(usuarios.getIdUsuario()));
        usuarios.setEstado("activo");
        usuarios.setRol("gerente");
        usuariosFacade.create(usuarios);
        compraventa.setIdUsuario(usuarios);
        compraventaFacade.create(compraventa);
        Mailler.enviarCorreo(compraventa.getNombre(), usuarios.getCorreoElectronico() + usuarios.getContrasena(), usuarios.getCorreoElectronico());
        compraventa = new Compraventa();
        usuarios = new Usuarios();
    }
    
    

    public String editarCamp(Compraventa item) {
        this.compraventa = item;
        return "editarCo";
    }

    public String editarCamp() {       
        compraventaFacade.edit(compraventa);      
        compraventa = new Compraventa();
        return "compraventas";
    }

    public Compraventa getCompraventa() {
        return compraventa;
    }

    public void setCompraventa(Compraventa compraventa) {
        this.compraventa = compraventa;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> listarUsuarios() {
        return usuariosFacade.findAll();
    }
    private Usuarios usuarios;
    private Compraventa compraventa;
}
