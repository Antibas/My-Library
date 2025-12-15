/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.games.cards;

import com.antibas.util.Nameable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 *
 * @author User
 */
@Setter
@Getter
public abstract class Card implements Nameable {
    /**
	 * 
	 */
    @Serial
	private static final long serialVersionUID = 2343352808775070403L;
	protected String name;
    protected String description;
    protected int points;

    public Card(String name, String description, int points) {
        this.name = name;
        this.description = description;
        this.points = points;
    }
    
    public Card(String name, String description) {
        this(name, description, 0);
    }
    
    public Card(String name) {
        this(name, "", 0);
    }

    public Card() {
        this("", "", 0);
    }


}