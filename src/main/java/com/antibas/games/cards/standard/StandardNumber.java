/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards.standard;

/**
 *
 * @author Antibassis
 */
public enum StandardNumber {
    Ace(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(13), BlackJoker(14), RedJoker(14);
    
    public final int value;
    
    StandardNumber(int v){
        value = v;
    }
}

