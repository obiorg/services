/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.tags;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import org.obi.services.entities.business.Companies;
import org.obi.services.sessions.business.CompaniesFacade;
import org.obi.services.sessions.tags.TagsListsFacade;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "tags_lists_content", catalog = "OBI", schema = "dbo", uniqueConstraints = {
//    @UniqueConstraint(columnNames = {"company", "list", "content"}),
//    @UniqueConstraint(columnNames = {"id"})})
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TagsListsContent.findAll", query = "SELECT t FROM TagsListsContent t"),
//    @NamedQuery(name = "TagsListsContent.findById", query = "SELECT t FROM TagsListsContent t WHERE t.id = :id"),
//    @NamedQuery(name = "TagsListsContent.findByDeleted", query = "SELECT t FROM TagsListsContent t WHERE t.deleted = :deleted"),
//    @NamedQuery(name = "TagsListsContent.findByCreated", query = "SELECT t FROM TagsListsContent t WHERE t.created = :created"),
//    @NamedQuery(name = "TagsListsContent.findByChanged", query = "SELECT t FROM TagsListsContent t WHERE t.changed = :changed"),
//    @NamedQuery(name = "TagsListsContent.findByContent", query = "SELECT t FROM TagsListsContent t WHERE t.content = :content"),
//    @NamedQuery(name = "TagsListsContent.findByValue", query = "SELECT t FROM TagsListsContent t WHERE t.value = :value"),
//    @NamedQuery(name = "TagsListsContent.findByDefault1", query = "SELECT t FROM TagsListsContent t WHERE t.default1 = :default1"),
//    @NamedQuery(name = "TagsListsContent.findByWidth", query = "SELECT t FROM TagsListsContent t WHERE t.width = :width"),
//    @NamedQuery(name = "TagsListsContent.findByHeight", query = "SELECT t FROM TagsListsContent t WHERE t.height = :height"),
//    @NamedQuery(name = "TagsListsContent.findByComment", query = "SELECT t FROM TagsListsContent t WHERE t.comment = :comment")})
public class TagsListsContent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean deleted;

    private Date created;

    private Date changed;
    private int content;
    private String value;
    private Boolean default1;
    private Double width;
    private Double height;
    private String comment;
    private Companies company;
    private TagsLists list;

    public TagsListsContent() {
    }

    public TagsListsContent(Integer id) {
        this.id = id;
    }

    public TagsListsContent(Integer id, int content) {
        this.id = id;
        this.content = content;
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

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getDefault1() {
        return default1;
    }

    public void setDefault1(Boolean default1) {
        this.default1 = default1;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
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

    public TagsLists getList() {
        return list;
    }

    public void setList(TagsLists list) {
        this.list = list;
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
        if (!(object instanceof TagsListsContent)) {
            return false;
        }
        TagsListsContent other = (TagsListsContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.obi.web.jsf.std.entities.tmps.TagsListsContent[ id=" + id + " ]";
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
                    CompaniesFacade cf = CompaniesFacade.getInstance();
                    company = cf.findById(rs.getInt(c));
                }
            } else if (c.matches("list")) {
                if (easy) {
                    list = new TagsLists(rs.getInt(c));
                } else {
                    TagsListsFacade facade = TagsListsFacade.getInstance();
                    list = facade.findById(rs.getInt(c));
                }
            } else if (c.matches("content")) {
                this.content = rs.getInt(c);
            } else if (c.matches("value")) {
                this.value = rs.getString(c);
            } else if (c.matches("default")) {
                this.default1 = rs.getBoolean(c);
            } else if (c.matches("width")) {
                this.width = rs.getDouble(c);
            } else if (c.matches("width")) {
                this.height = rs.getDouble(c);
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
