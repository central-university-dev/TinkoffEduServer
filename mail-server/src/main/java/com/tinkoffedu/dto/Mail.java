package com.tinkoffedu.dto;

import lombok.Builder;

@Builder
public record Mail(String recipient, String subject, String message) {

}
