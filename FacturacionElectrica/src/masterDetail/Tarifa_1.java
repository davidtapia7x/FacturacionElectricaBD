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
@Table(name = "TARIFA", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Tarifa_1.findAll", query = "SELECT t FROM Tarifa_1 t"),
    @NamedQuery(name = "Tarifa_1.findByCodTar", query = "SELECT t FROM Tarifa_1 t WHERE t.codTar = :codTar"),
    @NamedQuery(name = "Tarifa_1.findByCodUso", query = "SELECT t FROM Tarifa_1 t WHERE t.codUso = :codUso"),
    @NamedQuery(name = "Tarifa_1.findByTarifa", query = "SELECT t FROM Tarifa_1 t WHERE t.tarifa = :tarifa"),
    @NamedQuery(name = "Tarifa_1.findByFecha", query = "SELECT t FROM Tarifa_1 t WHERE t.fecha = :fecha")})
public class Tarifa_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_TAR")
    private Long codTar;
    @Basic(optional = false)
    @Column(name = "COD_USO")
    private long codUso;
    @Basic(optional = false)
    @Column(name = "TARIFA")
    private double tarifa;
    @Basic(optional = false)
    @Column(name = "FECHA")
    private String fecha;

    public Tarifa_1() {
    }

    public Tarifa_1(Long codTar) {
        this.codTar = codTar;
    }

    public Tarifa_1(Long codTar, long codUso, double tarifa, String fecha) {
        this.codTar = codTar;
        this.codUso = codUso;
        this.tarifa = tarifa;
        this.fecha = fecha;
    }

    public Long getCodTar() {
        return codTar;
    }

    public void setCodTar(Long codTar) {
        Long oldCodTar = this.codTar;
        this.codTar = codTar;
        changeSupport.firePropertyChange("codTar", oldCodTar, codTar);
    }

    public long getCodUso() {
        return codUso;
    }

    public void setCodUso(long codUso) {
        long oldCodUso = this.codUso;
        this.codUso = codUso;
        changeSupport.firePropertyChange("codUso", oldCodUso, codUso);
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        double oldTarifa = this.tarifa;
        this.tarifa = tarifa;
        changeSupport.firePropertyChange("tarifa", oldTarifa, tarifa);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        String oldFecha = this.fecha;
        this.fecha = fecha;
        changeSupport.firePropertyChange("fecha", oldFecha, fecha);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTar != null ? codTar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifa_1)) {
            return false;
        }
        Tarifa_1 other = (Tarifa_1) object;
        if ((this.codTar == null && other.codTar != null) || (this.codTar != null && !this.codTar.equals(other.codTar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Tarifa_1[ codTar=" + codTar + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
