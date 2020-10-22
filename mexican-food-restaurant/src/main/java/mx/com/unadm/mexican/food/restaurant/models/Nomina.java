/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.models;

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

/**
 *
 * @author macos
 */
@Entity
@Table(name = "NOMINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomina.findAll", query = "SELECT n FROM Nomina n"),
    @NamedQuery(name = "Nomina.findByNumeroNomina", query = "SELECT n FROM Nomina n WHERE n.numeroNomina = :numeroNomina"),
    @NamedQuery(name = "Nomina.findByRazonSocial", query = "SELECT n FROM Nomina n WHERE n.razonSocial = :razonSocial"),
    @NamedQuery(name = "Nomina.findByDomicilio", query = "SELECT n FROM Nomina n WHERE n.domicilio = :domicilio"),
    @NamedQuery(name = "Nomina.findByFecha", query = "SELECT n FROM Nomina n WHERE n.fecha = :fecha"),
    @NamedQuery(name = "Nomina.findByInicioPeriodo", query = "SELECT n FROM Nomina n WHERE n.inicioPeriodo = :inicioPeriodo"),
    @NamedQuery(name = "Nomina.findByFinPeriodo", query = "SELECT n FROM Nomina n WHERE n.finPeriodo = :finPeriodo"),
    @NamedQuery(name = "Nomina.findByDias", query = "SELECT n FROM Nomina n WHERE n.dias = :dias")})
public class Nomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numero_nomina")
    private Integer numeroNomina;
    @Basic(optional = false)
    @Column(name = "razon_social")
    private String razonSocial;
    @Basic(optional = false)
    @Column(name = "domicilio")
    private String domicilio;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "inicio_periodo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioPeriodo;
    @Basic(optional = false)
    @Column(name = "fin_periodo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finPeriodo;
    @Basic(optional = false)
    @Column(name = "dias")
    private int dias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroNomina")
    private Collection<Concepto> conceptoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroNomina")
    private Collection<Deduccion> deduccionCollection;
    @JoinColumn(name = "numero_empleado", referencedColumnName = "numero_empleado")
    @ManyToOne(optional = false)
    private Empleado numeroEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroNomina")
    private Collection<Aportacion> aportacionCollection;

    public Nomina() {
    }

    public Nomina(Integer numeroNomina) {
        this.numeroNomina = numeroNomina;
    }

    public Nomina(Integer numeroNomina, String razonSocial, String domicilio, Date fecha, Date inicioPeriodo, Date finPeriodo, int dias) {
        this.numeroNomina = numeroNomina;
        this.razonSocial = razonSocial;
        this.domicilio = domicilio;
        this.fecha = fecha;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        this.dias = dias;
    }
    
    public Nomina(String razonSocial, String domicilio, Date fecha, Date inicioPeriodo, Date finPeriodo, int dias) {
        this.razonSocial = razonSocial;
        this.domicilio = domicilio;
        this.fecha = fecha;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        this.dias = dias;
    }
    
    

    public Integer getNumeroNomina() {
        return numeroNomina;
    }

    public void setNumeroNomina(Integer numeroNomina) {
        this.numeroNomina = numeroNomina;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    @XmlTransient
    public Collection<Concepto> getConceptoCollection() {
        return conceptoCollection;
    }

    public void setConceptoCollection(Collection<Concepto> conceptoCollection) {
        this.conceptoCollection = conceptoCollection;
    }

    @XmlTransient
    public Collection<Deduccion> getDeduccionCollection() {
        return deduccionCollection;
    }

    public void setDeduccionCollection(Collection<Deduccion> deduccionCollection) {
        this.deduccionCollection = deduccionCollection;
    }

    public Empleado getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Empleado numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    @XmlTransient
    public Collection<Aportacion> getAportacionCollection() {
        return aportacionCollection;
    }

    public void setAportacionCollection(Collection<Aportacion> aportacionCollection) {
        this.aportacionCollection = aportacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroNomina != null ? numeroNomina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nomina)) {
            return false;
        }
        Nomina other = (Nomina) object;
        if ((this.numeroNomina == null && other.numeroNomina != null) || (this.numeroNomina != null && !this.numeroNomina.equals(other.numeroNomina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.unadm.mexican.food.restaurant.models.Nomina[ numeroNomina=" + numeroNomina + " ]";
    }
    
}
