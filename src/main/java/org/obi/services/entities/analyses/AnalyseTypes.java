/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.analyses;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.business.Businesses;
import org.obi.services.entities.business.Companies;
import org.obi.services.entities.measures.MeasUnits;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "analyse_types", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AnalyseTypes.findAll", query = "SELECT a FROM AnalyseTypes a"),
//    @NamedQuery(name = "AnalyseTypes.findById", query = "SELECT a FROM AnalyseTypes a WHERE a.id = :id"),
//    @NamedQuery(name = "AnalyseTypes.findByDeleted", query = "SELECT a FROM AnalyseTypes a WHERE a.deleted = :deleted"),
//    @NamedQuery(name = "AnalyseTypes.findByCreated", query = "SELECT a FROM AnalyseTypes a WHERE a.created = :created"),
//    @NamedQuery(name = "AnalyseTypes.findByChanged", query = "SELECT a FROM AnalyseTypes a WHERE a.changed = :changed"),
//    @NamedQuery(name = "AnalyseTypes.findByType", query = "SELECT a FROM AnalyseTypes a WHERE a.type = :type"),
//    @NamedQuery(name = "AnalyseTypes.findByDesignation", query = "SELECT a FROM AnalyseTypes a WHERE a.designation = :designation")})
public class AnalyseTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String type;
    private String designation;
    private String description;
    private Collection<AnalyseAllowed> analyseAllowedCollection;
    private AnalyseCategories category;
    private AnalyseMethods method;
    private Businesses business;
    private Companies company;
    private MeasUnits unit;

    public AnalyseTypes() {
    }

    public AnalyseTypes(Integer id) {
        this.id = id;
    }

    public AnalyseTypes(Integer id, String type) {
        this.id = id;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Collection<AnalyseAllowed> getAnalyseAllowedCollection() {
        return analyseAllowedCollection;
    }

    public void setAnalyseAllowedCollection(Collection<AnalyseAllowed> analyseAllowedCollection) {
        this.analyseAllowedCollection = analyseAllowedCollection;
    }

    public AnalyseCategories getCategory() {
        return category;
    }

    public void setCategory(AnalyseCategories category) {
        this.category = category;
    }

    public AnalyseMethods getMethod() {
        return method;
    }

    public void setMethod(AnalyseMethods method) {
        this.method = method;
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

    public MeasUnits getUnit() {
        return unit;
    }

    public void setUnit(MeasUnits unit) {
        this.unit = unit;
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
        if (!(object instanceof AnalyseTypes)) {
            return false;
        }
        AnalyseTypes other = (AnalyseTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.AnalyseTypes[ id=" + id + " ]";
    }

}
