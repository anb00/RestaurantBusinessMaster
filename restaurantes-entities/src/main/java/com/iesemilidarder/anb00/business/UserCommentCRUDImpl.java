package com.iesemilidarder.anb00.business;

import javax.ejb.Stateless;

import com.iesemilidarder.anb00.entities.UserComment;

@Stateless
public class UserCommentCRUDImpl extends CRUDImpl<Long, UserComment> implements UserCommentLocal {

    public UserCommentCRUDImpl() {
        super(UserComment.class);
    }
}
