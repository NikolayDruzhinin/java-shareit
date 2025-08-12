package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.client.ItemRequestClient;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestResponseDto;
import ru.practicum.shareit.util.Constants;

import java.util.List;


@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {
    private final ItemRequestClient requestCLient;

    @PostMapping
    ItemRequestDto createRequest(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                 @RequestBody ItemRequestDto itemRequest) {
        return requestCLient.createRequest(userId, itemRequest);
    }

    @GetMapping
    List<ItemRequestDto> findUserRequests(@RequestHeader(Constants.USER_ID_HEADER) long userId) {
        return requestCLient.findRequests(userId);
    }

    @GetMapping("/all")
    List<ItemRequestDto> findAllRequests(@RequestHeader(Constants.USER_ID_HEADER) long userId) {
        return requestCLient.findAllRequests(userId);
    }

    @GetMapping("/{requestId}")
    ItemRequestResponseDto findRequest(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                       @PathVariable Long requestId) {
        return requestCLient.findRequest(userId, requestId);
    }
}
