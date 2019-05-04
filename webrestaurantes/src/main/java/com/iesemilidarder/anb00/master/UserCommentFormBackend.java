package com.iesemilidarder.anb00.master;


import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class UserCommentFormBackend {

    int id;
    RestaurantFormBackend restaurante;
    UserFormBackend userFormBackend;
    Date timestamp;
    String texto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RestaurantFormBackend getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestaurantFormBackend restaurante) {
        this.restaurante = restaurante;
    }

    public UserFormBackend getUserFormBackend() {
        return userFormBackend;
    }

    public void setUserFormBackend(UserFormBackend userFormBackend) {
        this.userFormBackend = userFormBackend;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
