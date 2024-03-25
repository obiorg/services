/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.users;

import org.obi.services.entities.business.Companies;
import org.obi.services.entities.business.Entities;
import org.obi.services.entities.business.Businesses;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "user_role_permissions", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UserRolePermissions.findAll", query = "SELECT u FROM UserRolePermissions u"),
//    @NamedQuery(name = "UserRolePermissions.findById", query = "SELECT u FROM UserRolePermissions u WHERE u.id = :id"),
//    @NamedQuery(name = "UserRolePermissions.findByDeleted", query = "SELECT u FROM UserRolePermissions u WHERE u.deleted = :deleted"),
//    @NamedQuery(name = "UserRolePermissions.findByCreated", query = "SELECT u FROM UserRolePermissions u WHERE u.created = :created"),
//    @NamedQuery(name = "UserRolePermissions.findByChanged", query = "SELECT u FROM UserRolePermissions u WHERE u.changed = :changed")})
public class UserRolePermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private Businesses business;
    private Companies comapny;
    private Entities entity;
    private UserPermissions permission;
    private UserRoles role;

    public UserRolePermissions() {
    }

    public UserRolePermissions(Integer id) {
        this.id = id;
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

    public UserPermissions getPermission() {
        return permission;
    }

    public void setPermission(UserPermissions permission) {
        this.permission = permission;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
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
        if (!(object instanceof UserRolePermissions)) {
            return false;
        }
        UserRolePermissions other = (UserRolePermissions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.UserRolePermissions[ id=" + id + " ]";
    }

}
