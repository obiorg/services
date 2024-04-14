/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.tags;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "tags_lists_types", catalog = "OBI", schema = "dbo", uniqueConstraints = {
//    @UniqueConstraint(columnNames = {"id"})})
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TagsListsTypes.findAll", query = "SELECT t FROM TagsListsTypes t"),
//    @NamedQuery(name = "TagsListsTypes.findById", query = "SELECT t FROM TagsListsTypes t WHERE t.id = :id"),
//    @NamedQuery(name = "TagsListsTypes.findByDeleted", query = "SELECT t FROM TagsListsTypes t WHERE t.deleted = :deleted"),
//    @NamedQuery(name = "TagsListsTypes.findByCreated", query = "SELECT t FROM TagsListsTypes t WHERE t.created = :created"),
//    @NamedQuery(name = "TagsListsTypes.findByChanged", query = "SELECT t FROM TagsListsTypes t WHERE t.changed = :changed"),
//    @NamedQuery(name = "TagsListsTypes.findByDesignation", query = "SELECT t FROM TagsListsTypes t WHERE t.designation = :designation"),
//    @NamedQuery(name = "TagsListsTypes.findByComment", query = "SELECT t FROM TagsListsTypes t WHERE t.comment = :comment")})
public class TagsListsTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean deleted;

    private Date created;

    private Date changed;

    private String designation;

    private String comment;

    private Collection<TagsLists> tagsListsCollection;

    public TagsListsTypes() {
    }

    public TagsListsTypes(Integer id) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Collection<TagsLists> getTagsListsCollection() {
        return tagsListsCollection;
    }

    public void setTagsListsCollection(Collection<TagsLists> tagsListsCollection) {
        this.tagsListsCollection = tagsListsCollection;
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
        if (!(object instanceof TagsListsTypes)) {
            return false;
        }
        TagsListsTypes other = (TagsListsTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.designation + " [" + id + "]";
    }

    /**
     * Allow to affect result object
     *
     * @param rs a set of data of the row
     * @throws SQLException exception
     */
    public void update(ResultSet rs, Boolean easy) throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();
        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
            String c = rsMetaData.getColumnName(i);

            if (c.matches("id")) {
                this.id = rs.getInt(c);
            } else if (c.matches("deleted")) {
                this.deleted = rs.getBoolean(c);
            } else if (c.matches("created")) {
                this.created = rs.getDate(c);
            } else if (c.matches("changed")) {
                this.changed = rs.getDate(c);
            } else if (c.matches("designation")) {
                this.designation = rs.getString(c);
            }/**
             *
             * informations
             */
            else if (c.matches("comment")) {
                this.comment = rs.getString(c);
            } else {
                Util.out(TagsListsTypes.class + " >> update >> unknown column name " + c);
                System.out.println(TagsListsTypes.class + " >> update >> unknown column name " + c);
            }

        }
    }

}
