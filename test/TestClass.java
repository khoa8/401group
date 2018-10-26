/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import Model.Player;
import Model.TrainCard;
import Model.TrainCardDeck;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author nguyenminhkhoa
 */
public class TestClass {
    
    private TrainCardDeck deck;
    private Player player;
    
    public TestClass() {
    }
    
    @Before
    public void setUp() {
        deck = new TrainCardDeck();
        player = new Player();
    }

    @Test
    public void emptyDeck() {
        //test case for isEmpty() method from TrainCardDeck.class
        deck.discard();
        assertTrue(deck.deckIsEmpty());
    }
    
    @Test
    public void notEndGame() {
        //test case for endOfGame() method from Player.class
        assertFalse(player.endOfGame());
    }
    
    @Test
    public void endGame() {
        //test case for endOfGame() method from Player.class
        player.setTrains(2);
        assertTrue(player.endOfGame());
    }
    
    @Test
    public void fullDeck() {
        //test case for getSizeOfDeck() method from TrainCardDeck.class
        assertEquals(110, deck.getSizeOfDeck());
    }

}