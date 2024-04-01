/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.users;

import org.obi.services.entities.business.Companies;
import org.obi.services.entities.business.Entities;
import org.obi.services.entities.business.Businesses;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "user_roles", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UserRoles.findAll", query = "SELECT u FROM UserRoles u"),
//    @NamedQuery(name = "UserRoles.findById", query = "SELECT u FROM UserRoles u WHERE u.id = :id"),
//    @NamedQuery(name = "UserRoles.findByDeleted", query = "SELECT u FROM UserRoles u WHERE u.deleted = :deleted"),
//    @NamedQuery(name = "UserRoles.findByCreated", query = "SELECT u FROM UserRoles u WHERE u.created = :created"),
//    @NamedQuery(name = "UserRoles.findByChanged", query = "SELECT u FROM UserRoles u WHERE u.changed = :changed"),
//    @NamedQuery(name = "UserRoles.findByName", query = "SELECT u FROM UserRoles u WHERE u.name = :name"),
//    @NamedQuery(name = "UserRoles.findByDescription", query = "SELECT u FROM UserRoles u WHERE u.description = :description")})
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String name;
    private String description;
    private Collection<UserRolePermissions> userRolePermissionsCollection;
    private Businesses business;
    private Companies comapny;
    private Entities entity;
    private Collection<UserAccountRole> userAccountRoleCollection;

    public UserRoles() {
    }

    public UserRoles(Integer id) {
        this.id = id;
    }

    public UserRoles(Integer id, String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<UserRolePermissions> getUserRolePermissionsCollection() {
        return userRolePermissionsCollection;
    }

    public void setUserRolePermissionsCollection(Collection<UserRolePermissions> userRolePermissionsCollection) {
        this.userRolePermissionsCollection = userRolePermissionsCollection;
    }

    public Businesses getBusiness() {
        return business;
    }

    public void setBusiness(Businesses business) {
        this.business = business;
    }

    public Companies getComapny() {
        return comapny;
    }

    public void setComapny(Companies comapny) {
        this.comapny = comapny;
    }

    public Entities getEntity() {
        return entity;
    }

    public void setEntity(Entities entity) {
        this.entity = entity;
    }

    public Collection<UserAccountRole> getUserAccountRoleCollection() {
        return userAccountRoleCollection;
    }

    public void setUserAccountRoleCollection(Collection<UserAccountRole> userAccountRoleCollection) {
        this.userAccountRoleCollection = userAccountRoleCollection;
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
        if (!(object instanceof UserRoles)) {
            return false;
        }
        UserRoles other = (UserRoles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.UserRoles[ id=" + id + " ]";
    }

}
