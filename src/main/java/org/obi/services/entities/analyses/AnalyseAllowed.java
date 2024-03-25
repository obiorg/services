/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.analyses;

import java.io.Serializable;
import java.util.Date;
import org.obi.services.entities.business.Companies;
import org.obi.services.entities.tags.Tags;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "analyse_allowed", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AnalyseAllowed.findAll", query = "SELECT a FROM AnalyseAllowed a"),
//    @NamedQuery(name = "AnalyseAllowed.findById", query = "SELECT a FROM AnalyseAllowed a WHERE a.id = :id"),
//    @NamedQuery(name = "AnalyseAllowed.findByDeleted", query = "SELECT a FROM AnalyseAllowed a WHERE a.deleted = :deleted"),
//    @NamedQuery(name = "AnalyseAllowed.findByCreated", query = "SELECT a FROM AnalyseAllowed a WHERE a.created = :created"),
//    @NamedQuery(name = "AnalyseAllowed.findByChanged", query = "SELECT a FROM AnalyseAllowed a WHERE a.changed = :changed"),
//    @NamedQuery(name = "AnalyseAllowed.findByDesignation", query = "SELECT a FROM AnalyseAllowed a WHERE a.designation = :designation"),
//    @NamedQuery(name = "AnalyseAllowed.findByEnable", query = "SELECT a FROM AnalyseAllowed a WHERE a.enable = :enable")})
public class AnalyseAllowed implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String designation;
    private Boolean enable;
    private String description;
    private AnalysePoints point;
    private AnalyseTypes type;
    private Companies company;
    private Tags tag;

    public AnalyseAllowed() {
    }

    public AnalyseAllowed(Integer id) {
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AnalysePoints getPoint() {
        return point;
    }

    public void setPoint(AnalysePoints point) {
        this.point = point;
    }

    public AnalyseTypes getType() {
        return type;
    }

    public void setType(AnalyseTypes type) {
        this.type = type;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
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
        if (!(object instanceof AnalyseAllowed)) {
            return false;
        }
        AnalyseAllowed other = (AnalyseAllowed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.AnalyseAllowed[ id=" + id + " ]";
    }
    
}
