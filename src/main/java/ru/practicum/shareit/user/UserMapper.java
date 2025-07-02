package ru.practicum.shareit.user;

public class UserMapper {
    private UserMapper() {
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getName(), user.getEmail());
    }

    public static User toDal(UserDto userDto) {
        return new User(userDto.getName(), userDto.getEmail());
    }
}
