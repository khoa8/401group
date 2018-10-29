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
public class Board {
    final int NUM_CITIES = 9;
    Path[][] paths; // incidence matrix
    ArrayList<Integer> visited = new ArrayList<>(); // for search algorithm
    int longest;
    
    public Board() {
        paths = new Path[NUM_CITIES][NUM_CITIES];
        addPath(VALUE.RAINBOW, VALUE.ORANGE, 6, 0, 1, 2);
        addPath(VALUE.PINK, VALUE.BLACK, 6, 0, 2, 2);
        addPath(VALUE.RAINBOW, VALUE.RED, 6, 0, 3, 2);
        addPath(VALUE.WHITE, VALUE.GREEN, 6, 1, 2, 2);
        addPath(VALUE.RAINBOW, VALUE.PINK, 3, 1, 4, 2);
        addPath(VALUE.BLUE, VALUE.WHITE, 3, 2, 3, 2);
        addPath(VALUE.YELLOW, VALUE.BLUE, 3, 2, 4, 2);
        addPath(VALUE.RAINBOW, VALUE.YELLOW, 3, 3, 4, 2);
    }
    
    @Override
    public String toString() {
        String s =  "0-----1\n" +
                    "| \\ / |\n" +
                    "|  2  |\n" +
                    "| / \\ |\n" +
                    "3-----4\n\n";
        s += getPath(0,1).toString() + "\n";
        s += getPath(0,2).toString() + "\n";
        s += getPath(0,3).toString() + "\n";
        s += getPath(1,2).toString() + "\n";
        s += getPath(1,4).toString() + "\n";
        s += getPath(2,3).toString() + "\n";
        s += getPath(2,4).toString() + "\n";
        s += getPath(3,4).toString() + "\n";
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
            if(getPath(i, j).isClaimedBy(player))
                return true;
        }
        return false;
    }
    
    public int longest(Player player, int i, int j, int length) {
        
        return length;
    }
    
    //work in progress
    public ArrayList<Integer> longestHelper(Player player, int i, ArrayList<Integer> v) {
        ArrayList<Integer> biggest = v; //list of visited cities
        ArrayList<Integer> tempV = null;
        if(!v.contains(i)) {
            v.add(0, i);
            for(int n = 0; n < 5; n++) {
                if(isClaimedBy(player, i, n)) {
                    tempV = longestHelper(player, n, new ArrayList<>(v));
                    if(calculateLength(biggest) < calculateLength(tempV)) {
                        biggest = tempV;
                    }
                }
            }
            return biggest;
        }
        else// if i visited
            return v;
    }
    
    //sum of paths to visited cities 
    public int calculateLength(ArrayList<Integer> v) {
        int n = 0;
        for(int i = 0; i < v.size() - 1; i++)
            n += getPath(v.indexOf(i), v.indexOf(i+1)).getLength();
        return n;
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
        return false; //return found;
    }
    
}
