package com.antibas.parser;

import java.util.List;

public interface Parsable<T, C> {
    List<T> tokenize(String input);
    String parse(List<T> tokens);
    default String compile(String input) {
        return parse(tokenize(input));
    }
    C convert(List<T> tokens);
}
