/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.persistence;

import org.obi.services.entities.measures.MeasLimitsGroups;
import org.obi.services.entities.measures.MeasComparators;
import org.obi.services.entities.business.Companies;
import java.io.Serializable;
import java.util.Date;
import org.obi.services.entities.tags.Tags;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "pers_standard_limits", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "PersStandardLimits.findAll", query = "SELECT p FROM PersStandardLimits p"),
//    @NamedQuery(name = "PersStandardLimits.findById", query = "SELECT p FROM PersStandardLimits p WHERE p.id = :id"),
//    @NamedQuery(name = "PersStandardLimits.findByDeleted", query = "SELECT p FROM PersStandardLimits p WHERE p.deleted = :deleted"),
//    @NamedQuery(name = "PersStandardLimits.findByCreated", query = "SELECT p FROM PersStandardLimits p WHERE p.created = :created"),
//    @NamedQuery(name = "PersStandardLimits.findByChanged", query = "SELECT p FROM PersStandardLimits p WHERE p.changed = :changed"),
//    @NamedQuery(name = "PersStandardLimits.findByName", query = "SELECT p FROM PersStandardLimits p WHERE p.name = :name"),
//    @NamedQuery(name = "PersStandardLimits.findByValue", query = "SELECT p FROM PersStandardLimits p WHERE p.value = :value"),
//    @NamedQuery(name = "PersStandardLimits.findByDelay", query = "SELECT p FROM PersStandardLimits p WHERE p.delay = :delay"),
//    @NamedQuery(name = "PersStandardLimits.findByHysteresis", query = "SELECT p FROM PersStandardLimits p WHERE p.hysteresis = :hysteresis"),
//    @NamedQuery(name = "PersStandardLimits.findBySort", query = "SELECT p FROM PersStandardLimits p WHERE p.sort = :sort"),
//    @NamedQuery(name = "PersStandardLimits.findByHit", query = "SELECT p FROM PersStandardLimits p WHERE p.hit = :hit"),
//    @NamedQuery(name = "PersStandardLimits.findByReached", query = "SELECT p FROM PersStandardLimits p WHERE p.reached = :reached")})
public class PersStandardLimits implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String name;
    private Double value;
    private Integer delay;
    private Double hysteresis;
    private Integer sort;
    private Boolean hit;
    private Boolean reached;
    private Companies company;
    private MeasComparators comparator;
    private MeasLimitsGroups group1;
    private PersStandard persStandard;
    private Tags tag;

    public PersStandardLimits() {
    }

    public PersStandardLimits(Integer id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Double getHysteresis() {
        return hysteresis;
    }

    public void setHysteresis(Double hysteresis) {
        this.hysteresis = hysteresis;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
    }

    public Boolean getReached() {
        return reached;
    }

    public void setReached(Boolean reached) {
        this.reached = reached;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public MeasComparators getComparator() {
        return comparator;
    }

    public void setComparator(MeasComparators comparator) {
        this.comparator = comparator;
    }

    public MeasLimitsGroups getGroup1() {
        return group1;
    }

    public void setGroup1(MeasLimitsGroups group1) {
        this.group1 = group1;
    }

    public PersStandard getPersStandard() {
        return persStandard;
    }

    public void setPersStandard(PersStandard persStandard) {
        this.persStandard = persStandard;
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
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
        if (!(object instanceof PersStandardLimits)) {
            return false;
        }
        PersStandardLimits other = (PersStandardLimits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.PersStandardLimits[ id=" + id + " ]";
    }

}
