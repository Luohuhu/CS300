//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Ballot Box
// Course:   CS 300 Fall 2020
//
// Author:   Xianfu Luo
// Email:    xluo96@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
/**
 * A class to count Ballot and get winner.
 * @author Xianfu Luo
 * @version 1.0
 */
public class BallotBox {
    //private ​instance variables
    private ArrayList<Ballot> ballots; // the Ballots which have been cast

    public BallotBox(){
        ballots = new ArrayList<Ballot>();
    }
    // Accessor method
    public int getNumBallots(){
        return ballots.size();
    }
    public Candidate getWinner(String office){
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        if (ballots.size()==0) return null;
        int[] voteNumber = new int[1000];
        for (int i=0; i<ballots.size(); i++){
            boolean exist = false;
            for (int j=0; j<candidates.size(); j++){
                if (ballots.get(i).getVote(office).getName().equals(candidates.get(j).getName())){
                    voteNumber[j]++;
                    exist = true;
                    break;
                }
            }
            if (!exist){
                Candidate c = ballots.get(i).getVote(office);
                candidates.add(c);
                voteNumber[candidates.size()-1]++;
            }
        }int max=0;
        for (int i=1; i<candidates.size(); i++){
            if (voteNumber[max]<voteNumber[i]){
                max=i;
            }
        }
        return candidates.get(max);
    }
    public void submit(Ballot b){
        if (!ballots.contains(b)){
            ballots.add(b);
        }
    }
}
