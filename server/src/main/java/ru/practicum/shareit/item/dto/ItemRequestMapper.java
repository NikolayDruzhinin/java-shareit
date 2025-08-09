package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.request.model.ItemRequest;

public class ItemRequestMapper {
    private ItemRequestMapper() {
    }

    public static ItemRequest toItemRequest(ItemRequestDto itemRequestDto) {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setDescription(itemRequestDto.getDescription());
        return itemRequest;
    }

    public static ItemRequestDto toItemRequestDto(ItemRequest itemRequest) {
        return new ItemRequestDto(itemRequest.getId(), itemRequest.getDescription(), itemRequest.getCreatedAt());
    }
}
