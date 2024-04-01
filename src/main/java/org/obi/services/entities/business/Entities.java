/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.business;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.locations.Locations;
import org.obi.services.entities.measures.MeasUnits;
import org.obi.services.entities.users.UserRolePermissions;
import org.obi.services.entities.users.UserRoles;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Entities.findAll", query = "SELECT e FROM Entities e"),
//    @NamedQuery(name = "Entities.findById", query = "SELECT e FROM Entities e WHERE e.id = :id"),
//    @NamedQuery(name = "Entities.findByDeleted", query = "SELECT e FROM Entities e WHERE e.deleted = :deleted"),
//    @NamedQuery(name = "Entities.findByCreated", query = "SELECT e FROM Entities e WHERE e.created = :created"),
//    @NamedQuery(name = "Entities.findByChanged", query = "SELECT e FROM Entities e WHERE e.changed = :changed"),
//    @NamedQuery(name = "Entities.findByEntity", query = "SELECT e FROM Entities e WHERE e.entity = :entity"),
//    @NamedQuery(name = "Entities.findByDesignation", query = "SELECT e FROM Entities e WHERE e.designation = :designation"),
//    @NamedQuery(name = "Entities.findByBuilded", query = "SELECT e FROM Entities e WHERE e.builded = :builded"),
//    @NamedQuery(name = "Entities.findByMain", query = "SELECT e FROM Entities e WHERE e.main = :main"),
//    @NamedQuery(name = "Entities.findByActivated", query = "SELECT e FROM Entities e WHERE e.activated = :activated"),
//    @NamedQuery(name = "Entities.findByLogoPath", query = "SELECT e FROM Entities e WHERE e.logoPath = :logoPath")})
public class Entities implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String entity;
    private String designation;
    private Integer builded;
    private Boolean main;
    private Boolean activated;
    private String logoPath;
    private Collection<UserRolePermissions> userRolePermissionsCollection;
    private Collection<UserRoles> userRolesCollection;
    private Collection<Businesses> businessesCollection;
    private Locations location;
    private Collection<MeasUnits> measUnitsCollection;

    public Entities() {
    }

    public Entities(String entity) {
        this.entity = entity;
    }

    public Entities(String entity, int id) {
        this.entity = entity;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getBuilded() {
        return builded;
    }

    public void setBuilded(Integer builded) {
        this.builded = builded;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }


    public Collection<UserRolePermissions> getUserRolePermissionsCollection() {
        return userRolePermissionsCollection;
    }

    public void setUserRolePermissionsCollection(Collection<UserRolePermissions> userRolePermissionsCollection) {
        this.userRolePermissionsCollection = userRolePermissionsCollection;
    }


    public Collection<UserRoles> getUserRolesCollection() {
        return userRolesCollection;
    }

    public void setUserRolesCollection(Collection<UserRoles> userRolesCollection) {
        this.userRolesCollection = userRolesCollection;
    }


    public Collection<Businesses> getBusinessesCollection() {
        return businessesCollection;
    }

    public void setBusinessesCollection(Collection<Businesses> businessesCollection) {
        this.businessesCollection = businessesCollection;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }


    public Collection<MeasUnits> getMeasUnitsCollection() {
        return measUnitsCollection;
    }

    public void setMeasUnitsCollection(Collection<MeasUnits> measUnitsCollection) {
        this.measUnitsCollection = measUnitsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entity != null ? entity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entities)) {
            return false;
        }
        Entities other = (Entities) object;
        if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.Entities[ entity=" + entity + " ]";
    }
    
}
