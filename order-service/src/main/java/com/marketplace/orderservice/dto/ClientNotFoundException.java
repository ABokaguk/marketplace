package com.marketplace.orderservice.dto;

import java.util.UUID;

public class ClientNotFoundException extends Exception{

    public ClientNotFoundException(UUID id) {
        super("Клиент с id: " + id + " не найден");
    }
}
