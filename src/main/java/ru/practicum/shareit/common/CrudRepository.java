package ru.practicum.shareit.common;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    T create(T t);

    Optional<T> read(Long id);

    T update(T t);

    void delete(Long id);

    List<T> readAll(Long id);
}
