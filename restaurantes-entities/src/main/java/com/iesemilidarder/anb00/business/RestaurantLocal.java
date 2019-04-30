package com.iesemilidarder.anb00.business;

import javax.ejb.Local;

import com.iesemilidarder.anb00.entities.Restaurant;

@Local
public interface RestaurantLocal extends CRUD<Long, Restaurant> {
    Restaurant retrieve(String name,long id) throws CRUDException;

}