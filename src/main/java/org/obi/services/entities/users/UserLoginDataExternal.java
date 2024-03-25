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
//@Table(name = "user_login_data_external", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UserLoginDataExternal.findAll", query = "SELECT u FROM UserLoginDataExternal u"),
//    @NamedQuery(name = "UserLoginDataExternal.findById", query = "SELECT u FROM UserLoginDataExternal u WHERE u.id = :id"),
//    @NamedQuery(name = "UserLoginDataExternal.findByDeleted", query = "SELECT u FROM UserLoginDataExternal u WHERE u.deleted = :deleted"),
//    @NamedQuery(name = "UserLoginDataExternal.findByCreated", query = "SELECT u FROM UserLoginDataExternal u WHERE u.created = :created"),
//    @NamedQuery(name = "UserLoginDataExternal.findByChanged", query = "SELECT u FROM UserLoginDataExternal u WHERE u.changed = :changed"),
//    @NamedQuery(name = "UserLoginDataExternal.findByTokenExternalProvider", query = "SELECT u FROM UserLoginDataExternal u WHERE u.tokenExternalProvider = :tokenExternalProvider")})
public class UserLoginDataExternal implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String tokenExternalProvider;
    private UserExternalProviders externalProvider;

    public UserLoginDataExternal() {
    }

    public UserLoginDataExternal(Integer id) {
        this.id = id;
    }

    public UserLoginDataExternal(Integer id, String tokenExternalProvider) {
        this.id = id;
        this.tokenExternalProvider = tokenExternalProvider;
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

    public String getTokenExternalProvider() {
        return tokenExternalProvider;
    }

    public void setTokenExternalProvider(String tokenExternalProvider) {
        this.tokenExternalProvider = tokenExternalProvider;
    }

    public UserExternalProviders getExternalProvider() {
        return externalProvider;
    }

    public void setExternalProvider(UserExternalProviders externalProvider) {
        this.externalProvider = externalProvider;
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
        if (!(object instanceof UserLoginDataExternal)) {
            return false;
        }
        UserLoginDataExternal other = (UserLoginDataExternal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.UserLoginDataExternal[ id=" + id + " ]";
    }
    
}
