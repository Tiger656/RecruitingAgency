package com.itechart.agency.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    Long create(final T t);

    Optional<T> findById(final Integer id);

    List<T> findAll();

    Long update(final T t);

    void deleteById(final Integer id);

    void delete(final T t);
}