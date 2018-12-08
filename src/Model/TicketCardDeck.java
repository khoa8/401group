/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Daniel
 */
public class TicketCardDeck {
    
    public ArrayList<TicketCard> Ticketdeck = new ArrayList();    //ticket cards deck
    
    public TicketCardDeck(int numCities) {
        // create ticket cards and ticket deck
        Random rand = new Random();
        int randInt1;
        int randInt2;
        int randInt3;
        for(int i = 0; i < numCities * 2; i ++) {
            randInt1 = rand.nextInt(numCities);
            randInt2 = rand.nextInt(numCities);
            while(randInt1 == randInt2) {
                randInt2 = rand.nextInt(numCities);
            }
            randInt3 = rand.nextInt(11) + 5;
            Ticketdeck.add( 0, new TicketCard(randInt1, randInt2, randInt3));
            
        }
    }
    public TicketCard drawTicketCard(){
        //draw 1 cards from ticket deck and return ticketcard
        //0 is top of deck, ticketdeck.size()-1 is bottom of deck
        TicketCard card = Ticketdeck.get(0);
        Ticketdeck.remove(0);
        return card;
    }
    public void addTicketDeck(TicketCard tc) {
        //put ticket card from hand and add it to bottom of ticket deck
        Ticketdeck.add(tc);
}
    public void shuffleTicketdeck(){
        //shuffle Ticketdeck
        TicketCard temp = Ticketdeck.get(0);
        int rand;
        for (int i = 0; i < (Ticketdeck.size()-1); i++){
            rand = ((int) (Math.random() * 100) % (Ticketdeck.size() - i)) + i;
            if (rand < 0)
                rand = 0;
            Ticketdeck.set(i, Ticketdeck.get(rand));
            Ticketdeck.set(rand, temp);
        }
    }
    /*
        //test print for TicketCard
    public static void main(String[] args)  {
        TicketCardDeck d = new TicketCardDeck();
        System.out.print(d.Ticketdeck.get(0) + "\n");
        //run: City 0 to City 1 is: 1
   }
    */
}
