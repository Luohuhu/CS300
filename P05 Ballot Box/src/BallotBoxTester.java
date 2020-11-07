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

import java.util.NoSuchElementException;

public class BallotBoxTester {
    public static boolean testCandidate(){
        try {
            try {
                Candidate wrongTest = new Candidate("Biden","Master");
                System.out.println("Problem detected: Your Candidate constructor must "
                        + "throw an IllegalArgumentException if the office does not exist.");
                return false;
            }catch (IllegalArgumentException e){
                // catch only the expected exception to be thrown -- no problem detected
            }
            Candidate test = new Candidate("Biden","President");
            if(!test.getName().equals("Biden")){
                System.out.println("Problem detected: wrong name");
                return false;
            }
            if(!test.getOffice().equals("President")){
                System.out.println("Problem detected: wrong office");
                return false;
            }String expected = "Biden (President)";
            if (!test.toString().equals(expected)){
                System.out.println("Problem detected: wrong toString");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Candidate has thrown " +
                    "a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean testPartyConstructor(){
        try {
            Party test = new Party("Democratic");
            String expectedName = "Democratic";
            if (!test.getName().equals(expectedName)){
                System.out.println("Problem detected: Party accessor get wrong name");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Party constructor has thrown " +
                    "a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean testPartyAddCandidate(){
        Party test = new Party("Democratic");
        Candidate c = new Candidate("Biden","President");
        try {
            test.addCandidate(c);
            try {
                Candidate o = new Candidate("Obama","President");
                test.addCandidate(o);
                System.out.println("Problem detected: Your Party.addCandidate() must "
                        + "throw an IllegalArgumentException if the provided Candidate " +
                        "is running for the same office as another member of the Party");
                return false;
            }catch (IllegalArgumentException e){
                // catch only the expected exception to be thrown -- no problem detected
            }
            if (test.getSize()!=1){
                System.out.println("Problem detected: test only have one candidate");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Party constructor has thrown " +
                    "a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean testPartyGetCandidate(){
        Candidate a = new Candidate("Biden","President");
        Candidate b = new Candidate("Obama","Vice President");
        Candidate c = new Candidate("Max","Secretary");
        Party test = new Party("Democratic");
        try{
            try{
                test.getCandidate("President");
                System.out.println("Problem detected: Your Party.getCandidate() must "
                        + "throw an NoSuchElementException if no Candidate is found.");
                return false;
            }catch (NoSuchElementException e){
                // catch only the expected exception to be thrown -- no problem detected
            }
            test.addCandidate(a);test.addCandidate(b);test.addCandidate(c);
            try{
                test.getCandidate("Master");
                System.out.println("Problem detected: Your Party.getCandidate() must "
                        + "throw an NoSuchElementException if no Candidate is found.");
                return false;
            }catch (NoSuchElementException e){
                // catch only the expected exception to be thrown -- no problem detected
            }Candidate expected = new Candidate("Biden","President");
            if (!test.getCandidate("President").getName().equals(expected.getName())){
                System.out.println("Problem detected: test.getCandidate() return wrong candidate");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Party.getCandidate() has thrown " +
                    "a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean testPartyGetSize(){
        Candidate a = new Candidate("Biden","President");
        Candidate b = new Candidate("Obama","Vice President");
        Candidate c = new Candidate("Max","Secretary");
        Party test = new Party("Democratic");
        try {
            if (test.getSize()!=0){
                System.out.println("Problem detected: test.getSize() should return 0" +
                        " when no candidates");
                return false;
            }test.addCandidate(a);test.addCandidate(b);
            if (test.getSize()!=2){
                System.out.println("Problem detected: test.getSize() should return 2" +
                        " when there are 2 candidates");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Party.getSize() has thrown " +
                    "a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean testBallotEquals(){
        try{
            Ballot a = new Ballot();
            Ballot b = new Ballot();
            if (a.equals(b)){
                System.out.println("Problem detected: a is not equal to b");
                return false;
            }
            if (!a.equals(a)){
                System.out.println("Problem detected: a is equal to a");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Ballot.equals() has thrown " +
                    "a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean testBallotBoxGetWinner(){
        try{
            BallotBox box = new BallotBox();
            Ballot a= new Ballot(); // zero ballot
            if (box.getWinner("President")!=null){
                System.out.println("Problem detected: Winner suppose to be Null");
                return false;
            }
            Candidate c = new Candidate("Biden","President");
            Candidate t = new Candidate("Trump","President");
            a.vote(c);
            box.submit(a); // 1 ballot for Biden
            if (box.getWinner("Vice President")!=null){
                System.out.println("Problem detected: Winner suppose to be Null");
                return false;
            }
            if (!box.getWinner("President").getName().equals("Biden")){
                System.out.println("Problem detected: Winner suppose to be Biden");
                return false;
            }
            Ballot b= new Ballot();
            Ballot d= new Ballot();
            b.vote(t);d.vote(t); // 2 ballots for Trump
            box.submit(b);box.submit(d);
            if (!box.getWinner("President").getName().equals("Trump")){
                System.out.println("Problem detected: Winner suppose to be Trump");
                return false;
            }
            Ballot f = new Ballot();
            f.vote(c);box.submit(f);
            Ballot g = new Ballot();
            g.vote(c);box.submit(g); //total of 3 ballots for Biden
            if (!box.getWinner("President").getName().equals("Biden")){
                System.out.println("Problem detected: Winner suppose to be Biden");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: BallotBox.getWinner() has thrown " +
                    "a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void  main(String[] arg){
        System.out.println("testCandidate(): "+testCandidate());
        System.out.println("testPartyConstructor(): "+testPartyConstructor());
        System.out.println("testPartyGetCandidate(): "+testPartyGetCandidate());
        System.out.println("testPartyGetSize(): "+testPartyGetSize());
        System.out.println("testBallotEquals(): "+testBallotEquals());
        System.out.println("testBallotBoxGetWinner(): "+testBallotBoxGetWinner());
    }
}
