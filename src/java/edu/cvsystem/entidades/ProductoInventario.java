package edu.cvsystem.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "producto_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoInventario.findAll", query = "SELECT p FROM ProductoInventario p")
    , @NamedQuery(name = "ProductoInventario.findByIdProductoInventario", query = "SELECT p FROM ProductoInventario p WHERE p.idProductoInventario = :idProductoInventario")
    , @NamedQuery(name = "ProductoInventario.findByFechaIngreso", query = "SELECT p FROM ProductoInventario p WHERE p.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "ProductoInventario.findByEstado", query = "SELECT p FROM ProductoInventario p WHERE p.estado = :estado")})
public class ProductoInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto_inventario")
    private Integer idProductoInventario;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @JoinColumn(name = "id_compraventa", referencedColumnName = "id_compraventa")
    @ManyToOne(optional = false)
    private Compraventa idCompraventa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProductoInventario")
    private Collection<CompraProducto> compraProductoCollection;

    public ProductoInventario() {
    }

    public ProductoInventario(Integer idProductoInventario) {
        this.idProductoInventario = idProductoInventario;
    }

    public ProductoInventario(Integer idProductoInventario, Date fechaIngreso) {
        this.idProductoInventario = idProductoInventario;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdProductoInventario() {
        return idProductoInventario;
    }

    public void setIdProductoInventario(Integer idProductoInventario) {
        this.idProductoInventario = idProductoInventario;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Compraventa getIdCompraventa() {
        return idCompraventa;
    }

    public void setIdCompraventa(Compraventa idCompraventa) {
        this.idCompraventa = idCompraventa;
    }

    @XmlTransient
    public Collection<CompraProducto> getCompraProductoCollection() {
        return compraProductoCollection;
    }

    public void setCompraProductoCollection(Collection<CompraProducto> compraProductoCollection) {
        this.compraProductoCollection = compraProductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductoInventario != null ? idProductoInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProductoInventario)) {
            return false;
        }
        ProductoInventario other = (ProductoInventario) object;
        return !((this.idProductoInventario == null && other.idProductoInventario != null) || (this.idProductoInventario != null && !this.idProductoInventario.equals(other.idProductoInventario)));
    }

    @Override
    public String toString() {
        return "edu.cvsystem.entidades.ProductoInventario[ idProductoInventario=" + idProductoInventario + " ]";
    }
}
