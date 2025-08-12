package ru.practicum.shareit.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentCreateDto;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.util.Constants;

import java.util.Collection;

@Service
@FeignClient(name = "server",
        contextId = "itemClient",
        path = "/items",
        url = "${feign.client.config.server.url}")
public interface ItemClient {
    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable long itemId);

    @GetMapping
    public Collection<ItemDto> getUserItems(@RequestHeader(Constants.USER_ID_HEADER) long userId);

    @GetMapping("/search")
    public Collection<ItemDto> itemsSearch(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                           @RequestParam String text);

    @PostMapping
    public ItemDto createItem(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                              @RequestBody ItemDto item);

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                              @PathVariable long itemId,
                              @RequestBody ItemDto item);

    @PostMapping("/{itemId}/comment")
    public CommentDto addComment(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                 @PathVariable long itemId,
                                 @RequestBody CommentCreateDto comment);
}
