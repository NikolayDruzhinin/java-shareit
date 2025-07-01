package ru.practicum.shareit.common;

public interface CrudService<D, T> {
    T handlePost(D d);

    T handleGet(Long id);

    T handlePatch(D d, Long id);

    void handleDelete(Long id);
}
