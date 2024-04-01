/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.machines;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "mach_drivers", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MachDrivers.findAll", query = "SELECT m FROM MachDrivers m"),
//    @NamedQuery(name = "MachDrivers.findById", query = "SELECT m FROM MachDrivers m WHERE m.id = :id"),
//    @NamedQuery(name = "MachDrivers.findByDeleted", query = "SELECT m FROM MachDrivers m WHERE m.deleted = :deleted"),
//    @NamedQuery(name = "MachDrivers.findByCreated", query = "SELECT m FROM MachDrivers m WHERE m.created = :created"),
//    @NamedQuery(name = "MachDrivers.findByChanged", query = "SELECT m FROM MachDrivers m WHERE m.changed = :changed"),
//    @NamedQuery(name = "MachDrivers.findByDriver", query = "SELECT m FROM MachDrivers m WHERE m.driver = :driver"),
//    @NamedQuery(name = "MachDrivers.findByDesignation", query = "SELECT m FROM MachDrivers m WHERE m.designation = :designation")})
public class MachDrivers implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String driver;
    private String designation;
    private Collection<Machines> machinesCollection;

    public MachDrivers() {
    }

    public MachDrivers(Integer id) {
        this.id = id;
    }

    public MachDrivers(Integer id, String driver) {
        this.id = id;
        this.driver = driver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Collection<Machines> getMachinesCollection() {
        return machinesCollection;
    }

    public void setMachinesCollection(Collection<Machines> machinesCollection) {
        this.machinesCollection = machinesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MachDrivers)) {
            return false;
        }
        MachDrivers other = (MachDrivers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.MachDrivers[ id=" + id + " ]";
    }

}
