/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.locations;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "loc_states", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "LocStates.findAll", query = "SELECT l FROM LocStates l"),
//    @NamedQuery(name = "LocStates.findById", query = "SELECT l FROM LocStates l WHERE l.id = :id"),
//    @NamedQuery(name = "LocStates.findByName", query = "SELECT l FROM LocStates l WHERE l.name = :name"),
//    @NamedQuery(name = "LocStates.findByCountryCode", query = "SELECT l FROM LocStates l WHERE l.countryCode = :countryCode"),
//    @NamedQuery(name = "LocStates.findByFipsCode", query = "SELECT l FROM LocStates l WHERE l.fipsCode = :fipsCode"),
//    @NamedQuery(name = "LocStates.findByIso2", query = "SELECT l FROM LocStates l WHERE l.iso2 = :iso2"),
//    @NamedQuery(name = "LocStates.findByType", query = "SELECT l FROM LocStates l WHERE l.type = :type"),
//    @NamedQuery(name = "LocStates.findByLatitude", query = "SELECT l FROM LocStates l WHERE l.latitude = :latitude"),
//    @NamedQuery(name = "LocStates.findByLongitude", query = "SELECT l FROM LocStates l WHERE l.longitude = :longitude"),
//    @NamedQuery(name = "LocStates.findByCreatedAt", query = "SELECT l FROM LocStates l WHERE l.createdAt = :createdAt"),
//    @NamedQuery(name = "LocStates.findByUpdatedAt", query = "SELECT l FROM LocStates l WHERE l.updatedAt = :updatedAt"),
//    @NamedQuery(name = "LocStates.findByFlag", query = "SELECT l FROM LocStates l WHERE l.flag = :flag"),
//    @NamedQuery(name = "LocStates.findByWikiDataId", query = "SELECT l FROM LocStates l WHERE l.wikiDataId = :wikiDataId")})
public class LocStates implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String countryCode;
    private String fipsCode;
    private String iso2;
    private String type;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Date createdAt;
    private Date updatedAt;
    private boolean flag;
    private String wikiDataId;
    private LocCountries countryId;
    private Collection<LocCities> locCitiesCollection;
    private Collection<Locations> locationsCollection;

    public LocStates() {
    }

    public LocStates(Integer id) {
        this.id = id;
    }

    public LocStates(Integer id, String name, String countryCode, Date updatedAt, boolean flag) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFipsCode() {
        return fipsCode;
    }

    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
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

    public LocCountries getCountryId() {
        return countryId;
    }

    public void setCountryId(LocCountries countryId) {
        this.countryId = countryId;
    }

    public Collection<LocCities> getLocCitiesCollection() {
        return locCitiesCollection;
    }

    public void setLocCitiesCollection(Collection<LocCities> locCitiesCollection) {
        this.locCitiesCollection = locCitiesCollection;
    }

    public Collection<Locations> getLocationsCollection() {
        return locationsCollection;
    }

    public void setLocationsCollection(Collection<Locations> locationsCollection) {
        this.locationsCollection = locationsCollection;
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
        if (!(object instanceof LocStates)) {
            return false;
        }
        LocStates other = (LocStates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.LocStates[ id=" + id + " ]";
    }

}
