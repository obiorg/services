/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.alarms;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.business.Companies;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "alarm_render", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AlarmRender.findAll", query = "SELECT a FROM AlarmRender a"),
//    @NamedQuery(name = "AlarmRender.findById", query = "SELECT a FROM AlarmRender a WHERE a.id = :id"),
//    @NamedQuery(name = "AlarmRender.findByDeleted", query = "SELECT a FROM AlarmRender a WHERE a.deleted = :deleted"),
//    @NamedQuery(name = "AlarmRender.findByCreated", query = "SELECT a FROM AlarmRender a WHERE a.created = :created"),
//    @NamedQuery(name = "AlarmRender.findByChanged", query = "SELECT a FROM AlarmRender a WHERE a.changed = :changed"),
//    @NamedQuery(name = "AlarmRender.findByRender", query = "SELECT a FROM AlarmRender a WHERE a.render = :render"),
//    @NamedQuery(name = "AlarmRender.findByName", query = "SELECT a FROM AlarmRender a WHERE a.name = :name"),
//    @NamedQuery(name = "AlarmRender.findByColor", query = "SELECT a FROM AlarmRender a WHERE a.color = :color"),
//    @NamedQuery(name = "AlarmRender.findByBackground", query = "SELECT a FROM AlarmRender a WHERE a.background = :background"),
//    @NamedQuery(name = "AlarmRender.findByBlink", query = "SELECT a FROM AlarmRender a WHERE a.blink = :blink"),
//    @NamedQuery(name = "AlarmRender.findByColorBlink", query = "SELECT a FROM AlarmRender a WHERE a.colorBlink = :colorBlink"),
//    @NamedQuery(name = "AlarmRender.findByBackgroundBlink", query = "SELECT a FROM AlarmRender a WHERE a.backgroundBlink = :backgroundBlink"),
//    @NamedQuery(name = "AlarmRender.findByComment", query = "SELECT a FROM AlarmRender a WHERE a.comment = :comment")})
public class AlarmRender implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String render;
    private String name;
    private String color;
    private String background;
    private Boolean blink;
    private String colorBlink;
    private String backgroundBlink;
    private String comment;
    private Companies company;
    private Collection<AlarmClasses> alarmClassesCollection;

    public AlarmRender() {
    }

    public AlarmRender(Integer id) {
        this.id = id;
    }

    public AlarmRender(Integer id, String render) {
        this.id = id;
        this.render = render;
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

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Boolean getBlink() {
        return blink;
    }

    public void setBlink(Boolean blink) {
        this.blink = blink;
    }

    public String getColorBlink() {
        return colorBlink;
    }

    public void setColorBlink(String colorBlink) {
        this.colorBlink = colorBlink;
    }

    public String getBackgroundBlink() {
        return backgroundBlink;
    }

    public void setBackgroundBlink(String backgroundBlink) {
        this.backgroundBlink = backgroundBlink;
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


    public Collection<AlarmClasses> getAlarmClassesCollection() {
        return alarmClassesCollection;
    }

    public void setAlarmClassesCollection(Collection<AlarmClasses> alarmClassesCollection) {
        this.alarmClassesCollection = alarmClassesCollection;
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
        if (!(object instanceof AlarmRender)) {
            return false;
        }
        AlarmRender other = (AlarmRender) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.AlarmRender[ id=" + id + " ]";
    }
    
}
