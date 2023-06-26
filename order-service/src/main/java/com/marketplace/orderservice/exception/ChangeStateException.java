package com.marketplace.orderservice.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class ChangeStateException extends RuntimeException {
    public ChangeStateException(UUID orderId) {
        super("Failed to change state of order with id " + orderId);
    }
}
