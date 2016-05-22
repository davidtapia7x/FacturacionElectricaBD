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
@Table(name = "MEDIDOR", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Medidor_1.findAll", query = "SELECT m FROM Medidor_1 m"),
    @NamedQuery(name = "Medidor_1.findByCodMedidor", query = "SELECT m FROM Medidor_1 m WHERE m.codMedidor = :codMedidor"),
    @NamedQuery(name = "Medidor_1.findByConsumoInicial", query = "SELECT m FROM Medidor_1 m WHERE m.consumoInicial = :consumoInicial"),
    @NamedQuery(name = "Medidor_1.findByClavecatastral", query = "SELECT m FROM Medidor_1 m WHERE m.clavecatastral = :clavecatastral")})
public class Medidor_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_MEDIDOR")
    private String codMedidor;
    @Basic(optional = false)
    @Column(name = "CONSUMO_INICIAL")
    private double consumoInicial;
    @Basic(optional = false)
    @Column(name = "CLAVECATASTRAL")
    private long clavecatastral;

    public Medidor_1() {
    }

    public Medidor_1(String codMedidor) {
        this.codMedidor = codMedidor;
    }

    public Medidor_1(String codMedidor, double consumoInicial, long clavecatastral) {
        this.codMedidor = codMedidor;
        this.consumoInicial = consumoInicial;
        this.clavecatastral = clavecatastral;
    }

    public String getCodMedidor() {
        return codMedidor;
    }

    public void setCodMedidor(String codMedidor) {
        String oldCodMedidor = this.codMedidor;
        this.codMedidor = codMedidor;
        changeSupport.firePropertyChange("codMedidor", oldCodMedidor, codMedidor);
    }

    public double getConsumoInicial() {
        return consumoInicial;
    }

    public void setConsumoInicial(double consumoInicial) {
        double oldConsumoInicial = this.consumoInicial;
        this.consumoInicial = consumoInicial;
        changeSupport.firePropertyChange("consumoInicial", oldConsumoInicial, consumoInicial);
    }

    public long getClavecatastral() {
        return clavecatastral;
    }

    public void setClavecatastral(long clavecatastral) {
        long oldClavecatastral = this.clavecatastral;
        this.clavecatastral = clavecatastral;
        changeSupport.firePropertyChange("clavecatastral", oldClavecatastral, clavecatastral);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMedidor != null ? codMedidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medidor_1)) {
            return false;
        }
        Medidor_1 other = (Medidor_1) object;
        if ((this.codMedidor == null && other.codMedidor != null) || (this.codMedidor != null && !this.codMedidor.equals(other.codMedidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Medidor_1[ codMedidor=" + codMedidor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
