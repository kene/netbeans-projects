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
@Table(name = "APORTACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aportacion.findAll", query = "SELECT a FROM Aportacion a"),
    @NamedQuery(name = "Aportacion.findByIdAportacion", query = "SELECT a FROM Aportacion a WHERE a.idAportacion = :idAportacion"),
    @NamedQuery(name = "Aportacion.findByAportacion", query = "SELECT a FROM Aportacion a WHERE a.aportacion = :aportacion"),
    @NamedQuery(name = "Aportacion.findByCantidad", query = "SELECT a FROM Aportacion a WHERE a.cantidad = :cantidad")})
public class Aportacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aportacion")
    private Integer idAportacion;
    @Basic(optional = false)
    @Column(name = "aportacion")
    private String aportacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @JoinColumn(name = "numero_nomina", referencedColumnName = "numero_nomina")
    @ManyToOne(optional = false)
    private Nomina numeroNomina;

    public Aportacion() {
    }

    public Aportacion(Integer idAportacion) {
        this.idAportacion = idAportacion;
    }

    public Aportacion(Integer idAportacion, String aportacion, BigDecimal cantidad) {
        this.idAportacion = idAportacion;
        this.aportacion = aportacion;
        this.cantidad = cantidad;
    }

    public Integer getIdAportacion() {
        return idAportacion;
    }

    public void setIdAportacion(Integer idAportacion) {
        this.idAportacion = idAportacion;
    }

    public String getAportacion() {
        return aportacion;
    }

    public void setAportacion(String aportacion) {
        this.aportacion = aportacion;
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
        hash += (idAportacion != null ? idAportacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aportacion)) {
            return false;
        }
        Aportacion other = (Aportacion) object;
        if ((this.idAportacion == null && other.idAportacion != null) || (this.idAportacion != null && !this.idAportacion.equals(other.idAportacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.unadm.mexican.food.restaurant.models.Aportacion[ idAportacion=" + idAportacion + " ]";
    }
    
}
