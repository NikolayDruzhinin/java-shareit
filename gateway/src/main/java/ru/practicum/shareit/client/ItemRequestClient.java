package ru.practicum.shareit.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestResponseDto;
import ru.practicum.shareit.util.Constants;

import java.util.List;

@Service
@FeignClient(name = "server",
        contextId = "requestClient",
        path = "/requests",
        url = "${feign.client.config.server.url}")
public interface ItemRequestClient {
    @PostMapping
    ItemRequestDto createRequest(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                 @RequestBody ItemRequestDto itemRequest);

    @GetMapping
    List<ItemRequestDto> findRequests(@RequestHeader(Constants.USER_ID_HEADER) long userId);

    @GetMapping("/all")
    List<ItemRequestDto> findAllRequests(@RequestHeader(Constants.USER_ID_HEADER) long userId);

    @GetMapping("/{requestId}")
    ItemRequestResponseDto findRequest(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                       @PathVariable Long requestId);
}
