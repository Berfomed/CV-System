package edu.cvsystem.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.persistence.Query;
import javax.faces.context.FacesContext;
import javax.enterprise.context.RequestScoped;
import edu.cvsystem.entidades.CompraProducto;
import edu.cvsystem.entidades.SolicitudCompraventa;
import edu.cvsystem.entidades.SolicitudDomicilio;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.CompraProductoFacade;
import edu.cvsystem.facades.SolicitudCompraventaFacade;
import edu.cvsystem.facades.SolicitudDomicilioFacade;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

@Named(value = "historialBean")
@RequestScoped
public class HistorialBean {

    @EJB
    private CompraProductoFacade compraProductoFacade;
    @EJB
    private SolicitudDomicilioFacade solicitudDomicilioFacade;
    @EJB
    private SolicitudCompraventaFacade solicitudCompraventaFacade;

    public HistorialBean() {
    }

    public List<CompraProducto> getHistorialCompraProductos() {
        Query consulta = compraProductoFacade.getEm().createQuery("SELECT c FROM CompraProducto c WHERE c.idUsuario = :idUsuario");
        consulta.setParameter("idUsuario", getUsuarioSesion());
        return consulta.getResultList();
    }

    public List<SolicitudCompraventa> getHistorialSolicitudCompraventas(String tipo) {
        Query consulta = solicitudCompraventaFacade.getEm().createQuery("SELECT s FROM SolicitudCompraventa s WHERE s.idUsuario = :idUsuario AND s.tipo = :tipo");
        consulta.setParameter("idUsuario", getUsuarioSesion());
        consulta.setParameter("tipo", tipo);
        return consulta.getResultList();
    }

    public String[] getFotos(int idProducto) {
        String context = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/img/productos/Producto(" + idProducto + ")");
        File file = new File(context);
        for (String foto : file.list()) {
            System.out.println(foto);
        }
        return file.list();
    }

    public String[] getFotosSolicitudes(int idSolicitud) {
        String context = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("resources/img/solicitudes/Solicitud(" + idSolicitud + ")").replace("\\build", "");
        System.out.println(context);
        File file = new File(context);
        return file.list();
    }

    public String printEstadoSolicitud(String estado) {
        if (estado.length() > 0) {
            return (estado.equals("aceptada")) ? "Aceptada" : "Rechazada";
        }
        return "En espera";
    }

    public String printColorSolicitud(String estado) {
        if (estado.length() > 0) {
            return (estado.equals("aceptada")) ? "text-success" : "text-danger";
        }
        return "text-secondary";
    }

    public String getHistorialDomicilio(CompraProducto compraProducto) {
        Query consulta = solicitudDomicilioFacade.getEm().createQuery("SELECT s FROM SolicitudDomicilio s WHERE s.idUsuario = :idUsuario AND s.idProducto = :idProducto");
        consulta.setParameter("idUsuario", compraProducto.getIdUsuario());
        consulta.setParameter("idProducto", compraProducto.getIdProductoInventario().getIdProducto());
        List<SolicitudDomicilio> lista = consulta.getResultList();

        if (!lista.isEmpty()) {
            return (lista.get(0).getEstado().equals("pendiente")) ? "En proceso" : "Si aplica";
        }
        return "No aplica";
    }

    public Usuarios getUsuarioSesion() {
        return (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLog");
    }
}
