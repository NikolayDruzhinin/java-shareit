package ru.practicum.shareit.common;

import ru.practicum.shareit.item.Item;

import java.util.List;

public interface ItemCrudRepository extends CrudRepository<Item> {
    List<Item> search(String str);
}
