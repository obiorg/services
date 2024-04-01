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
//@Table(name = "analyse_methods", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AnalyseMethods.findAll", query = "SELECT a FROM AnalyseMethods a"),
//    @NamedQuery(name = "AnalyseMethods.findById", query = "SELECT a FROM AnalyseMethods a WHERE a.id = :id"),
//    @NamedQuery(name = "AnalyseMethods.findByDeleted", query = "SELECT a FROM AnalyseMethods a WHERE a.deleted = :deleted"),
//    @NamedQuery(name = "AnalyseMethods.findByCreated", query = "SELECT a FROM AnalyseMethods a WHERE a.created = :created"),
//    @NamedQuery(name = "AnalyseMethods.findByChanged", query = "SELECT a FROM AnalyseMethods a WHERE a.changed = :changed"),
//    @NamedQuery(name = "AnalyseMethods.findByMethod", query = "SELECT a FROM AnalyseMethods a WHERE a.method = :method"),
//    @NamedQuery(name = "AnalyseMethods.findByDesignation", query = "SELECT a FROM AnalyseMethods a WHERE a.designation = :designation"),
//    @NamedQuery(name = "AnalyseMethods.findByFilepath", query = "SELECT a FROM AnalyseMethods a WHERE a.filepath = :filepath")})
public class AnalyseMethods implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String method;
    private String designation;
    private String description;
    private String filepath;
    private Businesses business;
    private Companies company;
    private Collection<AnalyseTypes> analyseTypesCollection;

    public AnalyseMethods() {
    }

    public AnalyseMethods(Integer id) {
        this.id = id;
    }

    public AnalyseMethods(Integer id, String method) {
        this.id = id;
        this.method = method;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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

    public Collection<AnalyseTypes> getAnalyseTypesCollection() {
        return analyseTypesCollection;
    }

    public void setAnalyseTypesCollection(Collection<AnalyseTypes> analyseTypesCollection) {
        this.analyseTypesCollection = analyseTypesCollection;
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
        if (!(object instanceof AnalyseMethods)) {
            return false;
        }
        AnalyseMethods other = (AnalyseMethods) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.AnalyseMethods[ id=" + id + " ]";
    }
    
}
