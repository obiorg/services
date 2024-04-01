/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.measures;

import org.obi.services.entities.business.Companies;
import org.obi.services.entities.business.Businesses;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.persistence.PersStandardLimits;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "meas_limits_groups", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MeasLimitsGroups.findAll", query = "SELECT m FROM MeasLimitsGroups m"),
//    @NamedQuery(name = "MeasLimitsGroups.findById", query = "SELECT m FROM MeasLimitsGroups m WHERE m.id = :id"),
//    @NamedQuery(name = "MeasLimitsGroups.findByDeleted", query = "SELECT m FROM MeasLimitsGroups m WHERE m.deleted = :deleted"),
//    @NamedQuery(name = "MeasLimitsGroups.findByCreated", query = "SELECT m FROM MeasLimitsGroups m WHERE m.created = :created"),
//    @NamedQuery(name = "MeasLimitsGroups.findByChanged", query = "SELECT m FROM MeasLimitsGroups m WHERE m.changed = :changed"),
//    @NamedQuery(name = "MeasLimitsGroups.findByGroup", query = "SELECT m FROM MeasLimitsGroups m WHERE m.group = :group"),
//    @NamedQuery(name = "MeasLimitsGroups.findByDesignation", query = "SELECT m FROM MeasLimitsGroups m WHERE m.designation = :designation")})
public class MeasLimitsGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String group;
    private String designation;
    private String description;
    private Collection<MeasLimits> measLimitsCollection;
    private Collection<PersStandardLimits> persStandardLimitsCollection;
    private Businesses business;
    private Companies company;

    public MeasLimitsGroups() {
    }

    public MeasLimitsGroups(Integer id) {
        this.id = id;
    }

    public MeasLimitsGroups(Integer id, String group) {
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<MeasLimits> getMeasLimitsCollection() {
        return measLimitsCollection;
    }

    public void setMeasLimitsCollection(Collection<MeasLimits> measLimitsCollection) {
        this.measLimitsCollection = measLimitsCollection;
    }

    public Collection<PersStandardLimits> getPersStandardLimitsCollection() {
        return persStandardLimitsCollection;
    }

    public void setPersStandardLimitsCollection(Collection<PersStandardLimits> persStandardLimitsCollection) {
        this.persStandardLimitsCollection = persStandardLimitsCollection;
    }

    public Businesses getBusiness() {
        return business;
    }

    public void setBusiness(Businesses business) {
        this.business = business;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
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
        if (!(object instanceof MeasLimitsGroups)) {
            return false;
        }
        MeasLimitsGroups other = (MeasLimitsGroups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.MeasLimitsGroups[ id=" + id + " ]";
    }

}
