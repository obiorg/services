/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.analyses;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.business.Companies;
import org.obi.services.entities.maintenance.Equipements;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "analyse_points", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AnalysePoints.findAll", query = "SELECT a FROM AnalysePoints a"),
//    @NamedQuery(name = "AnalysePoints.findById", query = "SELECT a FROM AnalysePoints a WHERE a.id = :id"),
//    @NamedQuery(name = "AnalysePoints.findByDeleted", query = "SELECT a FROM AnalysePoints a WHERE a.deleted = :deleted"),
//    @NamedQuery(name = "AnalysePoints.findByCreated", query = "SELECT a FROM AnalysePoints a WHERE a.created = :created"),
//    @NamedQuery(name = "AnalysePoints.findByChanged", query = "SELECT a FROM AnalysePoints a WHERE a.changed = :changed"),
//    @NamedQuery(name = "AnalysePoints.findByPoint", query = "SELECT a FROM AnalysePoints a WHERE a.point = :point"),
//    @NamedQuery(name = "AnalysePoints.findByDesignation", query = "SELECT a FROM AnalysePoints a WHERE a.designation = :designation"),
//    @NamedQuery(name = "AnalysePoints.findByAvailable", query = "SELECT a FROM AnalysePoints a WHERE a.available = :available"),
//    @NamedQuery(name = "AnalysePoints.findByPicture", query = "SELECT a FROM AnalysePoints a WHERE a.picture = :picture")})
public class AnalysePoints implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String point;
    private String designation;
    private Boolean available;
    private String picture;
    private String description;
    private Collection<AnalyseAllowed> analyseAllowedCollection;
    private Companies company;
    private Equipements equipement;

    public AnalysePoints() {
    }

    public AnalysePoints(Integer id) {
        this.id = id;
    }

    public AnalysePoints(Integer id, String point) {
        this.id = id;
        this.point = point;
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

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public Equipements getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipements equipement) {
        this.equipement = equipement;
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
        if (!(object instanceof AnalysePoints)) {
            return false;
        }
        AnalysePoints other = (AnalysePoints) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.AnalysePoints[ id=" + id + " ]";
    }
    
}
