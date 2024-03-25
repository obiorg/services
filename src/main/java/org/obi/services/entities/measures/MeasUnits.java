/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.measures;

import org.obi.services.entities.business.Entities;
import org.obi.services.entities.analyses.AnalyseTypes;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.tags.Tags;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "meas_units", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MeasUnits.findAll", query = "SELECT m FROM MeasUnits m"),
//    @NamedQuery(name = "MeasUnits.findById", query = "SELECT m FROM MeasUnits m WHERE m.id = :id"),
//    @NamedQuery(name = "MeasUnits.findByDeleted", query = "SELECT m FROM MeasUnits m WHERE m.deleted = :deleted"),
//    @NamedQuery(name = "MeasUnits.findByCreated", query = "SELECT m FROM MeasUnits m WHERE m.created = :created"),
//    @NamedQuery(name = "MeasUnits.findByChanged", query = "SELECT m FROM MeasUnits m WHERE m.changed = :changed"),
//    @NamedQuery(name = "MeasUnits.findBySizeName", query = "SELECT m FROM MeasUnits m WHERE m.sizeName = :sizeName"),
//    @NamedQuery(name = "MeasUnits.findBySizeSymbol", query = "SELECT m FROM MeasUnits m WHERE m.sizeSymbol = :sizeSymbol"),
//    @NamedQuery(name = "MeasUnits.findByUnitName", query = "SELECT m FROM MeasUnits m WHERE m.unitName = :unitName"),
//    @NamedQuery(name = "MeasUnits.findByUnitSymbol", query = "SELECT m FROM MeasUnits m WHERE m.unitSymbol = :unitSymbol"),
//    @NamedQuery(name = "MeasUnits.findByDimension", query = "SELECT m FROM MeasUnits m WHERE m.dimension = :dimension"),
//    @NamedQuery(name = "MeasUnits.findByGroup", query = "SELECT m FROM MeasUnits m WHERE m.group = :group"),
//    @NamedQuery(name = "MeasUnits.findByTagging", query = "SELECT m FROM MeasUnits m WHERE m.tagging = :tagging"),
//    @NamedQuery(name = "MeasUnits.findByComment", query = "SELECT m FROM MeasUnits m WHERE m.comment = :comment")})
public class MeasUnits implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String sizeName;
    private String sizeSymbol;
    private String unitName;
    private String unitSymbol;
    private String dimension;
    private String group;
    private String tagging;
    private String comment;
    private Collection<AnalyseTypes> analyseTypesCollection;
    private Collection<Tags> tagsCollection;
    private Entities entity;

    public MeasUnits() {
    }

    public MeasUnits(Integer id) {
        this.id = id;
    }

    public MeasUnits(Integer id, String sizeName, String sizeSymbol, String unitName, String unitSymbol) {
        this.id = id;
        this.sizeName = sizeName;
        this.sizeSymbol = sizeSymbol;
        this.unitName = unitName;
        this.unitSymbol = unitSymbol;
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

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeSymbol() {
        return sizeSymbol;
    }

    public void setSizeSymbol(String sizeSymbol) {
        this.sizeSymbol = sizeSymbol;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTagging() {
        return tagging;
    }

    public void setTagging(String tagging) {
        this.tagging = tagging;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Collection<AnalyseTypes> getAnalyseTypesCollection() {
        return analyseTypesCollection;
    }

    public void setAnalyseTypesCollection(Collection<AnalyseTypes> analyseTypesCollection) {
        this.analyseTypesCollection = analyseTypesCollection;
    }

    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }

    public Entities getEntity() {
        return entity;
    }

    public void setEntity(Entities entity) {
        this.entity = entity;
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
        if (!(object instanceof MeasUnits)) {
            return false;
        }
        MeasUnits other = (MeasUnits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.MeasUnits[ id=" + id + " ]";
    }

}
