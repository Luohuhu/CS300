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
 * A class includes information of Party.
 * @author Xianfu Luo
 * @version 1.0
 */
public class Party {
    //private ​instance variables
    private String name; // The Party’s name
    private ArrayList<Candidate> candidates; // Candidates affiliated with that Party

    public Party(String name){
        this.name=name;
        candidates = new ArrayList<Candidate>();
    }
    // Accessor method
    public String getName(){ // get name.
        return name;
    }
    public int getSize(){ // get the number of Candidates belonging to the Party.
        return candidates.size();
    }
    public Candidate getCandidate(String office) throws NoSuchElementException {
        boolean flag = false;
        int index=0;
        for (int i=0; i<candidates.size(); i++){
            if (candidates.get(i).getOffice().equals(office)){
                index=i;
                flag = true;
                break;
            }
        }
        if (!flag||candidates.size()==0){
            throw new NoSuchElementException("no Candidate is found.");
        }
        return candidates.get(index);
    }
    public void addCandidate (Candidate c) throws IllegalArgumentException{
        boolean flag = true;
        for (int i=0; i<candidates.size(); i++){
            if (candidates.get(i).getOffice().equals(c.getOffice())){
                flag = false;
                break;
            }
        }
        if (!flag){
            throw new IllegalArgumentException(" the provided Candidate is running for the same " +
                    "office as another member of the Party.");
        }
        candidates.add(c);
    }
}
