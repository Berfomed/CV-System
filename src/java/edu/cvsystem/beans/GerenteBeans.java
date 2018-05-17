package edu.cvsystem.beans;

import edu.cvsystem.clases.CargaArchivos;
import edu.cvsystem.clases.Mailler;
import edu.cvsystem.entidades.Compraventa;
import edu.cvsystem.entidades.ProductoEmpeno;
import edu.cvsystem.entidades.Productos;
import edu.cvsystem.entidades.SolicitudCompraventa;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.CompraventaFacade;
import edu.cvsystem.facades.ProductoEmpenoFacade;
import edu.cvsystem.facades.ProductosFacade;
import edu.cvsystem.facades.SolicitudCompraventaFacade;
import edu.cvsystem.facades.UsuariosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import javax.persistence.Query;
import javax.servlet.http.Part;

@Named(value = "gerenteBeans")
@SessionScoped
public class GerenteBeans implements Serializable {

    private float item;
    private String opcion;
    private List<Productos> resultado;

    public GerenteBeans() {
        producto = new Productos();
        compraventa = new Compraventa();
        solicitud = new SolicitudCompraventa();        
        productoem = new ProductoEmpeno();
        usuario = new Usuarios();
    }
@EJB ProductoEmpenoFacade productoemfacade;
    
    @EJB
    CompraventaFacade compraventaFacade;

    /*Generamos las variables necesarias para el manejo de la entidad Productos*/
    @EJB
    ProductosFacade productofacade = new ProductosFacade();
    Productos producto = new Productos();
    Usuarios usuario = new Usuarios();
  

    /*Generamos las variables necesarias para el manejo de la entidad Solicitudes*/
    @EJB
    SolicitudCompraventaFacade solicitudfacade = new SolicitudCompraventaFacade();
    SolicitudCompraventa solicitud = new SolicitudCompraventa();
    private Compraventa compraventa = new Compraventa();
    

    /*aqui generamos las variables necesarias para generar la carga de archivos*/
    private Part file;
    private String nombre;
    private String pathReal;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    @EJB
    UsuariosFacade usuariosFacade = new UsuariosFacade();

//    public List<SolicitudCompraventa> listarSolicitudes() {
//        return solicitudfacade.findAll();
//    }
    public Usuarios getUsuarioSesion() {
        return (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLog");
    }

    //Aqui Generamos los Metodos para Generar Filtros en el Inventario.
    public float getItem() {
        float item = 0;
        return item;
    }

    public void setItem(float item) {
        this.item = item;
    }

    public int conteoProducto() {
        return productofacade.count();
    }

    public SolicitudCompraventaFacade getSolicitudfacade() {
        return solicitudfacade;
    }

    public void setSolicitudfacade(SolicitudCompraventaFacade solicitudfacade) {
        this.solicitudfacade = solicitudfacade;
    }

    public SolicitudCompraventa getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudCompraventa solicitud) {
        this.solicitud = solicitud;
    }

    public ProductosFacade getProductofacade() {
        return productofacade;
    }

    public void setProductofacade(ProductosFacade productofacade) {
        this.productofacade = productofacade;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Compraventa getCompraventa() {
        return compraventa;
    }

    public void setCompraventa(Compraventa compraventa) {
        this.compraventa = compraventa;
    }
  
      //aqui genero los set y get para filtrar por categoria 
       public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }


    public String editarProducto(Productos editproducto) {
        producto = editproducto;
        return "editarpro";
    }

    public String editarProducto() {
        productofacade.edit(producto);
        producto = new Productos();
        return "catalogoG";

    }

    public void eliminarProducto(Productos item) {
        productofacade.remove(item);
        producto = new Productos();
    }

    public List<Usuarios> usuarioServicios() {

        return usuariosFacade.findAll();
    }

    public UsuariosFacade getUsuariosFacade() {
        return usuariosFacade;
    }

