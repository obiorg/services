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
//@Table(name = "loc_cities", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "LocCities.findAll", query = "SELECT l FROM LocCities l"),
//    @NamedQuery(name = "LocCities.findById", query = "SELECT l FROM LocCities l WHERE l.id = :id"),
//    @NamedQuery(name = "LocCities.findByName", query = "SELECT l FROM LocCities l WHERE l.name = :name"),
//    @NamedQuery(name = "LocCities.findByStateCode", query = "SELECT l FROM LocCities l WHERE l.stateCode = :stateCode"),
//    @NamedQuery(name = "LocCities.findByCountryCode", query = "SELECT l FROM LocCities l WHERE l.countryCode = :countryCode"),
//    @NamedQuery(name = "LocCities.findByLatitude", query = "SELECT l FROM LocCities l WHERE l.latitude = :latitude"),
//    @NamedQuery(name = "LocCities.findByLongitude", query = "SELECT l FROM LocCities l WHERE l.longitude = :longitude"),
//    @NamedQuery(name = "LocCities.findByCreatedAt", query = "SELECT l FROM LocCities l WHERE l.createdAt = :createdAt"),
//    @NamedQuery(name = "LocCities.findByUpdatedAt", query = "SELECT l FROM LocCities l WHERE l.updatedAt = :updatedAt"),
//    @NamedQuery(name = "LocCities.findByFlag", query = "SELECT l FROM LocCities l WHERE l.flag = :flag"),
//    @NamedQuery(name = "LocCities.findByWikiDataId", query = "SELECT l FROM LocCities l WHERE l.wikiDataId = :wikiDataId")})
public class LocCities implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String stateCode;
    private String countryCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Date createdAt;
    private Date updatedAt;
    private boolean flag;
    private String wikiDataId;
    private LocCountries countryId;
    private LocStates stateId;
    private Collection<Locations> locationsCollection;

    public LocCities() {
    }

    public LocCities(Integer id) {
        this.id = id;
    }

    public LocCities(Integer id, String name, String stateCode, String countryCode, BigDecimal latitude, BigDecimal longitude, Date createdAt, Date updatedAt, boolean flag) {
        this.id = id;
        this.name = name;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
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

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public LocStates getStateId() {
        return stateId;
    }

    public void setStateId(LocStates stateId) {
        this.stateId = stateId;
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
        if (!(object instanceof LocCities)) {
            return false;
        }
        LocCities other = (LocCities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.LocCities[ id=" + id + " ]";
    }

}
