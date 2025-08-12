package ru.practicum.shareit.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.BookingsState;
import ru.practicum.shareit.client.BookingClient;
import ru.practicum.shareit.util.Constants;

import java.util.List;


@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingClient bookingClient;

    @PostMapping
    public BookingDto createBooking(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                    @Valid @RequestBody BookingCreateDto booking) {
        return bookingClient.createBooking(userId, booking);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approveBooking(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                     @PathVariable long bookingId,
                                     @RequestParam boolean approved) {
        return bookingClient.approveBooking(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBookingById(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                     @PathVariable long bookingId) {
        return bookingClient.getBookingById(userId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> getBookings(@RequestHeader(Constants.USER_ID_HEADER) long userId,
                                              @RequestParam(defaultValue = "all") BookingsState state) {
        return bookingClient.getBookings(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> getOwnerBookings(
            @RequestHeader(Constants.USER_ID_HEADER) long userId,
            @RequestParam(defaultValue = "all") BookingsState state) {
        return bookingClient.getOwnerBookings(userId, state);
    }
}
