package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Constants;
import ru.practicum.shareit.item.dto.ItemRequestDto;
import ru.practicum.shareit.item.dto.ItemRequestResponseDto;

import java.util.List;


@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {
    private final ItemRequestService requestService;

    @PostMapping
    public ItemRequestDto createRequest(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                        @RequestBody ItemRequestDto itemRequest) {
        return requestService.createRequest(userId, itemRequest);
    }

    @GetMapping
    public List<ItemRequestDto> findUserRequests(@RequestHeader(Constants.USER_ID_HEADER) long userId) {
        return requestService.findRequests(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDto> findAllRequests(@RequestHeader(Constants.USER_ID_HEADER) long userId) {
        return requestService.findAllRequests(userId);
    }

    @GetMapping("/{requestId}")
    public ItemRequestResponseDto findRequest(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                              @PathVariable Long requestId) {
        return requestService.findRequest(userId, requestId);
    }
}
