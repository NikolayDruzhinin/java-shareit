package ru.practicum.shareit.user;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.common.Mapper;

@Component
public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public UserDto toDto(User user) {
        return new UserDto(user.getName(), user.getEmail());
    }

    @Override
    public User toDal(UserDto userDto) {
        return new User(userDto.getName(), userDto.getEmail());
    }
}
