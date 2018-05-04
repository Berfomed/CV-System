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
@Table(name = "compra_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompraProducto.findAll", query = "SELECT c FROM CompraProducto c")
    , @NamedQuery(name = "CompraProducto.findByIdCompraProducto", query = "SELECT c FROM CompraProducto c WHERE c.idCompraProducto = :idCompraProducto")
    , @NamedQuery(name = "CompraProducto.findByFechaCompra", query = "SELECT c FROM CompraProducto c WHERE c.fechaCompra = :fechaCompra")})
public class CompraProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_compra_producto")
    private Integer idCompraProducto;
    @Basic(optional = false)
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_producto_inventario", referencedColumnName = "id_producto_inventario")
    @ManyToOne(optional = false)
    private ProductoInventario idProductoInventario;

    public CompraProducto() {
    }

    public CompraProducto(Integer idCompraProducto) {
        this.idCompraProducto = idCompraProducto;
    }

    public CompraProducto(Integer idCompraProducto, Date fechaCompra) {
        this.idCompraProducto = idCompraProducto;
        this.fechaCompra = fechaCompra;
    }

    public Integer getIdCompraProducto() {
        return idCompraProducto;
    }

    public void setIdCompraProducto(Integer idCompraProducto) {
        this.idCompraProducto = idCompraProducto;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ProductoInventario getIdProductoInventario() {
        return idProductoInventario;
    }

    public void setIdProductoInventario(ProductoInventario idProductoInventario) {
        this.idProductoInventario = idProductoInventario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompraProducto != null ? idCompraProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CompraProducto)) {
            return false;
        }
        CompraProducto other = (CompraProducto) object;
        return !((this.idCompraProducto == null && other.idCompraProducto != null) || (this.idCompraProducto != null && !this.idCompraProducto.equals(other.idCompraProducto)));
    }

    @Override
    public String toString() {
        return "edu.cvsystem.entidades.CompraProducto[ idCompraProducto=" + idCompraProducto + " ]";
    }
}
