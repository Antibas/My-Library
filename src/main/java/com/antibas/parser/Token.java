package com.antibas.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Token<E extends Enum<E>, T>  {
    protected T value;
    protected E state;

    public Token(T value) {
        this(value, null);
    }

    public Token(E state) {
        this(null, state);
    }

    public Token() {
        this(null, null);
    }
}
