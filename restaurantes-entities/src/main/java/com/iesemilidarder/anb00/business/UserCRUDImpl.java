package com.iesemilidarder.anb00.business;


import java.util.Collection;
import java.util.Iterator;

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.iesemilidarder.anb00.entities.User;

@Stateless
public class UserCRUDImpl extends CRUDImpl<Long, User> implements UserLocal {

    private static final Logger log = Logger.getLogger(UserCRUDImpl.class);

    public UserCRUDImpl() {
        super(User.class);
    }

    /**
     * Retrieves a {@link User} from the database, based on the {@code nick}.
     *
     * @param nick nickname of the user we are searching for.
     * @throws CRUDException if something goes wrong.
     *
     * @see com.iesemilidarder.anb00.business.UserLocal#retrieve(java.lang.String)
     */
    public User retrieve(String nick) throws CRUDException {
        Collection<User> lu = retrieveWhere("SELECT u FROM User AS u WHERE u.nick = ?0", nick);
        Iterator<User> it = lu.iterator();
        if (!it.hasNext())
            return null;
        return it.next();
    }

    public User authenticate(String nick, String password) throws AuthenticationException {
        User u = null;
        try {
            u = retrieve(nick);
        } catch (CRUDException e) {
            throw new AuthenticationException(nick, e);
        }

        if (u == null || !password.equals(u.getEncryptedPass()))
            throw new AuthenticationException(nick);
        return u;
    }

    public void touch(Long key) throws CRUDException {
        User u = retrieve(key);
        u.setLastUsed(System.currentTimeMillis());
    }

}

/*
 * public class UserCRUDImpl extends CRUDImpl<Long, User> implements UserLocal {
 *
 * public UserCRUDImpl() { super(User.class); }
 *
 * @Override public User create(User u) throws CRUDException { u =
 * super.create(u); long now = System.currentTimeMillis(); //u.setCreated(now);
 * //u.setLastUsed(now); return u; }
 *
 * public User retrieve(String nick) throws CRUDException { Collection<User> l =
 * retrieveWhere("where nick = ?", nick); if (l.size() == 0) { throw new
 * CRUDException("User " + l + " not found"); } // l.size() != 0 ==> l.size() ==
 * 1 return l.iterator().next(); }
 *
 * public User authenticate(String nick, String password) throws
 * AuthenticationException { try { User u = retrieve(nick);
 *
 * // must hash() pass before comparing. // password = sha1(password); if ( u ==
 * null || !password.equals(u.getEncryptedPass())) throw new
 * AuthenticationException("Usuario invalido " + nick); return u; } catch(
 * CRUDException e ) { throw new AuthenticationException("Usuario invalido " +
 * nick, e); } }
 *
 * public void touch(Long key) throws CRUDException { User u = retrieve(key);
 * u.setLastUsed(System.currentTimeMillis()); }
 */
