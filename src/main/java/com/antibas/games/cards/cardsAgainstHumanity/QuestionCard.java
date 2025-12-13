/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards.cardsAgainstHumanity;

import java.util.Vector;

/**
 *
 * @author User
 */
public class QuestionCard extends CAHCard{
    public final static String ANSWERBLOCK = "_____";

    public QuestionCard(String description) {
        super(description);
    }
    
    public String getAnswers(AnswerCard answerCard){
        String desc = description;
        Vector<String> answers = answerCard.getAnswers();
        for(String a: answers){
            desc = desc.replaceFirst(ANSWERBLOCK, a);
        }
        return desc;
    }
    
    public String getAnswers(String... answers){
        String desc = description;
        for(String a: answers){
            desc = desc.replaceFirst(ANSWERBLOCK, a);
        }
        return desc;
    }
}
