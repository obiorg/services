/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.users;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "user_account_role", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UserAccountRole.findAll", query = "SELECT u FROM UserAccountRole u"),
//    @NamedQuery(name = "UserAccountRole.findById", query = "SELECT u FROM UserAccountRole u WHERE u.id = :id"),
//    @NamedQuery(name = "UserAccountRole.findByDeleted", query = "SELECT u FROM UserAccountRole u WHERE u.deleted = :deleted"),
//    @NamedQuery(name = "UserAccountRole.findByCreated", query = "SELECT u FROM UserAccountRole u WHERE u.created = :created"),
//    @NamedQuery(name = "UserAccountRole.findByChanged", query = "SELECT u FROM UserAccountRole u WHERE u.changed = :changed")})
public class UserAccountRole implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private UserAccount user;
    private UserRoles role;

    public UserAccountRole() {
    }

    public UserAccountRole(Integer id) {
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

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
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
        if (!(object instanceof UserAccountRole)) {
            return false;
        }
        UserAccountRole other = (UserAccountRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.UserAccountRole[ id=" + id + " ]";
    }

}
