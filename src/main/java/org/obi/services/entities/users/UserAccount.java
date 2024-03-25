/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.entities.users;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
//@Entity
//@Table(name = "user_account", catalog = "OBI", schema = "dbo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u"),
//    @NamedQuery(name = "UserAccount.findById", query = "SELECT u FROM UserAccount u WHERE u.id = :id"),
//    @NamedQuery(name = "UserAccount.findByDeleted", query = "SELECT u FROM UserAccount u WHERE u.deleted = :deleted"),
//    @NamedQuery(name = "UserAccount.findByCreated", query = "SELECT u FROM UserAccount u WHERE u.created = :created"),
//    @NamedQuery(name = "UserAccount.findByChanged", query = "SELECT u FROM UserAccount u WHERE u.changed = :changed"),
//    @NamedQuery(name = "UserAccount.findByFirstName", query = "SELECT u FROM UserAccount u WHERE u.firstName = :firstName"),
//    @NamedQuery(name = "UserAccount.findByLastName", query = "SELECT u FROM UserAccount u WHERE u.lastName = :lastName"),
//    @NamedQuery(name = "UserAccount.findByMiddleName", query = "SELECT u FROM UserAccount u WHERE u.middleName = :middleName"),
//    @NamedQuery(name = "UserAccount.findByInitialLetter", query = "SELECT u FROM UserAccount u WHERE u.initialLetter = :initialLetter"),
//    @NamedQuery(name = "UserAccount.findByGenre", query = "SELECT u FROM UserAccount u WHERE u.genre = :genre"),
//    @NamedQuery(name = "UserAccount.findByDateOfBirth", query = "SELECT u FROM UserAccount u WHERE u.dateOfBirth = :dateOfBirth")})
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean deleted;
    private Date created;
    private Date changed;
    private String firstName;
    private String lastName;
    private String middleName;
    private String initialLetter;
    private Character genre;
    private Date dateOfBirth;
    private Collection<UserAccountRole> userAccountRoleCollection;

    public UserAccount() {
    }

    public UserAccount(Integer id) {
        this.id = id;
    }

    public UserAccount(Integer id, String firstName, String lastName, Character genre, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;
        this.dateOfBirth = dateOfBirth;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getInitialLetter() {
        return initialLetter;
    }

    public void setInitialLetter(String initialLetter) {
        this.initialLetter = initialLetter;
    }

    public Character getGenre() {
        return genre;
    }

    public void setGenre(Character genre) {
        this.genre = genre;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Collection<UserAccountRole> getUserAccountRoleCollection() {
        return userAccountRoleCollection;
    }

    public void setUserAccountRoleCollection(Collection<UserAccountRole> userAccountRoleCollection) {
        this.userAccountRoleCollection = userAccountRoleCollection;
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
        if (!(object instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.obi.services.entities.UserAccount[ id=" + id + " ]";
    }

}
