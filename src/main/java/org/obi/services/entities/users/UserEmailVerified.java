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
//@Table(name = "user_email_verified", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UserEmailVerified.findAll", query = "SELECT u FROM UserEmailVerified u"),
//    @NamedQuery(name = "UserEmailVerified.findById", query = "SELECT u FROM UserEmailVerified u WHERE u.id = :id"),
//    @NamedQuery(name = "UserEmailVerified.findByDeleted", query = "SELECT u FROM UserEmailVerified u WHERE u.deleted = :deleted"),
//    @NamedQuery(name = "UserEmailVerified.findByCreated", query = "SELECT u FROM UserEmailVerified u WHERE u.created = :created"),
//    @NamedQuery(name = "UserEmailVerified.findByChanged", query = "SELECT u FROM UserEmailVerified u WHERE u.changed = :changed"),
//    @NamedQuery(name = "UserEmailVerified.findByStatusDescription", query = "SELECT u FROM UserEmailVerified u WHERE u.statusDescription = :statusDescription")})
public class UserEmailVerified implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String statusDescription;
    private Collection<UserLoginData> userLoginDataCollection;

    public UserEmailVerified() {
    }

    public UserEmailVerified(Integer id) {
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

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Collection<UserLoginData> getUserLoginDataCollection() {
        return userLoginDataCollection;
    }

    public void setUserLoginDataCollection(Collection<UserLoginData> userLoginDataCollection) {
        this.userLoginDataCollection = userLoginDataCollection;
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
        if (!(object instanceof UserEmailVerified)) {
            return false;
        }
        UserEmailVerified other = (UserEmailVerified) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.UserEmailVerified[ id=" + id + " ]";
    }

}
