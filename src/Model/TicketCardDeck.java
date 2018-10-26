/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
/**
 *
 * @author Daniel
 */
public class TicketCardDeck {
    
    public ArrayList<TicketCard> Ticketdeck = new ArrayList();    //ticket cards deck
    
    public TicketCardDeck() {
        // create ticket cards and ticket deck
        Ticketdeck.add( 0, new TicketCard(0, 1, 1));
        Ticketdeck.add( 1, new TicketCard(0, 2, 1));
        Ticketdeck.add( 2, new TicketCard(0, 3, 1));
        Ticketdeck.add( 3, new TicketCard(1, 2, 1));
        Ticketdeck.add( 4, new TicketCard(1, 4, 1));
        Ticketdeck.add( 5, new TicketCard(2, 3, 1));
        Ticketdeck.add( 6, new TicketCard(2, 4, 1));
        Ticketdeck.add( 7, new TicketCard(3, 4, 1));
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
