/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Daniel
 */

public class TicketCard {
    
    public int value;    //stores card’s value
    public int loc1, loc2;    //stores int location
    
    public TicketCard(){
        loc1 = 1;
        loc2 = 2;
        value = 1;
    }
    public TicketCard(int location1, int location2, int values){
        // set ticket card from the list of created Ticketcards from TicketCardDeck.java
        loc1 = location1;
        loc2 = location2;
        value = values;
    }
    public int getValue() {
        
        return value;
    }
    public int getLocation1() {
        
        return loc1;
    }
    public int getLocation2() {
        
        return loc2;
    }    
    @Override
    public String toString(){
        return "City " + loc1 + " to City " + loc2 + " is: " + value;
    }
    /*
    //test print for TicketCard
    public static void main(String[] args)  {
        TicketCard tc = new TicketCard();
        System.out.print(tc.toString() + "\n");
        //run: City 1 to City 2 is: 1
   }
    */
}


