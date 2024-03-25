/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.locations;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "loc_regions", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "LocRegions.findAll", query = "SELECT l FROM LocRegions l"),
//    @NamedQuery(name = "LocRegions.findById", query = "SELECT l FROM LocRegions l WHERE l.id = :id"),
//    @NamedQuery(name = "LocRegions.findByName", query = "SELECT l FROM LocRegions l WHERE l.name = :name"),
//    @NamedQuery(name = "LocRegions.findByCreatedAt", query = "SELECT l FROM LocRegions l WHERE l.createdAt = :createdAt"),
//    @NamedQuery(name = "LocRegions.findByUpdatedAt", query = "SELECT l FROM LocRegions l WHERE l.updatedAt = :updatedAt"),
//    @NamedQuery(name = "LocRegions.findByFlag", query = "SELECT l FROM LocRegions l WHERE l.flag = :flag"),
//    @NamedQuery(name = "LocRegions.findByWikiDataId", query = "SELECT l FROM LocRegions l WHERE l.wikiDataId = :wikiDataId")})
public class LocRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String translations;
    private Date createdAt;
    private Date updatedAt;
    private boolean flag;
    private String wikiDataId;
    private Collection<LocCountries> locCountriesCollection;
    private Collection<LocSubregions> locSubregionsCollection;

    public LocRegions() {
    }

    public LocRegions(Integer id) {
        this.id = id;
    }

    public LocRegions(Integer id, String name, Date updatedAt, boolean flag) {
        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getWikiDataId() {
        return wikiDataId;
    }

    public void setWikiDataId(String wikiDataId) {
        this.wikiDataId = wikiDataId;
    }

    public Collection<LocCountries> getLocCountriesCollection() {
        return locCountriesCollection;
    }

    public void setLocCountriesCollection(Collection<LocCountries> locCountriesCollection) {
        this.locCountriesCollection = locCountriesCollection;
    }

    public Collection<LocSubregions> getLocSubregionsCollection() {
        return locSubregionsCollection;
    }

    public void setLocSubregionsCollection(Collection<LocSubregions> locSubregionsCollection) {
        this.locSubregionsCollection = locSubregionsCollection;
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
        if (!(object instanceof LocRegions)) {
            return false;
        }
        LocRegions other = (LocRegions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.LocRegions[ id=" + id + " ]";
    }

}
