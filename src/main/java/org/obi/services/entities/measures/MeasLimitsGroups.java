package org.obi.services.entities.measures;

import org.obi.services.entities.business.Companies;
import org.obi.services.entities.business.Businesses;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.persistence.PersStandardLimits;
import org.obi.services.sessions.business.BusinessesFacade;
import org.obi.services.sessions.business.CompaniesFacade;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "meas_limits_groups", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MeasLimitsGroups.findAll", query = "SELECT m FROM MeasLimitsGroups m"),
//    @NamedQuery(name = "MeasLimitsGroups.findById", query = "SELECT m FROM MeasLimitsGroups m WHERE m.id = :id"),
//    @NamedQuery(name = "MeasLimitsGroups.findByDeleted", query = "SELECT m FROM MeasLimitsGroups m WHERE m.deleted = :deleted"),
//    @NamedQuery(name = "MeasLimitsGroups.findByCreated", query = "SELECT m FROM MeasLimitsGroups m WHERE m.created = :created"),
//    @NamedQuery(name = "MeasLimitsGroups.findByChanged", query = "SELECT m FROM MeasLimitsGroups m WHERE m.changed = :changed"),
//    @NamedQuery(name = "MeasLimitsGroups.findByGroup", query = "SELECT m FROM MeasLimitsGroups m WHERE m.group = :group"),
//    @NamedQuery(name = "MeasLimitsGroups.findByDesignation", query = "SELECT m FROM MeasLimitsGroups m WHERE m.designation = :designation")})
public class MeasLimitsGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String group;
    private String designation;
    private String description;
    private Collection<MeasLimits> measLimitsCollection;
    private Collection<PersStandardLimits> persStandardLimitsCollection;
    private Businesses business;
    private Companies company;

    public MeasLimitsGroups() {
    }

    public MeasLimitsGroups(Integer id) {
        this.id = id;
    }

    public MeasLimitsGroups(Integer id, String group) {
        this.id = id;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<MeasLimits> getMeasLimitsCollection() {
        return measLimitsCollection;
    }

    public void setMeasLimitsCollection(Collection<MeasLimits> measLimitsCollection) {
        this.measLimitsCollection = measLimitsCollection;
    }

    public Collection<PersStandardLimits> getPersStandardLimitsCollection() {
        return persStandardLimitsCollection;
    }

    public void setPersStandardLimitsCollection(Collection<PersStandardLimits> persStandardLimitsCollection) {
        this.persStandardLimitsCollection = persStandardLimitsCollection;
    }

    public Businesses getBusiness() {
        return business;
    }

    public void setBusiness(Businesses business) {
        this.business = business;
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
        if (!(object instanceof MeasLimitsGroups)) {
            return false;
        }
        MeasLimitsGroups other = (MeasLimitsGroups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return group + " - " + designation + " [" + id + "]";
    }

    /**
     * Allow to affect result object
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
            } else if (c.matches("business")) {
                if (easy) {
                    business = new Businesses(rs.getInt(c));
                } else {
                    BusinessesFacade cf = BusinessesFacade.getInstance();
                    business = cf.findById(rs.getInt(c));
                }
            } else if (c.matches("company")) {
                if (easy) {
                    company = new Companies(rs.getInt(c));
                } else {
                    CompaniesFacade cf = CompaniesFacade.getInstance();
                    company = cf.findById(rs.getInt(c));
                }
            } /**
             *
             * PARAMETER
             */
            else if (c.matches("group")) {
                this.group = rs.getString(c);
            } else if (c.matches("designation")) {
                this.designation = rs.getString(c);
            } /**
             *
             * informations
             */
            else if (c.matches("comment")) {
                this.description = rs.getString(c);
            } else {
                Util.out(getClass().getSimpleName() + " >> update >> unknown column name " + c);
                System.out.println(getClass().getSimpleName() + " >> update >> unknown column name " + c);
            }

        }
    }

}
