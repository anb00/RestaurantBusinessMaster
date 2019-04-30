package com.iesemilidarder.anb00.business;

import java.util.Collection;

public interface CRUD<K, T> {

    T create(T item) throws CRUDException;

    Collection<T> retrieveAll() throws CRUDException;

    Collection<T> retrieveAll(int first, int count) throws CRUDException;

    Collection<T> retrieveWhere(int first, int count, String where, Object... args) throws CRUDException;

    Collection<T> retrieveWhere(String where, Object... args) throws CRUDException;

    T retrieve(K key) throws CRUDException;

    T update(T item) throws CRUDException;

    T delete(K key) throws CRUDException;
}