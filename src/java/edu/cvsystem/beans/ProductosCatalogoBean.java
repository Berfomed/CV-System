package edu.cvsystem.beans;

import edu.cvsystem.entidades.ProductoInventario;
import edu.cvsystem.entidades.Productos;
import edu.cvsystem.facades.ProductosFacade;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

@Named(value = "productosCatalogoBean")
@RequestScoped
public class ProductosCatalogoBean {

  @EJB
  private ProductosFacade productoFacade;

  public ProductosCatalogoBean() {
  }

  @PostConstruct
  public void init() {
    producto = new ProductoInventario();
  }

  public List<Productos> listarProductos() {
    TypedQuery<Productos> query = productoFacade.getEm()
            .createQuery("SELECT p FROM Productos p WHERE p.estatus = :estatus",
                    Productos.class);
    query.setParameter("estatus", "Disponible");
    return query.getResultList();
  }

  public ProductoInventario getProducto() {
    return producto;
  }

  public void setProducto(ProductoInventario producto) {
    this.producto = producto;
  }

  private ProductoInventario producto;
}
