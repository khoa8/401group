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
    //DiscardPile dp;
    TicketCard tc = new TicketCard();
    TicketCardDeck tcd = new TicketCardDeck();
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
    /*
    //cant test for DiscardPile.java yet b/c our code aren't merged yet
    @Test
    public void add1() {
        //DiscardPile.java
        //test if discarded traincard array is empty
        
    }
    @Test
    public void remove1() {
        //DiscardPile.java
        //test if used train cards in hand are removed
        
    }
    @Test
    public void remove2() {
        //DiscardPile.java 
        //test if removed train card is in discard pile 
        
    }
*/
    
    
    
            
            
}
