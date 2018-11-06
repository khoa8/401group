/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
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
