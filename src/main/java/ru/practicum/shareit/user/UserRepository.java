package ru.practicum.shareit.user;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.common.CrudRepository;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository implements CrudRepository<User> {
    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    @Override
    public User create(User user) {
        if (users.values().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new ValidationException("User with email " + user.getEmail() + " has already been added");
        }
        user.setId(idCounter.incrementAndGet());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> read(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User update(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(Long id) {
        users.remove(id);
    }

    @Override
    public List<User> readAll(Long id) {
        return null;
    }

    public void updateByEmail(User user) {
        User oldUser = users.values().stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findFirst().orElseThrow(() ->
                        new NotFoundException("User with email " + user.getEmail() + " not found"));
        if (user.getId() == null)
            user.setId(oldUser.getId());
        users.put(user.getId(), user);
    }

    public Optional<User> searchByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
