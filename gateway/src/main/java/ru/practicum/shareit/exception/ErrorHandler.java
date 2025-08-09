package ru.practicum.shareit.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeignException(FeignException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.status());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        try {
            if (ex.contentUTF8() != null && !ex.contentUTF8().isEmpty()) {
                Map<String, Object> errorDetails = new ObjectMapper().readValue(ex.contentUTF8(), Map.class);
                body.putAll(errorDetails);
            } else {
                body.put("error", status.getReasonPhrase());
            }
        } catch (Exception e) {
            body.put("error", ex.contentUTF8());
        }

        return new ResponseEntity<>(body, status);
    }
}
