/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.machines;

import org.obi.services.entities.business.Companies;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.machines.MachDrivers;
import org.obi.services.entities.tags.Tags;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Machines.findAll", query = "SELECT m FROM Machines m"),
//    @NamedQuery(name = "Machines.findById", query = "SELECT m FROM Machines m WHERE m.id = :id"),
//    @NamedQuery(name = "Machines.findByDeleted", query = "SELECT m FROM Machines m WHERE m.deleted = :deleted"),
//    @NamedQuery(name = "Machines.findByCreated", query = "SELECT m FROM Machines m WHERE m.created = :created"),
//    @NamedQuery(name = "Machines.findByChanged", query = "SELECT m FROM Machines m WHERE m.changed = :changed"),
//    @NamedQuery(name = "Machines.findByAddress", query = "SELECT m FROM Machines m WHERE m.address = :address"),
//    @NamedQuery(name = "Machines.findByMask", query = "SELECT m FROM Machines m WHERE m.mask = :mask"),
//    @NamedQuery(name = "Machines.findByDns", query = "SELECT m FROM Machines m WHERE m.dns = :dns"),
//    @NamedQuery(name = "Machines.findByIpv6", query = "SELECT m FROM Machines m WHERE m.ipv6 = :ipv6"),
//    @NamedQuery(name = "Machines.findByPort", query = "SELECT m FROM Machines m WHERE m.port = :port"),
//    @NamedQuery(name = "Machines.findByName", query = "SELECT m FROM Machines m WHERE m.name = :name"),
//    @NamedQuery(name = "Machines.findByRack", query = "SELECT m FROM Machines m WHERE m.rack = :rack"),
//    @NamedQuery(name = "Machines.findBySlot", query = "SELECT m FROM Machines m WHERE m.slot = :slot"),
//    @NamedQuery(name = "Machines.findByMqqt", query = "SELECT m FROM Machines m WHERE m.mqqt = :mqqt"),
//    @NamedQuery(name = "Machines.findByMqqtUser", query = "SELECT m FROM Machines m WHERE m.mqqtUser = :mqqtUser"),
//    @NamedQuery(name = "Machines.findByMqqtPassword", query = "SELECT m FROM Machines m WHERE m.mqqtPassword = :mqqtPassword"),
//    @NamedQuery(name = "Machines.findByWebhook", query = "SELECT m FROM Machines m WHERE m.webhook = :webhook"),
//    @NamedQuery(name = "Machines.findByWebhookSecret", query = "SELECT m FROM Machines m WHERE m.webhookSecret = :webhookSecret"),
//    @NamedQuery(name = "Machines.findByBus", query = "SELECT m FROM Machines m WHERE m.bus = :bus"),
//    @NamedQuery(name = "Machines.findByDescription", query = "SELECT m FROM Machines m WHERE m.description = :description")})
public class Machines implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String address;
    private String mask;
    private String dns;
    private String ipv6;
    private Integer port;
    private String name;
    private Integer rack;
    private Integer slot;
    private Boolean mqqt;
    private String mqqtUser;
    private String mqqtPassword;
    private Boolean webhook;
    private String webhookSecret;
    private Integer bus;
    private String description;
    private Collection<Tags> tagsCollection;
    private Companies company;
    private MachDrivers driver;

    public Machines() {
    }

    public Machines(Integer id) {
        this.id = id;
    }

    public Machines(Integer id, String address) {
        this.id = id;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRack() {
        return rack;
    }

    public void setRack(Integer rack) {
        this.rack = rack;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Boolean getMqqt() {
        return mqqt;
    }

    public void setMqqt(Boolean mqqt) {
        this.mqqt = mqqt;
    }

    public String getMqqtUser() {
        return mqqtUser;
    }

    public void setMqqtUser(String mqqtUser) {
        this.mqqtUser = mqqtUser;
    }

    public String getMqqtPassword() {
        return mqqtPassword;
    }

    public void setMqqtPassword(String mqqtPassword) {
        this.mqqtPassword = mqqtPassword;
    }

    public Boolean getWebhook() {
        return webhook;
    }

    public void setWebhook(Boolean webhook) {
        this.webhook = webhook;
    }

    public String getWebhookSecret() {
        return webhookSecret;
    }

    public void setWebhookSecret(String webhookSecret) {
        this.webhookSecret = webhookSecret;
    }

    public Integer getBus() {
        return bus;
    }

    public void setBus(Integer bus) {
        this.bus = bus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public MachDrivers getDriver() {
        return driver;
    }

    public void setDriver(MachDrivers driver) {
        this.driver = driver;
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
        if (!(object instanceof Machines)) {
            return false;
        }
        Machines other = (Machines) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.Machines[ id=" + id + " ]";
    }

    
    
    
    
    public void update(ResultSet rs, String c) throws SQLException {

        if (c.matches("id")) {
            this.id = rs.getInt(c);
        } else if (c.matches("address")) {
            this.address = rs.getString(c);
        } else if (c.matches("rack")) {
            this.rack = rs.getInt(c);
        } else if (c.matches("slot")) {
            this.slot = rs.getInt(c);
        } else if (c.matches("type")) {
            this.driver = new MachDrivers();
            this.driver.setDriver(rs.getString(c));
        } else if (c.matches("deleted")) {
            this.deleted = rs.getBoolean(c);
        } else if (c.matches("created")) {
            this.created = rs.getDate(c);
        } else if (c.matches("changed")) {
            this.changed = rs.getDate(c);
        } else {
            Util.out(Machines.class + " >> update >> unknown column name " + c);
            System.out.println(Machines.class + " >> update >> unknown column name " + c);
        }

    }

    public void update(ResultSet rs) throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int count = rsMetaData.getColumnCount();
        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
            String c = rsMetaData.getColumnName(i);

            if (c.matches("id")) {
                this.id = rs.getInt(c);
            } else if (c.matches("address")) {
                this.address = rs.getString(c);
            } else if (c.matches("rack")) {
                this.rack = rs.getInt(c);
            } else if (c.matches("slot")) {
                this.slot = rs.getInt(c);
            } else if (c.matches("type")) {
                this.driver = new MachDrivers();
                this.driver.setDriver(rs.getString(c));
            } else if (c.matches("deleted")) {
                this.deleted = rs.getBoolean(c);
            } else if (c.matches("created")) {
                this.created = rs.getDate(c);
            } else if (c.matches("changed")) {
                this.changed = rs.getDate(c);
            } else {
                Util.out(Machines.class + " >> update >> unknown column name " + c);
                System.out.println(Machines.class + " >> update >> unknown column name " + c);
            }

        }
    }

}
