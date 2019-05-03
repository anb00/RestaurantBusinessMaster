package com.iesemilidarder.anb00.master;


import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;

import com.iesemilidarder.anb00.business.CRUDException;
import com.iesemilidarder.anb00.business.RestaurantLocal;
import com.iesemilidarder.anb00.business.UserCommentLocal;
import com.iesemilidarder.anb00.entities.Restaurant;
import com.iesemilidarder.anb00.entities.UserComment;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class RestaurantFormBackend {

    /* Aplico el filtro de logs para ver los fallos */
    private static final Logger log = Logger.getLogger(RestaurantFormBackend.class);

    private Long id;

    Restaurant restaurant;

    Collection<UserComment> userComments;

    @Resource(lookup = "java:module/RestaurantCRUDImpl!com.iesemilidarder.anb00.business.RestaurantLocal")
    RestaurantLocal al;

    @Resource(lookup = "java:module/UserCommentCRUDImpl!com.iesemilidarder.anb00.business.UserCommentLocal")
    UserCommentLocal ucl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        try {
            if (restaurant == null) {
                if (id == null) {
                    log.infof("No id specified, retrieving a new restaurant");
                    return restaurant = new Restaurant();
                } else {
                    log.infof("al.retrieve(%ld)", id);
                    restaurant = al.retrieve(id);
                    log.infof("retrieved Restaurant %s", restaurant);
                }
            }
            return restaurant;
        } catch (CRUDException e) {
            FacesContext fc = FacesContext.getCurrentInstance();
            for (Throwable t = e; t != null; t = t.getCause()) {
                fc.addMessage(null, new FacesMessage(t.toString()));
            }
            return new Restaurant();
        }
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Collection<UserComment> getUserComments() {
        try {
            if (userComments == null) {
                if (id == null) {
                    return new ArrayList<UserComment>();
                }
                userComments = ucl.retrieveWhere(
                        "SELECT UC FROM UserComment AS UC WHERE UC.restaurant.id = ?0 ORDER BY UC.timestamp DESC", id);
            }
            return userComments;
        } catch (CRUDException e) {
            FacesContext fc = FacesContext.getCurrentInstance();
            for (Throwable t = e; t != null; t = t.getCause()) {
                fc.addMessage(null, new FacesMessage(t.toString()));
            }
            return new ArrayList<UserComment>();
        }
    }

    /**
     * need test this method
     *
     * @return
     * @throws CRUDException
     */

    public String addConfirmedRest() throws CRUDException {
        //loggerRest.info("El restaurante guardado en DB es = " + restaurant + " es " + getRestaurant() + ", rest = "
        //	+ restaurant);
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
