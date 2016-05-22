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
@Table(name = "PREDIO", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Predio_1.findAll", query = "SELECT p FROM Predio_1 p"),
    @NamedQuery(name = "Predio_1.findByClavecatastral", query = "SELECT p FROM Predio_1 p WHERE p.clavecatastral = :clavecatastral"),
    @NamedQuery(name = "Predio_1.findByCidentidad", query = "SELECT p FROM Predio_1 p WHERE p.cidentidad = :cidentidad"),
    @NamedQuery(name = "Predio_1.findByCduso", query = "SELECT p FROM Predio_1 p WHERE p.cduso = :cduso"),
    @NamedQuery(name = "Predio_1.findByCalle1", query = "SELECT p FROM Predio_1 p WHERE p.calle1 = :calle1"),
    @NamedQuery(name = "Predio_1.findByCalle2", query = "SELECT p FROM Predio_1 p WHERE p.calle2 = :calle2"),
    @NamedQuery(name = "Predio_1.findByCalle3", query = "SELECT p FROM Predio_1 p WHERE p.calle3 = :calle3")})
public class Predio_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLAVECATASTRAL")
    private Long clavecatastral;
    @Basic(optional = false)
    @Column(name = "CIDENTIDAD")
    private String cidentidad;
    @Basic(optional = false)
    @Column(name = "CDUSO")
    private long cduso;
    @Column(name = "CALLE1")
    private String calle1;
    @Column(name = "CALLE2")
    private String calle2;
    @Column(name = "CALLE3")
    private String calle3;

    public Predio_1() {
    }

    public Predio_1(Long clavecatastral) {
        this.clavecatastral = clavecatastral;
    }

    public Predio_1(Long clavecatastral, String cidentidad, long cduso) {
        this.clavecatastral = clavecatastral;
        this.cidentidad = cidentidad;
        this.cduso = cduso;
    }

    public Long getClavecatastral() {
        return clavecatastral;
    }

    public void setClavecatastral(Long clavecatastral) {
        Long oldClavecatastral = this.clavecatastral;
        this.clavecatastral = clavecatastral;
        changeSupport.firePropertyChange("clavecatastral", oldClavecatastral, clavecatastral);
    }

    public String getCidentidad() {
        return cidentidad;
    }

    public void setCidentidad(String cidentidad) {
        String oldCidentidad = this.cidentidad;
        this.cidentidad = cidentidad;
        changeSupport.firePropertyChange("cidentidad", oldCidentidad, cidentidad);
    }

    public long getCduso() {
        return cduso;
    }

    public void setCduso(long cduso) {
        long oldCduso = this.cduso;
        this.cduso = cduso;
        changeSupport.firePropertyChange("cduso", oldCduso, cduso);
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        String oldCalle1 = this.calle1;
        this.calle1 = calle1;
        changeSupport.firePropertyChange("calle1", oldCalle1, calle1);
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        String oldCalle2 = this.calle2;
        this.calle2 = calle2;
        changeSupport.firePropertyChange("calle2", oldCalle2, calle2);
    }

    public String getCalle3() {
        return calle3;
    }

    public void setCalle3(String calle3) {
        String oldCalle3 = this.calle3;
        this.calle3 = calle3;
        changeSupport.firePropertyChange("calle3", oldCalle3, calle3);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clavecatastral != null ? clavecatastral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predio_1)) {
            return false;
        }
        Predio_1 other = (Predio_1) object;
        if ((this.clavecatastral == null && other.clavecatastral != null) || (this.clavecatastral != null && !this.clavecatastral.equals(other.clavecatastral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Predio_1[ clavecatastral=" + clavecatastral + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
