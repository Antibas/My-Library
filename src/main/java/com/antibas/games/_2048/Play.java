package com.antibas.games._2048;

import java.util.Scanner;

public class Play {

	public static void main(String[] args) {
		  _2048 game = new _2048();
		  
		  Scanner sc = new Scanner(System.in);
		  do {
			  System.out.println(game);
			  System.out.print("Move: ");
			  String move = sc.next();
			  
			  if(move.equals("b")) {
				  break;
			  }
			  switch(move) {
			  case "l":
				  game.moveLeft();
				  break;
			  case "r":
				  game.moveRight();
				  break;
			  case "u":
				  game.moveUp();
				  break;
			  case "d":
				  game.moveDown();
				  break;
			  }
		  } while(!game.ends());
		  
	}

}
