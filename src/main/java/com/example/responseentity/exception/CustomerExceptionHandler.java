package com.example.responseentity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(value = {CustomerNotNullException.class})
    public ResponseEntity<?> notNullException(CustomerNotNullException customerNotNullException) {
        List<String> errors = new ArrayList<>();
        errors.add(customerNotNullException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Username not null", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<?> notFoundException(CustomerNotFoundException customerNotFoundException) {
        List<String> errors = new ArrayList<>();
        errors.add(customerNotFoundException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Customer not in database", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
