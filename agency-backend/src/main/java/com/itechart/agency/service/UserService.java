package com.itechart.agency.service;

import java.util.List;

public interface UserService<T> {
    T create(final T t);

    T findById(final Long id);

    List<T> findAll();

    T update(final T t);

    void deleteById(final Long id);

}
