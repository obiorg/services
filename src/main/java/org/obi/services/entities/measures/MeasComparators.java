package org.obi.services.entities.measures;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.business.Companies;
import org.obi.services.entities.persistence.PersMethod;
import org.obi.services.entities.persistence.PersStandardLimits;
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
//@Table(name = "meas_comparators", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MeasComparators.findAll", query = "SELECT m FROM MeasComparators m"),
//    @NamedQuery(name = "MeasComparators.findById", query = "SELECT m FROM MeasComparators m WHERE m.id = :id"),
//    @NamedQuery(name = "MeasComparators.findByDeleted", query = "SELECT m FROM MeasComparators m WHERE m.deleted = :deleted"),
//    @NamedQuery(name = "MeasComparators.findByCreated", query = "SELECT m FROM MeasComparators m WHERE m.created = :created"),
//    @NamedQuery(name = "MeasComparators.findByChanged", query = "SELECT m FROM MeasComparators m WHERE m.changed = :changed"),
//    @NamedQuery(name = "MeasComparators.findBySymbol", query = "SELECT m FROM MeasComparators m WHERE m.symbol = :symbol"),
//    @NamedQuery(name = "MeasComparators.findByName", query = "SELECT m FROM MeasComparators m WHERE m.name = :name")})
public class MeasComparators implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String symbol;
    private String name;
    private Collection<MeasLimits> measLimitsCollection;
    private Collection<PersStandardLimits> persStandardLimitsCollection;

    public MeasComparators() {
    }

    public MeasComparators(Integer id) {
        this.id = id;
    }

    public MeasComparators(Integer id, String symbol) {
        this.id = id;
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeasComparators)) {
            return false;
        }
        MeasComparators other = (MeasComparators) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return symbol + " " + name + " [id=" + id + "]";
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
            } /**
             *
             * PARAMETER
             */
            else if (c.matches("symbol")) {
                this.symbol = rs.getString(c);
            } else if (c.matches("name")) {
                this.name = rs.getString(c);
            } /**
             *
             * informations
             */
            else {
                Util.out(getClass().getSimpleName() + " >> update >> unknown column name " + c);
                System.out.println(getClass().getSimpleName() + " >> update >> unknown column name " + c);
            }

        }
    }

}
