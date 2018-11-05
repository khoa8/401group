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
    }
  
    @Test  
    public void testisPathClaimed() {
        assertFalse(board.getPath(0, 1).isClaimedBy(player));
    }
    
    @Test
    public void testpickCard() {
        TrainCard card = new TrainCard(VALUE.RAINBOW);
        zone.addCard(card);
        TrainCard picked = zone.pickCard(0);
        assertEquals(card, picked);
        assertFalse(zone.getCardArray().contains(card));
    }
    
    @Test
    public void testaddCard() {
        TrainCard card = new TrainCard(VALUE.RAINBOW);
        zone.addCard(card);
        assertTrue(zone.getCardArray().contains(card));
    }
    
    @Test
    public void testhasThreeRainbowsFalse() {
        assertFalse(zone.hasThreeRainbows());
    }
    
    /*@Test
    public void testhasThreeRainbows() {
        zone.addCard(new TrainCard(VALUE.RAINBOW));
        zone.addCard(new TrainCard(VALUE.RAINBOW));
        assertTrue(zone.hasThreeRainbows());
    }*/
    
}
