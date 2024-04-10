/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.maintenance;

import org.obi.services.entities.business.Companies;
import org.obi.services.entities.analyses.AnalysePoints;
import java.io.Serializable;
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
//    @NamedQuery(name = "Equipements.findAll", query = "SELECT e FROM Equipements e"),
//    @NamedQuery(name = "Equipements.findById", query = "SELECT e FROM Equipements e WHERE e.id = :id"),
//    @NamedQuery(name = "Equipements.findByDeleted", query = "SELECT e FROM Equipements e WHERE e.deleted = :deleted"),
//    @NamedQuery(name = "Equipements.findByCreated", query = "SELECT e FROM Equipements e WHERE e.created = :created"),
//    @NamedQuery(name = "Equipements.findByChanged", query = "SELECT e FROM Equipements e WHERE e.changed = :changed"),
//    @NamedQuery(name = "Equipements.findByEquipement", query = "SELECT e FROM Equipements e WHERE e.equipement = :equipement"),
//    @NamedQuery(name = "Equipements.findByName", query = "SELECT e FROM Equipements e WHERE e.name = :name")})
public class Equipements implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String equipement;
    private String name;
    private Companies company;
    private Collection<EquipementsDataExternal> equipementsDataExternalCollection;
    private Collection<AnalysePoints> analysePointsCollection;

    public Equipements() {
    }

    public Equipements(Integer id) {
        this.id = id;
    }

    public Equipements(Integer id, String equipement) {
        this.id = id;
        this.equipement = equipement;
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

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Collection<AnalysePoints> getAnalysePointsCollection() {
        return analysePointsCollection;
    }

    public void setAnalysePointsCollection(Collection<AnalysePoints> analysePointsCollection) {
        this.analysePointsCollection = analysePointsCollection;
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
        if (!(object instanceof Equipements)) {
            return false;
        }
        Equipements other = (Equipements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.Equipements[ id=" + id + " ]";
    }

}
