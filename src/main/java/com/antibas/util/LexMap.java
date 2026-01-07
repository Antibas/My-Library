package com.antibas.util;

import java.util.HashMap;
import java.util.Map;

public class LexMap extends HashMap<Character, Integer> {
    public LexMap(){
        super(26);
        int number = 26;
        for(char c = 'A'; c <= 'Z';c++){
            this.put(c, number--);
        }
    }

    public static int lexarithm(String phrase, Map<? super Character, ? super Integer> lexmap){
        String[] words = phrase.toUpperCase().split(" ");
        int result = 0;
        char[] wordArray;
        for(String word: words){
            wordArray = word.toCharArray();
            for(char c: wordArray) result += (Integer)lexmap.get(c);
        }
        return result;
    }
}
