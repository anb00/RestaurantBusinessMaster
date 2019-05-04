package com.iesemilidarder.anb00.business;

import javax.ejb.Stateless;

import com.iesemilidarder.anb00.entities.UserComment;

@Stateless
public class UserCommentCRUDImpl extends CRUDImpl<Long, UserComment> implements UserCommentLocal {

    public UserCommentCRUDImpl() {
        super(UserComment.class);
    }

    @Override
    public UserComment create(UserComment item) throws CRUDException {
        //em.refresh(item.getUser());  // need to refresh to make User instance managed.
        return super.create(item); // now, add.
    }

}
