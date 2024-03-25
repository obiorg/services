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

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "analyse_categories", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AnalyseCategories.findAll", query = "SELECT a FROM AnalyseCategories a"),
//    @NamedQuery(name = "AnalyseCategories.findById", query = "SELECT a FROM AnalyseCategories a WHERE a.id = :id"),
//    @NamedQuery(name = "AnalyseCategories.findByDeleted", query = "SELECT a FROM AnalyseCategories a WHERE a.deleted = :deleted"),
//    @NamedQuery(name = "AnalyseCategories.findByCreated", query = "SELECT a FROM AnalyseCategories a WHERE a.created = :created"),
//    @NamedQuery(name = "AnalyseCategories.findByChanged", query = "SELECT a FROM AnalyseCategories a WHERE a.changed = :changed"),
//    @NamedQuery(name = "AnalyseCategories.findByCategory", query = "SELECT a FROM AnalyseCategories a WHERE a.category = :category"),
//    @NamedQuery(name = "AnalyseCategories.findByDesignation", query = "SELECT a FROM AnalyseCategories a WHERE a.designation = :designation")})
public class AnalyseCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String category;
    private String designation;
    private String description;
    private Collection<AnalyseTypes> analyseTypesCollection;
    private Businesses business;
    private Companies company;

    public AnalyseCategories() {
    }

    public AnalyseCategories(Integer id) {
        this.id = id;
    }

    public AnalyseCategories(Integer id, String category) {
        this.id = id;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Collection<AnalyseTypes> getAnalyseTypesCollection() {
        return analyseTypesCollection;
    }

    public void setAnalyseTypesCollection(Collection<AnalyseTypes> analyseTypesCollection) {
        this.analyseTypesCollection = analyseTypesCollection;
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
        if (!(object instanceof AnalyseCategories)) {
            return false;
        }
        AnalyseCategories other = (AnalyseCategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.AnalyseCategories[ id=" + id + " ]";
    }
    
}
