package com.hangman.HangmanSpringBoot.controller;

import com.hangman.HangmanSpringBoot.HangmanLexicon;
import com.hangman.HangmanSpringBoot.OneGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author turdeant - 22.08.18
 */
@Controller
public class HangmanController {

    @Autowired
    public OneGame game;
    private HangmanLexicon hl;


    @RequestMapping("")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void startGame(){
        hl = new HangmanLexicon();
        game.startGame(hl.extractAWord());
        playGame();
    }

    @RequestMapping(value = "/play", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void playGame(){
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
