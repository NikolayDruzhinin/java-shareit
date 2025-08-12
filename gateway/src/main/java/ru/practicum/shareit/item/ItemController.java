package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.client.ItemClient;
import ru.practicum.shareit.item.dto.CommentCreateDto;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.util.Constants;

import java.util.Collection;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemClient itemClient;

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable long itemId) {
        return itemClient.getItemById(itemId);
    }

    @GetMapping
    public Collection<ItemDto> getUserItems(@RequestHeader(Constants.USER_ID_HEADER) long userId) {
        return itemClient.getUserItems(userId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> itemsSearch(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                           @RequestParam String text) {
        return itemClient.itemsSearch(userId, text);
    }

    @PostMapping
    public ItemDto createItem(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                              @Valid @RequestBody ItemDto item) {
        return itemClient.createItem(userId, item);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                              @PathVariable long itemId,
                              @Valid @RequestBody ItemDto item) {
        return itemClient.updateItem(userId, itemId, item);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComment(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                 @PathVariable long itemId,
                                 @Valid @RequestBody CommentCreateDto comment) {
        return itemClient.addComment(userId, itemId, comment);
    }
}
