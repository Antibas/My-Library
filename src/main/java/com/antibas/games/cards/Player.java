/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author User
 * @param <T>
 */
public abstract class Player<T extends Card> {
    protected Vector<T> hand, stock;
    @Setter @Getter
    protected int points;
    
    public Player(int p){
        hand = new Vector<>();
        stock = new Vector<>();
        points = p;
    }
    
    public void addToHand(T c){
        hand.addElement(c);
    }
    
    @SafeVarargs
    public final void addToHand(T... c){
        for(T cc: c)
            addToHand(cc);
    }
    
    public T discard(int i){
        return hand.remove(i);
    }
    
    public T reveal(int i){
        return hand.elementAt(i);
    }
    
    public int cardsInHand(){
        return hand.size();
    }
    
    public int cardsInStock(){
        return stock.size();
    }
    
    public T revealFromStock(int i){
        return stock.elementAt(i);
    }
    
    public void addToStock(T c){
        stock.addElement(c);
    }
    
    public void addToStock(Stack<T> c){
        for(int i = 0; i < c.size(); i++)
            addToStock(c.remove(i));
    }
    
    public void removeFromStock(int i){
        stock.removeElementAt(i);
    }
    
    public void addPoints(int p){
        points += p;
    }
    
    public void removePoints(int p){
        points -= p;
    }
}
