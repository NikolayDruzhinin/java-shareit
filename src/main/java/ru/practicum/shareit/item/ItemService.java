package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.common.CrudService;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService implements CrudService<ItemDto, Item> {
    private final ItemRepository repository;
    private final ItemMapper mapper;

    @Override
    public Item handlePost(ItemDto itemDto) {
        return repository.create(mapper.toDal(itemDto));
    }

    @Override
    public Item handleGet(Long id) {
        return repository.read(id)
                .orElseThrow(() -> new NotFoundException("Item with id " + id + " not found"));
    }

    public List<Item> handleGetAll(Long userId) {
        return repository.readAll(userId);
    }

    @Override
    public Item handlePatch(ItemDto itemDto, Long id) {
        Optional<Item> inMemoryItem = repository.read(id);
        if (inMemoryItem.isEmpty()) {
            throw new NotFoundException("Item with id " + id + " not found");
        }
        if (!inMemoryItem.get().getOwner().equals(itemDto.getOwner())) {
            throw new NotFoundException("Invalid owner of item with id " + id);
        }
        inMemoryItem.get().setOwner(itemDto.getOwner());
        inMemoryItem.get().setDescription(itemDto.getDescription());
        inMemoryItem.get().setAvailable(itemDto.getAvailable());
        inMemoryItem.get().setName(itemDto.getName());
        inMemoryItem.get().setRequest(itemDto.getRequest());
        repository.update(inMemoryItem.get());
        return inMemoryItem.get();
    }

    @Override
    public void handleDelete(Long id) {
        repository.delete(id);
    }

    public List<Item> search(String searchText) {
        if (searchText.isBlank()) {
            return new ArrayList<>();
        }
        return repository.search(searchText);
    }
}
