/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.tags;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "tags_types", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TagsTypes.findAll", query = "SELECT t FROM TagsTypes t"),
//    @NamedQuery(name = "TagsTypes.findById", query = "SELECT t FROM TagsTypes t WHERE t.id = :id"),
//    @NamedQuery(name = "TagsTypes.findByDeleted", query = "SELECT t FROM TagsTypes t WHERE t.deleted = :deleted"),
//    @NamedQuery(name = "TagsTypes.findByCreated", query = "SELECT t FROM TagsTypes t WHERE t.created = :created"),
//    @NamedQuery(name = "TagsTypes.findByChanged", query = "SELECT t FROM TagsTypes t WHERE t.changed = :changed"),
//    @NamedQuery(name = "TagsTypes.findByType", query = "SELECT t FROM TagsTypes t WHERE t.type = :type"),
//    @NamedQuery(name = "TagsTypes.findByDesignation", query = "SELECT t FROM TagsTypes t WHERE t.designation = :designation"),
//    @NamedQuery(name = "TagsTypes.findByBit", query = "SELECT t FROM TagsTypes t WHERE t.bit = :bit"),
//    @NamedQuery(name = "TagsTypes.findByByte1", query = "SELECT t FROM TagsTypes t WHERE t.byte1 = :byte1"),
//    @NamedQuery(name = "TagsTypes.findByWord", query = "SELECT t FROM TagsTypes t WHERE t.word = :word"),
//    @NamedQuery(name = "TagsTypes.findByGroup", query = "SELECT t FROM TagsTypes t WHERE t.group = :group")})
public class TagsTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String type;
    private String designation;
    private Integer bit;
    private Integer byte1;
    private Integer word;
    private String group;
    private Collection<Tags> tagsCollection;

    public TagsTypes() {
    }

    public TagsTypes(Integer id) {
        this.id = id;
    }

    public TagsTypes(Integer id, String type) {
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

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public Integer getByte1() {
        return byte1;
    }

    public void setByte1(Integer byte1) {
        this.byte1 = byte1;
    }

    public Integer getWord() {
        return word;
    }

    public void setWord(Integer word) {
        this.word = word;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
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
        if (!(object instanceof TagsTypes)) {
            return false;
        }
        TagsTypes other = (TagsTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.TagsTypes[ id=" + id + " ]";
    }

}
