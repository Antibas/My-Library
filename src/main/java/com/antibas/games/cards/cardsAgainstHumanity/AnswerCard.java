/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards.cardsAgainstHumanity;

import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author User
 */
public class AnswerCard extends CAHCard{
    private final Vector<String> answers;
    
    public AnswerCard(String... answers) {
        super(String.join(", ", answers));
        this.answers = new Vector<>(Arrays.asList(answers));
    }
    
    public Vector<String> getAnswers(){
        return answers;
    }

}
