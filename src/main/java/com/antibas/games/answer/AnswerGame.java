package com.antibas.games.answer;

import com.antibas.util.graph.AdjacencyGraph;
import com.antibas.util.graph.DirectedAdjacencyGraph;
import com.antibas.util.graph.Direction;
import com.antibas.util.graph.Graph;

import java.util.Scanner;
import java.util.Set;

public class AnswerGame {
    public static Question[] questions = new Question[]{
            new Question("Q1", "How are you?"),
            new Question("Q2", "Good")
    };

    public static Answer[] answers = new Answer[]{
            new Answer("Q1A1", "Fine, you?", Direction.TO),
            new Answer("Q1A2", "Not fine, you?", Direction.TO)
    };
    public static Graph<Question, Answer> getGraph(){
        Graph<Question, Answer> graph = new DirectedAdjacencyGraph<>();
        graph.putVertex(questions[0]);
        graph.putVertex(questions[1]);
        graph.putEdge(questions[0], questions[1], answers[0]);
        graph.putEdge(questions[0], questions[1], answers[1]);
        return graph;
    }
    public static void main(String[] args) {
        Graph<Question, Answer> graph = getGraph();
        System.out.println(graph);

//        Graph.GraphIterator<Question> it = graph.bfsIterator(questions[0]);
        Question currentQuestion = questions[0];
        Answer currentAnswer;
//        int input;
        Set<Answer> answers;
        while (currentQuestion != null){
            System.out.println(currentQuestion.getName()+": "+currentQuestion.getQuestion());
            answers = graph.incidentEdges(currentQuestion);
            if(answers.isEmpty()) break;
            for(Answer answer: answers){
                System.out.println("\t- "+answer.getAnswer());
            }
            currentAnswer = answers.stream().toList().get(new Scanner(System.in).nextInt());
            currentQuestion = graph.oppositeVertex(currentQuestion, currentAnswer);
        }
    }
}
