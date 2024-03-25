/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.measures;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.persistence.PersStandardLimits;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "meas_comparators", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MeasComparators.findAll", query = "SELECT m FROM MeasComparators m"),
//    @NamedQuery(name = "MeasComparators.findById", query = "SELECT m FROM MeasComparators m WHERE m.id = :id"),
//    @NamedQuery(name = "MeasComparators.findByDeleted", query = "SELECT m FROM MeasComparators m WHERE m.deleted = :deleted"),
//    @NamedQuery(name = "MeasComparators.findByCreated", query = "SELECT m FROM MeasComparators m WHERE m.created = :created"),
//    @NamedQuery(name = "MeasComparators.findByChanged", query = "SELECT m FROM MeasComparators m WHERE m.changed = :changed"),
//    @NamedQuery(name = "MeasComparators.findBySymbol", query = "SELECT m FROM MeasComparators m WHERE m.symbol = :symbol"),
//    @NamedQuery(name = "MeasComparators.findByName", query = "SELECT m FROM MeasComparators m WHERE m.name = :name")})
public class MeasComparators implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String symbol;
    private String name;
    private Collection<MeasLimits> measLimitsCollection;
    private Collection<PersStandardLimits> persStandardLimitsCollection;

    public MeasComparators() {
    }

    public MeasComparators(Integer id) {
        this.id = id;
    }

    public MeasComparators(Integer id, String symbol) {
        this.id = id;
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeasComparators)) {
            return false;
        }
        MeasComparators other = (MeasComparators) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.MeasComparators[ id=" + id + " ]";
    }

}
