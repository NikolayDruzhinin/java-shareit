package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.client.UserClient;
import ru.practicum.shareit.user.dto.UserDto;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserClient userClient;

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        return userClient.getUserById(userId);
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto user) {
        return userClient.createUser(user);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@Valid @RequestBody UserDto user, @PathVariable long userId) {
        return userClient.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userClient.deleteUser(userId);
    }
}
