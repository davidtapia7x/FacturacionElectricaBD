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
@Table(name = "PARAMETRO", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
    @NamedQuery(name = "Parametro.findByCodPar", query = "SELECT p FROM Parametro p WHERE p.codPar = :codPar"),
    @NamedQuery(name = "Parametro.findByTituloPar", query = "SELECT p FROM Parametro p WHERE p.tituloPar = :tituloPar"),
    @NamedQuery(name = "Parametro.findByDescripcionPar", query = "SELECT p FROM Parametro p WHERE p.descripcionPar = :descripcionPar")})
public class Parametro implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_PAR")
    private Long codPar;
    @Basic(optional = false)
    @Column(name = "TITULO_PAR")
    private String tituloPar;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_PAR")
    private String descripcionPar;

    public Parametro() {
    }

    public Parametro(Long codPar) {
        this.codPar = codPar;
    }

    public Parametro(Long codPar, String tituloPar, String descripcionPar) {
        this.codPar = codPar;
        this.tituloPar = tituloPar;
        this.descripcionPar = descripcionPar;
    }

    public Long getCodPar() {
        return codPar;
    }

    public void setCodPar(Long codPar) {
        Long oldCodPar = this.codPar;
        this.codPar = codPar;
        changeSupport.firePropertyChange("codPar", oldCodPar, codPar);
    }

    public String getTituloPar() {
        return tituloPar;
    }

    public void setTituloPar(String tituloPar) {
        String oldTituloPar = this.tituloPar;
        this.tituloPar = tituloPar;
        changeSupport.firePropertyChange("tituloPar", oldTituloPar, tituloPar);
    }

    public String getDescripcionPar() {
        return descripcionPar;
    }

    public void setDescripcionPar(String descripcionPar) {
        String oldDescripcionPar = this.descripcionPar;
        this.descripcionPar = descripcionPar;
        changeSupport.firePropertyChange("descripcionPar", oldDescripcionPar, descripcionPar);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPar != null ? codPar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.codPar == null && other.codPar != null) || (this.codPar != null && !this.codPar.equals(other.codPar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Parametro[ codPar=" + codPar + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
