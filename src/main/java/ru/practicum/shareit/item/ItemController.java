package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor

public class ItemController {
    private final ItemService itemService;
    private final UserService userService;

    @GetMapping("/search")
    public List<Item> search(@RequestParam("text") String searchText,
                             @RequestHeader("X-Sharer-User-Id") final String userId) {
        userService.get(Long.parseLong(userId));
        return itemService.search(searchText);
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable Long id,
                    @RequestHeader("X-Sharer-User-Id") final String userId) {
        userService.get(Long.parseLong(userId));
        return itemService.get(id);
    }

    @GetMapping
    public List<Item> getAll(@RequestHeader("X-Sharer-User-Id") final String userId) {
        userService.get(Long.parseLong(userId));
        return itemService.getAll(Long.parseLong(userId));
    }

    @PostMapping
    public Item post(@Valid @RequestBody ItemDto itemDto,
                     @RequestHeader("X-Sharer-User-Id") final String userId) {
        Long uId = Long.parseLong(userId);
        userService.get(uId);
        itemDto.setOwner(uId);
        return itemService.insert(itemDto);
    }

    @PatchMapping("/{id}")
    public Item patch(@PathVariable Long id,
                      @RequestBody ItemDto itemDto,
                      @RequestHeader("X-Sharer-User-Id") final String userId) {
        Long uId = Long.parseLong(userId);
        userService.get(uId);
        itemDto.setOwner(uId);
        return itemService.update(itemDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
