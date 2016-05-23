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
@Table(name = "EMPLEADO", catalog = "", schema = "FACTURACIONELECTRICA")
@NamedQueries({
    @NamedQuery(name = "Empleado_1.findAll", query = "SELECT e FROM Empleado_1 e"),
    @NamedQuery(name = "Empleado_1.findByIdEmpleado", query = "SELECT e FROM Empleado_1 e WHERE e.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "Empleado_1.findByNombre1E", query = "SELECT e FROM Empleado_1 e WHERE e.nombre1E = :nombre1E"),
    @NamedQuery(name = "Empleado_1.findByNombre2E", query = "SELECT e FROM Empleado_1 e WHERE e.nombre2E = :nombre2E"),
    @NamedQuery(name = "Empleado_1.findByApellido1E", query = "SELECT e FROM Empleado_1 e WHERE e.apellido1E = :apellido1E"),
    @NamedQuery(name = "Empleado_1.findByApellido2E", query = "SELECT e FROM Empleado_1 e WHERE e.apellido2E = :apellido2E"),
    @NamedQuery(name = "Empleado_1.findByFecNaciE", query = "SELECT e FROM Empleado_1 e WHERE e.fecNaciE = :fecNaciE"),
    @NamedQuery(name = "Empleado_1.findByFechaIngreso", query = "SELECT e FROM Empleado_1 e WHERE e.fechaIngreso = :fechaIngreso")})
public class Empleado_1 implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EMPLEADO")
    private String idEmpleado;
    @Basic(optional = false)
    @Column(name = "NOMBRE1_E")
    private String nombre1E;
    @Column(name = "NOMBRE2_E")
    private String nombre2E;
    @Basic(optional = false)
    @Column(name = "APELLIDO1_E")
    private String apellido1E;
    @Basic(optional = false)
    @Column(name = "APELLIDO2_E")
    private String apellido2E;
    @Basic(optional = false)
    @Column(name = "FEC_NACI_E")
    private String fecNaciE;
    @Basic(optional = false)
    @Column(name = "FECHA_INGRESO")
    private String fechaIngreso;

    public Empleado_1() {
    }

    public Empleado_1(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado_1(String idEmpleado, String nombre1E, String apellido1E, String apellido2E, String fecNaciE, String fechaIngreso) {
        this.idEmpleado = idEmpleado;
        this.nombre1E = nombre1E;
        this.apellido1E = apellido1E;
        this.apellido2E = apellido2E;
        this.fecNaciE = fecNaciE;
        this.fechaIngreso = fechaIngreso;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        String oldIdEmpleado = this.idEmpleado;
        this.idEmpleado = idEmpleado;
        changeSupport.firePropertyChange("idEmpleado", oldIdEmpleado, idEmpleado);
    }

    public String getNombre1E() {
        return nombre1E;
    }

    public void setNombre1E(String nombre1E) {
        String oldNombre1E = this.nombre1E;
        this.nombre1E = nombre1E;
        changeSupport.firePropertyChange("nombre1E", oldNombre1E, nombre1E);
    }

    public String getNombre2E() {
        return nombre2E;
    }

    public void setNombre2E(String nombre2E) {
        String oldNombre2E = this.nombre2E;
        this.nombre2E = nombre2E;
        changeSupport.firePropertyChange("nombre2E", oldNombre2E, nombre2E);
    }

    public String getApellido1E() {
        return apellido1E;
    }

    public void setApellido1E(String apellido1E) {
        String oldApellido1E = this.apellido1E;
        this.apellido1E = apellido1E;
        changeSupport.firePropertyChange("apellido1E", oldApellido1E, apellido1E);
    }

    public String getApellido2E() {
        return apellido2E;
    }

    public void setApellido2E(String apellido2E) {
        String oldApellido2E = this.apellido2E;
        this.apellido2E = apellido2E;
        changeSupport.firePropertyChange("apellido2E", oldApellido2E, apellido2E);
    }

    public String getFecNaciE() {
        return fecNaciE;
    }

    public void setFecNaciE(String fecNaciE) {
        String oldFecNaciE = this.fecNaciE;
        this.fecNaciE = fecNaciE;
        changeSupport.firePropertyChange("fecNaciE", oldFecNaciE, fecNaciE);
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        String oldFechaIngreso = this.fechaIngreso;
        this.fechaIngreso = fechaIngreso;
        changeSupport.firePropertyChange("fechaIngreso", oldFechaIngreso, fechaIngreso);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado_1)) {
            return false;
        }
        Empleado_1 other = (Empleado_1) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterDetail.Empleado_1[ idEmpleado=" + idEmpleado + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
