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
@Table(name = "CONCEPTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concepto.findAll", query = "SELECT c FROM Concepto c"),
    @NamedQuery(name = "Concepto.findByIdConcepto", query = "SELECT c FROM Concepto c WHERE c.idConcepto = :idConcepto"),
    @NamedQuery(name = "Concepto.findByConcepto", query = "SELECT c FROM Concepto c WHERE c.concepto = :concepto"),
    @NamedQuery(name = "Concepto.findByCantidad", query = "SELECT c FROM Concepto c WHERE c.cantidad = :cantidad")})
public class Concepto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concepto")
    private Integer idConcepto;
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @JoinColumn(name = "numero_nomina", referencedColumnName = "numero_nomina")
    @ManyToOne(optional = false)
    private Nomina numeroNomina;

    public Concepto() {
    }

    public Concepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public Concepto(Integer idConcepto, String concepto, BigDecimal cantidad) {
        this.idConcepto = idConcepto;
        this.concepto = concepto;
        this.cantidad = cantidad;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
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
        hash += (idConcepto != null ? idConcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concepto)) {
            return false;
        }
        Concepto other = (Concepto) object;
        if ((this.idConcepto == null && other.idConcepto != null) || (this.idConcepto != null && !this.idConcepto.equals(other.idConcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.unadm.mexican.food.restaurant.models.Concepto[ idConcepto=" + idConcepto + " ]";
    }
    
}
