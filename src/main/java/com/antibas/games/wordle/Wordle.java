package com.antibas.games.wordle;

public class Wordle {
    public static void main(String[] args) {
        Word
                word1 = new Word(
                "grape",
                LetterStatus.WRONG,
                LetterStatus.EXISTS,
                LetterStatus.EXISTS,
                LetterStatus.WRONG,
                LetterStatus.WRONG
                ),
                word2 = new Word(
                        "fairy",
                        LetterStatus.WRONG,
                        LetterStatus.CORRECT,
                        LetterStatus.WRONG,
                        LetterStatus.EXISTS,
                        LetterStatus.WRONG
                ),
                word3 = new Word(
                        "major",
                        LetterStatus.WRONG,
                        LetterStatus.CORRECT,
                        LetterStatus.WRONG,
                        LetterStatus.WRONG,
                        LetterStatus.CORRECT
                );
//                word4 = new Word(
//                        "grasp",
//                        LetterStatus.WRONG,
//                        LetterStatus.WRONG,
//                        LetterStatus.CORRECT,
//                        LetterStatus.CORRECT,
//                        LetterStatus.EXISTS
//                );
        System.out.println(Word.getPossibleWords(
                word1
                                ,word2
                                ,word3
//                                ,word4
                        )
                .stream()
//                .filter(word -> word.startsWith("a"))
                .toList()
                );
    }
}
