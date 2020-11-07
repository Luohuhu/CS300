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
import java.util.NoSuchElementException;
/**
 * A class includes information of Ballot.
 * @author Xianfu Luo
 * @version 1.0
 */
public class Ballot {
    //private ​class variables
    private static ArrayList<Party> parties = new ArrayList<Party>(); // the Parties for the election
    private static int counter = 0; // generate unique ID value

    public static void addParty(Party p){
        boolean flag = true;
        for (int i=0; i<parties.size(); i++){
            if (parties.get(i).getName().equals(p.getName())){
                flag = false;
                break;
            }
        }
        if (flag){
            parties.add(p);
        }
    }
    public static ArrayList<Candidate> getCandidates(String office){
        ArrayList<Candidate> Candidates = new ArrayList<Candidate>();
        for (int i=0; i<parties.size(); i++){
            try{
                Candidate c= parties.get(i).getCandidate(office);
                Candidates.add(c);
            }catch (NoSuchElementException e){
            }
        }
        return Candidates;
    }

    //private ​instance variables
    private Candidate[] votes;
    private final int ID; // the​ unique ID​ of this Ballot
    public Ballot(){
        votes = new Candidate[3];
        ID = counter++;
    }
    // Accessor method
    public Candidate getVote(String office){
        Candidate result = null;
        for (int i=0; i<votes.length; i++){
            if (votes[i]==null) continue;;
            if (votes[i].getOffice().equals(office)){
                result = votes[i];
                break;
            }
        }
        return result;
    }
    /**
     * Returns true if the argument is equal to this Ballot, false otherwise
     * @param o the object to compare to this Ballot
     * @return true if the Ballots have the same ID, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if(!o.getClass().equals(this.getClass())){
            return false;
        }else {
            Ballot other = (Ballot)o;
            return ID==other.ID;
        }
    }
    public void vote(Candidate c){
        for (int i=0; i<votes.length; i++){
            if (c.getOffice().equals(Candidate.OFFICE[i])){
                votes[i]=c;
            }
        }
    }
}
