/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards;

import com.antibas.math.Math2;

import java.util.Collection;
import java.util.Vector;

/**
 *
 * @author User
 * @param <T>
 */
public class Deck<T extends Card> extends CardCollection<T> {
    public Deck(int n){
        super(n);
    }
    
    public Deck(){
        super();
    }
    
    public Deck(Collection<? extends T> c) {
		super(c);
	}

	protected void swapCards(int i, int j){
        T tmp = super.elementAt(i);
        set(i, elementAt(j));
        set(j, tmp);
    }
    
    @Override
    public String toString(){
        String s = "";
        for(Card c: this) {
        	s += c.toString() + "\n";
        }
        return s;
    }
    
    
    public void shuffle(){
        int r1, r2;
        for(int i = 0; i < capacity(); i++){
            r1 = Math2.RNG(0, 51);
            r2 = Math2.RNG(0, 51);
            swapCards(r1, r2);
        }
    }
    
    public void shuffle(int rate){
        int r1, r2;
        for(int i = 0; i < rate; i++){
            r1 = Math2.RNG(0, 51);
            r2 = Math2.RNG(0, 51);
            swapCards(r1, r2);
        }
    }
    
    public void riffleShuffle(){
        for(int i = 0; i < capacity()-1; i += 2)
            swapCards(i, i+1);
    }
    
    public void riffleShuffle(int rate){
        for(int i = 0; i < capacity()-rate+1; i += rate)
            swapCards(i, i+rate);
    }
    
	public void packShuffle() {
    	packShuffle(7);
	}

	public void packShuffle(int packsNo) {
		Vector<Vector<T>> packs = new Vector<>(packsNo);
		
		for(T card: this) {
			int p = Math2.RNG(packsNo);
			packs.elementAt(p).add(card);
		}
		
		clear();
		
		for(Vector<T> pack: packs) {
			addAll(pack);
		}
	}
}
