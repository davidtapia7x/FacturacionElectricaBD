/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterDetail;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "RUBRO", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Rubro_1.findAll", query = "SELECT r FROM Rubro_1 r"),
    @NamedQuery(name = "Rubro_1.findByCodRubro", query = "SELECT r FROM Rubro_1 r WHERE r.codRubro = :codRubro"),
    @NamedQuery(name = "Rubro_1.findByDescRubro", query = "SELECT r FROM Rubro_1 r WHERE r.descRubro = :descRubro"),
    @NamedQuery(name = "Rubro_1.findByUnidad", query = "SELECT r FROM Rubro_1 r WHERE r.unidad = :unidad"),
    @NamedQuery(name = "Rubro_1.findByPrecioUnitario", query = "SELECT r FROM Rubro_1 r WHERE r.precioUnitario = :precioUnitario")})
public class Rubro_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_RUBRO")
    private String codRubro;
    @Basic(optional = false)
    @Column(name = "DESC_RUBRO")
    private String descRubro;
    @Basic(optional = false)
    @Column(name = "UNIDAD")
    private String unidad;
    @Basic(optional = false)
    @Column(name = "PRECIO_UNITARIO")
    private double precioUnitario;

    public Rubro_1() {
    }

    public Rubro_1(String codRubro) {
        this.codRubro = codRubro;
    }

    public Rubro_1(String codRubro, String descRubro, String unidad, double precioUnitario) {
        this.codRubro = codRubro;
        this.descRubro = descRubro;
        this.unidad = unidad;
        this.precioUnitario = precioUnitario;
    }

    public String getCodRubro() {
        return codRubro;
    }

    public void setCodRubro(String codRubro) {
        String oldCodRubro = this.codRubro;
        this.codRubro = codRubro;
        changeSupport.firePropertyChange("codRubro", oldCodRubro, codRubro);
    }

    public String getDescRubro() {
        return descRubro;
    }

    public void setDescRubro(String descRubro) {
        String oldDescRubro = this.descRubro;
        this.descRubro = descRubro;
        changeSupport.firePropertyChange("descRubro", oldDescRubro, descRubro);
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        String oldUnidad = this.unidad;
        this.unidad = unidad;
        changeSupport.firePropertyChange("unidad", oldUnidad, unidad);
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        double oldPrecioUnitario = this.precioUnitario;
        this.precioUnitario = precioUnitario;
        changeSupport.firePropertyChange("precioUnitario", oldPrecioUnitario, precioUnitario);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRubro != null ? codRubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubro_1)) {
            return false;
        }
        Rubro_1 other = (Rubro_1) object;
        if ((this.codRubro == null && other.codRubro != null) || (this.codRubro != null && !this.codRubro.equals(other.codRubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Rubro_1[ codRubro=" + codRubro + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
