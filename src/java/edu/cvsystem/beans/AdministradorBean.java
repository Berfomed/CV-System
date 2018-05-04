/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cvsystem.beans;

import edu.cvsystem.clases.EnvioCorreos;
import edu.cvsystem.clases.Mailler;
import edu.cvsystem.entidades.Compraventa;
import edu.cvsystem.entidades.Productos;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.CompraventaFacade;
import edu.cvsystem.facades.ProductosFacade;
import edu.cvsystem.facades.UsuariosFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Jorge Amado Perdomo
 */
@Named(value = "administradorBean")
@SessionScoped
public class AdministradorBean implements Serializable {

    /**
     * Creates a new instance of AdministradorBean
     */
    public AdministradorBean() {
        usuario = new Usuarios();
        compraventa = new Compraventa();
        producto = new Productos();
    }
    @EJB
    private UsuariosFacade usuariofacade = new UsuariosFacade();
    private Usuarios usuario;
    
    @EJB
    private ProductosFacade productofacade = new ProductosFacade();
    private Productos producto;

    @EJB
    private CompraventaFacade compraventafacade = new CompraventaFacade();
    private Compraventa compraventa;
    JasperPrint jasperPrint;

        
    private String contenido = "Se realizara un mantenimiento de una hora aproximadamente";
    private String asunto = "Mantenimiento";
    private List<Usuarios> listaUsuarios = new ArrayList<>();
    private Part file1;
    private String nombre1;
    private String pathReal1;
    
    
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public CompraventaFacade getCompraventafacade() {
        return compraventafacade;
    }

    public void setCompraventafacade(CompraventaFacade compraventafacade) {
        this.compraventafacade = compraventafacade;
    }

    public Compraventa getCompraventa() {
        return compraventa;
    }

    public void setCompraventa(Compraventa compraventa) {
        this.compraventa = compraventa;
    }

    public UsuariosFacade getUsuariofacade() {
        return usuariofacade;
    }

    public void setUsuariofacade(UsuariosFacade usuariofacade) {
        this.usuariofacade = usuariofacade;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios= usuariofacade.verUsuarios();
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    
    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getPathReal1() {
        return pathReal1;
    }

    public void setPathReal1(String pathReal1) {
        this.pathReal1 = pathReal1;
    }

    public String editarUsuario(Usuarios item) {
        this.usuario = item;
        return "editarUs";
    }

    public String editarUsuario() {
        usuariofacade.edit(usuario);
        usuario = new Usuarios();
        return "usuarios";
    }

    public String editarCompraventa(Compraventa item) {
        this.compraventa = item;
        return "editarCo?faces-redirect=true";
    }

    public String editarCompraventa() {
        compraventafacade.edit(compraventa);
        compraventa = new Compraventa();
        return "compraventas";
    }

    public void init() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(usuariofacade.findAll());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/rptUsuarios.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
    }

    public void PDF() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=usuariosReport.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    

    public void PDF2() throws JRException, IOException {
        jasperPrint = new  JasperPrint();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(compraventafacade.findAll());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/rptCasaComercial.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=casascomercialesReport.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
 
    
       public String upload() {
        String path1 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path1 = path1.substring(0, path1.indexOf("\\build"));
        path1 = path1 + "\\web\\archivos\\";
        try {
            this.nombre1 = file1.getSubmittedFileName();
            pathReal1 = "/CVSystem/archivos/" + nombre1;
            path1 = path1 + this.nombre1;
            InputStream in1 = file1.getInputStream();
            byte[] data1 = new byte[in1.available()];
            in1.read(data1);
            FileOutputStream out1 = new FileOutputStream(new File(path1));
            out1.write(data1);
            in1.close();
            out1.close();
            path1 = path1.replace("\\","\\\\");
            productofacade.importar(path1,"productos");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "compraventas?faces-redirect=true";
    }
       public void enviarCorreos() throws UnsupportedEncodingException{
        listaUsuarios = usuariofacade.verUsuarios();
        String[] correos = new String[listaUsuarios.size()];
        for (int i = 0; i < listaUsuarios.size(); i++) {
          correos[i] = listaUsuarios.get(i).getCorreoElectronico();
//            EnvioCorreos.send(listaUsuarios.get(i).getCorreoElectronico(),asunto,contenido);
        }
        Mailler.enviarCorreo("Alerta", "El servidor se encuentra en mantenimiento", correos);
        asunto = "";
        contenido = "";
    }
}
