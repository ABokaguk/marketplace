package com.marketplace.orderservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StateNotFoundException extends RuntimeException{

    public StateNotFoundException(String state) {
        super("State \"" +  state + "\" doesn't exist");
    }
}
