/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.users;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "user_permissions", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UserPermissions.findAll", query = "SELECT u FROM UserPermissions u"),
//    @NamedQuery(name = "UserPermissions.findById", query = "SELECT u FROM UserPermissions u WHERE u.id = :id"),
//    @NamedQuery(name = "UserPermissions.findByDeleted", query = "SELECT u FROM UserPermissions u WHERE u.deleted = :deleted"),
//    @NamedQuery(name = "UserPermissions.findByCreated", query = "SELECT u FROM UserPermissions u WHERE u.created = :created"),
//    @NamedQuery(name = "UserPermissions.findByChanged", query = "SELECT u FROM UserPermissions u WHERE u.changed = :changed"),
//    @NamedQuery(name = "UserPermissions.findByName", query = "SELECT u FROM UserPermissions u WHERE u.name = :name"),
//    @NamedQuery(name = "UserPermissions.findByDesignation", query = "SELECT u FROM UserPermissions u WHERE u.designation = :designation")})
public class UserPermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String name;
    private String designation;
    private Collection<UserRolePermissions> userRolePermissionsCollection;
    private Collection<UserPermissions> userPermissionsCollection;
    private UserPermissions parent;

    public UserPermissions() {
    }

    public UserPermissions(Integer id) {
        this.id = id;
    }

    public UserPermissions(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Collection<UserRolePermissions> getUserRolePermissionsCollection() {
        return userRolePermissionsCollection;
    }

    public void setUserRolePermissionsCollection(Collection<UserRolePermissions> userRolePermissionsCollection) {
        this.userRolePermissionsCollection = userRolePermissionsCollection;
    }

    public Collection<UserPermissions> getUserPermissionsCollection() {
        return userPermissionsCollection;
    }

    public void setUserPermissionsCollection(Collection<UserPermissions> userPermissionsCollection) {
        this.userPermissionsCollection = userPermissionsCollection;
    }

    public UserPermissions getParent() {
        return parent;
    }

    public void setParent(UserPermissions parent) {
        this.parent = parent;
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
        if (!(object instanceof UserPermissions)) {
            return false;
        }
        UserPermissions other = (UserPermissions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.UserPermissions[ id=" + id + " ]";
    }

}
