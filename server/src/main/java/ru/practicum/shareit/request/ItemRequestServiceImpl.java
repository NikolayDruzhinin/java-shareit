package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {
    private final UserRepository userRepository;
    private final ItemRequestRepository requestRepository;


    @Override
    public ItemRequestDto createRequest(long userId, ItemRequestDto itemRequest) {
        User user = userRepository.findByIdOrThrow(userId);
        ItemRequest request = ItemRequestMapper.toItemRequest(itemRequest);
        request.setUser(user);
        request.setCreatedAt(LocalDateTime.now());
        request = requestRepository.save(request);
        return ItemRequestMapper.toItemRequestDto(request);
    }

    @Override
    public List<ItemRequestDto> findRequests(long userId) {
        userRepository.findByIdOrThrow(userId);
        return requestRepository.findByUser_Id(userId).stream()
                .map(ItemRequestMapper::toItemRequestDto)
                .toList();
    }

    @Override
    public List<ItemRequestDto> findAllRequests(long userId) {
        userRepository.findByIdOrThrow(userId);
        return requestRepository.findAll().stream()
                .map(ItemRequestMapper::toItemRequestDto)
                .toList();
    }

    @Override
    public ItemRequestResponseDto findRequest(Long userId, Long requestId) {
        userRepository.findByIdOrThrow(userId);
        List<ItemRequestResponseDto> itemRequestResponse = requestRepository
                .findRequestsWithItemsAndOwnersByRequestId(requestId)
                .stream()
                .map(ItemMapper::toItemRequestDto)
                .toList();
        List<ItemForRequestDto> itemForRequestDtos = itemRequestResponse.stream()
                .flatMap(dto -> dto.getItems().stream())
                .toList();
        ItemRequestResponseDto responseDto = itemRequestResponse.getFirst();
        responseDto.setItems(itemForRequestDtos);

        return responseDto;
    }
}
