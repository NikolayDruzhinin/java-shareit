package ru.practicum.shareit.common;

public interface CrudService<D, T> {
    T insert(D d);

    T get(Long id);

    T update(D d, Long id);

    void delete(Long id);
}
