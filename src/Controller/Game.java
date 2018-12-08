/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import java.util.Scanner;
import java.util.*;

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
        ticketDeck = new TicketCardDeck(board.getNumCities());
        ticketDeck.shuffleTicketdeck();
        int trainsnum = view.promptTrains();
        player1 = new Player(view.PlayerName(), trainsnum);
        setupPlayer(player1);
        player2 = new Player("AIComputer", trainsnum);
        setupPlayerAI(player2);
        setupZone();
        setupDiscard();
    }
    
    public void setupBoard() {
        board = new Board(view.promptNum());
        view.printToString(board.toString());
    }
    
    public void setupPlayerAI(Player player) {
        
        player.addTrainCard(trainDeck.draw());
        player.addTrainCard(trainDeck.draw());
        player.addTrainCard(trainDeck.draw());
        player.addTrainCard(trainDeck.draw());

        TicketCard tc1 = ticketDeck.drawTicketCard();
        TicketCard tc2 = ticketDeck.drawTicketCard();
        TicketCard tc3 = ticketDeck.drawTicketCard();
        
        Random ran = new Random(); 
        int next = ran.nextInt(4) + 1;
        
        switch(next){
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
            
            if (i==0) performAction(players[i]);
            else performActionAI(players[i]);
            
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
    
    public void performActionAI(Player player) {
        Random ran = new Random(); 
        int next = ran.nextInt(4) + 1;
        switch(next) {
            case 1: drawTrainCardsAI(player);
                    break;
            case 2: claimPathAI(player);
                    break;
            case 3: drawTicketCardsAI(player);
                    break;
            case 4: break;  // Skip turn
        }
    }
    
    public void drawTrainCardsAI(Player player) {
        
        Random ran = new Random(); 
        int next1 = ran.nextInt(2) + 1;
        switch(next1){
            case 1: player.addTrainCard(trainDeck.draw());
                    int next2 = ran.nextInt(2) + 1;
                    switch(next2){
                        case 1: player.addTrainCard(trainDeck.draw());
                                break;
                        case 2: int next3 = ran.nextInt(5);
                                player.addTrainCard(zone.pickCard(next3));
                                zone.addCard(trainDeck.draw());
                                if (zone.hasThreeRainbows()) setupZone();
                                break;
                    }
                    break;
            case 2: int next4 = ran.nextInt(5);
                    TrainCard card1 = zone.pickCard(next4);
                    player.addTrainCard(card1);
                    zone.addCard(trainDeck.draw());
                    if (zone.hasThreeRainbows()) setupZone();
                    if (card1.getValue() == VALUE.RAINBOW) break;
                    else{
                        int next5 = ran.nextInt(2) + 1;
                        switch(next5){
                        case 1: player.addTrainCard(trainDeck.draw());
                                break;
                        case 2: int next6 = ran.nextInt(5);
                                TrainCard card2 = zone.pickCard(next6);
                                if (card2.getValue() != VALUE.RAINBOW) player.addTrainCard(card2);
                                else {
                                    do {
                                        next6 = ran.nextInt(5);
                                        zone.addCard(card2);
                                        card2 = zone.pickCard(next6);
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
    
    public void claimPathAI(Player player) {
        Random ran = new Random(); 
        boolean fitColor = true;
        boolean fitColor1 = false;
        boolean fitColor2 = false;
        int score,length;
        do
        {
        int next = ran.nextInt(player.getHandTicketCSize());
        int i = player.getTicketCard(next).getLocation1();
        int j = player.getTicketCard(next).getLocation2();
        Path claim = board.getPath(i, j);
        VALUE color = null;
        int index1 = -1,index2 = -1;
        for(int c = 0; c < player.getHandTrainCSize(); c++){
            if (claim.getColor1() == player.getTrainCard(c).getValue()){
                fitColor1 = true;
                index1=c;
            }
            if (claim.getColor2() == player.getTrainCard(c).getValue()){
                fitColor2 = true;
                index2=c;
            }
        }
        if (fitColor1 == true && fitColor2 == true){
            
        int next1 = ran.nextInt(2)+1;
        switch(next1) {
            case 1: color = claim.getColor1();
                    player.removeTrainCard(index1);
                    break;
            case 2: color = claim.getColor2();
                    player.removeTrainCard(index2);
                    break;
        }
        }
        else if (fitColor1 == true && fitColor2 == false){
            color = claim.getColor1();
            player.removeTrainCard(index1);
        }
        else if (fitColor2 == true && fitColor1 == false){
            color = claim.getColor2();
            player.removeTrainCard(index2);
        }
        else fitColor = false;
        board.claimPath(player, color, i, j);   // Claim path and color for player
        score = claim.getValue();
        length = claim.getLength();
        }
        while(!fitColor);
        player.addScore(score);      // Add score to player
        player.subtractTrains(length);   // Subtract trainsasdf
        
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
    
    public void drawTicketCardsAI(Player player) {
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
        
        Random ran = new Random(); 
        int next = ran.nextInt(7) + 1;
        switch(next){
            case 1:
                break;
            case 2:
                    ticketDeck.addTicketDeck(t3);
                player.removeTicketCard(player.getHandTicketCSize()-1);
                break;
            case 3:
                ticketDeck.addTicketDeck(t2);
                player.removeTicketCard(player.getHandTicketCSize()-2);
                break;
            case 4:
                if(t1 != null) {
                ticketDeck.addTicketDeck(t1);
                player.removeTicketCard(player.getHandTicketCSize()-3);
                }
                break;
            case 5:
                ticketDeck.addTicketDeck(t2);
                ticketDeck.addTicketDeck(t3);
                player.removeTicketCard(player.getHandTicketCSize()-1);
                player.removeTicketCard(player.getHandTicketCSize()-1);               
                break;
            case 6:
                if(t1 != null) {
                ticketDeck.addTicketDeck(t1);
                player.removeTicketCard(player.getHandTicketCSize()-3);
                }
                ticketDeck.addTicketDeck(t3);
                player.removeTicketCard(player.getHandTicketCSize()-1);
                break;
            case 7:
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
