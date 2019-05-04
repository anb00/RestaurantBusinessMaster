package com.iesemilidarder.anb00.business;

import java.util.List;

public interface CRUD<K, T> {

    T create(T item) throws CRUDException;

    List<T> retrieveAll() throws CRUDException;

    List<T> retrieveAll(int first, int count) throws CRUDException;

    List<T> retrieveWhere(int first, int count, String where, Object... args) throws CRUDException;

    List<T> retrieveWhere(String where, Object... args) throws CRUDException;

    T retrieve(K key) throws CRUDException;

    T update(T item) throws CRUDException;

    T delete(K key) throws CRUDException;
}
