package com.iesemilidarder.anb00.business;


import javax.ejb.Local;

import com.iesemilidarder.anb00.entities.UserComment;

@Local
public interface UserCommentLocal extends CRUD<Long, UserComment> {

}
