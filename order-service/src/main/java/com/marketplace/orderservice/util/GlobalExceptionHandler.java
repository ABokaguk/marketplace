package com.marketplace.orderservice.util;


import com.marketplace.orderservice.exception.ChangeStateException;
import com.marketplace.orderservice.exception.OrderNotFoundException;
import com.marketplace.orderservice.exception.StateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({OrderNotFoundException.class, StateNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(ChangeStateException.class)
    public ResponseEntity<String> handleFailedToChangeStateException(Exception ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }


}
