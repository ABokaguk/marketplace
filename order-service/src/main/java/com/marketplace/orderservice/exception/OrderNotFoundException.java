package com.marketplace.orderservice.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(UUID orderId) {
        super("Order with id" + orderId + "doesn't exist");
    }
}
