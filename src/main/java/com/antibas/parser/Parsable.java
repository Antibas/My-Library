package com.antibas.parser;

import java.util.List;

public interface Parsable<T, C> {
    List<T> tokenize(String input);
    C parse(List<T> tokens);
    default C compile(String input) {
        return parse(tokenize(input));
    }
    C convert(List<T> tokens);
}
