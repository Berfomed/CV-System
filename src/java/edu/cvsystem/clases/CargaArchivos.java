package edu.cvsystem.clases;


import edu.cvsystem.beans.GerenteBeans;
import edu.cvsystem.entidades.Compraventa;
import edu.cvsystem.entidades.Productos;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.CompraventaFacade;
import edu.cvsystem.facades.ProductosFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private ProductosFacade productofacade = new ProductosFacade();
    
    @EJB
    private CompraventaFacade compraventaFacade = new CompraventaFacade();
    
    Compraventa compraventa = new Compraventa();
    private GerenteBeans gerente = new GerenteBeans();
   
    private Part file;
    private Part file2;
    private Part file3;
    private Part file4;
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

    public Part getFile() {
        return file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFile(Part file) {
        this.file = file;
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
        producto.setFotos(pathReal);
        producto.setEstatus("Disponible");
        
        TypedQuery<Compraventa> query = compraventaFacade.getEm().createQuery("SELECT c FROM Compraventa "
                + "c WHERE c.idUsuario = :usuario", Compraventa.class);
        query.setParameter("usuario", getUsuarioSesion());
        producto.setIdCompraventa(query.getResultList().get(0));        
    
        productofacade.create(producto);
        producto = new Productos();
    }
    
    

    public String upload() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("imagenes");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\imagenes\\";
        try {
            this.nombre = file.getSubmittedFileName(); /*para guardar el nombre*/
            pathReal = "/CV-System/imagenes/" + nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();
            
            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String path2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
//        path2 = path2.substring(0, path2.indexOf("\\build"));
//        path2 = path2 + "\\web\\Archivos\\";
//        try {
//            this.nombre2 = file2.getSubmittedFileName();
//            pathReal2 = "/CVSystem/Archivos/" + nombre2;
//            path2 = path2 + this.nombre2;
//            FileOutputStream out2;
//            try (InputStream in2 = file2.getInputStream()) {
//                byte[] data2 = new byte[in2.available()];
//                in2.read(data2);
//                out2 = new FileOutputStream(new File(path2));
//                out2.write(data2);
//            }
//            out2.close();
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//        String path3 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
//        path3 = path3.substring(0, path3.indexOf("\\build"));
//        path3 = path3 + "\\web\\Archivos\\";
//        try {
//            this.nombre3 = file3.getSubmittedFileName();
//            pathReal3 = "/CVSystem/Archivos/" + nombre3;
//            path3 = path3 + this.nombre3;
//            FileOutputStream out3;
//            try (InputStream in3 = file3.getInputStream()) {
//                byte[] data3 = new byte[in3.available()];
//                in3.read(data3);
//                out3 = new FileOutputStream(new File(path3));
//                out3.write(data3);
//            }
//            out3.close();
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//        String path4 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
//        path4 = path4.substring(0, path4.indexOf("\\build"));
//        path4 = path4 + "\\web\\Archivos\\";
//        try {
//            this.nombre4 = file4.getSubmittedFileName();
//            pathReal4 = "/CVSystem/Archivos/" + nombre4;
//            path4 = path4 + this.nombre4;
//            FileOutputStream out4;
//            try (InputStream in4 = file4.getInputStream()) {
//                byte[] data4 = new byte[in4.available()];
//                in4.read(data4);
//                out4 = new FileOutputStream(new File(path4));
//                out4.write(data4);
//            }
//            out4.close();
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
        crearProducto();
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
    
    
    

}
