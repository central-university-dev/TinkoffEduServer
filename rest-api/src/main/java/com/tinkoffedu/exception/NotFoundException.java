package com.tinkoffedu.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> clazz) {
        super(clazz.getSimpleName() + " not found");
    }
}
