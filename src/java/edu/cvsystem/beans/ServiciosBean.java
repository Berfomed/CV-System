
package edu.cvsystem.beans;


import edu.cvsystem.entidades.Compraventa;
import edu.cvsystem.entidades.Productos;
import edu.cvsystem.facades.CompraventaFacade;
import edu.cvsystem.facades.ProductosFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

@Named(value = "serviciosBean")
@SessionScoped
public class ServiciosBean implements Serializable{

 
    public ServiciosBean() {
       producto = new Productos(); 
       casacomercial = new Compraventa();
    }
    @EJB
    private ProductosFacade productofacade = new ProductosFacade();
    @EJB
    private CompraventaFacade casacomercialFacade = new CompraventaFacade();
    
    private Productos producto = new Productos();
    private Compraventa casacomercial = new Compraventa();

    public ProductosFacade getProductofacade() {
        return productofacade;
    }

    public void setProductofacade(ProductosFacade productofacade) {
        this.productofacade = productofacade;
    }

    public CompraventaFacade getCasacomercialFacade() {
        return casacomercialFacade;
    }

    public void setCasacomercialFacade(CompraventaFacade casacomercialFacade) {
        this.casacomercialFacade = casacomercialFacade;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Compraventa getCasacomercial() {
        return casacomercial;
    }

    public void setCasacomercial(Compraventa casacomercial) {
        this.casacomercial = casacomercial;
    }
    
    
    public List<Productos> listarProductos(){
        return productofacade.findAll();
    }
    public void crearProducto(){
        productofacade.create(producto);
        producto = new Productos();
    }
  
    public void borrarProducto(Productos item){
        productofacade.remove(item);
        producto=new Productos();
    }
    
      public String editarProducto(Productos editproducto){
        producto = editproducto;
        return "Editar";
    }
      
    public String editarProducto(){
        productofacade.edit(producto);
        producto = new Productos();
        return "inventarios";
        
    }
}
