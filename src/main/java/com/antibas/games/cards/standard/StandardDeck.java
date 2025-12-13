/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards.standard;

import com.antibas.games.cards.CardCollection;
import com.antibas.games.cards.Deck;

import static com.antibas.games.cards.standard.StandardNumber.*;

/**
 *
 * @author Antibassis
 */
public class StandardDeck extends Deck<StandardCard>{
    
    public StandardDeck(boolean jokers){
        super((jokers)? 52:54);
        StandardNumber[] numbers = StandardNumber.values();
        StandardSuit[] suits = StandardSuit.values();
        for(StandardNumber n: numbers){
            for(StandardSuit s: suits){
                if(n.value != 14) addElement(new StandardCard(n, s));
            }
        }
        if(jokers){
            addElement(new StandardCard(BlackJoker, null));
            addElement(new StandardCard(RedJoker, null));
        }
    }
    
    public StandardDeck(){
        this(false);
    }
    
    public void setPoints(int p, String name){
        for(StandardCard c: this){
            if(c.getName().equals(name)){ 
                c.setPoints(p);
                return;
            }
        }
    }
    
    public void setPoints(int p, String... names){
        int count = 0;
        for(StandardCard c: this){
            if(c.getName().equals(names[count])){ 
                c.setPoints(p);
                count++;
                if(count == names.length) return;
            }
        }
    }
    
    public static int totalPoints(CardCollection<StandardCard> cards) {
        int s = 0;
        for(StandardCard c: cards) s += c.getPoints();
        return s;
    }

}
