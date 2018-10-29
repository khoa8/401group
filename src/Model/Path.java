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
    VALUE color1;
    VALUE color2;
    int length;
    int loc1;
    int loc2;
    int numPaths;
    Player claim1 = null;
    Player claim2 = null;
    
    public Path(VALUE color1, VALUE color2, int length, int loc1, int loc2, int numPaths) {
        this.color1 = color1;
        this.color2 = color2;
        this.length = length;
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.numPaths = numPaths;
    }
    
    @Override
    public String toString() { 
        String s = "Path(" + loc1 + "," + loc2 + ") , Length(" + length + ")\n";
        s += color1 + " claimed by " + (claim1 == null ? "nobody" : claim1.toString());
        s += "\n";
        if(numPaths == 2) {
            s += color2 + " claimed by " + (claim2 == null ? "nobody" : claim2.toString());
            s += "\n";
        }
        return s;
    }
    
    public boolean isEmpty() {
        return (numPaths == 1 && claim1 == null) 
                || (numPaths == 2 && claim1 == null && claim2 == null);
    }
    
    public boolean isFull() {
        return false;
    }
    
    public boolean isClaimedBy(Player player) {
        return claim1 == player || claim2 == player;
    }
    
    public VALUE getColor1() {
        return color1;
    }
    
    public VALUE getColor2() {
        return color2;
    }
    
    public int getLength() {
        return length;
    }
    
    public int getValue() {
        return calculateValue();
    }
    
    public int calculateValue() {
        //look at route length chart
        return length;
    }
    
    public Player getClaim1() {
        return claim1;
    }
    
    public Player getClaim2() {
        return claim2;
    }
    
    public void claim(Player player, VALUE color) {
          if(color == color1) claim1 = player;
          if(color == color2 && numPaths == 2) claim2 = player;
    }
    
}
