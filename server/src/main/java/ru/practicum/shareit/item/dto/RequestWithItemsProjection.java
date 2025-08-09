package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.ItemRequest;

public interface RequestWithItemsProjection {
    ItemRequest getRequest();

    Item getItem();

    Long getOwnerId();
}
