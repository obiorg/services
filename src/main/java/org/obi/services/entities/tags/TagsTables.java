/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.tags;

import org.obi.services.entities.business.Companies;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import org.obi.services.sessions.business.CompaniesFacade;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "tags_tables", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TagsTables.findAll", query = "SELECT t FROM TagsTables t"),
//    @NamedQuery(name = "TagsTables.findById", query = "SELECT t FROM TagsTables t WHERE t.id = :id"),
//    @NamedQuery(name = "TagsTables.findByDeleted", query = "SELECT t FROM TagsTables t WHERE t.deleted = :deleted"),
//    @NamedQuery(name = "TagsTables.findByCreated", query = "SELECT t FROM TagsTables t WHERE t.created = :created"),
//    @NamedQuery(name = "TagsTables.findByChanged", query = "SELECT t FROM TagsTables t WHERE t.changed = :changed"),
//    @NamedQuery(name = "TagsTables.findByTable", query = "SELECT t FROM TagsTables t WHERE t.table = :table"),
//    @NamedQuery(name = "TagsTables.findByDesignation", query = "SELECT t FROM TagsTables t WHERE t.designation = :designation"),
//    @NamedQuery(name = "TagsTables.findByComment", query = "SELECT t FROM TagsTables t WHERE t.comment = :comment")})
public class TagsTables implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String table;
    private String designation;
    private String comment;
    private Companies company;
    private Collection<Tags> tagsCollection;

    public TagsTables() {
    }

    public TagsTables(Integer id) {
        this.id = id;
    }

    public TagsTables(Integer id, String table) {
        this.id = id;
        this.table = table;
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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
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
        if (!(object instanceof TagsTables)) {
            return false;
        }
        TagsTables other = (TagsTables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "org.obi.services.entities.TagsTables[ id=" + id + " ]";
        return "" + this.table + " - " + this.designation + " [ id=" + id + " ]";
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
            } else if (c.matches("company")) {
                CompaniesFacade cf = new CompaniesFacade();
                company = cf.findById(rs.getInt(c));
            } else if (c.matches("table")) {
                this.table = rs.getString(c);
            } else if (c.matches("designation")) {
                this.designation = rs.getString(c);
            }/**
             *
             * informations
             */
            else if (c.matches("comment")) {
                this.comment = rs.getString(c);
            } else {
                Util.out(TagsTables.class + " >> update >> unknown column name " + c);
                System.out.println(TagsTables.class + " >> update >> unknown column name " + c);
            }

        }
    }

}
