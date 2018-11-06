/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Viet
 */
public class Display {
    private final Scanner stdin = new Scanner(System.in);
    public String PlayerName(){
        System.out.println("Please enter the name of player: ");
            return stdin.next();
    }
    public int drawTicketCardAtBeginning(TicketCard tc1,TicketCard tc2,TicketCard tc3){
        System.out.println("a. "+tc1.toString());
        System.out.println("b. "+tc2.toString());
        System.out.println("c. "+tc3.toString());
        System.out.println("Please choose to keep:");
        System.out.println("1. All three.");
        System.out.println("2. a + b");
        System.out.println("3. a + c");
        System.out.println("4. b + c");
        return stdin.nextInt();
    }
    public void trainCardZone(TrainCardZone tcz){
        System.out.println(tcz.getCardArray());
    }
    public int drawTrainCard(){
        System.out.println("Please choose to draw:");
        System.out.println("1. Blind draw.");
        System.out.println("2. Face-up card draw.");
        return stdin.nextInt();
    }
    public int drawTrainCardfromZone(TrainCardZone tcz){
        System.out.println(tcz.getCardArray());
        System.out.println("Please pick the index to draw:");
        return stdin.nextInt();
    }
    
    public void printToString(String s) {
        System.out.println(s);
    }
    
    public int promptAction() {
        System.out.println("Choose which action to take:");
        System.out.println("1. Draw train cards");
        System.out.println("2. Claim a path");
        System.out.println("3. Draw ticket cards");
        System.out.println("4. Skip turn");
        System.out.print("Choose number: ");
        return stdin.nextInt();
    }
    
    public void promptPath() {
        System.out.print("Choose which path to claim: ");
    }
    
    public int promptCity() {
        System.out.print("Choose city: ");
        return stdin.nextInt();
    }
    
    public int promptColor(VALUE value1, VALUE value2) {
        System.out.println("1. " + value1);
        System.out.println("1. " + value2);
        System.out.print("Choose color: ");
        return stdin.nextInt();
    }
    
    public int promptRemoveColor(VALUE color, List<TrainCard> hand) {
        int i = 0;
        System.out.println("Claiming " + color);
        System.out.println("-1. Done with claiming");
        for(TrainCard card : hand) {
            System.out.println(i + ". " + card.getValue());
            i++;
        }
        System.out.print("Choose card to remove: ");
        return stdin.nextInt();
    }
    
    public char promptToPlayAgain() {
        System.out.print("Play again? (Y/N): ");
        return stdin.next().charAt(0);
    }
    
    public void printCalculating() {
        System.out.println("Calculating scores...");
    }
    
    public void printScores(Player[] players, int[] scores) {
        System.out.println("Scores:");
        for(int i = 0; i < scores.length; i++) {
            System.out.print(players[i].getName() + ":" + scores[i]);
            if(i < scores.length - 1)
                System.out.print(", ");
        }
        System.out.println();
        
    }
    
    public void printWinner(Player player) {
        System.out.println("The winner is " + player + "!");
    }
    public int drawTicketCards(TicketCard tc1,TicketCard tc2,TicketCard tc3){
        if(tc1 != null && tc2 != null && tc3 != null){
        System.out.println("Please choose which ticket cards to keep:");
        if (tc1 != null)
            System.out.println("a. "+tc1.toString());
        if (tc2 != null)
            System.out.println("b. "+tc2.toString());
        System.out.println("c. "+tc3.toString());
        if(tc1 != null && tc2 != null && tc3 != null)
            System.out.println("1. All three.");
        if(tc1 != null && tc2 != null)
            System.out.println("2. a + b");
        if(tc1 != null && tc3 != null)
            System.out.println("3. a + c");
        if(tc2 != null && tc3 != null)
            System.out.println("4. b + c");
        if(tc1 != null)
            System.out.println("5. a");
        if(tc2 != null)
            System.out.println("6. b");
        if(tc3 != null)
            System.out.println("7. c");
        return stdin.nextInt();
    }
        else{
            System.out.println("Failed to draw ticket cards. Ticket deck is empty");
            return 1;
        }
    }
   
}

