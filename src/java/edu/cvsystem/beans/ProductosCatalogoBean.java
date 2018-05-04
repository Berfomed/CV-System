package edu.cvsystem.beans;

import edu.cvsystem.entidades.ProductoInventario;
import edu.cvsystem.facades.ProductoInventarioFacade;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

@Named(value = "productosCatalogoBean")
@RequestScoped
public class ProductosCatalogoBean {

    @EJB
    private ProductoInventarioFacade productoFacade;

    public ProductosCatalogoBean() {
    }

    @PostConstruct
    public void init() {
        producto = new ProductoInventario();
        filtro = "";
    }

    public List<ProductoInventario> listarProductos() {
        List<ProductoInventario> lista = productoFacade.findAll();
        if (!filtro.equals("")) {
            for (int i = 0; i < lista.size(); i++) {
                if (!lista.get(i).getIdProducto().getNombre().equalsIgnoreCase(filtro)) {
                    System.out.println(lista.get(i).getIdProducto().getNombre());
                    lista.remove(i);
                }
            }
        }
        return lista;
    }
    
//    public List<ProductoInventario> listarProductos() {
//        String query = "SELECT p FROM ProductoInventario p WHERE p.estado = 'disponible' ORDER BY p.fechaIngreso DESC";
//        Query consulta = productoFacade.getEm().createQuery(query);
//
//        consulta.setParameter("filtro", "");
//
//        List<ProductoInventario> lista = consulta.getResultList();
//        return lista;
//    }

    public ProductoInventario getProducto() {
        return producto;
    }

    public void setProducto(ProductoInventario producto) {
        this.producto = producto;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    private ProductoInventario producto;
    private String filtro;
}
