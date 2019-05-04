package com.iesemilidarder.anb00.business;

public class AuthenticationException extends Exception {

    private static final long serialVersionUID = -515446780592288286L;

    public AuthenticationException(String authmessage, Throwable throwable){
        super(authmessage, throwable);
    }
    public AuthenticationException(String authmessage){
        super(authmessage);
    }
}
