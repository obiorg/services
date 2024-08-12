/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.persistence;

import org.obi.services.entities.business.Companies;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import org.obi.services.entities.tags.Tags;
import org.obi.services.sessions.business.CompaniesFacade;
import org.obi.services.sessions.persistence.PersMethodFacade;
import org.obi.services.sessions.tags.TagsFacade;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Persistence.findAll", query = "SELECT p FROM Persistence p"),
//    @NamedQuery(name = "Persistence.findById", query = "SELECT p FROM Persistence p WHERE p.id = :id"),
//    @NamedQuery(name = "Persistence.findByDeleted", query = "SELECT p FROM Persistence p WHERE p.deleted = :deleted"),
//    @NamedQuery(name = "Persistence.findByCreated", query = "SELECT p FROM Persistence p WHERE p.created = :created"),
//    @NamedQuery(name = "Persistence.findByChanged", query = "SELECT p FROM Persistence p WHERE p.changed = :changed"),
//    @NamedQuery(name = "Persistence.findByActivate", query = "SELECT p FROM Persistence p WHERE p.activate = :activate"),
//    @NamedQuery(name = "Persistence.findByComment", query = "SELECT p FROM Persistence p WHERE p.comment = :comment")})
public class Persistence implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private Boolean activate;
    private String comment;
    private Companies company;
    private PersMethod method;
    private Tags tag;

    public Persistence() {
    }

    public Persistence(Integer id) {
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

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
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

    public PersMethod getMethod() {
        return method;
    }

    public void setMethod(PersMethod method) {
        this.method = method;
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
        if (!(object instanceof Persistence)) {
            return false;
        }
        Persistence other = (Persistence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        
        
        if (object == this) {
            return true;
        }

        if (other.id != this.id) {
            return false;
        }
        if (other.deleted != this.deleted) {
            return false;
        }
        if (!other.created.equals(this.created)) {
            return false;
        }
        if (!other.changed.equals(this.changed)) {
            return false;
        }
        if (!other.company.equals(this.company)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.tag.getName() + "("+ tag.getId() +") " + this.method.getName() + " [" + id + "]";
    }

    /**
     * Allow to affect result objectect
     *
     * @param rs set of data
     * @param easy indicate no class is required
     * @throws SQLException
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
            } else if (c.matches("tag")) {
                if (easy) {
                    tag = new Tags(rs.getInt(c));
                } else {
                    TagsFacade ttf = TagsFacade.getInstance();
                    tag = ttf.findById(rs.getInt(c));
                }
            } else if (c.matches("method")) {
                if (easy) {
                    method = new PersMethod(rs.getInt(c));
                } else {
                    PersMethodFacade ttf = PersMethodFacade.getInstance();
                    method = ttf.findById(rs.getInt(c));
                }
            } /**
             *
             * PARAMETER
             */
            else if (c.matches("activate")) {
                this.activate = rs.getBoolean(c);
            } /**
             *
             * informations
             */
            else if (c.matches("comment")) {
                this.comment = rs.getString(c);
            } else {
                Util.out(getClass().getSimpleName() + " >> update >> unknown column name " + c);
                System.out.println(getClass().getSimpleName() + " >> update >> unknown column name " + c);
            }

        }
    }

}
