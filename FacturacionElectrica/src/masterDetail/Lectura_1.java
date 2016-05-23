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
 * @author Andrés Campoverde <andrescamp_ac at hotmail.com>
 */
@Entity
@Table(name = "LECTURA", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Lectura_1.findAll", query = "SELECT l FROM Lectura_1 l"),
    @NamedQuery(name = "Lectura_1.findByNumLectura", query = "SELECT l FROM Lectura_1 l WHERE l.numLectura = :numLectura"),
    @NamedQuery(name = "Lectura_1.findByCodMedidor", query = "SELECT l FROM Lectura_1 l WHERE l.codMedidor = :codMedidor"),
    @NamedQuery(name = "Lectura_1.findByValorLectura", query = "SELECT l FROM Lectura_1 l WHERE l.valorLectura = :valorLectura"),
    @NamedQuery(name = "Lectura_1.findByFechaLectura", query = "SELECT l FROM Lectura_1 l WHERE l.fechaLectura = :fechaLectura"),
    @NamedQuery(name = "Lectura_1.findByIdEmpleado", query = "SELECT l FROM Lectura_1 l WHERE l.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "Lectura_1.findByCodRuta", query = "SELECT l FROM Lectura_1 l WHERE l.codRuta = :codRuta")})
public class Lectura_1 implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUM_LECTURA")
    private Long numLectura;
    @Basic(optional = false)
    @Column(name = "COD_MEDIDOR")
    private String codMedidor;
    @Basic(optional = false)
    @Column(name = "VALOR_LECTURA")
    private double valorLectura;
    @Basic(optional = false)
    @Column(name = "FECHA_LECTURA")
    private String fechaLectura;
    @Basic(optional = false)
    @Column(name = "ID_EMPLEADO")
    private String idEmpleado;
    @Basic(optional = false)
    @Column(name = "COD_RUTA")
    private long codRuta;

    public Lectura_1() {
    }

    public Lectura_1(Long numLectura) {
        this.numLectura = numLectura;
    }

    public Lectura_1(Long numLectura, String codMedidor, double valorLectura, String fechaLectura, String idEmpleado, long codRuta) {
        this.numLectura = numLectura;
        this.codMedidor = codMedidor;
        this.valorLectura = valorLectura;
        this.fechaLectura = fechaLectura;
        this.idEmpleado = idEmpleado;
        this.codRuta = codRuta;
    }

    public Long getNumLectura() {
        return numLectura;
    }

    public void setNumLectura(Long numLectura) {
        Long oldNumLectura = this.numLectura;
        this.numLectura = numLectura;
        changeSupport.firePropertyChange("numLectura", oldNumLectura, numLectura);
    }

    public String getCodMedidor() {
        return codMedidor;
    }

    public void setCodMedidor(String codMedidor) {
        String oldCodMedidor = this.codMedidor;
        this.codMedidor = codMedidor;
        changeSupport.firePropertyChange("codMedidor", oldCodMedidor, codMedidor);
    }

    public double getValorLectura() {
        return valorLectura;
    }

    public void setValorLectura(double valorLectura) {
        double oldValorLectura = this.valorLectura;
        this.valorLectura = valorLectura;
        changeSupport.firePropertyChange("valorLectura", oldValorLectura, valorLectura);
    }

    public String getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(String fechaLectura) {
        String oldFechaLectura = this.fechaLectura;
        this.fechaLectura = fechaLectura;
        changeSupport.firePropertyChange("fechaLectura", oldFechaLectura, fechaLectura);
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        String oldIdEmpleado = this.idEmpleado;
        this.idEmpleado = idEmpleado;
        changeSupport.firePropertyChange("idEmpleado", oldIdEmpleado, idEmpleado);
    }

    public long getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(long codRuta) {
        long oldCodRuta = this.codRuta;
        this.codRuta = codRuta;
        changeSupport.firePropertyChange("codRuta", oldCodRuta, codRuta);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numLectura != null ? numLectura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lectura_1)) {
            return false;
        }
        Lectura_1 other = (Lectura_1) object;
        if ((this.numLectura == null && other.numLectura != null) || (this.numLectura != null && !this.numLectura.equals(other.numLectura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Lectura_1[ numLectura=" + numLectura + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
