package com.assignment.vanessacm.service;

import java.util.List;

/**
 * Interface for Crud services
 *
 * @param <T> Entity
 */
public interface Crud<T> {
    T save(T object);

    T findById(Integer id);

    List<T> findAll();

    void deleteById(Integer id);

    T update(Integer id, T object);
}
