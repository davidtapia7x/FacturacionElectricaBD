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
@Table(name = "SERVICIO_ADICIONAL", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "ServicioAdicional_1.findAll", query = "SELECT s FROM ServicioAdicional_1 s"),
    @NamedQuery(name = "ServicioAdicional_1.findByCodServAdicional", query = "SELECT s FROM ServicioAdicional_1 s WHERE s.codServAdicional = :codServAdicional"),
    @NamedQuery(name = "ServicioAdicional_1.findByDescripcionServAdi", query = "SELECT s FROM ServicioAdicional_1 s WHERE s.descripcionServAdi = :descripcionServAdi"),
    @NamedQuery(name = "ServicioAdicional_1.findByPorcentajeServAdi", query = "SELECT s FROM ServicioAdicional_1 s WHERE s.porcentajeServAdi = :porcentajeServAdi")})
public class ServicioAdicional_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_SERV_ADICIONAL")
    private Long codServAdicional;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_SERV_ADI")
    private String descripcionServAdi;
    @Basic(optional = false)
    @Column(name = "PORCENTAJE_SERV_ADI")
    private double porcentajeServAdi;

    public ServicioAdicional_1() {
    }

    public ServicioAdicional_1(Long codServAdicional) {
        this.codServAdicional = codServAdicional;
    }

    public ServicioAdicional_1(Long codServAdicional, String descripcionServAdi, double porcentajeServAdi) {
        this.codServAdicional = codServAdicional;
        this.descripcionServAdi = descripcionServAdi;
        this.porcentajeServAdi = porcentajeServAdi;
    }

    public Long getCodServAdicional() {
        return codServAdicional;
    }

    public void setCodServAdicional(Long codServAdicional) {
        Long oldCodServAdicional = this.codServAdicional;
        this.codServAdicional = codServAdicional;
        changeSupport.firePropertyChange("codServAdicional", oldCodServAdicional, codServAdicional);
    }

    public String getDescripcionServAdi() {
        return descripcionServAdi;
    }

    public void setDescripcionServAdi(String descripcionServAdi) {
        String oldDescripcionServAdi = this.descripcionServAdi;
        this.descripcionServAdi = descripcionServAdi;
        changeSupport.firePropertyChange("descripcionServAdi", oldDescripcionServAdi, descripcionServAdi);
    }

    public double getPorcentajeServAdi() {
        return porcentajeServAdi;
    }

    public void setPorcentajeServAdi(double porcentajeServAdi) {
        double oldPorcentajeServAdi = this.porcentajeServAdi;
        this.porcentajeServAdi = porcentajeServAdi;
        changeSupport.firePropertyChange("porcentajeServAdi", oldPorcentajeServAdi, porcentajeServAdi);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codServAdicional != null ? codServAdicional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioAdicional_1)) {
            return false;
        }
        ServicioAdicional_1 other = (ServicioAdicional_1) object;
        if ((this.codServAdicional == null && other.codServAdicional != null) || (this.codServAdicional != null && !this.codServAdicional.equals(other.codServAdicional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.ServicioAdicional_1[ codServAdicional=" + codServAdicional + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
