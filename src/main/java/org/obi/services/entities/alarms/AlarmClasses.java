/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.alarms;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.machines.Machines;
import org.obi.services.entities.tags.TagsMemories;
import org.obi.services.entities.tags.TagsTables;
import org.obi.services.entities.tags.TagsTypes;
import org.obi.services.entities.business.Companies;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
public class AlarmClasses implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String class1;
    private String name;
    private String comment;
    private Collection<Alarms> alarmsCollection;
    private AlarmRender render;
    private Companies company;

    public AlarmClasses() {
    }

    public AlarmClasses(Integer id) {
        this.id = id;
    }

    public AlarmClasses(Integer id, String class1) {
        this.id = id;
        this.class1 = class1;
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

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
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

    public Collection<Alarms> getAlarmsCollection() {
        return alarmsCollection;
    }

    public void setAlarmsCollection(Collection<Alarms> alarmsCollection) {
        this.alarmsCollection = alarmsCollection;
    }

    public AlarmRender getRender() {
        return render;
    }

    public void setRender(AlarmRender render) {
        this.render = render;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
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
        if (!(object instanceof AlarmClasses)) {
            return false;
        }
        AlarmClasses other = (AlarmClasses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.AlarmClasses[ id=" + id + " ]";
    }

    /**
     * Allow to affect result object
     *
     * @param rs
     * @throws SQLException
     */
    public void update(ResultSet rs) throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int count = rsMetaData.getColumnCount();
        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
            String c = rsMetaData.getColumnName(i);

            if (c.matches("id")) {
                this.id = rs.getInt(c);
            } else if (c.matches("deleted")) {
                this.deleted = rs.getBoolean(c);
            } else if (c.matches("created")) {
                Timestamp timestamp = rs.getTimestamp(c);
                if (timestamp != null) {
                    this.created = new java.util.Date(timestamp.getTime());
                } else {
                    this.created = null;
                }
            } else if (c.matches("changed")) {
                Timestamp timestamp = rs.getTimestamp(c);
                if (timestamp != null) {
                    this.changed = new java.util.Date(timestamp.getTime());
                } else {
                    this.changed = null;
                }
            } else if (c.matches("company")) {
                this.company = new Companies(rs.getInt(c));
            } else if (c.matches("class")) {
                this.class1 = rs.getString(c);
            } else if (c.matches("name")) {
                this.name = rs.getString(c);
            } else if (c.matches("render")) {
                this.render = new AlarmRender(rs.getInt(c));
            } else if (c.matches("comment")) {
                this.comment = rs.getString(c);
            } else {
                Util.out(AlarmClasses.class + " >> update >> unknown field name " + c);
            }

        }
    }

    /**
     * Allow to update one field at a time depend on id
     *
     * @param field string name of field
     * @param value
     * @param id
     * @return
     */
    public static String queryUpdateOn(String field, Object value, Integer id) {
        if (field.matches("id")) {
            return null;
        } else if (field.matches("deleted")) {
            return "UPDATE dbo.tags set [deleted] = " + value + " WHERE t_id = " + id;
        } else if (field.matches("created")) {
            return "UPDATE dbo.tags set [created] = " + value + " WHERE t_id = " + id;
        } else if (field.matches("changed")) {
            return "UPDATE dbo.tags set [changed] = " + value + " WHERE t_id = " + id;
        } else if (field.matches("company")) {
            return "UPDATE dbo.tags set [company] = " + value + " WHERE t_id = " + id;
        } else if (field.matches("class")) {
            return "UPDATE dbo.tags set [class] = " + value + " WHERE t_id = " + id;
        } else if (field.matches("name")) {
            return "UPDATE dbo.tags set [name] = " + value + " WHERE t_id = " + id;
        } else if (field.matches("render")) {
            return "UPDATE dbo.tags set [render] = " + value + " WHERE t_id = " + id;
        } else if (field.matches("comment")) {
            return "UPDATE dbo.tags set [comment] = " + value + " WHERE t_id = " + id;
        } else {
            Util.out(AlarmClasses.class + " >> queryUpdateOn >> unknown field name " + field);
            System.out.println(AlarmClasses.class + " >> queryUpdateOn >> unknown field name " + field);
        }
        return null;
    }

}
