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
import org.obi.services.sessions.tags.TagsListsTypesFacade;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "tags_lists", catalog = "OBI", schema = "dbo", uniqueConstraints = {
//    @UniqueConstraint(columnNames = {"company", "list"}),
//    @UniqueConstraint(columnNames = {"id"})})
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TagsLists.findAll", query = "SELECT t FROM TagsLists t"),
//    @NamedQuery(name = "TagsLists.findById", query = "SELECT t FROM TagsLists t WHERE t.id = :id"),
//    @NamedQuery(name = "TagsLists.findByDeleted", query = "SELECT t FROM TagsLists t WHERE t.deleted = :deleted"),
//    @NamedQuery(name = "TagsLists.findByCreated", query = "SELECT t FROM TagsLists t WHERE t.created = :created"),
//    @NamedQuery(name = "TagsLists.findByChanged", query = "SELECT t FROM TagsLists t WHERE t.changed = :changed"),
//    @NamedQuery(name = "TagsLists.findByList", query = "SELECT t FROM TagsLists t WHERE t.list = :list"),
//    @NamedQuery(name = "TagsLists.findByDesignation", query = "SELECT t FROM TagsLists t WHERE t.designation = :designation"),
//    @NamedQuery(name = "TagsLists.findByComment", query = "SELECT t FROM TagsLists t WHERE t.comment = :comment")})
public class TagsLists implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean deleted;

    private Date created;

    private Date changed;

    private String list;
    private String designation;
    private String comment;
    private Companies company;
    private TagsListsTypes type;
    private Collection<TagsListsContent> tagsListsContentCollection;
    private Collection<Tags> tagsCollection;

    public TagsLists() {
    }

    public TagsLists(Integer id) {
        this.id = id;
    }

    public TagsLists(Integer id, String list) {
        this.id = id;
        this.list = list;
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

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
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

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public TagsListsTypes getType() {
        return type;
    }

    public void setType(TagsListsTypes type) {
        this.type = type;
    }

    public Collection<TagsListsContent> getTagsListsContentCollection() {
        return tagsListsContentCollection;
    }

    public void setTagsListsContentCollection(Collection<TagsListsContent> tagsListsContentCollection) {
        this.tagsListsContentCollection = tagsListsContentCollection;
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
        if (!(object instanceof TagsLists)) {
            return false;
        }
        TagsLists other = (TagsLists) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.list + " - " + this.designation + " [" + id + "]";
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
            } else if (c.matches("company")) {
                if (easy) {
                    company = new Companies(rs.getInt(c));
                } else {
                    CompaniesFacade cf = new CompaniesFacade();
                    company = cf.findById(rs.getInt(c));
                }
            } else if (c.matches("type")) {
                if (easy) {
                    type = new TagsListsTypes(rs.getInt(c));
                } else {
                    TagsListsTypesFacade facade = TagsListsTypesFacade.getInstance();
                    type = facade.findById(rs.getInt(c));
                }
            } else if (c.matches("list")) {
                this.list = rs.getString(c);
            } else if (c.matches("designation")) {
                this.designation = rs.getString(c);
            }/**
             *
             * informations
             */
            else if (c.matches("comment")) {
                this.comment = rs.getString(c);
            } else {
                Util.out(TagsMemories.class + " >> update >> unknown column name " + c);
                System.out.println(TagsMemories.class + " >> update >> unknown column name " + c);
            }

        }
    }
}
