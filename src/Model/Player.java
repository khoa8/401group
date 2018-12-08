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
 * @author nguyenminhkhoa
 */
/*
enum CAR {
     BLUEcar, YELLOWcar, BLACKcar, REDcar, GREENcar;
}
*/

public class Player {
    
    private int score;
    private final String name;
    private int trains; //number of train cars;
    List<TrainCard> handTrainC;
    List<TicketCard> handTicketC;
    //private static final List<CAR> train = new ArrayList<>();
    //private static final int SIZE = train.size();
    //private static final Random rand = new Random();
    
   
    //initialize player with name, score, assign random color trains
    public Player(String name, int trainsnum){
        handTrainC = new ArrayList<>();
        handTicketC = new ArrayList<>();
        //train.addAll(Arrays.asList(CAR.values()));
        this.name = name; 
        score = 0;
        trains = trainsnum;
    }
    
    public List<TrainCard> getHandTrainC(){
        return handTrainC;
    }
    
    public List<TicketCard> getHandTicketC(){
        return handTicketC;
    }

    public void addTrainCard(TrainCard trc) {
        handTrainC.add(trc);
    }
    
    public void addTicketCard(TicketCard tic) {
        handTicketC.add(tic);
    }
    
    public String getName() {
        return name;
    }

    // Get the number of card left in the player's hand
    public int getHandTrainCSize() {
        return handTrainC.size();
    }
    
     public int getHandTicketCSize() {
        return handTicketC.size();
    }

    // retrieve card at indicated position
    public TrainCard getTrainCard(int CardToPlay) {
        return handTrainC.get(CardToPlay);
    }

    public TicketCard getTicketCard(int CardToPlay) {
        return handTicketC.get(CardToPlay);
    }
    
    // remove selected card from hand
    public void removeTrainCard(int CardToPlay) {
        handTrainC.remove(CardToPlay);
    }
    
    public void removeTicketCard(int CardToPlay) {
        handTicketC.remove(CardToPlay);
    }
    
    @Override
    public String toString() {
        return "Player: " + name + "\n" +
                "Score: " + score + "\t" + "Trains: " + trains + "\n" +
                "handTrainCard= " + handTrainC + 
                "\nhandTicketCard= " + handTicketC;
    }
   
    public String showHandTrainCard(){
        return "\nPlayer's handTrainCard= " + handTrainC;
    }
    
    //method to add score
    public void addScore(int score){
        this.score += score; 
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    //method to subtract score if not finishing missions when game ends
    public void subtractScore(int score){
        this.score -= score;
    }
    
    //When player’s stock of colored trains gets down 
    //to less than 3 trains at the end of turn, the game is going to end.
    public boolean endOfGame(){
        return trains < 3;
    }
    
    public void subtractTrains(int num){
        this.trains -= num;
    }
    
    public int getScore(){
        return score;
    }

    public void setTrains(int i) {
        this.trains = i;
    }
     
}
