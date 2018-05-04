package edu.cvsystem.beans;


import edu.cvsystem.entidades.SolicitudDomicilio;
import edu.cvsystem.facades.SolicitudDomicilioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "domiciliosBean")
@SessionScoped
public class DomiciliosBean implements Serializable {

    public DomiciliosBean() {
        domicilio = new SolicitudDomicilio();
    }
    @EJB
    private SolicitudDomicilioFacade domiciliofacade = new SolicitudDomicilioFacade();
    private SolicitudDomicilio domicilio = new SolicitudDomicilio();

    public SolicitudDomicilioFacade getDomiciliofacade() {
        return domiciliofacade;
    }

    public void setDomiciliofacade(SolicitudDomicilioFacade domiciliofacade) {
        this.domiciliofacade = domiciliofacade;
    }

    public SolicitudDomicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(SolicitudDomicilio domicilio) {
        this.domicilio = domicilio;
    }
    public List<SolicitudDomicilio> listarDomicilios(){
        return domiciliofacade.findAll();
    }  
    public void registrarDomicilio(){
        domiciliofacade.create(domicilio);
        domicilio = new SolicitudDomicilio();
    }
    public void eliminarDomicilio(SolicitudDomicilio item){
        domiciliofacade.remove(item);
        domicilio=new SolicitudDomicilio();
    }
    public String editarDomicilio(SolicitudDomicilio item){
        domicilio = item;
        return "editardomi";
    }
    public String editarDomicilio(){
        domiciliofacade.edit(domicilio);
        domicilio = new SolicitudDomicilio();
        return "domicilios";
    }
    
    
    
}
