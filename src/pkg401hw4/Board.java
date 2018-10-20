/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg401hw4;

/**
 *
 * @author Viet
 */
public class Board {
    final int NUM_CITIES = 9;
    Path[][] paths;
    int longest;
    
    public Board() {
        paths = new Path[NUM_CITIES][NUM_CITIES];
    }
    
    public Path[][] getPaths() {
        return paths;
    }
    
    public void addPath(int i, int j) {
        
    }
    
    public int getPath(int i, int j) {
        
        return 0;
    }
    
    public boolean isTherePath(int i, int j) {
        return false;
    }
    
    public void claimPath(int i, int j) {
        
    }
    
    public boolean isPathClaimed(int i, int j) {
        return false;
    }
    
    
}
