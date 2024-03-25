/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.business;

import org.obi.services.entities.analyses.AnalyseTypes;
import org.obi.services.entities.analyses.AnalyseCategories;
import org.obi.services.entities.analyses.AnalyseMethods;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.locations.Locations;
import org.obi.services.entities.measures.MeasLimits;
import org.obi.services.entities.measures.MeasLimitsGroups;
import org.obi.services.entities.users.UserRolePermissions;
import org.obi.services.entities.users.UserRoles;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Businesses.findAll", query = "SELECT b FROM Businesses b"),
//    @NamedQuery(name = "Businesses.findById", query = "SELECT b FROM Businesses b WHERE b.id = :id"),
//    @NamedQuery(name = "Businesses.findByDeleted", query = "SELECT b FROM Businesses b WHERE b.deleted = :deleted"),
//    @NamedQuery(name = "Businesses.findByCreated", query = "SELECT b FROM Businesses b WHERE b.created = :created"),
//    @NamedQuery(name = "Businesses.findByChanged", query = "SELECT b FROM Businesses b WHERE b.changed = :changed"),
//    @NamedQuery(name = "Businesses.findByBusiness", query = "SELECT b FROM Businesses b WHERE b.business = :business"),
//    @NamedQuery(name = "Businesses.findByDesignation", query = "SELECT b FROM Businesses b WHERE b.designation = :designation"),
//    @NamedQuery(name = "Businesses.findByBuilded", query = "SELECT b FROM Businesses b WHERE b.builded = :builded"),
//    @NamedQuery(name = "Businesses.findByMain", query = "SELECT b FROM Businesses b WHERE b.main = :main"),
//    @NamedQuery(name = "Businesses.findByActivated", query = "SELECT b FROM Businesses b WHERE b.activated = :activated"),
//    @NamedQuery(name = "Businesses.findByLogoPath", query = "SELECT b FROM Businesses b WHERE b.logoPath = :logoPath")})
public class Businesses implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String business;
    private String designation;
    private Integer builded;
    private Boolean main;
    private Boolean activated;
    private String logoPath;
    private Collection<UserRolePermissions> userRolePermissionsCollection;
    private Collection<AnalyseMethods> analyseMethodsCollection;
    private Collection<AnalyseTypes> analyseTypesCollection;
    private Collection<MeasLimits> measLimitsCollection;
    private Collection<UserRoles> userRolesCollection;
    private Collection<Companies> companiesCollection;
    private Collection<MeasLimitsGroups> measLimitsGroupsCollection;
    private Entities entity;
    private Locations location;
    private Collection<AnalyseCategories> analyseCategoriesCollection;

    public Businesses() {
    }

    public Businesses(Integer id) {
        this.id = id;
    }

    public Businesses(Integer id, String business) {
        this.id = id;
        this.business = business;
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

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getBuilded() {
        return builded;
    }

    public void setBuilded(Integer builded) {
        this.builded = builded;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Collection<UserRolePermissions> getUserRolePermissionsCollection() {
        return userRolePermissionsCollection;
    }

    public void setUserRolePermissionsCollection(Collection<UserRolePermissions> userRolePermissionsCollection) {
        this.userRolePermissionsCollection = userRolePermissionsCollection;
    }

    public Collection<AnalyseMethods> getAnalyseMethodsCollection() {
        return analyseMethodsCollection;
    }

    public void setAnalyseMethodsCollection(Collection<AnalyseMethods> analyseMethodsCollection) {
        this.analyseMethodsCollection = analyseMethodsCollection;
    }

    public Collection<AnalyseTypes> getAnalyseTypesCollection() {
        return analyseTypesCollection;
    }

    public void setAnalyseTypesCollection(Collection<AnalyseTypes> analyseTypesCollection) {
        this.analyseTypesCollection = analyseTypesCollection;
    }

    public Collection<MeasLimits> getMeasLimitsCollection() {
        return measLimitsCollection;
    }

    public void setMeasLimitsCollection(Collection<MeasLimits> measLimitsCollection) {
        this.measLimitsCollection = measLimitsCollection;
    }

    public Collection<UserRoles> getUserRolesCollection() {
        return userRolesCollection;
    }

    public void setUserRolesCollection(Collection<UserRoles> userRolesCollection) {
        this.userRolesCollection = userRolesCollection;
    }

    public Collection<Companies> getCompaniesCollection() {
        return companiesCollection;
    }

    public void setCompaniesCollection(Collection<Companies> companiesCollection) {
        this.companiesCollection = companiesCollection;
    }

    public Collection<MeasLimitsGroups> getMeasLimitsGroupsCollection() {
        return measLimitsGroupsCollection;
    }

    public void setMeasLimitsGroupsCollection(Collection<MeasLimitsGroups> measLimitsGroupsCollection) {
        this.measLimitsGroupsCollection = measLimitsGroupsCollection;
    }

    public Entities getEntity() {
        return entity;
    }

    public void setEntity(Entities entity) {
        this.entity = entity;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public Collection<AnalyseCategories> getAnalyseCategoriesCollection() {
        return analyseCategoriesCollection;
    }

    public void setAnalyseCategoriesCollection(Collection<AnalyseCategories> analyseCategoriesCollection) {
        this.analyseCategoriesCollection = analyseCategoriesCollection;
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
        if (!(object instanceof Businesses)) {
            return false;
        }
        Businesses other = (Businesses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.Businesses[ id=" + id + " ]";
    }

}
