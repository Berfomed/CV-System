package edu.cvsystem.beans;

import edu.cvsystem.clases.LoaderFiles;
import edu.cvsystem.clases.Mailler;
import javax.inject.Named;
import edu.cvsystem.entidades.Usuarios;
import edu.cvsystem.facades.UsuariosFacade;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.http.Part;

@Named(value = "usuariosBean")
@RequestScoped
public class UsuariosBean {

    @EJB
    private UsuariosFacade usuariosFacade;

    public UsuariosBean() {
    }

    @PostConstruct
    public void init() {
        usuario = new Usuarios();
        pathFoto = "usuarios/perfil.jpg";
    }

    public void registrarUsuario() {
        try {
            Query consulta = usuariosFacade.getEm().createNamedQuery("Usuarios.findByCorreoElectronico");
            consulta.setParameter("correoElectronico", usuario.getCorreoElectronico());
            List<Usuarios> lista = consulta.getResultList();
            if (!lista.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage("formulario_registro:inCorreoRegistro",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Oh oh!", "Correo ya registrado"));
            } else {
                usuario.setEstado("activo");
                usuario.setRol("cliente");
                usuariosFacade.create(usuario);
                usuario = new Usuarios();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Listo!", "Registro completado exitosamente"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Ups!", "Se ha presentado un error"));
        }
    }

    public void registrarUsuarioS() {
        usuario.setEstado("activo");
        usuario.setRol("servicios");
        usuariosFacade.create(usuario);
        Mailler.enviarCorreo("Usuario Servicios","<html lang='en' dir='ltr'><body><div style:' font-family: 'Pacifico', cursive;font-size: 6em;'><h2>CVSYSTEM</h2></div><br><div >Correo: "+usuario.getCorreoElectronico() +"<br> Contraseña: "+ usuario.getContrasena()+"</div><br><br><div>Cvsystem te la la vienvenida señor(a) "+usuario.getNombre()+"</div></body></html>", usuario.getCorreoElectronico());
        usuario = new Usuarios();
    }

    public String iniciarSesion() {
        try {
            Query consulta = usuariosFacade.getEm().createQuery("SELECT u FROM Usuarios u WHERE u.correoElectronico = :correoElectronico AND u.contrasena = :contrasena");
            consulta.setParameter("correoElectronico", usuario.getCorreoElectronico());
            consulta.setParameter("contrasena", usuario.getContrasena());
            List<Usuarios> lista = consulta.getResultList();
            if (lista.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Oh oh!", "Usuario y/o contraseña incorrecto(s)"));
            } else {
                if (!lista.get(0).getEstado().equals("bloqueado")) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLog", lista.get(0));
                    return "/" + lista.get(0).getRol() + "/perfil?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Jumm...", "El usuario se encuetra actualmente bloqueado. "
                            + "Para más información puedes contactarte con la línea (+57) 3196419019"));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Ups!", "Se ha presentado un error"));
        }
        return null;
    }
    public void cambiarEstado() {
        usuario.setEstado("bloqueado");
        usuariosFacade.edit(usuario);
    }
    public void actualizarPerfilAd() {
        usuariosFacade.edit(getUsuarioSesion());
    }

    public Usuarios getUsuarioSesion() {
        return (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLog");
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
 

    public void actualizarPerfil(String form, String component) throws IOException {
        usuariosFacade.edit(getUsuarioSesion());
        FacesContext.getCurrentInstance().addMessage(form + ":" + component,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Listo!", "¡Actualizado con exito!"));
    }

    public void actualizarFoto() {
        if (PathFotoNueva != null) {
            try {
                String nombre = PathFotoNueva.getSubmittedFileName();
                nombre = nombre.substring(nombre.indexOf("."), nombre.length());
                nombre = "usuario(" + getUsuarioSesion().getIdUsuario() + ")" + nombre;
                String path = FacesContext.getCurrentInstance().getExternalContext()
                        .getRealPath("resources").concat("\\img\\usuarios\\" + nombre);
                LoaderFiles.copiarArchivo(PathFotoNueva.getInputStream(), path);
                LoaderFiles.copiarArchivo(PathFotoNueva.getInputStream(), path.replace("\\build", ""));
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Imagen actualizada"));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public String getContrasenaOculta() {
        return getUsuarioSesion().getContrasena().replaceAll(".", "*");
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    public void enviarCorreos() {
      
        Query query = usuariosFacade.getEm().createQuery("SELECT u FROM Usuarios u WHERE u.estado = :estado");
        query.setParameter("estado", "activo");
        List<Usuarios> usuarios = query.getResultList();
      String[] correos = new String[usuarios.size()];
      for (int i = 0; i < correos.length; i++) {
        correos[i] = usuarios.get(i).getCorreoElectronico();
      }
      Mailler.enviarCorreo("Alerta","<html lang='en' dir='ltr'><body><div style:' font-family: 'Pacifico', cursive;font-size: 6em;'><h2>CVSYSTEM</h2></div><br><div >El servidor se encuentra en mantenimiento</div></body></html>", correos);
    }

    public String getPathFoto() {
        String path = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("resources\\img\\usuarios").replace("\\build", "");
        String[] fotos = LoaderFiles.listarArchivos(path);
        for (String foto : fotos) {
            if (foto.contains("(" + getUsuarioSesion().getIdUsuario().toString() + ")")) {
                pathFoto = "usuarios/" + foto;
                break;
            }
        }
        return pathFoto;
    }

    public List<Usuarios> listarUsuarios() {
        return usuariosFacade.findAll();
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public Part getPathFotoNueva() {
        return PathFotoNueva;
    }

    public void setPathFotoNueva(Part PathFotoNueva) {
        this.PathFotoNueva = PathFotoNueva;
    }

    private Usuarios usuario;
    private String pathFoto;
    private Part PathFotoNueva;
}
