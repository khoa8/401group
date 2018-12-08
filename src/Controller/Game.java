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
    
    public void setupDiscard() {    //initialize discardpile class
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
        int pay = 0;    //protection money rule's status choose to include/exclude rule
        pay = view.protectionMoney(pay, i); //protection money status -1 exclude rule 1 include rule
        int endTurns = NUM_PLAYERS;
        while(!endGame || endTurns > 0) {
            view.printToString(board.toString());   // Print board
            if(endGame) {   //
                endTurns--;
                view.printEndGame();
            }
            view.printToString(players[i].toString());  // Print player
            view.printToString(zone.toString());    // Print card zone
            
            protectionMoney(players[i], pay, i);//if pay > 0, protection money rule in play & print player (updated)
            
            performAction(players[i]);
            
            endGame = endGame || players[i].endOfGame();
            
            i++;
            if(i == NUM_PLAYERS) i = 0;
        }
        
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
        player.subtractTrains(claim.getLength());   // Subtract trainsasdf
        
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
        TicketCard t1 = null;   //ticket cards 
        TicketCard t2 = null;   //t1 is a, t2 is b, t3 is c
        TicketCard t3 = null;
        if(!ticketDeck.Ticketdeck.isEmpty()){   //add ticket card to hand if ticket deck isn't empty
        t3 = player.getTicketCard(player.getHandTicketCSize()-1);
        player.addTicketCard(ticketDeck.drawTicketCard());
        }
        if(!ticketDeck.Ticketdeck.isEmpty()){   //add ticket card to hand if ticket deck isn't empty
        t2 = player.getTicketCard(player.getHandTicketCSize()-1);
        player.addTicketCard(ticketDeck.drawTicketCard());
        }
        if(!ticketDeck.Ticketdeck.isEmpty()){   //add ticket card to hand if ticket deck isn't empty
        t1 = player.getTicketCard(player.getHandTicketCSize()-1);
        player.addTicketCard(ticketDeck.drawTicketCard());
        }
        switch(view.drawTicketCards(t1, t2, t3)){
            case 1: //keep ticketcard 1. All three
                break;
            case 2: //keep ticketcard 2. a + b      and remove c
                    ticketDeck.addTicketDeck(t3);
                player.removeTicketCard(player.getHandTicketCSize()-1);
                break;
            case 3: //keep ticketcard 3. a + c      and remove b
                ticketDeck.addTicketDeck(t2);
                player.removeTicketCard(player.getHandTicketCSize()-2);
                break;
            case 4: //keep ticketcard 4. b + c      and remove a if it isn't null
                if(t1 != null) {
                ticketDeck.addTicketDeck(t1);
                player.removeTicketCard(player.getHandTicketCSize()-3);
                }
                break;
            case 5: //keep ticketcard 5. a      and remove b + c  if they aren't null
                ticketDeck.addTicketDeck(t2);
                ticketDeck.addTicketDeck(t3);
                player.removeTicketCard(player.getHandTicketCSize()-1);
                player.removeTicketCard(player.getHandTicketCSize()-1);               
                break;
            case 6: //keep ticketcard 6. b      and remove a + c if they aren't null
                if(t1 != null) {
                ticketDeck.addTicketDeck(t1);
                player.removeTicketCard(player.getHandTicketCSize()-3);
                }
                ticketDeck.addTicketDeck(t3);
                player.removeTicketCard(player.getHandTicketCSize()-1);
                break;
            case 7: //keep ticketcard 7. c      and remove a + b if they aren't null
                if(t1 != null) {
                ticketDeck.addTicketDeck(t1);
                player.removeTicketCard(player.getHandTicketCSize()-3);
                }
                if(t2 != null) {
                ticketDeck.addTicketDeck(t2);
                player.removeTicketCard(player.getHandTicketCSize()-2);
                }
                break;
        }
    }
    public void protectionMoney(Player player, int pay, int ai) {   //daniel
        int take;
        if(pay == -1){
        //-1 for if players choose to not play with this game rule    
        }
        else{
        //ask if player wants to pay protection money
        pay = view.protectionMoney(pay, ai);
        if(pay == 1){
        //pay protection money (1 train card)
            take = (int) (Math.random() * 100) % player.getHandTrainCSize();
            player.removeTrainCard(take);
        }
        else if(pay == 2){
        //refuse to pay protection money
        //1 - 6 random roll for possible sabotage
        pay = (int) ((Math.random() * 100) % 6) + 1;
            // 1,2,3 nothing happens
            if(pay == 4){   //4 remove 10 trains
                player.subtractTrains(10);  //if # trains < 0 it means you owe trains
            }
            else if(pay == 5){   //5 remove up to 2 train cards, take none if one none
                if(player.getHandTrainCSize() != 0){
                    take = (int) (Math.random() * 100) % player.getHandTrainCSize();
                    player.removeTrainCard(take);
                }
                if(player.getHandTrainCSize() != 0){
                    take = (int) (Math.random() * 100) % player.getHandTrainCSize();
                    player.removeTrainCard(take);
                }
            }
            else if(pay == 6){  //6 remove up to 2 ticket cards, take none if one none
                if(player.getHandTicketCSize() != 0){
                    take = (int) (Math.random() * 100) % player.getHandTicketCSize();
                    player.removeTicketCard(take);
                }
                if(player.getHandTicketCSize() != 0){
                    take = (int) (Math.random() * 100) % player.getHandTicketCSize();
                    player.removeTicketCard(take);
                }
            }
        }
        view.printToString(player.toString());
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
            players[i].setScore(scores[i]);
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
