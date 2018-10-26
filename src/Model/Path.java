/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Viet
 */
public class Path {
    int color;
    int length;
    int loc1;
    int loc2;
    int numPaths;
    Player claim1 = null;
    Player claim2 = null;
    
    public Path(int color, int length, int loc1, int loc2, int numPaths) {
        this.color = color;
        this.length = length;
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.numPaths = numPaths;
    }
    
    @Override
    public String toString() { 
        String s = "Path(" + loc1 + "," + loc2 + ") : Color " + color + " : Length " + length + "\n";
        // claim1 claim2
        return s;
    }
    
    public int calculateValue() {
        return 0;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public boolean isFull() {
        return false;
    }
    
    public boolean isClaimedBy(Player player) {
        return claim1 == player || claim2 == player;
    }
    
    public int getColor() {
        return 0;
    }
    
    public int getLength() {
        return 0;
    }
    
    public int getValue() {
        return 0;
    }
    
    public int getClaims() {
        return 0;
    }
    
    public int getClaim1() {
        return 0;
    }
    
    public int getClaim2() {
        return 0;
    }
    
    public void claim(Player player) {
        if(claim1 != null)
            claim1 = player;
        else if(numPaths == 2)
            claim2 = player;
        //else no claim   
    }
    
}
