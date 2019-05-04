package com.iesemilidarder.anb00.entities;
import java.io.Serializable;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserComment implements Serializable {

    private static final long serialVersionUID = 3015125087775066781L;

    long id;
    Restaurant restaurant;
    User user;
    Date timestamp;
    String texto;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(length = 4096)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        final int max_len = 20;
        StringBuffer sb = new StringBuffer();
        sb.append("[" + id + "]: Texto = " + getTexto().substring(0, max_len));
        if (getTexto().length() > max_len)
            sb.append("...");
        return sb.toString();
    }
}
