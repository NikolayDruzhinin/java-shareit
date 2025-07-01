package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.common.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository implements CrudRepository<Item> {
    private final Map<Long, Item> items = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Item create(Item item) {
        item.setId(idCounter.incrementAndGet());
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Optional<Item> read(Long id) {
        return Optional.of(items.get(id));
    }

    @Override
    public Item update(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public void delete(Long id) {
        items.remove(id);
    }

    @Override
    public List<Item> readAll(Long userId) {
        return items.values().stream()
                .filter(item -> item.getOwner().equals(userId))
                .toList();
    }

    public List<Item> search(String searchText) {
        return items.values().stream()
                .filter(item -> item.getName() != null && item.getAvailable() != null)
                .filter(item -> item.getName().toLowerCase().contains(searchText.toLowerCase()) &&
                        item.getAvailable().equals(true))
                .toList();
    }
}
