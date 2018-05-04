/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Jorge Amado Perdomo
 */
@Entity
@Table(name = "producto_empeno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoEmpeno.findAll", query = "SELECT p FROM ProductoEmpeno p")
    , @NamedQuery(name = "ProductoEmpeno.findByIdProductoEmpeno", query = "SELECT p FROM ProductoEmpeno p WHERE p.idProductoEmpeno = :idProductoEmpeno")
    , @NamedQuery(name = "ProductoEmpeno.findByFechaInicio", query = "SELECT p FROM ProductoEmpeno p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "ProductoEmpeno.findByFechaFinal", query = "SELECT p FROM ProductoEmpeno p WHERE p.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "ProductoEmpeno.findByEstado", query = "SELECT p FROM ProductoEmpeno p WHERE p.estado = :estado")
    , @NamedQuery(name = "ProductoEmpeno.findByPrecioapagar", query = "SELECT p FROM ProductoEmpeno p WHERE p.precioapagar = :precioapagar")
    , @NamedQuery(name = "ProductoEmpeno.findByInteres", query = "SELECT p FROM ProductoEmpeno p WHERE p.interes = :interes")
    , @NamedQuery(name = "ProductoEmpeno.findByDias", query = "SELECT p FROM ProductoEmpeno p WHERE p.dias = :dias")})
public class ProductoEmpeno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto_empeno")
    private Integer idProductoEmpeno;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioapagar")
    private Float precioapagar;
    @Column(name = "interes")
    private Float interes;
    @Column(name = "dias")
    private Integer dias;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_compraventa", referencedColumnName = "id_compraventa")
    @ManyToOne(optional = false)
    private Compraventa idCompraventa;

    public ProductoEmpeno() {
    }

    public ProductoEmpeno(Integer idProductoEmpeno) {
        this.idProductoEmpeno = idProductoEmpeno;
    }

    public ProductoEmpeno(Integer idProductoEmpeno, Date fechaInicio, Date fechaFinal, String estado) {
        this.idProductoEmpeno = idProductoEmpeno;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }

    public Integer getIdProductoEmpeno() {
        return idProductoEmpeno;
    }

    public void setIdProductoEmpeno(Integer idProductoEmpeno) {
        this.idProductoEmpeno = idProductoEmpeno;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Float getPrecioapagar() {
        return precioapagar;
    }

    public void setPrecioapagar(Float precioapagar) {
        this.precioapagar = precioapagar;
    }

    public Float getInteres() {
        return interes;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
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
        hash += (idProductoEmpeno != null ? idProductoEmpeno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoEmpeno)) {
            return false;
        }
        ProductoEmpeno other = (ProductoEmpeno) object;
        if ((this.idProductoEmpeno == null && other.idProductoEmpeno != null) || (this.idProductoEmpeno != null && !this.idProductoEmpeno.equals(other.idProductoEmpeno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cvsystem.entidades.ProductoEmpeno[ idProductoEmpeno=" + idProductoEmpeno + " ]";
    }
    
}
