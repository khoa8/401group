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
public class DiscardPile {
    
    public ArrayList <TrainCard> discarded = new ArrayList();  //discarded cards list created/ininlalized
    
    public void add(TrainCardDeck tcd) {
        //put all discarded train cards back to train card deck when train deck is empty
        //for (TrainCard tc : discarded){
            //tcd.cardDeck.add(tc);
            discarded.remove(0);
        }
        //for (TrainCard tr : discarded){
            discarded.remove(0);
        }
    }
    
    public void remove(TrainCard [] discard) {
        //move train cards from hand to discard pile;
        for (TrainCard tc : discard){
            discarded.add(tc);
        }     
    }
    public TrainCard getDiscardPile(){
        return discarded;
    }
}

