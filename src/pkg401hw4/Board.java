/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg401hw4;

import java.util.ArrayList;

/**
 *
 * @author Viet
 */
public class Board {
    final int NUM_CITIES = 9;
    Path[][] paths;
    ArrayList<Integer> visited = new ArrayList<>();
    int longest;
    
    public Board() {
        paths = new Path[NUM_CITIES][NUM_CITIES];
        addPath(0,1);
        addPath(0,2);
        addPath(0,3);
        addPath(1,2);
        addPath(1,4);
        addPath(2,3);
        addPath(2,4);
        addPath(3,4);
    }
    
    @Override
    public String toString() {
        String s =  "0-----1\n" +
                    "| \\ / |\n" +
                    "|  2  |\n" +
                    "| / \\ |\n" +
                    "3-----4\n\n";
        s += getPath(0,1).toString();
        return s;
    }
    
    public Path[][] getPaths() {
        return paths;
    }
    
    public void addPath(int i, int j) {
        Path p = new Path(0,0,i,j,2);//
        paths[i][j] = p;
        paths[j][i] = p;
    }
    
    public Path getPath(int i, int j) {
        return paths[i][j];
    }
    
    public boolean hasPath(int i, int j) {
        return paths[i][j] instanceof Path;
    }
    
    public void claimPath(int player, int i, int j) {
        //paths[i][j].claim(0);
    }
    
    public boolean isClaimedBy(int player, int i, int j) {
         //if hasPath(i, n)
            //if getPath(i, n).isClaimedBy(player)
                //return true;
        return false;
    }
    
    public int longest(int player, int i, int j, int length) {
        
        return length;
    }
    //work in progress
    /*public ArrayList<Integer> longestHelper(int i, ArrayList<Integer> visited) {
        //ArrayList<Integer> biggest = visited;
        //ArrayList<Integer> tempV;
        //if i is not visited
            //visited.add(0, i);
            //biggest = calculateLength(v);
            //for(int n = 0; n < 5; n++) 
                //if isClaimedBy(player, i, n)
                    //
                    //tempV = longestHelper(n, new ArrayList<Integer>(v);
                    //if(calculateLenth(biggest) < calculate(tempV))
                        //biggest = tempV;
                    
        //else if i visited
            //return visited
    }*/
    
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
    public boolean checkTicket(int player, int t) {
        //check both loc1 and loc2 if there exists paths claimed by player
        //true or false
        //boolean p1 p2
        //for(int = j; j < 5; j++)
            //if isClaimedBy(player, t.getloc1, i)
                //p1 = true;
        //for(int = i; i < 5; i++)
            //if isClaimedBy(player, t.getloc2, i)
                //p2 = true;
        //if !p1 || !p2 then false
        //else do algorithm
        //visited.clear();
        //return checkRoute(player, t.getloc1, t.getloc2);
        //visited.clear();
        return false;
    }
    
    // search algorithm
    public boolean checkRoute(int player, int i, int j) {
        // found = false;
        // if i == j 
            // found = true;
        //else if i is not visited
            //add i to visited
            //for(int n = 0; n < 5; n++) 
                //if isClaimedBy(player, i, n)
                    //found = checkRoute(player, n, j)
        //
        return false; //return found;
    }
    
}
