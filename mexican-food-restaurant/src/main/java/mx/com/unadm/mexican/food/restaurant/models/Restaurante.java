/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author macos
 */
@Entity
@Table(name = "RESTAURANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurante.findAll", query = "SELECT r FROM Restaurante r"),
    @NamedQuery(name = "Restaurante.findByCodigoRestaurante", query = "SELECT r FROM Restaurante r WHERE r.codigoRestaurante = :codigoRestaurante"),
    @NamedQuery(name = "Restaurante.findByNombre", query = "SELECT r FROM Restaurante r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Restaurante.findByCiudad", query = "SELECT r FROM Restaurante r WHERE r.ciudad = :ciudad"),
    @NamedQuery(name = "Restaurante.findByTelefono", query = "SELECT r FROM Restaurante r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "Restaurante.findByDireccion", query = "SELECT r FROM Restaurante r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "Restaurante.findByPais", query = "SELECT r FROM Restaurante r WHERE r.pais = :pais"),
    @NamedQuery(name = "Restaurante.findByCodigoPostal", query = "SELECT r FROM Restaurante r WHERE r.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Restaurante.findByColonia", query = "SELECT r FROM Restaurante r WHERE r.colonia = :colonia")})
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_restaurante")
    private String codigoRestaurante;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @Basic(optional = false)
    @Column(name = "colonia")
    private String colonia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRestaurante")
    private Collection<Producto> productoCollection;
    @OneToMany(mappedBy = "codigoRestaurante")
    private Collection<Empleado> empleadoCollection;

    public Restaurante() {
    }

    public Restaurante(String codigoRestaurante) {
        this.codigoRestaurante = codigoRestaurante;
    }

    public Restaurante(String codigoRestaurante, String nombre, String ciudad, String telefono, String direccion, String pais, String codigoPostal, String colonia) {
        this.codigoRestaurante = codigoRestaurante;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.direccion = direccion;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
    }

    public String getCodigoRestaurante() {
        return codigoRestaurante;
    }

    public void setCodigoRestaurante(String codigoRestaurante) {
        this.codigoRestaurante = codigoRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing 
        return Objects.hashCode(this.codigoRestaurante);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Restaurante) {
            Restaurante other = (Restaurante) object;
            // Google Guava provides great utilities for equals too! 
            return Objects.equals(this.codigoRestaurante, other.codigoRestaurante);

        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Restaurante{" + "codigoRestaurante=" + codigoRestaurante + ", nombre=" + nombre + ", ciudad=" + ciudad + ", telefono=" + telefono + ", direccion=" + direccion + ", pais=" + pais + ", codigoPostal=" + codigoPostal + ", colonia=" + colonia + ", productoCollection=" + productoCollection + ", empleadoCollection=" + empleadoCollection + '}';
    }   

}