    public void setUsuariosFacade(UsuariosFacade usuariosFacade) {
        this.usuariosFacade = usuariosFacade;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String editarSolicitud(SolicitudCompraventa item) {
        solicitud = item;
        solicitud.setRespuesta("Respondido");
//        Mailler.enviarCorreo(solicitud.getTitulo(), "", usuario.getCorreoElectronico());
        return "respondercorreo";
    }

    public String editarSolicitud() throws UnsupportedEncodingException {
        solicitud.setIdUsuarioServicios(new Usuarios(1).getIdUsuario());
        solicitudfacade.edit(solicitud);
//        EnvioCorreos.send(solicitud.getIdUsuario().getCorreoElectronico(), solicitud.getTitulo(), solicitud.getRespuestaMsg());
        Mailler.enviarCorreo(solicitud.getTitulo(), solicitud.getRespuestaMsg(), solicitud.getIdUsuario().getCorreoElectronico());
        solicitud = new SolicitudCompraventa();
        return "solicitudes";

    }

    public void eliminarSolicitud() {
        solicitudfacade.remove(solicitud);
        solicitud = new SolicitudCompraventa();

    }
    
    public float sumatoria(){
        float g = productofacade.suma(producto.getPrecio());
        System.out.println(g);
        return g;
    }
    
    //metodo para filtrar por precio
    public List<Productos> filtrarPrecio() {
        Query consulta = productofacade.getEm().createQuery("SELECT  p FROM Productos p WHERE p.precio = :precio");
        consulta.setParameter("precio", item);
        return consulta.getResultList();
    }

     
   public Compraventa idCompraventa(){
       Query consulta = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idCompraventa = :idCompraventa");
       consulta.setParameter("idCompraventa", getUsuarioSesion());
       List<Compraventa> idusuario = consulta.getResultList();
        Compraventa id = idusuario.get(0) ;
       return id;   
   }
    
     //metodo para filtrar las solicitudes por compraventa
     public List<SolicitudCompraventa> listarSolicitudes() {
//        return solicitudfacade.findAll();
        Query consulta = solicitudfacade.getEm().createQuery("SELECT s FROM SolicitudCompraventa s WHERE s.idUsuarioServicios = :idUsuariosServicios AND s.idCompraventa = :idCompraventa");
        consulta.setParameter("idUsuariosServicios", new Usuarios(0).getIdUsuario());

        Query consultaCompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultaCompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> compraventas = consultaCompraventa.getResultList();

        consulta.setParameter("idCompraventa", compraventas.get(0));
        return consulta.getResultList();
    }
     
     public List<SolicitudCompraventa> conteoSolicitudes(){
         Query consulta = solicitudfacade.getEm().createQuery("SELECT (s) FROM SolicitudCompraventa s WHERE s.idUsuarioServicios = :idUsuariosServicios AND s.idCompraventa = :idCompraventa");
        consulta.setParameter("idUsuariosServicios", new Usuarios(0).getIdUsuario());
         
        Query consultaCompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
         consultaCompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> compraventas = consultaCompraventa.getResultList();
       

        consulta.setParameter("idCompraventa", compraventas.get(0));
        return consulta.getResultList();

     }
    
    //metodos para filtrar por categorias y enviar datos a los charts.
//     public List<Productos> conteo(){
//         Query consulta = productofacade.getEm().createQuery("SELECT count(p) FROM Productos p WHERE p.categoria = :categoria");
//         consulta.setParameter("categoria", "Antiques");
//         return consulta.getResultList();         
//     }
     
     public List<Productos> listaProductos(){//este metodo lista los productos por casa comercial.
        Query consulta = productofacade.getEm().createQuery("SELECT p FROM Productos p WHERE p.idCompraventa = :idCompraventa");
              
        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas=consultacompraventa.getResultList();
       
        consulta.setParameter("idCompraventa", listacompraventas.get(0) );
        return consulta.getResultList();
    }
    
    public List<Productos> conteoAntiguedades() {
        Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Antiguedades");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();
    }
    
     public List<Productos> conteoComputacion(){
         Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
         consulta.setParameter("categoria", "Computacion");
         
         Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c Where c.idUsuario = :idUsuario");
         consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
         List<Compraventa> listacompraventas = consultacompraventa.getResultList();
         
         consulta.setParameter("idCompraventa", listacompraventas.get(0));
         return consulta.getResultList();   
     }
     
     public List<Productos> conteoDeportes(){
        Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Deportes");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();
    }
     
     public List<Productos> conteoElectrodomesticos(){
         Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Electrodomesticos");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();         
     }
     
     public List<Productos> conteoIndustria(){
          Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Industria");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();  
     }
     
     public List<Productos> conteoInmuebles(){
          Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Inmuebles");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();         
     }
     
     public List<Productos> conteoJoyeria(){
          Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Joyeria");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();       
     }
     
     public List<Productos> conteoJugueteria(){
          Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Jugueteria");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();         
     }
     
     public List<Productos> conteoMuebles(){
          Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Muebles");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();         
     }
     public List<Productos> conteoRelojeria(){
         Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Relogeria");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();        
     }
     
     public List<Productos> conteoTecnologia(){
          Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Tecnologia");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();       
     }
     
     public List<Productos> conteoVehiculos(){
          Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Vehiculos");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList();       
     }
     
     public List<Productos> conteoArte(){
          Query consulta = productofacade.getEm().createQuery("SELECT (p) FROM Productos p WHERE p.categoria = :categoria AND p.idCompraventa = :idCompraventa");
        consulta.setParameter("categoria", "Arte");

        Query consultacompraventa = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa c WHERE c.idUsuario = :idUsuario");
        consultacompraventa.setParameter("idUsuario", getUsuarioSesion());
        List<Compraventa> listacompraventas = consultacompraventa.getResultList();

        consulta.setParameter("idCompraventa", listacompraventas.get(0));
        return consulta.getResultList(); 
     }
     
     private ProductoEmpeno productoem = new ProductoEmpeno();
     
    public ProductoEmpeno getProductoem() {
        return productoem;
    }

    public void setProductoem(ProductoEmpeno productoem) {
        this.productoem = productoem; 
    }
    
    float dias;

    public float getDias() {
        return dias;
    }

    public void setDias(float dias) {
        this.dias = dias;
    }
    
    
     public Float calcularEmpeño(){
         
        
        productoem.setInteres((compraventa.getInteresAnual()/360)*productoem.getDias());//agregar los dias de empeño
        productoem.setPrecioapagar(solicitud.getPrecio() * productoem.getInteres()); 
        
       
         
         return null;
         
     }
     public void empeñarProducto(){
         
         productoemfacade.create(productoem);         
     }

  
   
 
        
    }


 