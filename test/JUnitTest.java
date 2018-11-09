/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.DiscardPile;
import Model.TicketCard;
import Model.TicketCardDeck;
import Model.TrainCard;
import Model.TrainCardDeck;
import static Model.VALUE.RAINBOW;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */

public class JUnitTest {
    DiscardPile dp = new DiscardPile();
    TicketCard tc = new TicketCard();
    TicketCardDeck tcd = new TicketCardDeck();
    TrainCard Tc = new TrainCard();
    TrainCardDeck Tcd = new TrainCardDeck();
    
    @Test
    public void addTicketDecktest() {
        //TicketCardDeck.java 
        //test if ticket deck recieved the ticket card added to bottom of deck
        tc = new TicketCard(4,5,6);
         tcd.addTicketDeck(tc);
         assertEquals(true, tcd.Ticketdeck.contains(tc));
    }
    @Test
    public void drawTicketCardtest() {
        //TicketCardDeck.java 
        //test if the top ticket card in deck is removed from the deck
        tc = new TicketCard(4,5,7);
        tcd.Ticketdeck.set(0, tc);
        tcd.drawTicketCard();
        assertEquals(false, tcd.Ticketdeck.contains(tc));
    }
    @Test
    public void shuffleTicketdecktest() {
        //TicketCardDeck.java 
        //shuffle Ticketdeck test
        //might fail sometimes bacause Ticketdeck could be shuffled but have same top card
        tcd.Ticketdeck = new ArrayList();
        tc = new TicketCard(4,5,7);
        tcd.Ticketdeck.add(0, tc);
        tc = new TicketCard(4,5,6);
        tcd.Ticketdeck.add(1, tc);
        tc = new TicketCard(4,5,5);
        tcd.Ticketdeck.add(2, tc);
        tc = new TicketCard(4,5,4);
        tcd.Ticketdeck.add(3, tc);
        tc = new TicketCard(4,5,7);
        tcd.drawTicketCard();
        assertFalse(tc == tcd.Ticketdeck.get(0));
    }
    @Test
    public void resetDiscardPiletest() {
        //DiscardPile.java
        //test if discard pile is empty
        Tc = new TrainCard(RAINBOW);
        dp.discarded.add( Tc);
        dp.resetDiscardPile();
        assertEquals(false, dp.discarded.contains(Tc));
    }
    @Test
    public void addDiscardPiletest() {
        //DiscardPile.java 
        //test if removed train card is in discard pile 
        Tc = new TrainCard(RAINBOW);
        dp.resetDiscardPile();
        dp.addDiscardPile(Tc);
        assertEquals(true, dp.discarded.contains(Tc));
    }
    public void addDiscardPileToTrainDecktest() {
        //test if discardpile is empty and cards passed to train card deck
        Tc = new TrainCard(RAINBOW);
        Tcd = new TrainCardDeck();
        Tcd.cardDeck = new ArrayList();
        dp.addDiscardPile(Tc);
        dp.addDiscardPile(Tc);
        dp.addDiscardPileToTrainDeck(Tcd);
        assertEquals(false, dp.discarded.contains(Tc));
        assertEquals(true, Tcd.cardDeck.contains(Tc));
    }
}
    
    
    
            
            
