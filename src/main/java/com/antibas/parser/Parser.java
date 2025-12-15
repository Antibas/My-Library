package com.antibas.parser;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Parser<E extends Enum<E>, T extends Token<E, C>, C> implements Parsable<T, C> {
    public static String[]
            OPERATORS = {"+", "-", "*", "/"},
            BOOLEAN_OPERATORS = {"AND", "OR"};
    protected E[][] grammar;

    public Parser(E[][] grammar) {
        this.grammar = grammar;
    }
}
