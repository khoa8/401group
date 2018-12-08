/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Viet
 */
public class Board {
    final int NUM_CITIES;
    Path[][] paths; // incidence matrix
    ArrayList<Integer> visited = new ArrayList<>(); // for search algorithm
    //int longest;
    
    public Board(int n) {
        if(n < 4) n = 4;
        NUM_CITIES = n;
        paths = new Path[NUM_CITIES][NUM_CITIES];
        
        VALUE[] color = VALUE.values();
        
        Random rand = new Random();
        int[] randInts = new int[4];
        
        randInts = randomInts(rand, randInts);  
        addPath(color[randInts[0]], color[randInts[1]], randInts[2], 0, 1, randInts[3]);
        for(int i = 0; i < NUM_CITIES - 2; i++) {
            randInts = randomInts(rand, randInts);  
            addPath(color[randInts[0]], color[randInts[1]], randInts[2], i, i + 2, randInts[3]);
            randInts = randomInts(rand, randInts);  
            addPath(color[randInts[0]], color[randInts[1]], randInts[2], i + 1, i + 2, randInts[3]);
        }
    }
    
    public int[] randomInts(Random rand, int[] arr) {
        arr[0] = rand.nextInt(VALUE.values().length);
        arr[1] = rand.nextInt(VALUE.values().length);
        while(arr[0] == arr[1]) {
            arr[1] = rand.nextInt(VALUE.values().length);
        }
        arr[2] = rand.nextInt(4) + 3;
        arr[3] = rand.nextInt(2) + 1;
        return arr;
    }
    
    public int getNumCities() {
        return NUM_CITIES;
    }
    
    @Override
    public String toString() {
        String s =  "Board has " + NUM_CITIES + " cities:\n";
        for(int i = 0; i < NUM_CITIES; i++) {
            for(int j = 0; j < i; j++) {
                if(hasPath(i,j)) {
                    s += getPath(i,j).toString() + "\n";
                }
            }
        }
        return s;
    }
    
    public Path[][] getPaths() {
        return paths;
    }
    
    public void addPath(VALUE c1, VALUE c2, int l, int i, int j, int n) {
        Path p = new Path(c1,c2,l,i,j,n);
        paths[i][j] = p;
        paths[j][i] = p;
    }
    
    public Path getPath(int i, int j) {
        return paths[i][j];
    }
    
    public boolean hasPath(int i, int j) {
        return paths[i][j] instanceof Path;
    }
    
    public void claimPath(Player player, VALUE color, int i, int j) {
        paths[i][j].claim(player, color);
    }
    
    public boolean isClaimedBy(Player player, int i, int j) {
        if(hasPath(i, j)){
            return getPath(i, j).isClaimedBy(player);
        }
        return false;
    }
    
    // if player paths for city1 or city2 do not exists,
    // then return false
    // else do search algorithm from city1 to city2
    public boolean checkTicket(Player player, TicketCard ticket) {
        //check both loc1 and loc2 if there exists paths claimed by player
        //true or false
        boolean p1 = false, p2 = false;
        for(int j = 0; j < 5; j++) {
            if(isClaimedBy(player, ticket.getLocation1(), j))
                p1 = true;
        }
        for(int i = 0; i < 5; i++) {
            if(isClaimedBy(player, ticket.getLocation2(), i))
                p2 = true;
        }
        if(!p1 || !p2) return false; //impossible route
        //else
        visited.clear();
        return checkRoute(player, ticket.getLocation1(), ticket.getLocation2());
    }
    
    // search algorithm
    public boolean checkRoute(Player player, int i, int j) {
        boolean found = false;
        if(i == j) found = true;
        else if(!visited.contains(i)) {
            visited.add(0, i);
            for(int n = 0; n < 5; n++) {
                if(isClaimedBy(player, i, n))
                    found = checkRoute(player, n, j);
            }
        }
        return found;
    }
    
}
