package com.antibas.parser.query;

public enum QueryState {
    NEW_TOKEN,
    STRING_LITERAL,
    COMPLETE_TOKEN,
    OPERATOR,
    BOOLEAN_OPERATOR,
    PARENTHESIS_OPEN,
    PARENTHESIS_CLOSE,
    EXPRESSION;
}
