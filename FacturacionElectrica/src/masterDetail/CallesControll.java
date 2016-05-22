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
@Table(name = "CALLES", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Calles.findAll", query = "SELECT c FROM Calles c"),
    @NamedQuery(name = "Calles.findByCodcalles", query = "SELECT c FROM Calles c WHERE c.codcalles = :codcalles"),
    @NamedQuery(name = "Calles.findByNombrecalle", query = "SELECT c FROM Calles c WHERE c.nombrecalle = :nombrecalle")})
public class CallesControll implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODCALLES")
    private String codcalles;
    @Basic(optional = false)
    @Column(name = "NOMBRECALLE")
    private String nombrecalle;

    public CallesControll() {
    }

    public CallesControll(String codcalles) {
        this.codcalles = codcalles;
    }

    public CallesControll(String codcalles, String nombrecalle) {
        this.codcalles = codcalles;
        this.nombrecalle = nombrecalle;
    }

    public String getCodcalles() {
        return codcalles;
    }

    public void setCodcalles(String codcalles) {
        String oldCodcalles = this.codcalles;
        this.codcalles = codcalles;
        changeSupport.firePropertyChange("codcalles", oldCodcalles, codcalles);
    }

    public String getNombrecalle() {
        return nombrecalle;
    }

    public void setNombrecalle(String nombrecalle) {
        String oldNombrecalle = this.nombrecalle;
        this.nombrecalle = nombrecalle;
        changeSupport.firePropertyChange("nombrecalle", oldNombrecalle, nombrecalle);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcalles != null ? codcalles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallesControll)) {
            return false;
        }
        CallesControll other = (CallesControll) object;
        if ((this.codcalles == null && other.codcalles != null) || (this.codcalles != null && !this.codcalles.equals(other.codcalles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Calles[ codcalles=" + codcalles + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
