/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.business;

import org.obi.services.entities.analyses.AnalyseTypes;
import org.obi.services.entities.analyses.AnalyseCategories;
import org.obi.services.entities.analyses.AnalyseMethods;
import org.obi.services.entities.analyses.AnalysePoints;
import org.obi.services.entities.analyses.AnalyseAllowed;
import org.obi.services.entities.alarms.AlarmRender;
import org.obi.services.entities.alarms.AlarmGroups;
import org.obi.services.entities.alarms.AlarmClasses;
import org.obi.services.entities.alarms.Alarms;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.maintenance.Equipements;
import org.obi.services.entities.maintenance.EquipementsDataExternal;
import org.obi.services.entities.maintenance.EquipementsExternalProviders;
import org.obi.services.entities.locations.Locations;
import org.obi.services.entities.machines.Machines;
import org.obi.services.entities.measures.MeasLimits;
import org.obi.services.entities.measures.MeasLimitsGroups;
import org.obi.services.entities.persistence.PersStandard;
import org.obi.services.entities.persistence.PersStandardLimits;
import org.obi.services.entities.persistence.Persistence;
import org.obi.services.entities.tags.Tags;
import org.obi.services.entities.tags.TagsTables;
import org.obi.services.entities.users.UserRolePermissions;
import org.obi.services.entities.users.UserRoles;
import org.obi.services.sessions.business.BusinessesFacade;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Companies.findAll", query = "SELECT c FROM Companies c"),
//    @NamedQuery(name = "Companies.findById", query = "SELECT c FROM Companies c WHERE c.id = :id"),
//    @NamedQuery(name = "Companies.findByDeleted", query = "SELECT c FROM Companies c WHERE c.deleted = :deleted"),
//    @NamedQuery(name = "Companies.findByCreated", query = "SELECT c FROM Companies c WHERE c.created = :created"),
//    @NamedQuery(name = "Companies.findByChanged", query = "SELECT c FROM Companies c WHERE c.changed = :changed"),
//    @NamedQuery(name = "Companies.findByCompany", query = "SELECT c FROM Companies c WHERE c.company = :company"),
//    @NamedQuery(name = "Companies.findByDesignation", query = "SELECT c FROM Companies c WHERE c.designation = :designation"),
//    @NamedQuery(name = "Companies.findByBuilded", query = "SELECT c FROM Companies c WHERE c.builded = :builded"),
//    @NamedQuery(name = "Companies.findByMain", query = "SELECT c FROM Companies c WHERE c.main = :main"),
//    @NamedQuery(name = "Companies.findByActivated", query = "SELECT c FROM Companies c WHERE c.activated = :activated"),
//    @NamedQuery(name = "Companies.findByLogoPath", query = "SELECT c FROM Companies c WHERE c.logoPath = :logoPath")})
public class Companies implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String company;
    private String designation;
    private Integer builded;
    private Boolean main;
    private Boolean activated;
    private String logoPath;
    private Collection<EquipementsExternalProviders> equipementsExternalProvidersCollection;
    private Collection<TagsTables> tagsTablesCollection;
    private Collection<UserRolePermissions> userRolePermissionsCollection;
    private Collection<AnalyseAllowed> analyseAllowedCollection;
    private Collection<PersStandard> persStandardCollection;
    private Collection<AnalyseMethods> analyseMethodsCollection;
    private Collection<AnalyseTypes> analyseTypesCollection;
    private Collection<MeasLimits> measLimitsCollection;
    private Collection<Equipements> equipementsCollection;
    private Collection<UserRoles> userRolesCollection;
    private Businesses business;
    private Locations location;
    private Collection<PersStandardLimits> persStandardLimitsCollection;
    private Collection<MeasLimitsGroups> measLimitsGroupsCollection;
    private Collection<AlarmGroups> alarmGroupsCollection;
    private Collection<EquipementsDataExternal> equipementsDataExternalCollection;
    private Collection<Alarms> alarmsCollection;
    private Collection<AnalysePoints> analysePointsCollection;
    private Collection<AnalyseCategories> analyseCategoriesCollection;
    private Collection<Tags> tagsCollection;
    private Collection<AlarmRender> alarmRenderCollection;
    private Collection<Persistence> persistenceCollection;
    private Collection<Machines> machinesCollection;
    private Collection<AlarmClasses> alarmClassesCollection;

    public Companies() {
    }

    public Companies(Integer id) {
        this.id = id;
    }

    public Companies(Integer id, String company) {
        this.id = id;
        this.company = company;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getBuilded() {
        return builded;
    }

    public void setBuilded(Integer builded) {
        this.builded = builded;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Collection<EquipementsExternalProviders> getEquipementsExternalProvidersCollection() {
        return equipementsExternalProvidersCollection;
    }

    public void setEquipementsExternalProvidersCollection(Collection<EquipementsExternalProviders> equipementsExternalProvidersCollection) {
        this.equipementsExternalProvidersCollection = equipementsExternalProvidersCollection;
    }

    public Collection<TagsTables> getTagsTablesCollection() {
        return tagsTablesCollection;
    }

    public void setTagsTablesCollection(Collection<TagsTables> tagsTablesCollection) {
        this.tagsTablesCollection = tagsTablesCollection;
    }

    public Collection<UserRolePermissions> getUserRolePermissionsCollection() {
        return userRolePermissionsCollection;
    }

    public void setUserRolePermissionsCollection(Collection<UserRolePermissions> userRolePermissionsCollection) {
        this.userRolePermissionsCollection = userRolePermissionsCollection;
    }

    public Collection<AnalyseAllowed> getAnalyseAllowedCollection() {
        return analyseAllowedCollection;
    }

    public void setAnalyseAllowedCollection(Collection<AnalyseAllowed> analyseAllowedCollection) {
        this.analyseAllowedCollection = analyseAllowedCollection;
    }

    public Collection<PersStandard> getPersStandardCollection() {
        return persStandardCollection;
    }

    public void setPersStandardCollection(Collection<PersStandard> persStandardCollection) {
        this.persStandardCollection = persStandardCollection;
    }

    public Collection<AnalyseMethods> getAnalyseMethodsCollection() {
        return analyseMethodsCollection;
    }

    public void setAnalyseMethodsCollection(Collection<AnalyseMethods> analyseMethodsCollection) {
        this.analyseMethodsCollection = analyseMethodsCollection;
    }

    public Collection<AnalyseTypes> getAnalyseTypesCollection() {
        return analyseTypesCollection;
    }

    public void setAnalyseTypesCollection(Collection<AnalyseTypes> analyseTypesCollection) {
        this.analyseTypesCollection = analyseTypesCollection;
    }

    public Collection<MeasLimits> getMeasLimitsCollection() {
        return measLimitsCollection;
    }

    public void setMeasLimitsCollection(Collection<MeasLimits> measLimitsCollection) {
        this.measLimitsCollection = measLimitsCollection;
    }

    public Collection<Equipements> getEquipementsCollection() {
        return equipementsCollection;
    }

    public void setEquipementsCollection(Collection<Equipements> equipementsCollection) {
        this.equipementsCollection = equipementsCollection;
    }

    public Collection<UserRoles> getUserRolesCollection() {
        return userRolesCollection;
    }

    public void setUserRolesCollection(Collection<UserRoles> userRolesCollection) {
        this.userRolesCollection = userRolesCollection;
    }

    public Businesses getBusiness() {
        return business;
    }

    public void setBusiness(Businesses business) {
        this.business = business;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public Collection<PersStandardLimits> getPersStandardLimitsCollection() {
        return persStandardLimitsCollection;
    }

    public void setPersStandardLimitsCollection(Collection<PersStandardLimits> persStandardLimitsCollection) {
        this.persStandardLimitsCollection = persStandardLimitsCollection;
    }

    public Collection<MeasLimitsGroups> getMeasLimitsGroupsCollection() {
        return measLimitsGroupsCollection;
    }

    public void setMeasLimitsGroupsCollection(Collection<MeasLimitsGroups> measLimitsGroupsCollection) {
        this.measLimitsGroupsCollection = measLimitsGroupsCollection;
    }

    public Collection<AlarmGroups> getAlarmGroupsCollection() {
        return alarmGroupsCollection;
    }

    public void setAlarmGroupsCollection(Collection<AlarmGroups> alarmGroupsCollection) {
        this.alarmGroupsCollection = alarmGroupsCollection;
    }

    public Collection<EquipementsDataExternal> getEquipementsDataExternalCollection() {
        return equipementsDataExternalCollection;
    }

    public void setEquipementsDataExternalCollection(Collection<EquipementsDataExternal> equipementsDataExternalCollection) {
        this.equipementsDataExternalCollection = equipementsDataExternalCollection;
    }

    public Collection<Alarms> getAlarmsCollection() {
        return alarmsCollection;
    }

    public void setAlarmsCollection(Collection<Alarms> alarmsCollection) {
        this.alarmsCollection = alarmsCollection;
    }

    public Collection<AnalysePoints> getAnalysePointsCollection() {
        return analysePointsCollection;
    }

    public void setAnalysePointsCollection(Collection<AnalysePoints> analysePointsCollection) {
        this.analysePointsCollection = analysePointsCollection;
    }

    public Collection<AnalyseCategories> getAnalyseCategoriesCollection() {
        return analyseCategoriesCollection;
    }

    public void setAnalyseCategoriesCollection(Collection<AnalyseCategories> analyseCategoriesCollection) {
        this.analyseCategoriesCollection = analyseCategoriesCollection;
    }

    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }

    public Collection<AlarmRender> getAlarmRenderCollection() {
        return alarmRenderCollection;
    }

    public void setAlarmRenderCollection(Collection<AlarmRender> alarmRenderCollection) {
        this.alarmRenderCollection = alarmRenderCollection;
    }

    public Collection<Persistence> getPersistenceCollection() {
        return persistenceCollection;
    }

    public void setPersistenceCollection(Collection<Persistence> persistenceCollection) {
        this.persistenceCollection = persistenceCollection;
    }

    public Collection<Machines> getMachinesCollection() {
        return machinesCollection;
    }

    public void setMachinesCollection(Collection<Machines> machinesCollection) {
        this.machinesCollection = machinesCollection;
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
        if (!(object instanceof Companies)) {
            return false;
        }
        
        Companies other = (Companies) object;
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
        if (!other.company.matches(company)) {
            return false;
        }
        if (!other.designation.matches(designation)) {
            return false;
        }
        
        
        return true;
    }

    @Override
    public String toString() {
//        return "org.obi.services.entities.Companies[ id=" + id + " ]";
        return "" + this.company + " - " + this.designation + " (" + this.business + ") [ id=" + id + " ]";
    }

    /**
     * Update the entity with result provide by result set
     *
     * All field need to be check in order to set the value
     *
     * @param rs corresponding to request of field
     * @throws SQLException if SQL error appear on result set use
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
                this.company = rs.getString(c);
            } else if (c.matches("designation")) {
                this.designation = rs.getString(c);
            } else if (c.matches("builded")) {
                this.builded = rs.getInt(c);
            } else if (c.matches("main")) {
                this.main = rs.getBoolean(c);
            } else if (c.matches("activated")) {
                this.activated = rs.getBoolean(c);
            } else if (c.matches("logoPath")) {
                this.logoPath = rs.getString(c);
            } /**
             * Need to get entity
             */
            else if (c.matches("location")) {
                //this.location = rs.getInt(c);
//                System.out.println("!!!! Companies entity no recovery location yet only id!!!");
                int val = rs.getInt(c);
                if (val == 0) {
                    this.location = null;
                } else {
                    this.location = new Locations(rs.getInt(c));
                }
            } /**
             * end entity GET
             */
            /**
             * Need to get entity
             */
            else if (c.matches("business")) {
                BusinessesFacade ttf = new BusinessesFacade();
                this.business = ttf.findById(rs.getInt(c));
            } /**
             * end entity GET
             */
            else {
                Util.out(Companies.class + " >> update >> unknown column name " + c);
                System.out.println(Companies.class + " >> update >> unknown column name " + c);
            }
        }
    }
}
