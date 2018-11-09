/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Viet
 */
public class JUtest {
    static Board board;
    static Path path;
    static TrainCardZone zone;
    static Player player;
    static TrainCard card;
    
    
    public JUtest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        board = new Board();
        zone = new TrainCardZone();
        player = new Player("new");
        
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
    
    @Test   
    public void testisTherePath() {
        assertTrue(board.hasPath(0, 1));
        assertTrue(board.hasPath(0, 2));
        assertTrue(board.hasPath(0, 3));
    }
    
    @Test   
    public void testisTherePathFalse() {
        assertFalse(board.hasPath(0, 0));
        assertFalse(board.hasPath(0, 4));
    }
    
    @Test
    public void testisPathClaimed() {
        board.claimPath(player, VALUE.RAINBOW, 0, 1);
        assertTrue(board.getPath(0, 1).isClaimedBy(player));
    }
    
    @Test  
    public void testisPathClaimedFalse() {
        assertFalse(board.getPath(0, 2).isClaimedBy(player));
    }
    
    @Test
    public void testaddCard() {
        TrainCard card = new TrainCard(VALUE.RAINBOW);
        zone.addCard(card);
        assertTrue(zone.getCardArray().contains(card));
        
        int size = zone.getSize();
        card = new TrainCard(VALUE.RAINBOW);
        zone.addCard(card);
        assertTrue(zone.getSize() > size);
        
    }
    
    @Test
    public void testpickCard() {
        TrainCard picked = zone.pickCard(0);
        assertFalse(zone.getCardArray().contains(picked));
    }
    
    @Test
    public void testhasThreeRainbowsFalse() {
        assertFalse(zone.hasThreeRainbows());
        zone.addCard(new TrainCard(VALUE.RAINBOW));
        assertFalse(zone.hasThreeRainbows());
        zone.addCard(new TrainCard(VALUE.RAINBOW));
        assertFalse(zone.hasThreeRainbows());
    }
    
    @Test
    public void testhasThreeRainbows() {
        zone.addCard(new TrainCard(VALUE.RAINBOW));
        assertTrue(zone.hasThreeRainbows());
    }
    
    @Test
    public void testcheckTicket() {
        board.claimPath(player, VALUE.RAINBOW, 0, 1);
        board.claimPath(player, VALUE.RAINBOW, 1, 4);
        board.claimPath(player, VALUE.RAINBOW, 3, 4);
        TicketCard card = new TicketCard(0, 1, 1);
        assertTrue(board.checkTicket(player, card));
        card = new TicketCard(1, 4, 1);
        assertTrue(board.checkTicket(player, card));
        card = new TicketCard(0, 4, 1);
        assertTrue(board.checkTicket(player, card));
        card = new TicketCard(0, 3, 1);
        assertTrue(board.checkTicket(player, card));
    }
    
    @Test
    public void testcheckTicketFalse() {
        TicketCard card = new TicketCard(0, 2, 1);
        assertFalse(board.checkTicket(player, card));
    }
    
    @Test
    public void testlongest() {
        board.claimPath(player, VALUE.RAINBOW, 0, 3);
        int n = board.longest(player, 1, 4, 0);
        assertEquals(15, n);
    }
    
}
