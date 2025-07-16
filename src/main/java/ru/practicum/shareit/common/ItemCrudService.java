package ru.practicum.shareit.common;

import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.item.ItemDto;

import java.util.List;

public interface ItemCrudService extends CrudService<ItemDto, Item> {
    List<Item> getAll(Long id);
}
