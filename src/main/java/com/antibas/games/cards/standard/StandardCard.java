/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards.standard;

import com.antibas.games.cards.Card;
import lombok.Getter;

import java.util.Objects;


/**
 * Represents a deck card which has a name, a suit, a number, a rate
 and a value. 
 * @author Antibassis
 */
@Getter
public class StandardCard extends Card {
    private StandardSuit suit;
    private StandardNumber number;
    

    /**
     * Creates a standard card with suit and number and initializes all other variables.
     * @param num suit of card
     * @param su number of card
     * @pre names must be valid according to the arrays in CardStuff
     */
    public StandardCard(StandardNumber num, StandardSuit su) {
        this(num, su, 0);
    }
    
    public StandardCard(StandardNumber num, StandardSuit su, int p) {
        super(num.name() + " of " + su.name(), "", p);
        suit = su;
        number = num;
    }

    public StandardCard() {
        super();
    }
    
    
    
    //Getters
    @Override
    public String toString(){
        return getName() + ((points == 0)?"":(" Points: " + points));
    }

    public int getRate(){
        return number.value;
    }
    
    public boolean isFigure(){
        return number.value > StandardNumber.Ten.value || number == StandardNumber.Ace;
    }
    
    public boolean isNumber(StandardNumber n){
        return number == n;
    }
    
    public boolean isSuit(StandardSuit s){
        return suit == s;
    }
    
    @Override
    public boolean equals(Object c){
        if(!(c instanceof StandardCard)) return false;
        return ((StandardCard)c).suit == suit && ((StandardCard)c).number == number;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.suit);
        hash = 47 * hash + Objects.hashCode(this.number);
        return hash;
    }

}