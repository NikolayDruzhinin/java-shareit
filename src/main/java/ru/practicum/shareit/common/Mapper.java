package ru.practicum.shareit.common;

public interface Mapper<D, T> {
    D toDto(T t);

    T toDal(D d);
}
