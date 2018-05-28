package edu.cvsystem.beans;

import com.sun.faces.facelets.util.Path;
import edu.cvsystem.clases.LoaderFiles;
import edu.cvsystem.entidades.Compraventa;
import edu.cvsystem.entidades.ProductoEmpeno;
import edu.cvsystem.entidades.SolicitudCompraventa;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.CompraventaFacade;
import edu.cvsystem.facades.ProductoEmpenoFacade;
import edu.cvsystem.facades.SolicitudCompraventaFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.servlet.http.Part;
import javax.faces.context.FacesContext;
import javax.persistence.Query;

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
        solicitud.setIdUsuarioServicios(0);
        solicitud.setIdCompraventa(compraventa);
        solicitud.setFechaEnvio(Date.valueOf(LocalDate.now()));
        solicitud.setFotos("");
//        int calc = (int) ((empeno.getFechaFinal().getTime() - empeno.getFechaInicio().getTime()) / 86400000);
//        empeno.setDias(calc);
//        compraventa = compraventaFacade.find(compraventa.getIdCompraventa());

//        System.out.println("++++++++++++" + compraventa.getInteresCompraventa());
//        System.out.println("++++++++++++" + empeno.getDias());
//        empeno.setInteres((compraventa.getInteresCompraventa() / 360) * empeno.getDias());
//        empeno.setPrecioapagar(solicitud.getPrecio() * empeno.getInteres());
        solicitudCompraventaFacade.create(solicitud);
        solicitud.setFotos(cargarImagen(path, solicitud.getIdSolicitudCompraventa()));
        solicitudCompraventaFacade.edit(solicitud);
//        productoEmpenoFacade.create(empeno);
//        Query query = compraventaFacade.getEm().createNativeQuery("SELECT MAX(id_solicitud_compraventa) FROM solicitud_compraventa");
//        int id = (int) query.getResultList().get(0);
//        solicitud.setFotos("resources/img/solicitudes/" + "Solicitud(" + id + ")");
//        solicitudCompraventaFacade.edit(solicitud);
//        List<SolicitudCompraventa> lista = solicitudCompraventaFacade.findAll();
//        msg = "La solicitud ha sido envida exitosamente";
//        if (path != null) {
//            cargarImagen(path, lista.get(lista.size() - 1).getIdSolicitudCompraventa());
//        }
        solicitud = new SolicitudCompraventa();
    }

    public String cargarImagen(Part p, Integer id) {
        try {
//            String pathFolder = FacesContext.getCurrentInstance().getExternalContext()
//                    .getRealPath("resources").concat("\\img\\solicitudes\\Solicitud(" + id + ")").replace("\\build", "");
            String pathFolderBuild = FacesContext.getCurrentInstance().getExternalContext()
                    .getRealPath("resources").concat("\\img\\solicitudes\\Solicitud(" + id + ")");

            File folderBuild = new File(pathFolderBuild);
            File folder = new File(pathFolderBuild.replace("\\build", ""));

            folderBuild.mkdir();
            folder.mkdir();

            String pathFolder = folder.getPath() + "\\" + p.getSubmittedFileName();
            pathFolderBuild += "\\" + p.getSubmittedFileName();

            LoaderFiles.copiarArchivo(p.getInputStream(), pathFolder);
            LoaderFiles.copiarArchivo(p.getInputStream(), pathFolderBuild);

            pathFolder = pathFolder.substring(pathFolder.lastIndexOf("resources"));

            return pathFolder;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return "";
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

    public ProductoEmpeno getEmpeno() {
        return empeno;
    }

    public void setEmpeno(ProductoEmpeno empeno) {
        this.empeno = empeno;
    }

    private Part path;
    private String msg;
    private ProductoEmpeno empeno;
    private Compraventa compraventa;
    private SolicitudCompraventa solicitud;
}
