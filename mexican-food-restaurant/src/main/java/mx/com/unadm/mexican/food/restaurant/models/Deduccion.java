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
 * @author macos
 */
@Entity
@Table(name = "DEDUCCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deduccion.findAll", query = "SELECT d FROM Deduccion d"),
    @NamedQuery(name = "Deduccion.findByIdDeduccion", query = "SELECT d FROM Deduccion d WHERE d.idDeduccion = :idDeduccion"),
    @NamedQuery(name = "Deduccion.findByDeduccion", query = "SELECT d FROM Deduccion d WHERE d.deduccion = :deduccion"),
    @NamedQuery(name = "Deduccion.findByCantidad", query = "SELECT d FROM Deduccion d WHERE d.cantidad = :cantidad")})
public class Deduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_deduccion")
    private Integer idDeduccion;
    @Basic(optional = false)
    @Column(name = "deduccion")
    private String deduccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @JoinColumn(name = "numero_nomina", referencedColumnName = "numero_nomina")
    @ManyToOne(optional = false)
    private Nomina numeroNomina;

    public Deduccion() {
    }

    public Deduccion(Integer idDeduccion) {
        this.idDeduccion = idDeduccion;
    }

    public Deduccion(Integer idDeduccion, String deduccion, BigDecimal cantidad) {
        this.idDeduccion = idDeduccion;
        this.deduccion = deduccion;
        this.cantidad = cantidad;
    }

    public Integer getIdDeduccion() {
        return idDeduccion;
    }

    public void setIdDeduccion(Integer idDeduccion) {
        this.idDeduccion = idDeduccion;
    }

    public String getDeduccion() {
        return deduccion;
    }

    public void setDeduccion(String deduccion) {
        this.deduccion = deduccion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Nomina getNumeroNomina() {
        return numeroNomina;
    }

    public void setNumeroNomina(Nomina numeroNomina) {
        this.numeroNomina = numeroNomina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeduccion != null ? idDeduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deduccion)) {
            return false;
        }
        Deduccion other = (Deduccion) object;
        if ((this.idDeduccion == null && other.idDeduccion != null) || (this.idDeduccion != null && !this.idDeduccion.equals(other.idDeduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.unadm.mexican.food.restaurant.models.Deduccion[ idDeduccion=" + idDeduccion + " ]";
    }
    
}
