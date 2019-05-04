package com.iesemilidarder.anb00.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "firstName", "lastName" }))
public class User implements Serializable {

    private static final long serialVersionUID = 6437879879494090565L;

    private long id;
    private String nick;
    private String encryptedPass;
    private String firstName;
    private String lastName;
    private Date dob;
    private long lastUsed;
    private long created;
    private Sex sex;
    private String email;


    private Collection<UserComment> comments;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length=16, unique=true, nullable = false)
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Column(nullable = false)
    public String getEncryptedPass() {
        return encryptedPass;
    }

    public void setEncryptedPass(String encryptedPass) {
        this.encryptedPass = encryptedPass;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    public Collection<UserComment> getComments() {
        return comments;
    }

    public void setComments(Collection<UserComment> comments) {
        this.comments = comments;
    }

    @Column(nullable=false)
    public long getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Column(nullable=false)
    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }



    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}