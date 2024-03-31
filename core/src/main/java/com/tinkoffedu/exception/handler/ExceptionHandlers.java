package com.tinkoffedu.exception.handler;

import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.exception.NotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    private static final String ERROR_STATUS = "error";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StatusResponse> handleMethodArgumentNotValidException(NotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new StatusResponse(ERROR_STATUS, e.getMessage()));
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<StatusResponse> handleBadRequestException(InvalidArgumentException e) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new StatusResponse(ERROR_STATUS, e.getMessage()));
    }

    @ExceptionHandler({
        MissingServletRequestParameterException.class,
        MethodArgumentNotValidException.class,
        HttpMessageNotReadableException.class,
        PSQLException.class
    })
    public ResponseEntity<StatusResponse> handleBadRequestException(Exception e) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new StatusResponse(ERROR_STATUS, e.getMessage()));
    }
}
