/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.tags;

import org.obi.services.entities.persistence.PersStandardLimits;
import org.obi.services.entities.persistence.Persistence;
import org.obi.services.entities.persistence.PersStandard;
import org.obi.services.entities.measures.MeasUnits;
import org.obi.services.entities.measures.MeasLimits;
import org.obi.services.entities.machines.Machines;
import org.obi.services.entities.business.Companies;
import org.obi.services.entities.analyses.AnalyseAllowed;
import org.obi.services.entities.alarms.Alarms;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Tags.findAll", query = "SELECT t FROM Tags t"),
//    @NamedQuery(name = "Tags.findById", query = "SELECT t FROM Tags t WHERE t.id = :id"),
//    @NamedQuery(name = "Tags.findByDeleted", query = "SELECT t FROM Tags t WHERE t.deleted = :deleted"),
//    @NamedQuery(name = "Tags.findByCreated", query = "SELECT t FROM Tags t WHERE t.created = :created"),
//    @NamedQuery(name = "Tags.findByChanged", query = "SELECT t FROM Tags t WHERE t.changed = :changed"),
//    @NamedQuery(name = "Tags.findByName", query = "SELECT t FROM Tags t WHERE t.name = :name"),
//    @NamedQuery(name = "Tags.findByDb", query = "SELECT t FROM Tags t WHERE t.db = :db"),
//    @NamedQuery(name = "Tags.findByByte1", query = "SELECT t FROM Tags t WHERE t.byte1 = :byte1"),
//    @NamedQuery(name = "Tags.findByBit", query = "SELECT t FROM Tags t WHERE t.bit = :bit"),
//    @NamedQuery(name = "Tags.findByActive", query = "SELECT t FROM Tags t WHERE t.active = :active"),
//    @NamedQuery(name = "Tags.findByCycle", query = "SELECT t FROM Tags t WHERE t.cycle = :cycle"),
//    @NamedQuery(name = "Tags.findByDelta", query = "SELECT t FROM Tags t WHERE t.delta = :delta"),
//    @NamedQuery(name = "Tags.findByDeltaFloat", query = "SELECT t FROM Tags t WHERE t.deltaFloat = :deltaFloat"),
//    @NamedQuery(name = "Tags.findByDeltaInt", query = "SELECT t FROM Tags t WHERE t.deltaInt = :deltaInt"),
//    @NamedQuery(name = "Tags.findByDeltaBool", query = "SELECT t FROM Tags t WHERE t.deltaBool = :deltaBool"),
//    @NamedQuery(name = "Tags.findByDeltaDateTime", query = "SELECT t FROM Tags t WHERE t.deltaDateTime = :deltaDateTime"),
//    @NamedQuery(name = "Tags.findByVFloat", query = "SELECT t FROM Tags t WHERE t.vFloat = :vFloat"),
//    @NamedQuery(name = "Tags.findByVInt", query = "SELECT t FROM Tags t WHERE t.vInt = :vInt"),
//    @NamedQuery(name = "Tags.findByVBool", query = "SELECT t FROM Tags t WHERE t.vBool = :vBool"),
//    @NamedQuery(name = "Tags.findByVStr", query = "SELECT t FROM Tags t WHERE t.vStr = :vStr"),
//    @NamedQuery(name = "Tags.findByVDateTime", query = "SELECT t FROM Tags t WHERE t.vDateTime = :vDateTime"),
//    @NamedQuery(name = "Tags.findByVStamp", query = "SELECT t FROM Tags t WHERE t.vStamp = :vStamp"),
//    @NamedQuery(name = "Tags.findByVDefault", query = "SELECT t FROM Tags t WHERE t.vDefault = :vDefault"),
//    @NamedQuery(name = "Tags.findByVFloatDefault", query = "SELECT t FROM Tags t WHERE t.vFloatDefault = :vFloatDefault"),
//    @NamedQuery(name = "Tags.findByVIntDefault", query = "SELECT t FROM Tags t WHERE t.vIntDefault = :vIntDefault"),
//    @NamedQuery(name = "Tags.findByVBoolDefault", query = "SELECT t FROM Tags t WHERE t.vBoolDefault = :vBoolDefault"),
//    @NamedQuery(name = "Tags.findByVStrDefault", query = "SELECT t FROM Tags t WHERE t.vStrDefault = :vStrDefault"),
//    @NamedQuery(name = "Tags.findByVDateTimeDefault", query = "SELECT t FROM Tags t WHERE t.vDateTimeDefault = :vDateTimeDefault"),
//    @NamedQuery(name = "Tags.findByVStampDefault", query = "SELECT t FROM Tags t WHERE t.vStampDefault = :vStampDefault"),
//    @NamedQuery(name = "Tags.findByCounter", query = "SELECT t FROM Tags t WHERE t.counter = :counter"),
//    @NamedQuery(name = "Tags.findByCounterType", query = "SELECT t FROM Tags t WHERE t.counterType = :counterType"),
//    @NamedQuery(name = "Tags.findByMesure", query = "SELECT t FROM Tags t WHERE t.mesure = :mesure"),
//    @NamedQuery(name = "Tags.findByMesureMin", query = "SELECT t FROM Tags t WHERE t.mesureMin = :mesureMin"),
//    @NamedQuery(name = "Tags.findByMesureMax", query = "SELECT t FROM Tags t WHERE t.mesureMax = :mesureMax"),
//    @NamedQuery(name = "Tags.findByMqqtTopic", query = "SELECT t FROM Tags t WHERE t.mqqtTopic = :mqqtTopic"),
//    @NamedQuery(name = "Tags.findByWebhook", query = "SELECT t FROM Tags t WHERE t.webhook = :webhook"),
//    @NamedQuery(name = "Tags.findByLaboratory", query = "SELECT t FROM Tags t WHERE t.laboratory = :laboratory"),
//    @NamedQuery(name = "Tags.findByFormula", query = "SELECT t FROM Tags t WHERE t.formula = :formula"),
//    @NamedQuery(name = "Tags.findByFormCalculus", query = "SELECT t FROM Tags t WHERE t.formCalculus = :formCalculus"),
//    @NamedQuery(name = "Tags.findByFormProcessing", query = "SELECT t FROM Tags t WHERE t.formProcessing = :formProcessing"),
//    @NamedQuery(name = "Tags.findByError", query = "SELECT t FROM Tags t WHERE t.error = :error"),
//    @NamedQuery(name = "Tags.findByErrorMsg", query = "SELECT t FROM Tags t WHERE t.errorMsg = :errorMsg"),
//    @NamedQuery(name = "Tags.findByErrorStamp", query = "SELECT t FROM Tags t WHERE t.errorStamp = :errorStamp"),
//    @NamedQuery(name = "Tags.findByAlarmEnable", query = "SELECT t FROM Tags t WHERE t.alarmEnable = :alarmEnable"),
//    @NamedQuery(name = "Tags.findByPersistenceEnable", query = "SELECT t FROM Tags t WHERE t.persistenceEnable = :persistenceEnable"),
//    @NamedQuery(name = "Tags.findByPersOffsetEnable", query = "SELECT t FROM Tags t WHERE t.persOffsetEnable = :persOffsetEnable"),
//    @NamedQuery(name = "Tags.findByPersOffsetFloat", query = "SELECT t FROM Tags t WHERE t.persOffsetFloat = :persOffsetFloat"),
//    @NamedQuery(name = "Tags.findByPersOffsetInt", query = "SELECT t FROM Tags t WHERE t.persOffsetInt = :persOffsetInt"),
//    @NamedQuery(name = "Tags.findByPersOffsetBool", query = "SELECT t FROM Tags t WHERE t.persOffsetBool = :persOffsetBool"),
//    @NamedQuery(name = "Tags.findByPersOffsetDateTime", query = "SELECT t FROM Tags t WHERE t.persOffsetDateTime = :persOffsetDateTime"),
//    @NamedQuery(name = "Tags.findByComment", query = "SELECT t FROM Tags t WHERE t.comment = :comment")})
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String name;
    private Integer db;
    private Integer byte1;
    private Integer bit;
    private Boolean active;
    private Integer cycle;
    private Boolean delta;
    private Double deltaFloat;
    private Integer deltaInt;
    private Integer deltaBool;
    private BigInteger deltaDateTime;
    private Double vFloat;
    private Integer vInt;
    private Boolean vBool;
    private String vStr;
    private Date vDateTime;
    private Date vStamp;
    private Boolean vDefault;
    private Double vFloatDefault;
    private Integer vIntDefault;
    private Boolean vBoolDefault;
    private String vStrDefault;
    private Date vDateTimeDefault;
    private Date vStampDefault;
    private Boolean counter;
    private Integer counterType;
    private Boolean mesure;
    private Double mesureMin;
    private Double mesureMax;
    private String mqqtTopic;
    private String webhook;
    private Boolean laboratory;
    private Boolean formula;
    private String formCalculus;
    private Integer formProcessing;
    private Boolean error;
    private String errorMsg;
    private Date errorStamp;
    private Boolean alarmEnable;
    private Boolean persistenceEnable;
    private Boolean persOffsetEnable;
    private Double persOffsetFloat;
    private Integer persOffsetInt;
    private Boolean persOffsetBool;
    private Date persOffsetDateTime;
    private String comment;
    private Collection<AnalyseAllowed> analyseAllowedCollection;
    private Collection<PersStandard> persStandardCollection;
    private Collection<MeasLimits> measLimitsCollection;
    private Collection<PersStandardLimits> persStandardLimitsCollection;
    private Alarms alarm;
    private Companies company;
    private Machines machine;
    private MeasUnits measureUnit;
    private TagsMemories memory;
    private TagsTables table;
    private TagsTypes type;
    private Collection<Persistence> persistenceCollection;

    public Tags() {
    }

    public Tags(Integer id) {
        this.id = id;
    }

    public Tags(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    public Integer getByte1() {
        return byte1;
    }

    public void setByte1(Integer byte1) {
        this.byte1 = byte1;
    }

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Boolean getDelta() {
        return delta;
    }

    public void setDelta(Boolean delta) {
        this.delta = delta;
    }

    public Double getDeltaFloat() {
        return deltaFloat;
    }

    public void setDeltaFloat(Double deltaFloat) {
        this.deltaFloat = deltaFloat;
    }

    public Integer getDeltaInt() {
        return deltaInt;
    }

    public void setDeltaInt(Integer deltaInt) {
        this.deltaInt = deltaInt;
    }

    public Integer getDeltaBool() {
        return deltaBool;
    }

    public void setDeltaBool(Integer deltaBool) {
        this.deltaBool = deltaBool;
    }

    public BigInteger getDeltaDateTime() {
        return deltaDateTime;
    }

    public void setDeltaDateTime(BigInteger deltaDateTime) {
        this.deltaDateTime = deltaDateTime;
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

    public Boolean getVDefault() {
        return vDefault;
    }

    public void setVDefault(Boolean vDefault) {
        this.vDefault = vDefault;
    }

    public Double getVFloatDefault() {
        return vFloatDefault;
    }

    public void setVFloatDefault(Double vFloatDefault) {
        this.vFloatDefault = vFloatDefault;
    }

    public Integer getVIntDefault() {
        return vIntDefault;
    }

    public void setVIntDefault(Integer vIntDefault) {
        this.vIntDefault = vIntDefault;
    }

    public Boolean getVBoolDefault() {
        return vBoolDefault;
    }

    public void setVBoolDefault(Boolean vBoolDefault) {
        this.vBoolDefault = vBoolDefault;
    }

    public String getVStrDefault() {
        return vStrDefault;
    }

    public void setVStrDefault(String vStrDefault) {
        this.vStrDefault = vStrDefault;
    }

    public Date getVDateTimeDefault() {
        return vDateTimeDefault;
    }

    public void setVDateTimeDefault(Date vDateTimeDefault) {
        this.vDateTimeDefault = vDateTimeDefault;
    }

    public Date getVStampDefault() {
        return vStampDefault;
    }

    public void setVStampDefault(Date vStampDefault) {
        this.vStampDefault = vStampDefault;
    }

    public Boolean getCounter() {
        return counter;
    }

    public void setCounter(Boolean counter) {
        this.counter = counter;
    }

    public Integer getCounterType() {
        return counterType;
    }

    public void setCounterType(Integer counterType) {
        this.counterType = counterType;
    }

    public Boolean getMesure() {
        return mesure;
    }

    public void setMesure(Boolean mesure) {
        this.mesure = mesure;
    }

    public Double getMesureMin() {
        return mesureMin;
    }

    public void setMesureMin(Double mesureMin) {
        this.mesureMin = mesureMin;
    }

    public Double getMesureMax() {
        return mesureMax;
    }

    public void setMesureMax(Double mesureMax) {
        this.mesureMax = mesureMax;
    }

    public String getMqqtTopic() {
        return mqqtTopic;
    }

    public void setMqqtTopic(String mqqtTopic) {
        this.mqqtTopic = mqqtTopic;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public Boolean getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Boolean laboratory) {
        this.laboratory = laboratory;
    }

    public Boolean getFormula() {
        return formula;
    }

    public void setFormula(Boolean formula) {
        this.formula = formula;
    }

    public String getFormCalculus() {
        return formCalculus;
    }

    public void setFormCalculus(String formCalculus) {
        this.formCalculus = formCalculus;
    }

    public Integer getFormProcessing() {
        return formProcessing;
    }

    public void setFormProcessing(Integer formProcessing) {
        this.formProcessing = formProcessing;
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

    public Date getErrorStamp() {
        return errorStamp;
    }

    public void setErrorStamp(Date errorStamp) {
        this.errorStamp = errorStamp;
    }

    public Boolean getAlarmEnable() {
        return alarmEnable;
    }

    public void setAlarmEnable(Boolean alarmEnable) {
        this.alarmEnable = alarmEnable;
    }

    public Boolean getPersistenceEnable() {
        return persistenceEnable;
    }

    public void setPersistenceEnable(Boolean persistenceEnable) {
        this.persistenceEnable = persistenceEnable;
    }

    public Boolean getPersOffsetEnable() {
        return persOffsetEnable;
    }

    public void setPersOffsetEnable(Boolean persOffsetEnable) {
        this.persOffsetEnable = persOffsetEnable;
    }

    public Double getPersOffsetFloat() {
        return persOffsetFloat;
    }

    public void setPersOffsetFloat(Double persOffsetFloat) {
        this.persOffsetFloat = persOffsetFloat;
    }

    public Integer getPersOffsetInt() {
        return persOffsetInt;
    }

    public void setPersOffsetInt(Integer persOffsetInt) {
        this.persOffsetInt = persOffsetInt;
    }

    public Boolean getPersOffsetBool() {
        return persOffsetBool;
    }

    public void setPersOffsetBool(Boolean persOffsetBool) {
        this.persOffsetBool = persOffsetBool;
    }

    public Date getPersOffsetDateTime() {
        return persOffsetDateTime;
    }

    public void setPersOffsetDateTime(Date persOffsetDateTime) {
        this.persOffsetDateTime = persOffsetDateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Alarms getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarms alarm) {
        this.alarm = alarm;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public Machines getMachine() {
        return machine;
    }

    public void setMachine(Machines machine) {
        this.machine = machine;
    }

    public MeasUnits getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasUnits measureUnit) {
        this.measureUnit = measureUnit;
    }

    public TagsMemories getMemory() {
        return memory;
    }

    public void setMemory(TagsMemories memory) {
        this.memory = memory;
    }

    public TagsTables getTable() {
        return table;
    }

    public void setTable(TagsTables table) {
        this.table = table;
    }

    public TagsTypes getType() {
        return type;
    }

    public void setType(TagsTypes type) {
        this.type = type;
    }

    public Collection<Persistence> getPersistenceCollection() {
        return persistenceCollection;
    }

    public void setPersistenceCollection(Collection<Persistence> persistenceCollection) {
        this.persistenceCollection = persistenceCollection;
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
        if (!(object instanceof Tags)) {
            return false;
        }
        Tags other = (Tags) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.Tags[ id=" + id + " ]";
    }

}
