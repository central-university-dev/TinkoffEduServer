package com.tinkoffedu.entity.question;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record Answer(String answer, Boolean isTrue) implements Serializable {

}
