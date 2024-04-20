package org.obi.services.entities.measures;

import org.obi.services.entities.business.Companies;
import org.obi.services.entities.business.Businesses;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import org.obi.services.entities.persistence.PersMethod;
import org.obi.services.entities.tags.Tags;
import org.obi.services.sessions.business.BusinessesFacade;
import org.obi.services.sessions.business.CompaniesFacade;
import org.obi.services.sessions.measures.MeasComparatorsFacade;
import org.obi.services.sessions.measures.MeasLimitsGroupsFacade;
import org.obi.services.sessions.persistence.PersMethodFacade;
import org.obi.services.sessions.tags.TagsFacade;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "meas_limits", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MeasLimits.findAll", query = "SELECT m FROM MeasLimits m"),
//    @NamedQuery(name = "MeasLimits.findById", query = "SELECT m FROM MeasLimits m WHERE m.id = :id"),
//    @NamedQuery(name = "MeasLimits.findByDeleted", query = "SELECT m FROM MeasLimits m WHERE m.deleted = :deleted"),
//    @NamedQuery(name = "MeasLimits.findByCreated", query = "SELECT m FROM MeasLimits m WHERE m.created = :created"),
//    @NamedQuery(name = "MeasLimits.findByChanged", query = "SELECT m FROM MeasLimits m WHERE m.changed = :changed"),
//    @NamedQuery(name = "MeasLimits.findByName", query = "SELECT m FROM MeasLimits m WHERE m.name = :name"),
//    @NamedQuery(name = "MeasLimits.findByValue", query = "SELECT m FROM MeasLimits m WHERE m.value = :value"),
//    @NamedQuery(name = "MeasLimits.findByDelay", query = "SELECT m FROM MeasLimits m WHERE m.delay = :delay"),
//    @NamedQuery(name = "MeasLimits.findByHysteresis", query = "SELECT m FROM MeasLimits m WHERE m.hysteresis = :hysteresis"),
//    @NamedQuery(name = "MeasLimits.findByTarget", query = "SELECT m FROM MeasLimits m WHERE m.target = :target"),
//    @NamedQuery(name = "MeasLimits.findByEnable", query = "SELECT m FROM MeasLimits m WHERE m.enable = :enable"),
//    @NamedQuery(name = "MeasLimits.findBySort", query = "SELECT m FROM MeasLimits m WHERE m.sort = :sort")})
public class MeasLimits implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String name;
    private Double value;
    private Integer delay;
    private Double hysteresis;
    private Boolean target;
    private Boolean enable;
    private Integer sort;
    private String description;
    private Businesses business;
    private Companies company;
    private MeasComparators comparator;
    private MeasLimitsGroups group1;
    private Tags tag;

    public MeasLimits() {
    }

    public MeasLimits(Integer id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Double getHysteresis() {
        return hysteresis;
    }

    public void setHysteresis(Double hysteresis) {
        this.hysteresis = hysteresis;
    }

    public Boolean getTarget() {
        return target;
    }

    public void setTarget(Boolean target) {
        this.target = target;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public MeasComparators getComparator() {
        return comparator;
    }

    public void setComparator(MeasComparators comparator) {
        this.comparator = comparator;
    }

    public MeasLimitsGroups getGroup1() {
        return group1;
    }

    public void setGroup1(MeasLimitsGroups group1) {
        this.group1 = group1;
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
        if (!(object instanceof MeasLimits)) {
            return false;
        }
        MeasLimits other = (MeasLimits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name + " " + value + " >> " + company.getCompany() + " [" + id + "]";
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
            } else if (c.matches("tag")) {
                if (easy) {
                    tag = new Tags(rs.getInt(c));
                } else {
                    TagsFacade ttf = TagsFacade.getInstance();
                    tag = ttf.findById(rs.getInt(c));
                }
            } /**
             *
             * PARAMETER
             */
            else if (c.matches("name")) {
                this.name = rs.getString(c);
            }else if (c.matches("value")) {
                this.value = rs.getDouble(c);
            }else if (c.matches("comparator")) {
                if (easy) {
                    comparator = new MeasComparators(rs.getInt(c));
                } else {
                    MeasComparatorsFacade ttf = MeasComparatorsFacade.getInstance();
                    comparator = ttf.findById(rs.getInt(c));
                }
            }else if (c.matches("delay")) {
                this.delay = rs.getInt(c);
            }else if (c.matches("hysteresis")) {
                this.hysteresis = rs.getDouble(c);
            }else if (c.matches("target")) {
                target = rs.getBoolean(c);
            }else if (c.matches("enable")) {
                enable = rs.getBoolean(c);
            }else if (c.matches("group")) {
                if (easy) {
                    group1 = new MeasLimitsGroups(rs.getInt(c));
                } else {
                    MeasLimitsGroupsFacade ttf = MeasLimitsGroupsFacade.getInstance();
                    group1 = ttf.findById(rs.getInt(c));
                }
            }else if (c.matches("sort")) {
                this.sort = rs.getInt(c);
            }/**
             *
             * informations
             */
            else if (c.matches("description")) {
                this.description = rs.getString(c);
            } else {
                Util.out(getClass().getSimpleName() + " >> update >> unknown column name " + c);
                System.out.println(getClass().getSimpleName() + " >> update >> unknown column name " + c);
            }

        }
    }

}
