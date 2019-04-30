package com.iesemilidarder.anb00.master;


import com.iesemilidarder.anb00.business.AuthenticationException;
import com.iesemilidarder.anb00.business.UserLocal;
import com.iesemilidarder.anb00.entities.User;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class LoginFormBackend {

    public static final FacesMessage AUTHENTICATION_FAILURE = new FacesMessage("Authentication failure");

    String userid;
    String password;

    User user = null; // not yet authenticated.

    @Resource(lookup = "java:module/UserCRUDImpl!com.iesemilidarder.anb00.business.UserLocal")
    UserLocal ul;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Action Method
    public String loginAction() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            user = ul.authenticate(userid, password);
            password = null;
            return "index";
        } catch (AuthenticationException e) {
            for(Throwable t = e; t != null; t = t.getCause())
                fc.addMessage(null, new FacesMessage(t.toString()));
            fc.addMessage(null, AUTHENTICATION_FAILURE);
            return "login";
        }

    }
}