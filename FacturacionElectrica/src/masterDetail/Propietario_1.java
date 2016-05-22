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
@Table(name = "PROPIETARIO", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Propietario_1.findAll", query = "SELECT p FROM Propietario_1 p"),
    @NamedQuery(name = "Propietario_1.findByIdent", query = "SELECT p FROM Propietario_1 p WHERE p.ident = :ident"),
    @NamedQuery(name = "Propietario_1.findByNombre1", query = "SELECT p FROM Propietario_1 p WHERE p.nombre1 = :nombre1"),
    @NamedQuery(name = "Propietario_1.findByNombre2", query = "SELECT p FROM Propietario_1 p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "Propietario_1.findByApellido1", query = "SELECT p FROM Propietario_1 p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "Propietario_1.findByApellido2", query = "SELECT p FROM Propietario_1 p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "Propietario_1.findByTelefono", query = "SELECT p FROM Propietario_1 p WHERE p.telefono = :telefono")})
public class Propietario_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private String ident;
    @Basic(optional = false)
    @Column(name = "NOMBRE1")
    private String nombre1;
    @Column(name = "NOMBRE2")
    private String nombre2;
    @Basic(optional = false)
    @Column(name = "APELLIDO1")
    private String apellido1;
    @Basic(optional = false)
    @Column(name = "APELLIDO2")
    private String apellido2;
    @Basic(optional = false)
    @Column(name = "TELEFONO")
    private String telefono;

    public Propietario_1() {
    }

    public Propietario_1(String ident) {
        this.ident = ident;
    }

    public Propietario_1(String ident, String nombre1, String apellido1, String apellido2, String telefono) {
        this.ident = ident;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        String oldIdent = this.ident;
        this.ident = ident;
        changeSupport.firePropertyChange("ident", oldIdent, ident);
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        String oldNombre1 = this.nombre1;
        this.nombre1 = nombre1;
        changeSupport.firePropertyChange("nombre1", oldNombre1, nombre1);
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        String oldNombre2 = this.nombre2;
        this.nombre2 = nombre2;
        changeSupport.firePropertyChange("nombre2", oldNombre2, nombre2);
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        String oldApellido1 = this.apellido1;
        this.apellido1 = apellido1;
        changeSupport.firePropertyChange("apellido1", oldApellido1, apellido1);
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        String oldApellido2 = this.apellido2;
        this.apellido2 = apellido2;
        changeSupport.firePropertyChange("apellido2", oldApellido2, apellido2);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        String oldTelefono = this.telefono;
        this.telefono = telefono;
        changeSupport.firePropertyChange("telefono", oldTelefono, telefono);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ident != null ? ident.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propietario_1)) {
            return false;
        }
        Propietario_1 other = (Propietario_1) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Propietario_1[ ident=" + ident + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
