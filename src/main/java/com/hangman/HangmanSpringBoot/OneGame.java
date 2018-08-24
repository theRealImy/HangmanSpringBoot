package com.hangman.HangmanSpringBoot;

import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @author turdeant - 22.08.18
 */

@Service
public class OneGame {

    //----------------variables-------------//

    private char inputLetter;
    private String lexicon;
    private char[] guessedLexicon;
    private int noOfGuesses = 0;
    private static final int MAX_GUESSES = 6;

    private String hangmanDrawing = "" +
            "    0000000000000\n" +
            "    0       |    \n" +
            "    0            \n" +
            "    0            \n" +
            "    0            \n" +
            "    0            \n" +
            "    0            \n" +
            "    0            \n" +
            "    0            \n" +
            "    0            \n" +
            "    0            \n" +
            "    0            \n" +
            "    0\n" +
            "    0\n" +
            "    0" +
            "";
    private static final int[][] changeSequence ={{48,65,67,84},//head
            {102,120,138,156},// body
            {101,118,135},//arm
            {103,122,141},//arm
            {173,190,207},//leg
            {175,194,213}//leg
    };


    //----------------init-------------//

    public void startGame(String lexicon){
        this.lexicon = lexicon;
        this.noOfGuesses = 0;
        this.guessedLexicon = new char[lexicon.length()];
        for (int i=0; i<this.lexicon.length();i++)
            this.guessedLexicon[i] = '_';
        displayStart();
        drawHangman();
    }

    //----------------game play-------------//
    public void drawHangman(){

        if (this.noOfGuesses <= MAX_GUESSES) {
            if (checkIfLetterExists()) {
                displayHangman();
                updateLetter();
                displayLetters();

            } else {
                this.noOfGuesses ++;
                updateHangman();
                displayHangman();
                displayLetters();
                if (this.noOfGuesses == MAX_GUESSES) {
                    outOfMoves();
                    endGame();
                }
            }
        }
        else {
            outOfMoves();
            endGame();
        }
    }

    public boolean checkIfLetterExists(){
        if (!(""+this.inputLetter).equals("\u0000")) return this.lexicon.contains(""+this.inputLetter);
        else return true;
    }

    private void updateHangman() {
        char[] hang = hangmanDrawing.toCharArray();
        for (int i=0; i<this.changeSequence[this.noOfGuesses-1].length;i++) {
            hang[this.changeSequence[this.noOfGuesses-1][i]] = '0';
        }
        this.hangmanDrawing = "";
        for (int i = 0; i<hang.length; i++) {
            this.hangmanDrawing += hang[i];

        }
    }

    private void updateLetter() {
        for (int i=0; i< this.lexicon.length();i++) {
            if (this.lexicon.charAt(i) == this.inputLetter)
                this.guessedLexicon[i] = this.inputLetter;
        }
    }

    public void askForInput() {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.println("Give me a letter: ");

        // get their input as a String
        String letter = scanner.nextLine();

        if ((letter.length() == 1) || (!letter.isEmpty()))
            if (!Character.isLetter(letter.charAt(0)))
            {
                System.out.println("Give me only a letter, one letter pls ");
                askForInput();
            }
            else if (letter.toUpperCase().equals("STOP")) {
                    endGame();
            }
            else
                this.inputLetter = letter.toUpperCase().charAt(0);
        else {
            System.out.println("Give me only a letter, one letter pls ");
            askForInput();
        }


    }

    //----------------display-------------//

    private void displayStart(){
        System.out.println("" +
                " _    _      _                            _          _   _   ___   _    _ _____  ___  ___  ___   _   _                \n" +
                "| |  | |    | |                          | |        | | | | / _ \\ | \\ | |  __ \\|  \\/  | / _ \\ | \\ | |            \n" +
                "| |  | | ___| | ___ ___  _ __ ___   ___  | |_ ___   | |_| |/ /_\\ \\|  \\| | |  \\/| .  . |/ /_\\ \\|  \\| |           \n" +
                "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  |  _  ||  _  || . ` | | __ | |\\/| ||  _  || . ` |           \n" +
                "\\  /\\  /  __/ | (_| (_) | | | | | |  __/ | || (_) | | | | || | | || |\\  | |_\\ \\| |  | || | | || |\\  |           \n" +
                " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  \\_| |_/\\_| |_/\\_| \\_/\\____/\\_|  |_/\\_| |_/\\_| \\_/ \n" +
                "                                                                                                                       \n" +
                "                                                                                                     " +
                "");
    }

    private void displayHangman() {
        System.out.println(this.hangmanDrawing);
    }

    public void displayLetters(){
        System.out.println(this.guessedLexicon);
    }

    //----------------end game-------------//

    public void winGame() {
        System.out.println("" +
                " __      __.___ _______   \n" +
                "/  \\    /  \\   |\\      \\  \n" +
                "\\   \\/\\/   /   |/   |   \\ \n" +
                " \\        /|   /    |    \\\n" +
                "  \\__/\\  / |___\\____|__  /\n" +
                "       \\/              \\/ \n" +
                "");
    }

    public void endGame() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    public void outOfMoves() {
        System.out.println("You guessed more than 6 letters!");
    }

    public boolean isDone() {
        if (this.noOfGuesses > MAX_GUESSES) return false;
        boolean notGuessed = true;
        for (int i=0; i<this.guessedLexicon.length;i++)
            if (this.guessedLexicon[i] == '_') notGuessed = false;
        return notGuessed;
    }
}
