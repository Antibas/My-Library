package com.antibas.games.wordle;
import lombok.Getter;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
public class Word {
    public static final int LENGTH = 5;

    private final String word;
    private final List<LetterStatus> letterStatuses;
    private static Set<String> POSSIBLE_WORDS = null;

    public Word(String word, List<LetterStatus> letterStatuses) {
        assert word.length() == LENGTH && letterStatuses.size() == LENGTH;
        this.word = word.toLowerCase();
        this.letterStatuses = letterStatuses;
        POSSIBLE_WORDS = getDictionary();
    }

    public Word(String word, LetterStatus... letterStatuses) {
        this(word, List.of(letterStatuses));
    }

    public Word(String word) {
        this(word, List.of(LetterStatus.values()).subList(0, LENGTH));
    }

    public void addLetterStatus(LetterStatus letterStatuses) {
        this.letterStatuses.add(letterStatuses);
    }

    public Set<String> getPossibleWords(){
//        Set<String> possibleWords = getDictionary();
        for(int i = 0; i < LENGTH; i++){
            int finalI = i;
            POSSIBLE_WORDS = POSSIBLE_WORDS
                    .stream()
                    .filter(w ->
                        switch (letterStatuses.get(finalI)) {
                            case WRONG -> !w.contains(String.valueOf(word.charAt(finalI)));
                            case EXISTS -> w.contains(String.valueOf(word.charAt(finalI))) && w.charAt(finalI) != word.charAt(finalI);
                            case CORRECT -> w.charAt(finalI) == word.charAt(finalI);
                        }
                    )
                    .collect(Collectors.toSet());
        }
        return POSSIBLE_WORDS;
    }

    public static Set<String> getDictionary(){
        if(POSSIBLE_WORDS != null)
            return POSSIBLE_WORDS;
        try {
            return StreamSupport.stream(
                            Spliterators.spliteratorUnknownSize(
                                    Dictionary
                                            .getDefaultResourceInstance()
                                            .getIndexWordIterator(POS.NOUN),
                                    0),
                            false
                    )
                    .map(IndexWord::getLemma)
                    .filter(lemma -> lemma.length() == LENGTH)
                    .filter(lemma -> !lemma.matches(".*[\\d' ].*"))
                    .collect(Collectors.toSet());
        } catch (JWNLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Set<String> getPossibleWords(Word... words){
        for(Word word : words)
           word.getPossibleWords();
        return POSSIBLE_WORDS;
//                .reduce((list1, list2) -> list1.stream()
//                        .filter(list2::contains)
//                        .toList())
//                .orElse(List.of());
    }
}
