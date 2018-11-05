/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.DiscardPile;
import Model.TicketCard;
import Model.TicketCardDeck;
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
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:

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
}
    
    
    
            
            
}
