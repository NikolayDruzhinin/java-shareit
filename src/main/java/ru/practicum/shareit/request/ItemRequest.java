package ru.practicum.shareit.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Data
public class ItemRequest {
    private Long id;
    @NotBlank
    private String description;
    private User requestor;
    @PastOrPresent
    private LocalDateTime created;
}
