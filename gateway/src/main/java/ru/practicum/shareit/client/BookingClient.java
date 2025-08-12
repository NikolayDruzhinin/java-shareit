package ru.practicum.shareit.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.BookingsState;
import ru.practicum.shareit.util.Constants;

import java.util.List;

@Service
@FeignClient(name = "server",
        contextId = "bookingClient",
        path = "/bookings",
        url = "${feign.client.config.server.url}")
public interface BookingClient {

    @PatchMapping("/{bookingId}")
    BookingDto approveBooking(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                              @PathVariable long bookingId,
                              @RequestParam boolean approved);

    @GetMapping("/{bookingId}")
    BookingDto getBookingById(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                              @PathVariable long bookingId);

    @GetMapping
    ResponseEntity<Object> getBookings(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                       @RequestParam(defaultValue = "all") BookingsState state);

    @GetMapping("/owner")
    List<BookingDto> getOwnerBookings(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                      @RequestParam(defaultValue = "all") BookingsState state);

    @PostMapping
    BookingDto createBooking(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                             @RequestBody BookingCreateDto booking);
}
