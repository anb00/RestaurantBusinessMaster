package com.iesemilidarder.anb00.business;

public class AuthenticationException extends Exception {
    public AuthenticationException(String authmessage, Throwable throwable){
        super(authmessage, throwable);
    }
    public AuthenticationException(String authmessage){
        super(authmessage);
    }
}