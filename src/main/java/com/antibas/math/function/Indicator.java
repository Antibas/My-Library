package com.antibas.math.function;

import lombok.Getter;

@Getter
public enum Indicator {
    PLUS('+'),
    MINUS('-');

    private final char character;
    Indicator(char character){
        this.character = character;
    }
}
