package com.hangman.HangmanSpringBoot.controller;

import com.hangman.HangmanSpringBoot.HangmanLexicon;
import com.hangman.HangmanSpringBoot.OneGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author turdeant - 22.08.18
 */
@Controller
public class HangmanController {

    @Autowired
    public OneGame game;

    private HangmanLexicon hl;

    @RequestMapping("")
    public void playGame(){
        hl = new HangmanLexicon();
        game.startGame(hl.extractAWord());
        while (game.isDone()) {
            game.askForInput();
            game.drawHangman();
        }
        game.endGame();
    }

}
