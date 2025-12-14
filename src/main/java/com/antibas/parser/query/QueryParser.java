package com.antibas.parser.query;

import com.antibas.parser.Parser;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class QueryParser extends Parser<QueryState, QueryToken, String> {
    public QueryParser() {
        super(new QueryState[][]{
                {QueryState.STRING_LITERAL},
                {QueryState.PARENTHESIS_OPEN, QueryState.EXPRESSION, QueryState.PARENTHESIS_CLOSE},
                {QueryState.STRING_LITERAL, QueryState.BOOLEAN_OPERATOR, QueryState.STRING_LITERAL},
                {QueryState.EXPRESSION, QueryState.BOOLEAN_OPERATOR, QueryState.EXPRESSION},
                {QueryState.STRING_LITERAL, QueryState.BOOLEAN_OPERATOR, QueryState.EXPRESSION},
                {QueryState.EXPRESSION, QueryState.BOOLEAN_OPERATOR, QueryState.STRING_LITERAL}
        });
    }

    @Override
    public List<QueryToken> tokenize(String input) {
        QueryState stateNow = QueryState.NEW_TOKEN, stateNext = QueryState.NEW_TOKEN;
        QueryToken currentToken = new QueryToken();
        List<QueryToken> tokens = new ArrayList<>();
        int parenthesisCount = 0, i = 0;
        while (i < input.length()) {
            switch (stateNow) {
                case NEW_TOKEN:
                    currentToken = new QueryToken();
                    if (currentToken.getValue().matches("[ \t\n\r\f]")){
                        stateNext = QueryState.NEW_TOKEN;
                        i++;
                    } else stateNext = switch (input.charAt(i)) {
                        case '(' -> {
                            currentToken.append(input.charAt(i));
                            yield QueryState.PARENTHESIS_OPEN;
                        }
                        case ')' -> {
                            currentToken.append(input.charAt(i));
                            yield QueryState.PARENTHESIS_CLOSE;
                        }
//                        case '+', '-', '*', '/' ->{
//                            currentToken.append(input.charAt(i));
//                            currentToken.setState(State.OPERATOR);
//                            yield State.COMPLETE_TOKEN;
//                        }
                        case '"' -> {
                            currentToken.append(input.charAt(i));
                            i++;
                            yield QueryState.STRING_LITERAL;
                        }
                        default -> QueryState.BOOLEAN_OPERATOR;
                    };
                    break;
                case STRING_LITERAL:
                    if(input.charAt(i) == '"') {
                        currentToken.append(input.charAt(i));
                        currentToken.setState(QueryState.STRING_LITERAL);
                        stateNext = QueryState.COMPLETE_TOKEN;
                    } else currentToken.append(input.charAt(i));
                    i++;
                    break;
                case BOOLEAN_OPERATOR:
                    if(String.valueOf(input.charAt(i)).matches("[ANDOR]")) {
                        currentToken.append(input.charAt(i));
                        i++;
                    } else if(Arrays.stream(BOOLEAN_OPERATORS).toList().contains(currentToken.getValue())){
                        currentToken.setState(QueryState.BOOLEAN_OPERATOR);
                        stateNext = QueryState.COMPLETE_TOKEN;
                    } else {
                        i++;
                        stateNext = QueryState.NEW_TOKEN;
                    }
                    break;
                case PARENTHESIS_OPEN:
                    currentToken.setState(QueryState.PARENTHESIS_OPEN);
                    parenthesisCount++;
                    stateNext = QueryState.COMPLETE_TOKEN;
                    i++;
                    break;
                case PARENTHESIS_CLOSE:
                    currentToken.setState(QueryState.PARENTHESIS_CLOSE);
                    parenthesisCount--;
                    stateNext = QueryState.COMPLETE_TOKEN;
                    i++;
                    break;
                case COMPLETE_TOKEN:
                    tokens.add(currentToken);
                    stateNext = QueryState.NEW_TOKEN;

            }
            stateNow = stateNext;
        }
        if(parenthesisCount != 0) throw new IllegalArgumentException("Unclosed parentheses");
        if(stateNow == QueryState.STRING_LITERAL) throw new IllegalArgumentException("Missing quotation mark - \"");
        if(!currentToken.getValue().isBlank()) tokens.add(currentToken);
        return tokens;
    }

    @Override
    public String parse(List<QueryToken> tokens) {
        List<QueryToken>
                stack = new ArrayList<>(),
                inputBuffer = new ArrayList<>(tokens);
        String newValue;
        int leftParenthesisIndex;
        String expression;
        boolean isReduced, match;
        while (!inputBuffer.isEmpty() || stack.size() > 1) {
            if(!inputBuffer.isEmpty()){
                stack.add(inputBuffer.removeFirst());
            }
            while (stack.stream().anyMatch(token -> token.getState() == QueryState.PARENTHESIS_CLOSE)) {
                leftParenthesisIndex = -1;

                for (int i = stack.size() - 1; i >= 0; i--) {
                    if (stack.get(i).getState() == QueryState.PARENTHESIS_OPEN) {
                        leftParenthesisIndex = i;
                        break;
                    }
                }

                if (leftParenthesisIndex == -1) {
                    throw new IllegalArgumentException("Parenthesis '(' & ')' not balanced");
                }
                if (leftParenthesisIndex < stack.size() - 2) {
                    expression = stack.get(leftParenthesisIndex + 1).getValue();

                    List<QueryToken> newStack = new ArrayList<>(stack.subList(0, leftParenthesisIndex));
                    newStack.add(new QueryToken(expression, QueryState.EXPRESSION));
                    stack = newStack;
                } else throw new IllegalArgumentException("Unclosed parentheses");
            }

            isReduced = true;
            while (isReduced) {
                isReduced = false;

                for(QueryState[] gram: grammar){
                    if (stack.size() >= gram.length) {
                        match = true;

                        for (int i = 0; i < gram.length; i++) {
                            if (stack.get(stack.size() - gram.length + i).getState() != gram[i]) {
                                match = false;
                                break;
                            }
                        }

                        if (match) {
                            List<QueryToken> slice = stack.subList(stack.size() - gram.length, stack.size());
                            newValue = this.convert(slice);

                            for (int i = 0; i < gram.length; i++) {
                                stack.removeLast();
                            }

                            stack.add(new QueryToken(newValue, QueryState.EXPRESSION));
                            isReduced = true;
                            break;
                        }
                    }
                }
            }
        }
        return (!stack.isEmpty() && stack.getFirst().getState() == QueryState.EXPRESSION) ? stack.getFirst().getValue(): null;
    }

    @Override
    public String convert(List<QueryToken> tokens) {
        if (tokens.size() == 1) return tokens.getFirst().getValue();

        if (tokens.size() == 3){
            String left, operator, right, leftExpression, rightExpression;
            List<String> values = tokens.stream().map(t -> t.getValue().replace("\"", "")).toList();
            return """
                   {
                       "$%s": [
                           {
                                "$or": [
                                    {"title": {"$regex": %s, "$options": "i"}},
                                    {"abstract": {"$regex": %s, "$options": "i"}}
                                ]
                           },
                           {
                             "$or": [
                                 {"title": {"$regex": %s, "$options": "i"}},
                                 {"abstract": {"$regex": %s, "$options": "i"}}
                             ]
                           }
                       ]
                   }
            """.formatted(values.get(1).toLowerCase(), values.get(0), values.get(0), values.get(2), values.get(2));
        }
        return null;
    }
}
