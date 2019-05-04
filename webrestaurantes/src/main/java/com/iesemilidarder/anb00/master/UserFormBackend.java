package com.iesemilidarder.anb00.master;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jboss.logging.Logger;

import com.iesemilidarder.anb00.business.CRUDException;
import com.iesemilidarder.anb00.entities.User;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class UserFormBackend extends BackendBaseClass {

    private static final Logger log = Logger.getLogger(UserFormBackend.class);

    User user = new User();
    String password;
    String password2;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        String eMail = (String) value;
        if (eMail.indexOf("@") < 0) {
            FacesMessage message = new FacesMessage("Invalid email address");
            throw new ValidatorException(message);
        }
    }

    public String addConfirmedUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (userLocal == null) {
                addMessage("no access to EJB to register user %s", user);
                return "index";
            }
            user.setEncryptedPass(password); // the password should be encrpted before going to database.
            long now = System.currentTimeMillis();
            user.setCreated(now);
            user.setLastUsed(now);
            user = userLocal.create(user); // here goes to database the user ul
            log.infof("Created user %s", user);
        } catch (CRUDException e) {
            fc.addMessage(null, new FacesMessage("Error al añadir el usuario: " + e));
            return "register";
        }
        fc.addMessage(null, new FacesMessage("Usuario " + user + " añadido correctamente"));
        user = new User();
        return "index";
    }

}