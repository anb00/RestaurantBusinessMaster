package com.iesemilidarder.anb00.master;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.iesemilidarder.anb00.business.RestaurantLocal;
import com.iesemilidarder.anb00.business.UserCommentLocal;
import com.iesemilidarder.anb00.business.UserLocal;

/**
 * This class is the superclass providing all the needed elements and
 * interfaces.
 */
public class BackendBaseClass {

    @Resource(lookup = "java:module/UserCRUDImpl!com.iesemilidarder.anb00.business.UserLocal")
    protected UserLocal userLocal;
    @Resource(lookup = "java:module/RestaurantCRUDImpl!com.iesemilidarder.anb00.business.RestaurantLocal")
    protected RestaurantLocal restaurantLocal;
    @Resource(lookup = "java:module/UserCommentCRUDImpl!com.iesemilidarder.anb00.business.UserCommentLocal")
    protected UserCommentLocal userCommentLocal;

    protected void addMessage(String fmt, Object... args) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage m = new FacesMessage(String.format(fmt, args));
        fc.addMessage(null, m);
    }

    protected void addException(final Throwable e) {
        FacesContext fc = FacesContext.getCurrentInstance();
        String fmt = "Exception %s";
        Throwable t;
        for (t = e; t != null; t = t.getCause()) {
            addMessage(fmt, t.getMessage());
            fmt = "... caused by %s";
        }
    }
}