/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.business.Companies;
import org.obi.services.entities.machines.Machines;
import org.obi.services.entities.measures.MeasUnits;
import org.obi.services.entities.tags.Tags;
import org.obi.services.entities.tags.TagsLists;
import org.obi.services.entities.tags.TagsMemories;
import org.obi.services.entities.tags.TagsTypes;
import org.obi.services.sessions.alarms.AlarmsFacade;
import org.obi.services.sessions.business.CompaniesFacade;
import org.obi.services.sessions.machines.MachinesFacade;
import org.obi.services.sessions.measures.MeasUnitsFacade;
import org.obi.services.sessions.tags.TagsFacade;
import org.obi.services.sessions.tags.TagsListsFacade;
import org.obi.services.sessions.tags.TagsMemoriesFacade;
import org.obi.services.sessions.tags.TagsTypesFacade;
import org.obi.services.util.Util;

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
        return name + " [id=" + id + "]";
    }

    /**
     * Allow to affect result object
     *
     * @param rs set of data
     * @param easy indicate no class is required
     * @throws SQLException
     */
    public void update(ResultSet rs, Boolean easy) throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();

        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
            String c = rsMetaData.getColumnName(i);

            if (c.matches("id")) {
                this.id = rs.getInt(c);
            } else if (c.matches("deleted")) {
                this.deleted = rs.getBoolean(c);
            } else if (c.matches("created")) {
                this.created = rs.getDate(c);
            } else if (c.matches("changed")) {
                this.changed = rs.getDate(c);
            } /**
             *
             * PARAMETER
             */
            else if (c.matches("name")) {
                this.name = rs.getString(c);
            } /**
             *
             * informations
             */
            else if (c.matches("comment")) {
                this.comment = rs.getString(c);
            } else {
                Util.out(getClass().getSimpleName() + " >> update >> unknown column name " + c);
                System.out.println(getClass().getSimpleName() + " >> update >> unknown column name " + c);
            }

        }
    }
}
