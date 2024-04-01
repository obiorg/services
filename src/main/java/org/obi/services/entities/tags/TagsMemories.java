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
//@Table(name = "tags_memories", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TagsMemories.findAll", query = "SELECT t FROM TagsMemories t"),
//    @NamedQuery(name = "TagsMemories.findById", query = "SELECT t FROM TagsMemories t WHERE t.id = :id"),
//    @NamedQuery(name = "TagsMemories.findByDeleted", query = "SELECT t FROM TagsMemories t WHERE t.deleted = :deleted"),
//    @NamedQuery(name = "TagsMemories.findByCreated", query = "SELECT t FROM TagsMemories t WHERE t.created = :created"),
//    @NamedQuery(name = "TagsMemories.findByChanged", query = "SELECT t FROM TagsMemories t WHERE t.changed = :changed"),
//    @NamedQuery(name = "TagsMemories.findByName", query = "SELECT t FROM TagsMemories t WHERE t.name = :name"),
//    @NamedQuery(name = "TagsMemories.findByComment", query = "SELECT t FROM TagsMemories t WHERE t.comment = :comment")})
public class TagsMemories implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String name;
    private String comment;
    private Collection<Tags> tagsCollection;

    public TagsMemories() {
    }

    public TagsMemories(Integer id) {
        this.id = id;
    }

    public TagsMemories(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        if (!(object instanceof TagsMemories)) {
            return false;
        }
        TagsMemories other = (TagsMemories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.TagsMemories[ id=" + id + " ]";
    }

}
