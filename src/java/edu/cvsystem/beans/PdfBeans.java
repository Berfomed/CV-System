
package edu.cvsystem.beans;

import edu.cvsystem.entidades.Productos;
import edu.cvsystem.facades.ProductosFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Named(value = "pdfBeans")
@SessionScoped
public class PdfBeans implements Serializable {
   
    @EJB
    private ProductosFacade productofacade;
    private List<Productos> listaproducto;
    JasperPrint jasperPrint;

    public PdfBeans() {
      
    }
    public ProductosFacade getProductofacade() {
        return productofacade;
    }

    public void setProductofacade(ProductosFacade productofacade) {
        this.productofacade = productofacade;
    }

    public List<Productos> listarInventario() {
        listaproducto=productofacade.findAll();
        return listaproducto;
    }

    public void setListaproducto(List<Productos> listaproducto) {
        this.listaproducto = listaproducto;
    }

    public List<Productos> getListaproducto() {
        return listaproducto;
    }
    

    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productofacade.findAll());
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//reportes//");
        jasperPrint = JasperFillManager.fillReport(ruta + "//Reporteinventario.jasper", new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=Report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
 }
    
}
