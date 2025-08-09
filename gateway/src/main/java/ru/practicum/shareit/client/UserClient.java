package ru.practicum.shareit.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;

@Service
@FeignClient(name = "server",
        contextId = "userClient",
        path = "/users",
        url = "${feign.client.config.server.url}")
public interface UserClient {
    @GetMapping("/{userId}")
    UserDto getUserById(@PathVariable long userId);

    @PostMapping
    UserDto createUser(@RequestBody UserDto user);

    @PatchMapping("/{userId}")
    UserDto updateUser(@PathVariable long userId, @RequestBody UserDto user);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable long userId);
}
