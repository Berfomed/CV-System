/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author jafp9
 */
@Entity
@Table(name = "compraventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compraventa.findAll", query = "SELECT c FROM Compraventa c")
    , @NamedQuery(name = "Compraventa.findByIdCompraventa", query = "SELECT c FROM Compraventa c WHERE c.idCompraventa = :idCompraventa")
    , @NamedQuery(name = "Compraventa.findByNombre", query = "SELECT c FROM Compraventa c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Compraventa.findByDescripcion", query = "SELECT c FROM Compraventa c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Compraventa.findByDireccion", query = "SELECT c FROM Compraventa c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Compraventa.findByTelefono", query = "SELECT c FROM Compraventa c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Compraventa.findByInteresCompraventa", query = "SELECT c FROM Compraventa c WHERE c.interesCompraventa = :interesCompraventa")})
public class Compraventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_compraventa")
    private Integer idCompraventa;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "interesAnual")
    private Float interesCompraventa;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public Compraventa() {
    }

    public Compraventa(Integer idCompraventa) {
        this.idCompraventa = idCompraventa;
    }

    public Compraventa(Integer idCompraventa, String nombre, String descripcion, String direccion, String telefono) {
        this.idCompraventa = idCompraventa;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Integer getIdCompraventa() {
        return idCompraventa;
    }

    public void setIdCompraventa(Integer idCompraventa) {
        this.idCompraventa = idCompraventa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Float getInteresCompraventa() {
        return interesCompraventa;
    }

    public void setInteresCompraventa(Float interesCompraventa) {
        this.interesCompraventa = interesCompraventa;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompraventa != null ? idCompraventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compraventa)) {
            return false;
        }
        Compraventa other = (Compraventa) object;
        if ((this.idCompraventa == null && other.idCompraventa != null) || (this.idCompraventa != null && !this.idCompraventa.equals(other.idCompraventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cvsystem.entidades.Compraventa[ idCompraventa=" + idCompraventa + " ]";
    }
    
}
