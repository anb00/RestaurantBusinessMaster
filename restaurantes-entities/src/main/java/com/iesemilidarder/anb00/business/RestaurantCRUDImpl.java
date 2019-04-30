package com.iesemilidarder.anb00.business;

import javax.ejb.Stateless;

import com.iesemilidarder.anb00.entities.Restaurant;

@Stateless
public class RestaurantCRUDImpl extends CRUDImpl<Long, Restaurant> implements RestaurantLocal {

    public RestaurantCRUDImpl() {
        super(Restaurant.class);
    }

    public Restaurant retrieve(String Restaurant,long id) throws CRUDException {
        if ( Restaurant == null ){
            System.out.println("Restauarnt null");
        }
        return null;
    }

}