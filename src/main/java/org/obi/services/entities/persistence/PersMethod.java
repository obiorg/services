/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "pers_method", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "PersMethod.findAll", query = "SELECT p FROM PersMethod p"),
//    @NamedQuery(name = "PersMethod.findById", query = "SELECT p FROM PersMethod p WHERE p.id = :id"),
//    @NamedQuery(name = "PersMethod.findByDeleted", query = "SELECT p FROM PersMethod p WHERE p.deleted = :deleted"),
//    @NamedQuery(name = "PersMethod.findByCreated", query = "SELECT p FROM PersMethod p WHERE p.created = :created"),
//    @NamedQuery(name = "PersMethod.findByChanged", query = "SELECT p FROM PersMethod p WHERE p.changed = :changed"),
//    @NamedQuery(name = "PersMethod.findByName", query = "SELECT p FROM PersMethod p WHERE p.name = :name"),
//    @NamedQuery(name = "PersMethod.findByComment", query = "SELECT p FROM PersMethod p WHERE p.comment = :comment")})
public class PersMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String name;
    private String comment;
    private Collection<Persistence> persistenceCollection;

    public PersMethod() {
    }

    public PersMethod(Integer id) {
        this.id = id;
    }

    public PersMethod(Integer id, String name) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Collection<Persistence> getPersistenceCollection() {
        return persistenceCollection;
    }

    public void setPersistenceCollection(Collection<Persistence> persistenceCollection) {
        this.persistenceCollection = persistenceCollection;
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
        if (!(object instanceof PersMethod)) {
            return false;
        }
        PersMethod other = (PersMethod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.PersMethod[ id=" + id + " ]";
    }

}
