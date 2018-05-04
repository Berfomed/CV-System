package edu.cvsystem.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "solicitud_domicilio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudDomicilio.findAll", query = "SELECT s FROM SolicitudDomicilio s")
    , @NamedQuery(name = "SolicitudDomicilio.findByIdSolicitudDomicilio", query = "SELECT s FROM SolicitudDomicilio s WHERE s.idSolicitudDomicilio = :idSolicitudDomicilio")
    , @NamedQuery(name = "SolicitudDomicilio.findByDireccion", query = "SELECT s FROM SolicitudDomicilio s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "SolicitudDomicilio.findByCelular", query = "SELECT s FROM SolicitudDomicilio s WHERE s.celular = :celular")
    , @NamedQuery(name = "SolicitudDomicilio.findByEstado", query = "SELECT s FROM SolicitudDomicilio s WHERE s.estado = :estado")})
public class SolicitudDomicilio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud_domicilio")
    private Integer idSolicitudDomicilio;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public SolicitudDomicilio() {
    }

    public SolicitudDomicilio(Integer idSolicitudDomicilio) {
        this.idSolicitudDomicilio = idSolicitudDomicilio;
    }

    public SolicitudDomicilio(Integer idSolicitudDomicilio, String direccion, String celular, String estado) {
        this.idSolicitudDomicilio = idSolicitudDomicilio;
        this.direccion = direccion;
        this.celular = celular;
        this.estado = estado;
    }

    public Integer getIdSolicitudDomicilio() {
        return idSolicitudDomicilio;
    }

    public void setIdSolicitudDomicilio(Integer idSolicitudDomicilio) {
        this.idSolicitudDomicilio = idSolicitudDomicilio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitudDomicilio != null ? idSolicitudDomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SolicitudDomicilio)) {
            return false;
        }
        SolicitudDomicilio other = (SolicitudDomicilio) object;
        return !((this.idSolicitudDomicilio == null && other.idSolicitudDomicilio != null) || (this.idSolicitudDomicilio != null && !this.idSolicitudDomicilio.equals(other.idSolicitudDomicilio)));
    }

    @Override
    public String toString() {
        return "edu.cvsystem.entidades.SolicitudDomicilio[ idSolicitudDomicilio=" + idSolicitudDomicilio + " ]";
    }
}
