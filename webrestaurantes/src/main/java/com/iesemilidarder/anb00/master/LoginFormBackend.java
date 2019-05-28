package com.iesemilidarder.anb00.master;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.iesemilidarder.anb00.business.AuthenticationException;
import com.iesemilidarder.anb00.entities.User;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class LoginFormBackend extends BackendBaseClass {

    public static final FacesMessage AUTHENTICATION_FAILURE = new FacesMessage("Authentication failure");

    String userid;
    String password;

    User user = null; // not yet authenticated.

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
        try {
            user = userLocal.authenticate(userid, password);
            userid = null;
            password = null;
            return "OK";
        } catch (AuthenticationException e) {
            addException(e);
            return "ERROR";
        }
    }

    public String logoutAction() {
        if (user != null)
            addMessage("User %s logged out", user.getNick());
        user = null;
        userid = null;
        password = null;
        return "OK";
    }
}