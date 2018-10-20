/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pkg401hw4.*;

/**
 *
 * @author Viet
 */
public class JUnitTest {
    static Board board;
    static Path path;
    static TrainCardZone zone;
    
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        board = new Board();
        path = new Path(0, 0, 0, 0);
        zone = new TrainCardZone();
        
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
    public void testaddPath() {
        board.addPath(0, 0);
    }
    
    @Test   
    public void testisTherePath() {
        assertFalse(board.isTherePath(0, 0));
    }
 
    @Test   
    public void testclaimPath() {
        board.claimPath(0, 0);
    }
  
    @Test  
    public void testisPathClaimed() {
        assertFalse(board.isPathClaimed(0, 0));
    }
    
    @Test
    public void testcalculateLengthPoints() {
        assertEquals(0, path.calculateLengthPoints());
    }
    
    @Test
    public void testisEmpty() {
        assertFalse(path.isEmpty());
    }
    
    @Test
    public void testisFull() {
        assertFalse(path.isFull());
    }
    
    @Test
    public void testclaim() {
        path.claim(0, 0);
    }
    
    @Test
    public void testpickCard() {
        zone.pickCard();
    }
    
    @Test
    public void testaddCard() {
        zone.addCard();
    }
    
    @Test
    public void testremoveCard() {
        zone.removeCard();
    }
    
    @Test
    public void testhasThreeLocomotives() {
        assertFalse(zone.hasThreeLocomotives());
    }
    
}
