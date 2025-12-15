/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards.tichu;

import com.antibas.games.cards.Card;
import lombok.Getter;

/**
 *
 * @author User
 */
@Getter
public class TichuCard extends Card{
    private final TichuSuit suit;
    private final TichuNumber number;
    
    public TichuCard(TichuNumber num, TichuSuit su) {
        this(num, su, 0);
    }
    
    public TichuCard(TichuNumber num, TichuSuit su, int p) {
        super(num.name() + su.name(), "", p);
        suit = su;
        number = num;
    }

}
