package com.iesemilidarder.anb00.business;

import javax.ejb.Local;

import com.iesemilidarder.anb00.entities.User;

@Local
public interface UserLocal extends CRUD<Long, User> {
    User authenticate(String nick, String password) throws AuthenticationException;
    User retrieve(String nick) throws CRUDException;
    void touch(Long key) throws CRUDException;
}