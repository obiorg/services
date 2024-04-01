/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.persistence;

import org.obi.services.entities.business.Companies;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.obi.services.entities.tags.Tags;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "pers_standard", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "PersStandard.findAll", query = "SELECT p FROM PersStandard p"),
//    @NamedQuery(name = "PersStandard.findById", query = "SELECT p FROM PersStandard p WHERE p.id = :id"),
//    @NamedQuery(name = "PersStandard.findByDeleted", query = "SELECT p FROM PersStandard p WHERE p.deleted = :deleted"),
//    @NamedQuery(name = "PersStandard.findByCreated", query = "SELECT p FROM PersStandard p WHERE p.created = :created"),
//    @NamedQuery(name = "PersStandard.findByChanged", query = "SELECT p FROM PersStandard p WHERE p.changed = :changed"),
//    @NamedQuery(name = "PersStandard.findByVFloat", query = "SELECT p FROM PersStandard p WHERE p.vFloat = :vFloat"),
//    @NamedQuery(name = "PersStandard.findByVInt", query = "SELECT p FROM PersStandard p WHERE p.vInt = :vInt"),
//    @NamedQuery(name = "PersStandard.findByVBool", query = "SELECT p FROM PersStandard p WHERE p.vBool = :vBool"),
//    @NamedQuery(name = "PersStandard.findByVStr", query = "SELECT p FROM PersStandard p WHERE p.vStr = :vStr"),
//    @NamedQuery(name = "PersStandard.findByVDateTime", query = "SELECT p FROM PersStandard p WHERE p.vDateTime = :vDateTime"),
//    @NamedQuery(name = "PersStandard.findByVStamp", query = "SELECT p FROM PersStandard p WHERE p.vStamp = :vStamp"),
//    @NamedQuery(name = "PersStandard.findByStampStart", query = "SELECT p FROM PersStandard p WHERE p.stampStart = :stampStart"),
//    @NamedQuery(name = "PersStandard.findByStampEnd", query = "SELECT p FROM PersStandard p WHERE p.stampEnd = :stampEnd"),
//    @NamedQuery(name = "PersStandard.findByTbf", query = "SELECT p FROM PersStandard p WHERE p.tbf = :tbf"),
//    @NamedQuery(name = "PersStandard.findByTtr", query = "SELECT p FROM PersStandard p WHERE p.ttr = :ttr"),
//    @NamedQuery(name = "PersStandard.findByError", query = "SELECT p FROM PersStandard p WHERE p.error = :error"),
//    @NamedQuery(name = "PersStandard.findByErrorMsg", query = "SELECT p FROM PersStandard p WHERE p.errorMsg = :errorMsg")})
public class PersStandard implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private Double vFloat;
    private Integer vInt;
    private Boolean vBool;
    private String vStr;
    private Date vDateTime;
    private Date vStamp;
    private Date stampStart;
    private Date stampEnd;
    private Float tbf;
    private Float ttr;
    private Boolean error;
    private String errorMsg;
    private Companies company;
    private Tags tag;
    private Collection<PersStandardLimits> persStandardLimitsCollection;

    public PersStandard() {
    }

    public PersStandard(Integer id) {
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

    public Double getVFloat() {
        return vFloat;
    }

    public void setVFloat(Double vFloat) {
        this.vFloat = vFloat;
    }

    public Integer getVInt() {
        return vInt;
    }

    public void setVInt(Integer vInt) {
        this.vInt = vInt;
    }

    public Boolean getVBool() {
        return vBool;
    }

    public void setVBool(Boolean vBool) {
        this.vBool = vBool;
    }

    public String getVStr() {
        return vStr;
    }

    public void setVStr(String vStr) {
        this.vStr = vStr;
    }

    public Date getVDateTime() {
        return vDateTime;
    }

    public void setVDateTime(Date vDateTime) {
        this.vDateTime = vDateTime;
    }

    public Date getVStamp() {
        return vStamp;
    }

    public void setVStamp(Date vStamp) {
        this.vStamp = vStamp;
    }

    public Date getStampStart() {
        return stampStart;
    }

    public void setStampStart(Date stampStart) {
        this.stampStart = stampStart;
    }

    public Date getStampEnd() {
        return stampEnd;
    }

    public void setStampEnd(Date stampEnd) {
        this.stampEnd = stampEnd;
    }

    public Float getTbf() {
        return tbf;
    }

    public void setTbf(Float tbf) {
        this.tbf = tbf;
    }

    public Float getTtr() {
        return ttr;
    }

    public void setTtr(Float ttr) {
        this.ttr = ttr;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
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
        if (!(object instanceof PersStandard)) {
            return false;
        }
        PersStandard other = (PersStandard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.PersStandard[ id=" + id + " ]";
    }

}
