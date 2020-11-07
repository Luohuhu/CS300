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
/**
 * A class includes information of Candidate.
 * @author Xianfu Luo
 * @version 1.0
 */
public class Candidate {
    //private ​instance variables
    private String name; // The Candidate’s name
    private String office; // The Candidate’s office

    // Initialize 3 different office title contain
    protected static final String[] OFFICE = {"President", "Vice President", "Secretary"};

    public  Candidate (String name, String office) throws IllegalArgumentException{
        boolean flag = false;
        for (int i=0; i<OFFICE.length; i++){
            if(OFFICE[i].equals(office)){
                flag = true;
                break;
            }
        }
        if (flag){
            this.name=name;
            this.office=office;
        }else {
            throw new IllegalArgumentException("Not match office");
        }

    }
    // Accessor method
    public String getName(){
        return name;
    }
    public String getOffice(){
        return office;
    }
    public String toString(){
        return name+" ("+office+")";
    }
}
