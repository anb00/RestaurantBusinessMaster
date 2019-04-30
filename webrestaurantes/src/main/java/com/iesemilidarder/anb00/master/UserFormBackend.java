package com.iesemilidarder.anb00.master;




import com.iesemilidarder.anb00.business.CRUDException;
import com.iesemilidarder.anb00.business.UserLocal;
import com.iesemilidarder.anb00.entities.User;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;


@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class UserFormBackend {

    private static final FacesMessage PASSWORDS_MUST_MATCH = new FacesMessage("The passwords must be non null and the same value");
    private static final Logger log = Logger.getLogger(UserFormBackend.class);

    User user = new User();
    String password = "";
    String password2 = "";

    @Resource(lookup = "java:module/UserCRUDImpl!com.iesemilidarder.anb00.business.UserLocal")
    UserLocal ul;

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




    public void validateEmail(FacesContext context, UIComponent toValidate,
                              Object value) throws ValidatorException {
        String eMail = (String) value;
        if(eMail.indexOf("@")<0) {
            FacesMessage message = new FacesMessage("Invalid email address");
            throw new ValidatorException(message);
        }
    }


    public String addConfirmedUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (ul == null )  {
                fc.addMessage(null, new FacesMessage("No hay acceso a la aplicacin EJB"));
                return "Register";
            }
            user.setEncryptedPass(password); // the password should be encrpted before going to database.
            log.info("La password asociada a user = " + user + " es " + user.getEncryptedPass() + ", UserFormBackend.password = " + password);
            long now = System.currentTimeMillis();
            user.setCreated(now);
            user.setLastUsed(now);
            user = ul.create(user); //here goes to database the user ul
        } catch (CRUDException e) {
            fc.addMessage(null, new FacesMessage("Error al añadir el usuario: " + e));
            return "Register";
        }
        fc.addMessage(null, new FacesMessage("Usuario " + user + " añadido correctamente"));
        user = new User();
        return "index";
    }

}