package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.handleGet(id);
    }

    @PostMapping
    public User post(@Valid @RequestBody UserDto userDto) {
        return userService.handlePost(userDto);
    }

    @PatchMapping("/{id}")
    public User patch(@PathVariable(required = false) String id,
                      @RequestBody UserDto userDto) {
        if (id.equals("null")) {
            return userService.handlePost(userDto);
        }
        return userService.handlePatch(userDto, Long.parseLong(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.handleDelete(id);
    }
}
