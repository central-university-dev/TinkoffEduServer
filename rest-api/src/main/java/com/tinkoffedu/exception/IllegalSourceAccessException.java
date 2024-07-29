package com.tinkoffedu.exception;

public class IllegalSourceAccessException extends RuntimeException {

    public IllegalSourceAccessException(String message) {
        super("illegal access: " + message);
    }

}
