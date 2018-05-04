package edu.cvsystem.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "solicitud_compraventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudCompraventa.findAll", query = "SELECT s FROM SolicitudCompraventa s")
    , @NamedQuery(name = "SolicitudCompraventa.findByIdSolicitudCompraventa", query = "SELECT s FROM SolicitudCompraventa s WHERE s.idSolicitudCompraventa = :idSolicitudCompraventa")
    , @NamedQuery(name = "SolicitudCompraventa.findByTitulo", query = "SELECT s FROM SolicitudCompraventa s WHERE s.titulo = :titulo")
    , @NamedQuery(name = "SolicitudCompraventa.findByCategoria", query = "SELECT s FROM SolicitudCompraventa s WHERE s.categoria = :categoria")
    , @NamedQuery(name = "SolicitudCompraventa.findByDescripcion", query = "SELECT s FROM SolicitudCompraventa s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "SolicitudCompraventa.findByTipo", query = "SELECT s FROM SolicitudCompraventa s WHERE s.tipo = :tipo")
    , @NamedQuery(name = "SolicitudCompraventa.findByPrecio", query = "SELECT s FROM SolicitudCompraventa s WHERE s.precio = :precio")
    , @NamedQuery(name = "SolicitudCompraventa.findByFotos", query = "SELECT s FROM SolicitudCompraventa s WHERE s.fotos = :fotos")
    , @NamedQuery(name = "SolicitudCompraventa.findByFechaEnvio", query = "SELECT s FROM SolicitudCompraventa s WHERE s.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "SolicitudCompraventa.findByRespuestaMsg", query = "SELECT s FROM SolicitudCompraventa s WHERE s.respuestaMsg = :respuestaMsg")
    , @NamedQuery(name = "SolicitudCompraventa.findByRespuesta", query = "SELECT s FROM SolicitudCompraventa s WHERE s.respuesta = :respuesta")
    , @NamedQuery(name = "SolicitudCompraventa.findByIdUsuarioServicios", query = "SELECT s FROM SolicitudCompraventa s WHERE s.idUsuarioServicios = :idUsuarioServicios")})
public class SolicitudCompraventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud_compraventa")
    private Integer idSolicitudCompraventa;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "categoria")
    private String categoria;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "fotos")
    private String fotos;
    @Basic(optional = false)
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Column(name = "respuesta_msg")
    private String respuestaMsg;
    @Column(name = "respuesta")
    private String respuesta;
    @Column(name = "id_usuario_servicios")
    private Integer idUsuarioServicios;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_compraventa", referencedColumnName = "id_compraventa")
    @ManyToOne(optional = false)
    private Compraventa idCompraventa;

    public SolicitudCompraventa() {
    }

    public SolicitudCompraventa(Integer idSolicitudCompraventa) {
        this.idSolicitudCompraventa = idSolicitudCompraventa;
    }

    public SolicitudCompraventa(Integer idSolicitudCompraventa, String titulo, String categoria, String descripcion, String tipo, float precio, String fotos, Date fechaEnvio) {
        this.idSolicitudCompraventa = idSolicitudCompraventa;
        this.titulo = titulo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio;
        this.fotos = fotos;
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getIdSolicitudCompraventa() {
        return idSolicitudCompraventa;
    }

    public void setIdSolicitudCompraventa(Integer idSolicitudCompraventa) {
        this.idSolicitudCompraventa = idSolicitudCompraventa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getRespuestaMsg() {
        return respuestaMsg;
    }

    public void setRespuestaMsg(String respuestaMsg) {
        this.respuestaMsg = respuestaMsg;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Integer getIdUsuarioServicios() {
        return idUsuarioServicios;
    }

    public void setIdUsuarioServicios(Integer idUsuarioServicios) {
        this.idUsuarioServicios = idUsuarioServicios;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Compraventa getIdCompraventa() {
        return idCompraventa;
    }

    public void setIdCompraventa(Compraventa idCompraventa) {
        this.idCompraventa = idCompraventa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitudCompraventa != null ? idSolicitudCompraventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SolicitudCompraventa)) {
            return false;
        }
        SolicitudCompraventa other = (SolicitudCompraventa) object;
        return !((this.idSolicitudCompraventa == null && other.idSolicitudCompraventa != null) || (this.idSolicitudCompraventa != null && !this.idSolicitudCompraventa.equals(other.idSolicitudCompraventa)));
    }

    @Override
    public String toString() {
        return "edu.cvsystem.entidades.SolicitudCompraventa[ idSolicitudCompraventa=" + idSolicitudCompraventa + " ]";
    }
}
