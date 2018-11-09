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
    Player player1; // Player[NUM_PLAYERS]
    Player player2;
    TrainCardDeck trainDeck;
    TicketCardDeck ticketDeck;
    TrainCardZone zone;
    DiscardPile discarded;
    Player longestPathHolder;
    boolean endGame;
    
    public Game() {}
    
    //##########INITIALIZE######################################################
    public void initialize() {
        setupBoard();
        trainDeck = new TrainCardDeck();
        trainDeck.shuffle();
        ticketDeck = new TicketCardDeck();
        ticketDeck.shuffleTicketdeck();
        player1 = new Player(view.PlayerName());
        setupPlayer(player1);
        player2 = new Player(view.PlayerName());
        setupPlayer(player2);
        setupZone();
        setupDiscard();
    }
    
    public void setupBoard() {  // setup's for future features
        board = new Board();    // hard coded
    }
    
    public void setupPlayer(Player player) {
        //pdf step 2 & step 5
        // deal a starting hand of 4 train cards 
        player.addTrainCard(trainDeck.draw());
        player.addTrainCard(trainDeck.draw());
        player.addTrainCard(trainDeck.draw());
        player.addTrainCard(trainDeck.draw());
        
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
        discarded = new DiscardPile();
    }
    
    //##########PLAY############################################################
    public void play() {
        endGame = false;
        // Hardcoded
        Player[] players = new Player[NUM_PLAYERS];
        players[0] = player1;
        players[1] = player2;
        //
        int i = 0;
        while(!endGame) {
            view.printToString(board.toString());   // Print board
            view.printToString(players[i].toString());  // Print player
            view.printToString(zone.toString());    // Print card zone
            
            performAction(players[i]);
            
            endGame = players[i].endOfGame();
            
            i++;
            if(i == NUM_PLAYERS) i = 0;
        }
        
        // Last turns
        int j = i;
        do {
            view.printToString(board.toString());   // Print board
            view.printToString(players[i].toString());  // Print player
            view.printToString(zone.toString());    // Print card zone
            
            performAction(players[i]);
            
            endGame = players[i].endOfGame();
            
            j++;
            if(j == NUM_PLAYERS) j = 0;
        }while(j != i);
        
        calculateWinner();
    }
    
    public void performAction(Player player) {
        switch(view.promptAction()) {
            case 1: drawTrainCards(player);
                    break;
            case 2: claimPath(player);
                    break;
            case 3: drawTicketCards(player);
                    break;
            case 4: break;  // Skip turn
        }
    }
    
    public void drawTrainCards(Player player) {
        view.trainCardZone(zone);
        view.printToString(player.showHandTrainCard());
        switch(view.drawTrainCard()){
            case 1: player.addTrainCard(trainDeck.draw());
                    view.printToString(player.showHandTrainCard());
                    switch(view.drawTrainCard()){
                        case 1: player.addTrainCard(trainDeck.draw());
                                view.printToString(player.showHandTrainCard());
                                break;
                        case 2: player.addTrainCard(zone.pickCard(view.drawTrainCardfromZone(zone)));
                                zone.addCard(trainDeck.draw());
                                if (zone.hasThreeRainbows()) setupZone();
                                break;
                    }
                    break;
            case 2: TrainCard card1 = zone.pickCard(view.drawTrainCardfromZone(zone));
                    player.addTrainCard(card1);
                    view.printToString(player.showHandTrainCard());
                    zone.addCard(trainDeck.draw());
                    if (zone.hasThreeRainbows()) setupZone();
                    if (card1.getValue() == VALUE.RAINBOW) break;
                    else{
                        switch(view.drawTrainCard()){
                        case 1: player.addTrainCard(trainDeck.draw());
                                view.printToString(player.showHandTrainCard());
                                break;
                        case 2: TrainCard card2 = zone.pickCard(view.drawTrainCardfromZone(zone));
                                if (card2.getValue() != VALUE.RAINBOW) player.addTrainCard(card2);
                                else {
                                    do {
                                        view.printToString(zone.errorRainBow());
                                        zone.addCard(card2);
                                        card2 = zone.pickCard(view.drawTrainCardfromZone(zone));
                                        if (card2.getValue() != VALUE.RAINBOW) player.addTrainCard(card2);
                                    }
                                    while (card2.getValue() == VALUE.RAINBOW);
                                }
                                view.printToString(player.showHandTrainCard());
                                zone.addCard(trainDeck.draw());
                                if (zone.hasThreeRainbows()) setupZone();
                                break;
                    }
                    }
                    break;        
        }
    }
    
    public void claimPath(Player player) {
        view.promptPath();
        int i = view.promptCity();
        int j = view.promptCity();
        Path claim = board.getPath(i, j);
        VALUE color = null;
        switch(view.promptColor(claim.getColor1(), claim.getColor2())) {
            case 1: color = claim.getColor1();
                    break;
            case 2: color = claim.getColor2();
                    break;
        }
        board.claimPath(player, color, i, j);   // Claim path and color for player
        player.addScore(claim.getValue());      // Add score to player
        
        boolean done = false;   // Remove the set of train cards
        int index = 0;
        while(!done) {
            index = view.promptRemoveColor(color, player.getHandTrainC());
            if(index != -1)
                player.removeTrainCard(index);
            else
                done = true;
        }
    }
    
    public void drawTicketCards(Player player) {
        TicketCard t1 = null;
        TicketCard t2 = null;
        TicketCard t3 = null;
        if(!ticketDeck.Ticketdeck.isEmpty()){
        t3 = player.getTicketCard(player.getHandTicketCSize()-1);
        player.addTicketCard(ticketDeck.drawTicketCard());
        }
        if(!ticketDeck.Ticketdeck.isEmpty()){
        t2 = player.getTicketCard(player.getHandTicketCSize()-1);
        player.addTicketCard(ticketDeck.drawTicketCard());
        }
        if(!ticketDeck.Ticketdeck.isEmpty()){
        t1 = player.getTicketCard(player.getHandTicketCSize()-1);
        player.addTicketCard(ticketDeck.drawTicketCard());
        }
        switch(view.drawTicketCards(t1, t2, t3)){
            case 1:
                break;
            case 2:
                    ticketDeck.addTicketDeck(t3);
                player.removeTrainCard(player.getHandTicketCSize()-1);
                break;
            case 3:
                ticketDeck.addTicketDeck(t2);
                player.removeTrainCard(player.getHandTicketCSize()-2);
                break;
            case 4:
                if(t1 != null) {
                ticketDeck.addTicketDeck(t1);
                player.removeTrainCard(player.getHandTicketCSize()-3);
                }
                break;
            case 5:
                ticketDeck.addTicketDeck(t2);
                ticketDeck.addTicketDeck(t3);
                player.removeTrainCard(player.getHandTicketCSize()-1);
                player.removeTrainCard(player.getHandTicketCSize()-1);
                break;
            case 6:
                if(t1 != null) {
                ticketDeck.addTicketDeck(t1);
                player.removeTrainCard(player.getHandTicketCSize()-3);
                }
                ticketDeck.addTicketDeck(t3);
                player.removeTrainCard(player.getHandTicketCSize()-1);
                break;
            case 7:
                if(t1 != null) {
                ticketDeck.addTicketDeck(t1);
                player.removeTrainCard(player.getHandTicketCSize()-3);
                }
                if(t2 != null) {
                ticketDeck.addTicketDeck(t2);
                player.removeTrainCard(player.getHandTicketCSize()-2);
                }
                break;
        }
    }
    
    public void calculateWinner() {
        Player winner = null;
        int highestScore = 0;
        view.printCalculating();
        // Hardcoded
        Player[] players = new Player[NUM_PLAYERS];
        players[0] = player1;
        players[1] = player2;
        //
        int[] scores = new int[NUM_PLAYERS];
        for(int i = 0; i < NUM_PLAYERS; i++) {
            scores[i] = calculateScore(players[i]);
            if(scores[i] > highestScore) {
                highestScore = scores[i];
                winner = players[i];
            }
        }
        view.printScores(players, scores);
        view.printWinner(winner);
    }
    
    public int calculateScore(Player player) {
        int score = player.getScore();
        // +longest route
        for(TicketCard card : player.getHandTicketC()) {
            if(board.checkTicket(player, card))
                score += card.getValue();
            else
                score -= card.getValue();
        }
        return score;
    }
    
    public static void main(String args[]) {
        Game game = new Game();
        boolean keepPlaying = true;
        char c;
        while(keepPlaying == true) {
            game.initialize();
            game.play();
            c = game.view.promptToPlayAgain();
            keepPlaying = (c == 'Y' || c == 'y');
        }
    }
    
}
