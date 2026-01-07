package com.antibas.games.answer;

import com.antibas.util.graph.Vertex;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Question extends Vertex {
    private final String question;
    public Question(String name, String question) {
        super(name);
        this.question = question;
    }
}
