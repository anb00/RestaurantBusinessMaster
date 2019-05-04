package com.iesemilidarder.anb00.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1878061099776559468L;

    long id;
    String name;
    Cathegory cathegory;
    String location;
    String description;
    String imageUrl;

    Collection<UserComment> comments;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cathegory getCathegory() {
        return cathegory;
    }

    public void setCathegory(Cathegory cathegory) {
        this.cathegory = cathegory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    public Collection<UserComment> getComments() {
        return comments;
    }

    public void setComments(Collection<UserComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Restaurante \"" + getName() + "\"";
    }
}
