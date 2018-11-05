/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import java.util.Scanner;

/**
 *
 * @author Viet
 */
public class Game {
    private final Scanner stdin = new Scanner(System.in);
    static final int NUM_PLAYERS = 2;
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
        deck = new TrainCardDeck();
        deck.shuffle();
    }
    
    public void setupDeck(TicketCardDeck deck) {
        deck = new TicketCardDeck();
        deck.shuffleTicketdeck();
    }
    
    public void setupPlayer(Player player) {
        //pdf step 2 & step 5
        String name;
        name = view.PlayerName();
        player = new Player(name);
        // deal a starting hand of 4 train cards 
        if (trainDeck.draw() != null) player.addTrainCard(trainDeck.draw());
        if (trainDeck.draw() != null) player.addTrainCard(trainDeck.draw());
        if (trainDeck.draw() != null) player.addTrainCard(trainDeck.draw());
        if (trainDeck.draw() != null) player.addTrainCard(trainDeck.draw());
        
        //deal 3 cards to each player.Each player decides which ones they wish to keep,
        //must keep at least two, but may keep all three. Any returned cards are
        //placed on the bottom of the Destination Ticket deck.
        TicketCard tc1 = ticketDeck.drawTicketCard();
        TicketCard tc2 = ticketDeck.drawTicketCard();
        TicketCard tc3 = ticketDeck.drawTicketCard();
        switch(view.drawTicketCardAtBeginning(tc1,tc2,tc3)){
            case 1: player.addTicketCard(tc1);
                    player.addTicketCard(tc2);
                    player.addTicketCard(tc3);
                    break;
            case 2: player.addTicketCard(tc1);
                    player.addTicketCard(tc2);
                    ticketDeck.addTicketDeck(tc3);
                    break;
            case 3: player.addTicketCard(tc1);
                    player.addTicketCard(tc3);
                    ticketDeck.addTicketDeck(tc2);
                    break;
            case 4: player.addTicketCard(tc2);
                    player.addTicketCard(tc3);
                    ticketDeck.addTicketDeck(tc1);
                    break;        
        }
    }
    
    public void setupZone() {
        //step 3 - getting the top five cards from Train card deck
        zone = new TrainCardZone();
        do{
        while(zone.getSize() > 0) zone.removeCard(0);
        zone.addCard(trainDeck.draw());
        zone.addCard(trainDeck.draw());
        zone.addCard(trainDeck.draw());
        zone.addCard(trainDeck.draw());
        zone.addCard(trainDeck.draw());
        }while(zone.hasThreeRainbows());
    }
    
    public void setupDiscard() {
        discarded.resetDiscardPile();
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
        view.trainCardZone(zone);
        switch(view.drawTrainCard()){
            case 1: player.addTrainCard(trainDeck.draw());
                    switch(view.drawTrainCard()){
                        case 1: player.addTrainCard(trainDeck.draw());
                                break;
                        case 2: player.addTrainCard(zone.pickCard(view.drawTrainCardfromZone(zone)));
                                zone.addCard(trainDeck.draw());
                                if (zone.hasThreeRainbows()) setupZone();
                                break;
                    }
                    break;
            case 2: TrainCard card1 = zone.pickCard(view.drawTrainCardfromZone(zone));
                    player.addTrainCard(card1);
                    zone.addCard(trainDeck.draw());
                    if (zone.hasThreeRainbows()) setupZone();
                    if (card1.getValue() == VALUE.RAINBOW) break;
                    else{
                        switch(view.drawTrainCard()){
                        case 1: player.addTrainCard(trainDeck.draw());
                                break;
                        case 2: TrainCard card2 = zone.pickCard(view.drawTrainCardfromZone(zone));
                                if (card2.getValue() != VALUE.RAINBOW) player.addTrainCard(card2);
                                else {
                                    do {
                                        zone.addCard(card2);
                                        card2 = zone.pickCard(view.drawTrainCardfromZone(zone));
                                        if (card2.getValue() != VALUE.RAINBOW) player.addTrainCard(card2);
                                    }
                                    while (card2.getValue() == VALUE.RAINBOW);
                                }
                                zone.addCard(trainDeck.draw());
                                if (zone.hasThreeRainbows()) setupZone();
                                
                                break;
                    }
                    }
                    break;        
        }
        TrainCard nextTrainCard = trainDeck.draw();
        if (nextTrainCard != null) player.addTrainCard(nextTrainCard);
    }
    
    public void claimPath(Player player) {
        
    }
    
    public void drawTicketCards(Player player) {
        player.addTicketCard(ticketDeck.drawTicketCard());
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
        //might not need this
    }
    
    public static void main(String args[]) {
        Game game = new Game();
        boolean keepPlaying = true;
        while(keepPlaying = true) {
            //game.reset();
            game.initialize();
            game.play();
            keepPlaying = game.askToPlayAgain();
        }
    }
    
    public boolean askToPlayAgain() {
        return false;
    }
}
