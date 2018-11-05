/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Daniel
 */
public class DiscardPile {
    
    public static List <TrainCard> discarded = new ArrayList();  //discarded cards list created/ininlalized

    public void resetDiscardPile() {
        //empty the discardpile because the TrainCards in discard pile
        //are put back in TrainCardDeck
            discarded = new ArrayList();
    }
    public void addDiscardPile(TrainCard discard) {
        //move train card from hand to discard pile;
            discarded.add(discard);     
    }
    public TrainCard getDiscardPile() {
        //get discardpile
        return (TrainCard) discarded;
    }
}


