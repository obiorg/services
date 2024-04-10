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
import org.obi.services.entities.business.Companies;
import org.obi.services.sessions.business.CompaniesFacade;
import org.obi.services.util.Util;

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
//        return "org.obi.services.entities.TagsTypes[ id=" + id + " ]";
        return "" + this.type + " - " + this.designation
                + " - " + this.group + " (" + this.bit + "; " + this.byte1 + "; " + this.getWord() + ") "
                + " [ id=" + id + " ]";

    }

    /**
     * Allow to affect result object
     *
     * @param rs a set of data of the row
     * @throws SQLException exception
     */
    public void update(ResultSet rs) throws SQLException {
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
            } else if (c.matches("type")) {
                this.type = rs.getString(c);
            } else if (c.matches("designation")) {
                this.designation = rs.getString(c);
            } else if (c.matches("bit")) {
                this.bit = rs.getInt(c);
            } else if (c.matches("byte")) {
                this.byte1 = rs.getInt(c);
            } else if (c.matches("word")) {
                this.word = rs.getInt(c);
            } else if (c.matches("group")) {
                this.group = rs.getString(c);
            } /**
             *
             * informations
             */
             else {
                Util.out(TagsTypes.class + " >> update >> unknown column name " + c);
                System.out.println(TagsTypes.class + " >> update >> unknown column name " + c);
            }

        }
    }

}
