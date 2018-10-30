/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;

/**
 *
 * @author Viet
 */
public class Game {
    Display view = new Display();
    Board board;
    Player player1;
    Player player2;
    TrainCardDeck trainDeck;
    TicketCardDeck ticketDeck;
    TrainCardZone zone;
    DiscardPile discarded;
    Player longestPathHolder;
    
    public Game() {}
    
    //##########INITIALIZE######################################################
    public void initialize() {
        setupBoard();
        setupDeck(trainDeck);
        setupDeck(ticketDeck);
        setupPlayer(player1);
        setupPlayer(player2);
        setupZone();
        setupDiscard();
    }
    
    public void setupBoard() {  // setup's for future features
        board = new Board();    // hard coded
    }
    
    public void setupDeck(TrainCardDeck deck) {
        
    }
    
    public void setupDeck(TicketCardDeck deck) {
        
    }
    
    public void setupPlayer(Player player) {
        //pdf
        //step 2
        //step 5
    }
    
    public void setupZone() {
        //step 3
    }
    
    public void setupDiscard() {
        
    }
    
    //##########PLAY############################################################
    public void play() {
        //performAction();
    }
    
    public void performAction() {
        //loop
            //draw
            //claim
            //draw
            //checkEndGame
        
    }
    
    public void drawTrainCards(Player player) {
        
    }
    
    public void claimPath(Player player) {
        
    }
    
    public void drawTicketCards(Player player) {
        
    }
    
    public void checkEndGame() {
        
    }
    
    public void calculateWinner() {
        //calculateScore(
    }
    
    public void calculateScore(Player player) {
        
    }
    
    //##########RESET###########################################################
    public void reset() {
        
    }
    
    public static void main(String args[]) {
        Game game = new Game();
        boolean keepPlaying = true;
        while(keepPlaying = true) {
            game.reset();
            game.initialize();
            game.play();
            keepPlaying = game.askToPlayAgain();
        }
    }
    
    public boolean askToPlayAgain() {
        return false;
    }
}
