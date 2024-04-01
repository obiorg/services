/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.maintenance;

import org.obi.services.entities.business.Companies;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "equipements_external_providers", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "EquipementsExternalProviders.findAll", query = "SELECT e FROM EquipementsExternalProviders e"),
//    @NamedQuery(name = "EquipementsExternalProviders.findById", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.id = :id"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByDeleted", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.deleted = :deleted"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByCreated", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.created = :created"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByChanged", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.changed = :changed"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByProvider", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.provider = :provider"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByName", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.name = :name"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByType", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.type = :type"),
//    @NamedQuery(name = "EquipementsExternalProviders.findBySourceType", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.sourceType = :sourceType"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByLink", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.link = :link"),
//    @NamedQuery(name = "EquipementsExternalProviders.findBySource", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.source = :source"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByBddServer", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.bddServer = :bddServer"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByBddUser", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.bddUser = :bddUser"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByBddPassword", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.bddPassword = :bddPassword"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByBddPort", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.bddPort = :bddPort"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByApprouved", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.approuved = :approuved"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamBool1", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramBool1 = :paramBool1"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamBool2", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramBool2 = :paramBool2"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamBool3", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramBool3 = :paramBool3"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamBool4", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramBool4 = :paramBool4"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamBool5", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramBool5 = :paramBool5"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamInt1", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramInt1 = :paramInt1"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamInt2", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramInt2 = :paramInt2"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamInt3", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramInt3 = :paramInt3"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamInt4", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramInt4 = :paramInt4"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamInt5", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramInt5 = :paramInt5"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamStr1", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramStr1 = :paramStr1"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamStr2", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramStr2 = :paramStr2"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamStr3", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramStr3 = :paramStr3"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamStr4", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramStr4 = :paramStr4"),
//    @NamedQuery(name = "EquipementsExternalProviders.findByParamStr5", query = "SELECT e FROM EquipementsExternalProviders e WHERE e.paramStr5 = :paramStr5")})
public class EquipementsExternalProviders implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String provider;
    private String name;
    private Integer type;
    private Integer sourceType;
    private Integer link;
    private String source;
    private String bddServer;
    private String bddUser;
    private String bddPassword;
    private Integer bddPort;
    private Boolean approuved;
    private Boolean paramBool1;
    private Boolean paramBool2;
    private Boolean paramBool3;
    private Boolean paramBool4;
    private Boolean paramBool5;
    private Integer paramInt1;
    private Integer paramInt2;
    private Integer paramInt3;
    private Integer paramInt4;
    private Integer paramInt5;
    private String paramStr1;
    private String paramStr2;
    private String paramStr3;
    private String paramStr4;
    private String paramStr5;
    private Companies company;
    private Collection<EquipementsDataExternal> equipementsDataExternalCollection;

    public EquipementsExternalProviders() {
    }

    public EquipementsExternalProviders(Integer id) {
        this.id = id;
    }

    public EquipementsExternalProviders(Integer id, String provider) {
        this.id = id;
        this.provider = provider;
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBddServer() {
        return bddServer;
    }

    public void setBddServer(String bddServer) {
        this.bddServer = bddServer;
    }

    public String getBddUser() {
        return bddUser;
    }

    public void setBddUser(String bddUser) {
        this.bddUser = bddUser;
    }

    public String getBddPassword() {
        return bddPassword;
    }

    public void setBddPassword(String bddPassword) {
        this.bddPassword = bddPassword;
    }

    public Integer getBddPort() {
        return bddPort;
    }

    public void setBddPort(Integer bddPort) {
        this.bddPort = bddPort;
    }

    public Boolean getApprouved() {
        return approuved;
    }

    public void setApprouved(Boolean approuved) {
        this.approuved = approuved;
    }

    public Boolean getParamBool1() {
        return paramBool1;
    }

    public void setParamBool1(Boolean paramBool1) {
        this.paramBool1 = paramBool1;
    }

    public Boolean getParamBool2() {
        return paramBool2;
    }

    public void setParamBool2(Boolean paramBool2) {
        this.paramBool2 = paramBool2;
    }

    public Boolean getParamBool3() {
        return paramBool3;
    }

    public void setParamBool3(Boolean paramBool3) {
        this.paramBool3 = paramBool3;
    }

    public Boolean getParamBool4() {
        return paramBool4;
    }

    public void setParamBool4(Boolean paramBool4) {
        this.paramBool4 = paramBool4;
    }

    public Boolean getParamBool5() {
        return paramBool5;
    }

    public void setParamBool5(Boolean paramBool5) {
        this.paramBool5 = paramBool5;
    }

    public Integer getParamInt1() {
        return paramInt1;
    }

    public void setParamInt1(Integer paramInt1) {
        this.paramInt1 = paramInt1;
    }

    public Integer getParamInt2() {
        return paramInt2;
    }

    public void setParamInt2(Integer paramInt2) {
        this.paramInt2 = paramInt2;
    }

    public Integer getParamInt3() {
        return paramInt3;
    }

    public void setParamInt3(Integer paramInt3) {
        this.paramInt3 = paramInt3;
    }

    public Integer getParamInt4() {
        return paramInt4;
    }

    public void setParamInt4(Integer paramInt4) {
        this.paramInt4 = paramInt4;
    }

    public Integer getParamInt5() {
        return paramInt5;
    }

    public void setParamInt5(Integer paramInt5) {
        this.paramInt5 = paramInt5;
    }

    public String getParamStr1() {
        return paramStr1;
    }

    public void setParamStr1(String paramStr1) {
        this.paramStr1 = paramStr1;
    }

    public String getParamStr2() {
        return paramStr2;
    }

    public void setParamStr2(String paramStr2) {
        this.paramStr2 = paramStr2;
    }

    public String getParamStr3() {
        return paramStr3;
    }

    public void setParamStr3(String paramStr3) {
        this.paramStr3 = paramStr3;
    }

    public String getParamStr4() {
        return paramStr4;
    }

    public void setParamStr4(String paramStr4) {
        this.paramStr4 = paramStr4;
    }

    public String getParamStr5() {
        return paramStr5;
    }

    public void setParamStr5(String paramStr5) {
        this.paramStr5 = paramStr5;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public Collection<EquipementsDataExternal> getEquipementsDataExternalCollection() {
        return equipementsDataExternalCollection;
    }

    public void setEquipementsDataExternalCollection(Collection<EquipementsDataExternal> equipementsDataExternalCollection) {
        this.equipementsDataExternalCollection = equipementsDataExternalCollection;
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
        if (!(object instanceof EquipementsExternalProviders)) {
            return false;
        }
        EquipementsExternalProviders other = (EquipementsExternalProviders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.EquipementsExternalProviders[ id=" + id + " ]";
    }

}
