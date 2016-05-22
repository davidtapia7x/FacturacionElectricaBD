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
@Table(name = "USOS", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Usos.findAll", query = "SELECT u FROM Usos u"),
    @NamedQuery(name = "Usos.findByCodusos", query = "SELECT u FROM Usos u WHERE u.codusos = :codusos"),
    @NamedQuery(name = "Usos.findByDescripcionuso", query = "SELECT u FROM Usos u WHERE u.descripcionuso = :descripcionuso")})
public class Usos implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODUSOS")
    private Long codusos;
    @Basic(optional = false)
    @Column(name = "DESCRIPCIONUSO")
    private String descripcionuso;

    public Usos() {
    }

    public Usos(Long codusos) {
        this.codusos = codusos;
    }

    public Usos(Long codusos, String descripcionuso) {
        this.codusos = codusos;
        this.descripcionuso = descripcionuso;
    }

    public Long getCodusos() {
        return codusos;
    }

    public void setCodusos(Long codusos) {
        Long oldCodusos = this.codusos;
        this.codusos = codusos;
        changeSupport.firePropertyChange("codusos", oldCodusos, codusos);
    }

    public String getDescripcionuso() {
        return descripcionuso;
    }

    public void setDescripcionuso(String descripcionuso) {
        String oldDescripcionuso = this.descripcionuso;
        this.descripcionuso = descripcionuso;
        changeSupport.firePropertyChange("descripcionuso", oldDescripcionuso, descripcionuso);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codusos != null ? codusos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usos)) {
            return false;
        }
        Usos other = (Usos) object;
        if ((this.codusos == null && other.codusos != null) || (this.codusos != null && !this.codusos.equals(other.codusos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Usos[ codusos=" + codusos + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
