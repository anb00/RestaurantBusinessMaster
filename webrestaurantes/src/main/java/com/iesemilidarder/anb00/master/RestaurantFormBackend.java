package com.iesemilidarder.anb00.master;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jboss.logging.Logger;

import com.iesemilidarder.anb00.business.CRUDException;
import com.iesemilidarder.anb00.entities.Restaurant;
import com.iesemilidarder.anb00.entities.User;
import com.iesemilidarder.anb00.entities.UserComment;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class RestaurantFormBackend extends BackendBaseClass {

    /* Aplico el filtro de logs para ver los fallos */
    private static final Logger log = Logger.getLogger(RestaurantFormBackend.class);

    private Long id;
    private Restaurant restaurant;
    private List<UserComment> userComments;
    private UserComment newUserComment = new UserComment();

    @ManagedProperty(value = "#{loginFormBackend}")
    private LoginFormBackend loginFormBackend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        try {
            if (restaurant == null) {
                restaurant = new Restaurant();
                if (id != null) {
                    restaurant = restaurantLocal.retrieve(id);
                    log.infof("Retrieving restaurant with id = %d => %s", id, restaurant);
                }
            }
            return restaurant;
        } catch (CRUDException e) {
            addException(e);
            return new Restaurant();
        }
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<UserComment> getUserComments() {
        try {
            if (userComments == null) {
                if (id != null) {
                    userComments = userCommentLocal.retrieveWhere(
                            "SELECT UC FROM UserComment AS UC WHERE UC.restaurant.id = ?0 ORDER BY UC.timestamp DESC",
                            id);
                }
            }
            return userComments;
        } catch (CRUDException e) {
            addException(e);
            return new ArrayList<UserComment>();
        }
    }

    public UserComment getNewUserComment() {
        return newUserComment;
    }

    public void setNewUserComment(UserComment newUserComment) {
        this.newUserComment = newUserComment;
    }

    public void setLoginFormBackend(LoginFormBackend loginFormBackend) {
        this.loginFormBackend = loginFormBackend;
    }

    public String addUserCommentAction() {
        String outcome = null;
        String message = null;
        if (loginFormBackend == null) {
            addMessage(message = "Cannot access to loginFormBackend");
            log.infof(message);
            return outcome;
        }
        User user = loginFormBackend.getUser();
        if (user == null) {
            addMessage(message = "Unlogged user");
            log.infof(message);
            return outcome;
        }

        log.infof("Setting restaurant to %s", getRestaurant());
        newUserComment.setRestaurant(getRestaurant());

        Date now = new Date();
        log.infof("Setting date to %s", now);
        newUserComment.setTimestamp(now);

        log.infof("Setting user to %s", user);
        newUserComment.setUser(user);

        try {
            newUserComment = userCommentLocal.create(newUserComment);
            log.infof("Created comment %s", newUserComment);
            userComments = null; // force reload view.
        } catch (CRUDException e) {
            addException(e);
        }
        newUserComment = new UserComment();
        return outcome;
    }

    public void validateComment(FacesContext fc, UIComponent comp, Object value) throws ValidatorException {
        final int MINIMUM_LENGTH = 16;
        if (((String) value).length() < MINIMUM_LENGTH)
            throw new ValidatorException(
                    new FacesMessage("Comment must be at least " + MINIMUM_LENGTH + " characters."));
    }

    /**
     * need test this method
     *
     * @return
     * @throws CRUDException
     */

    public String addConfirmedRest() throws CRUDException {
        log.infof("El restaurante guardado en DB es %s", getRestaurant());
        restaurant = restaurantLocal.create(restaurant);
        restaurant = new Restaurant();
        return "index";

    }

    public Collection<Restaurant> getList() {
        try {
            return restaurantLocal.retrieveAll();
        } catch (CRUDException e) {
            return new ArrayList<Restaurant>(); // una lista vacía.
        }
    }
}