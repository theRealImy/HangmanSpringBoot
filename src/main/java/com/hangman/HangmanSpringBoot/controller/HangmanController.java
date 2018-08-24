package com.hangman.HangmanSpringBoot.controller;

import com.hangman.HangmanSpringBoot.model.HangmanLexicon;
import com.hangman.HangmanSpringBoot.service.OneGame;
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
    public void startGame(){
        hl = new HangmanLexicon();
        game.startGame(hl.extractAWord());
        playGame();
    }

    @RequestMapping(value = "/play")
    public void playGame(){
        if  (!game.isDone()) {
            game.askForInput();
            game.drawHangman();
            playGame();
        }
        else {
            game.winGame();
            game.endGame();
        }
    }

}
