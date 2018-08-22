package com.hangman.HangmanSpringBoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author turdeant - 22.08.18
 */
public class HangmanLexicon {

    private static final List<String> lexicon = new ArrayList<String>(Arrays.asList("HANGMAN","SECURITY","OVERLOADING"));

    public String extractAWord() {

        Random rand = new Random();
        int value = rand.nextInt(lexicon.size());
        return lexicon.get(value);

    }
}
