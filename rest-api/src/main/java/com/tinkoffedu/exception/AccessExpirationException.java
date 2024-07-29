package com.tinkoffedu.exception;

public class AccessExpirationException extends RuntimeException {

    public AccessExpirationException(String message) {
        super("source access time is expired: " + message);
    }

}
