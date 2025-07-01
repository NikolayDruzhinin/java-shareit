package ru.practicum.shareit.user;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.common.CrudService;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements CrudService<UserDto, User> {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public User handlePost(UserDto user) {
        return repository.create(mapper.toDal(user));
    }

    @Override
    public User handleGet(Long id) {
        return repository.read(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User handlePatch(UserDto userDto, Long id) {
        Optional<User> user = repository.read(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }

        if (repository.searchByEmail(userDto.getEmail()).isPresent()) {
            throw new ValidationException("User with email " +
                    userDto.getEmail() + " has already registered");
        }
        if (userDto.getName() != null) {
            user.get().setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            user.get().setEmail(userDto.getEmail());
        }
        repository.update(user.get());
        return user.get();
    }


    @Override
    public void handleDelete(Long id) {
        repository.delete(id);
    }
}
