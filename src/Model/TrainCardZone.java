/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Viet
 */
public class TrainCardZone {
    ArrayList<TrainCard> zone;
    
    public TrainCardZone() {
        zone = new ArrayList<>();
    }
    
    public ArrayList getCardArray() {
        return zone;
    }
    
    public void addCard(TrainCard card) {
        zone.add(card);
    }
    
    public int getSize() {
        return zone.size();
    }
    
    public TrainCard pickCard(int i) {
        TrainCard card = getCard(i);
        removeCard(i);
        return card;
    }
    
    public TrainCard getCard(int i) {
        return zone.get(i);
    }
    
    public void removeCard(int i) {
        zone.remove(i);
    }
    
    public boolean hasThreeRainbows() {
        int rainbows = 0;
        for(int i = 0; i < zone.size(); i++) {
            //if(zone.get(i).getValue() == VALUE.RAINBOW)
                //rainbows += 1;
            
        }
        return rainbows >= 3;
    }
    
}
