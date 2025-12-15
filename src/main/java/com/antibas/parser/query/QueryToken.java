package com.antibas.parser.query;

import com.antibas.parser.Token;

public class QueryToken extends Token<QueryState, String> {

    public QueryToken(String value, QueryState state) {
        super(value, state);
    }

    public QueryToken(String value) {
        this(value, QueryState.NEW_TOKEN);
    }

    public QueryToken(QueryState state) {
        this("", state);
    }

    public QueryToken() {
        this("", QueryState.NEW_TOKEN);
    }

    public void append(String value) {
        this.value += value;
    }

    public void append(char value) {
        this.value += value;
    }
}
