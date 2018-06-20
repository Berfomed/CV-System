package edu.cvsystem.clases;

import edu.cvsystem.beans.GerenteBeans;
import edu.cvsystem.entidades.Compraventa;

import edu.cvsystem.entidades.Productos;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.CompraventaFacade;
import edu.cvsystem.facades.ProductosFacade;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;

@ManagedBean(name = "cargaarchivos")
public class CargaArchivos {

  public CargaArchivos() {
    producto = new Productos();
    compraventa = new Compraventa();
    gerente = new GerenteBeans();

  }
  @EJB
  private ProductosFacade productofacade;

  @EJB
  private CompraventaFacade compraventaFacade;

  Compraventa compraventa = new Compraventa();
  private GerenteBeans gerente = new GerenteBeans();

  private Part file1;
  private Part file2;
  private Part file3;
  private Part file4;
  private Part file5;
  private Part file6;
  private String nombre;
  private String nombre2;
  private String nombre3;
  private String nombre4;
  private String pathReal;
  private String pathReal2;
  private String pathReal3;
  private String pathReal4;
  private Productos producto;

  public Compraventa getCompraventa() {
    return compraventa;
  }

  public void setCompraventa(Compraventa compraventa) {
    this.compraventa = compraventa;
  }

  public Productos getFuncionario() {
    return producto;
  }

  public void setFuncionario(Productos funcionario) {
    this.producto = funcionario;
  }

  public String getPath() {
    return pathReal;
  }

  public void setPath(String path) {
    this.pathReal = path;
  }

  public Part getFile1() {
    return file1;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setFile1(Part file1) {
    this.file1 = file1;
  }

  public Part getFile2() {
    return file2;
  }

  public void setFile2(Part file2) {
    this.file2 = file2;
  }

  public String getNombre2() {
    return nombre2;
  }

  public void setNombre2(String nombre2) {
    this.nombre2 = nombre2;
  }

  public String getPathReal2() {
    return pathReal2;
  }

  public void setPathReal2(String pathReal2) {
    this.pathReal2 = pathReal2;
  }

  public Part getFile3() {
    return file3;
  }

  public void setFile3(Part file3) {
    this.file3 = file3;
  }

  public Part getFile4() {
    return file4;
  }

  public void setFile4(Part file4) {
    this.file4 = file4;
  }

  public Part getFile5() {
    return file5;
  }

  public void setFile5(Part file5) {
    this.file5 = file5;
  }

  public Part getFile6() {
    return file6;
  }

  public void setFile6(Part file6) {
    this.file6 = file6;
  }

  public String getNombre3() {
    return nombre3;
  }

  public void setNombre3(String nombre3) {
    this.nombre3 = nombre3;
  }

  public String getNombre4() {
    return nombre4;
  }

  public void setNombre4(String nombre4) {
    this.nombre4 = nombre4;
  }

  public String getPathReal3() {
    return pathReal3;
  }

  public void setPathReal3(String pathReal3) {
    this.pathReal3 = pathReal3;
  }

  public String getPathReal4() {
    return pathReal4;
  }

  public void setPathReal4(String pathReal4) {
    this.pathReal4 = pathReal4;
  }

  public String getPathReal() {
    return pathReal;
  }

  public void setPathReal(String pathReal) {
    this.pathReal = pathReal;
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

  public Usuarios getUsuarioSesion() {
    return (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLog");
  }

  public void crearProducto() {
    producto.setEstatus("Disponible");

    TypedQuery<Compraventa> query = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa "
            + "c WHERE c.idUsuario = :usuario", Compraventa.class);
    query.setParameter("usuario", getUsuarioSesion());
    producto.setIdCompraventa(query.getResultList().get(0));

    productofacade.create(producto);
  }

  private void copiarFoto(Part file, String pathBuild, String path) {
    try {
      if (file != null) {
        LoaderFiles.copiarArchivo(file.getInputStream(), pathBuild
                .concat(file.getSubmittedFileName()));
        LoaderFiles.copiarArchivo(file.getInputStream(), path
                .concat(file.getSubmittedFileName()));
      }
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public String upload() {
    crearProducto();

    int idProducto = producto.getIdProducto();
    String pathBuild = FacesContext.getCurrentInstance().getExternalContext()
            .getRealPath("resources\\img\\productos");
    String path = pathBuild.replace("\\build", "");

    LoaderFiles.crearCarpeta(pathBuild, "Producto(" + idProducto + ")");
    LoaderFiles.crearCarpeta(path, "Producto(" + idProducto + ")");

    pathBuild += "\\Producto(" + idProducto + ")\\";
    path += "\\Producto(" + idProducto + ")\\";

    copiarFoto(file1, pathBuild, path);
    copiarFoto(file2, pathBuild, path);
    copiarFoto(file3, pathBuild, path);
    copiarFoto(file4, pathBuild, path);
    copiarFoto(file5, pathBuild, path);
    copiarFoto(file6, pathBuild, path);

    return "catalogoG";
  }

  /*metodo para cargar imagenes*/
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

  public GerenteBeans getGerente() {
    return gerente;
  }

  public void setGerente(GerenteBeans gerente) {
    this.gerente = gerente;
  }

  //metodo para mostrar las fotos
  public String[] getFotos(int idProducto) {
    String context = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/img/productos/Producto(" + idProducto + ")");
    File file = new File(context);
    for (String foto : file.list()) {
      System.out.println(foto);
    }
    return file.list();
  }
  //metodo para cargar las fotos de michael

  public String cargarImagen1(Part p, Integer id) {
    try {
//            String pathFolder = FacesContext.getCurrentInstance().getExternalContext()
//                    .getRealPath("resources").concat("\\img\\solicitudes\\Solicitud(" + id + ")").replace("\\build", "");
      String pathFolderBuild = FacesContext.getCurrentInstance().getExternalContext()
              .getRealPath("resources").concat("\\img\\productos\\Producto()(" + id + ")");

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

  //metodo para cargar las fotos de los productos
}
