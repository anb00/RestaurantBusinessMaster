package com.iesemilidarder.anb00.master;


import com.iesemilidarder.anb00.*;
import com.iesemilidarder.anb00.business.CRUDException;
import com.iesemilidarder.anb00.business.RestaurantLocal;
import com.iesemilidarder.anb00.entities.Restaurant;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

@ManagedBean
@RequestScoped
public class RestaurantFormBackend {
    private static final FacesMessage MESSAGE_FROM_RESTAURANT = new FacesMessage("The Restaurant cant Create");
    /* Aplico el filtro de logs para ver los fallos */
    private static final Logger loggerRest = Logger.getLogger(RestaurantFormBackend.class);

    int id;



    Restaurant restaurant = new Restaurant();


    @Resource(lookup = "java:module/RestaurantCRUDImpl!com.iesemilidarder.anb00.business.RestaurantLocal")
    RestaurantLocal al;



    public Restaurant getRestaurant () {
        return restaurant;
    }


    public void setRestaurant (Restaurant restaurant){
        this.restaurant = restaurant;
    }


    /**
     * need test this method
     * @return
     * @throws CRUDException
     */


    public String addConfirmedRest () throws CRUDException {
        loggerRest.info("El restaurante guardado en DB es = " + restaurant + " es " +getRestaurant() + ", rest = " + restaurant);
        restaurant = al.create(restaurant);
        restaurant = new Restaurant();
        return "index";


    }


    public Collection<Restaurant> getList() {
        try {
            return al.retrieveAll();
        } catch (CRUDException e) {
            return new ArrayList<Restaurant>(); // una lista vacía.
        }
    }

}
