package ru.practicum.shareit.request;

import ru.practicum.shareit.item.dto.ItemRequestDto;
import ru.practicum.shareit.item.dto.ItemRequestResponseDto;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDto createRequest(long userId, ItemRequestDto itemRequest);

    List<ItemRequestDto> findRequests(long userId);

    List<ItemRequestDto> findAllRequests(long userId);

    ItemRequestResponseDto findRequest(Long requestId, Long id);
}
