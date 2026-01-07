package com.antibas.games.answer;

import com.antibas.util.graph.DirectedEdge;
import com.antibas.util.graph.Direction;
import com.antibas.util.graph.Edge;
import lombok.Getter;

@Getter
public class Answer extends DirectedEdge {
    private final String answer;
    public Answer(String name, String answer, Direction direction) {
        super(name, direction);
        this.answer = answer;
    }

    public Answer(String name, String answer, double cost) {
        super(name, cost);
        this.answer = answer;
    }
}
