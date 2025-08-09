package ru.practicum.shareit.item.dto;


import ru.practicum.shareit.item.model.Item;

import java.util.List;

public final class ItemMapper {
    private ItemMapper() {
    }

    public static ItemDto toDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getAvailable());
    }


    public static Item toModel(ItemDto item) {
        return new Item(item.getId(), item.getName(), item.getDescription(), item.getAvailable());
    }

    public static ItemRequestResponseDto toItemRequestDto(RequestWithItemsProjection proj) {
        ItemForRequestDto ir = new ItemForRequestDto(proj.getItem().getId(),
                proj.getItem().getName(),
                proj.getOwnerId());
        return new ItemRequestResponseDto(proj.getRequest().getId(),
                proj.getRequest().getDescription(),
                proj.getRequest().getCreatedAt(),
                List.of(ir)
        );
    }
}
