/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.alarms;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.business.Companies;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "alarm_groups", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AlarmGroups.findAll", query = "SELECT a FROM AlarmGroups a"),
//    @NamedQuery(name = "AlarmGroups.findById", query = "SELECT a FROM AlarmGroups a WHERE a.id = :id"),
//    @NamedQuery(name = "AlarmGroups.findByDeleted", query = "SELECT a FROM AlarmGroups a WHERE a.deleted = :deleted"),
//    @NamedQuery(name = "AlarmGroups.findByCreated", query = "SELECT a FROM AlarmGroups a WHERE a.created = :created"),
//    @NamedQuery(name = "AlarmGroups.findByChanged", query = "SELECT a FROM AlarmGroups a WHERE a.changed = :changed"),
//    @NamedQuery(name = "AlarmGroups.findByGroup", query = "SELECT a FROM AlarmGroups a WHERE a.group = :group"),
//    @NamedQuery(name = "AlarmGroups.findByComment", query = "SELECT a FROM AlarmGroups a WHERE a.comment = :comment")})
public class AlarmGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String group;
    private String comment;
    private Companies company;
    private Collection<Alarms> alarmsCollection;

    public AlarmGroups() {
    }

    public AlarmGroups(Integer id) {
        this.id = id;
    }

    public AlarmGroups(Integer id, String group) {
        this.id = id;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public Collection<Alarms> getAlarmsCollection() {
        return alarmsCollection;
    }

    public void setAlarmsCollection(Collection<Alarms> alarmsCollection) {
        this.alarmsCollection = alarmsCollection;
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
        if (!(object instanceof AlarmGroups)) {
            return false;
        }
        AlarmGroups other = (AlarmGroups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.AlarmGroups[ id=" + id + " ]";
    }
    
}
