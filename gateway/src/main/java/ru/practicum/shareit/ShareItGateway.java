package ru.practicum.shareit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.practicum.shareit.client.BookingClient;
import ru.practicum.shareit.client.ItemClient;
import ru.practicum.shareit.client.ItemRequestClient;
import ru.practicum.shareit.client.UserClient;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = GatewayFeignMarker.class,
        clients = {
                BookingClient.class,
                ItemClient.class,
                UserClient.class,
                ItemRequestClient.class
        })
public class ShareItGateway {
    public static void main(String[] args) {
        SpringApplication.run(ShareItGateway.class, args);
    }

}
