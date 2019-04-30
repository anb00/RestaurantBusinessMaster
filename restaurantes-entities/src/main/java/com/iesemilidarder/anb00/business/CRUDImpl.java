package com.iesemilidarder.anb00.business;


import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

public class CRUDImpl<K, T> implements CRUD<K, T> {

    private static final Logger log = Logger.getLogger(CRUDImpl.class);

    @PersistenceContext(unitName = "restaurantes")
    protected EntityManager em;

    private final Class<T> cl;

    private final String SELECT_ALL;

    CRUDImpl(Class<T> cl) {
        this.cl = cl;
        SELECT_ALL = "SELECT e FROM " + cl.getSimpleName() + " AS e";
    }

    @Override
    public T create(T item) throws CRUDException {
        try {
            log.infof("create(%s)", item);
            em.persist(item);
            return item;
        } catch (EntityExistsException e) {
            throw new CRUDException("User " + item + " already exists", e);
        } catch (IllegalArgumentException e) {
            throw new CRUDException(item.getClass() + " is not an Entity: " + e, e);
        } catch (Exception e) {
            throw new CRUDException(item + ": " + e, e);
        }
    };

    @Override
    public Collection<T> retrieveAll() throws CRUDException {
        return retrieveAll(0, 0);
    }

    @Override
    public Collection<T> retrieveAll(int first, int count) throws CRUDException {
        return retrieveWhere(first, count, SELECT_ALL);
    }

    @Override
    public Collection<T> retrieveWhere(int first, int count, String hql_statement, Object... hql_args)
            throws CRUDException {
        try {
            TypedQuery<T> q = em.createQuery(hql_statement, cl);
            for (int i = 0; i < hql_args.length; i++)
                q.setParameter(i, hql_args[i]);
            if (first > 0)
                q.setFirstResult(first);
            if (count > 0)
                q.setMaxResults(count);
            return q.getResultList();
        } catch (Exception e) {
            throw new CRUDException(String.format("retrieveWhere(%d, %d, %s, ...): " + e,
                    first, count, hql_statement),
                    e);
        }
    }

    @Override
    public Collection<T> retrieveWhere(String where, Object... args) throws CRUDException {
        return retrieveWhere(0, 0, where, args);
    }

    @Override
    public T retrieve(K key) throws CRUDException {
        try {
            return em.find(cl, key);
        } catch (Exception e) {
            throw new CRUDException(String.format("retrieve(%s): ", key) + e, e);
        }
    }

    @Override
    public T update(T item) throws CRUDException {
        try {
            return em.merge(item);
        } catch (Exception e) {
            throw new CRUDException(String.format("update(%s): ", item) + e, e);
        }
    }

    @Override
    public T delete(K key) throws CRUDException {
        try {
            T result = em.find(cl, key);
            if (result == null)
                throw new CRUDException(String.format("delete(%s): instance not found", key));
            em.remove(result);
            return result;
        } catch (Exception e) {
            throw new CRUDException(String.format("delete(%s): %s", key, e), e);
        }
    }
}
