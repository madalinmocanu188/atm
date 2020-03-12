package com.bank.atm.exceptionhandlers;

import com.bank.atm.exceptions.InsufficientFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResolverHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InsufficientFundsException.class)
    protected ResponseEntity<Object> handleInsufficientFunds() {
        return new ResponseEntity<>("Insufficient funds", HttpStatus.NOT_ACCEPTABLE);
    }
}
