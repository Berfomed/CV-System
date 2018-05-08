package edu.cvsystem.beans;

import edu.cvsystem.clases.LoaderFiles;
import edu.cvsystem.entidades.Compraventa;
import edu.cvsystem.entidades.ProductoEmpeno;
import edu.cvsystem.entidades.SolicitudCompraventa;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.CompraventaFacade;
import edu.cvsystem.facades.ProductoEmpenoFacade;
import edu.cvsystem.facades.SolicitudCompraventaFacade;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.servlet.http.Part;
import javax.faces.context.FacesContext;

@Model
public class SolicitudVentaBean {

    @EJB
    private SolicitudCompraventaFacade solicitudCompraventaFacade;

    @EJB
    private CompraventaFacade compraventaFacade;
    
    @EJB
   private ProductoEmpenoFacade productoEmpenoFacade;
    
    public SolicitudVentaBean() {
    }

    @PostConstruct
    public void init() {
        empeno = new ProductoEmpeno();
        solicitud = new SolicitudCompraventa();
        compraventa = new Compraventa();
        msg = "";
    }

public void registrarSolicitud(String tipo) {
        Usuarios usuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLog");
        solicitud.setTipo(tipo);
        solicitud.setIdUsuario(usuario);
//        solicitud.setIdUsuarioServicios(0);
        solicitud.setIdCompraventa(compraventa);
        solicitud.setFechaEnvio(new GregorianCalendar().getTime());
        int calc=(int) ((empeno.getFechaFinal().getTime()-empeno.getFechaInicio().getTime())/86400000);
        empeno.setDias(calc);
        empeno.setInteres((compraventa.getInteresCompraventa()/360)*empeno.getDias());
        empeno.setPrecioapagar(solicitud.getPrecio()*empeno.getInteres());
       
    // solicitud.setFotos("https://cvsystem.com/fotos/fs31a5d/");

        solicitudCompraventaFacade.create(solicitud);
        List<SolicitudCompraventa> lista = solicitudCompraventaFacade.findAll();

        msg = "La solicitud ha sido envida exitosamente";
        if (path != null) {
            cargarImagen(path, lista.get(lista.size() - 1).getIdSolicitudCompraventa());
        }
        solicitud = new SolicitudCompraventa();
    }

    public void cargarImagen(Part p, Integer id) {
        try {
            String pathFolder = FacesContext.getCurrentInstance().getExternalContext()
                    .getRealPath("resources").concat("\\img\\solicitudes\\Solicitud(" + id + ")").replace("\\build", "");
            System.out.println("------------------------------------------------------");
            System.out.println(pathFolder);
            System.out.println("------------------------------------------------------");
            File folder = new File(pathFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }
            String nombre = p.getSubmittedFileName();
            LoaderFiles.copiarArchivo(p.getInputStream(), pathFolder.concat("\\" + nombre));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Compraventa> listarCompraventas() {
        return compraventaFacade.findAll();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SolicitudCompraventa getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudCompraventa solicitud) {
        this.solicitud = solicitud;
    }

    public Compraventa getCompraventa() {
        return compraventa;
    }

    public void setCompraventa(Compraventa compraventa) {
        this.compraventa = compraventa;
    }

    public Part getPath() {
        return path;
    }

    public void setPath(Part path) {
        this.path = path;
    }

    private Part path;
    private String msg;
    private ProductoEmpeno empeno;
    private Compraventa compraventa;
    private SolicitudCompraventa solicitud;
}
