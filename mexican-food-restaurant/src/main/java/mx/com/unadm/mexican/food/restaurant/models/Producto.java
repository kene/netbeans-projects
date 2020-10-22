/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author macos
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByCodigoProducto", query = "SELECT p FROM Producto p WHERE p.codigoProducto = :codigoProducto"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByCantidad", query = "SELECT p FROM Producto p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_producto")
    private String codigoProducto;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private String cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio")
    private BigDecimal precio;
    @JoinColumn(name = "codigo_restaurante", referencedColumnName = "codigo_restaurante")
    @ManyToOne(optional = false)
    private Restaurante codigoRestaurante;

    public Producto() {
    }

    public Producto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Producto(String codigoProducto, String nombre, String descripcion, String cantidad, BigDecimal precio) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Restaurante getCodigoRestaurante() {
        return codigoRestaurante;
    }

    public void setCodigoRestaurante(Restaurante codigoRestaurante) {
        this.codigoRestaurante = codigoRestaurante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoProducto != null ? codigoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.codigoProducto == null && other.codigoProducto != null) || (this.codigoProducto != null && !this.codigoProducto.equals(other.codigoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.unadm.mexican.food.restaurant.models.Producto[ codigoProducto=" + codigoProducto + " ]";
    }
    
}
