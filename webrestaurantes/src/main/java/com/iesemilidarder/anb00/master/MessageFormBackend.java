package com.iesemilidarder.anb00.master;

import org.jboss.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MessageFormBackend {

    private static final FacesMessage MSG_MUST_BE_SOMETHING = new FacesMessage("The msg must not be empty");
    private static final Logger log = Logger.getLogger(MessageFormBackend.class);

    String Msgsender;
    String Msgreceiver;
    String idMsg;
    String msg = "";

    MessageFormBackend messageFormBackend = new MessageFormBackend();
     public MessageFormBackend() {

    }

    public String getMsgsender() {
        return Msgsender;
    }

    public void setMsgsender(String msgsender) {
        Msgsender = msgsender;
    }

    public String getMsgreceiver() {
        return Msgreceiver;
    }

    public void setMsgreceiver(String msgreceiver) {
        Msgreceiver = msgreceiver;
    }

    public String getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(String idMsg) {
        this.idMsg = idMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void greetingMessage() {

    }
}
