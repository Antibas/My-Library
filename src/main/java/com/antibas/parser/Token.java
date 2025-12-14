package com.antibas.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Token<E extends Enum<E>>  {
    private String value;
    private E state;

    public Token(String value) {
        this(value, null);
    }

    public Token(E state) {
        this("", state);
    }

    public Token() {
        this("", null);
    }

    public void append(String value) {
        this.value += value;
    }

    public void append(char value) {
        this.value += value;
    }
}
