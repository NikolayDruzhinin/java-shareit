package ru.practicum.shareit.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.model.User;


public class UserRepositoryExtensionImpl implements UserRepositoryExtension {
    @PersistenceContext
    private EntityManager entityManager;

    public User findByIdOrThrow(long userId) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id = :userId", User.class)
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("User %d not found!", userId)));
    }
}
