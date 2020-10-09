//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Simple Benchmarking
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * A class to output the different running time of different methods.
 * @author Xianfu Luo
 * @version 1.0
 */
public class Benchmarker {

    public static void main ( String[] arg ) {
        long[] queryNs={100000, 1000000, 10000000, 100000000, 1000000000};
        File f = new File("/Volumes/LaCie/p03/output");
        createResultsFile(f,queryNs);
    }
    /**
     * Runs both methods from ComparisonMethods.java on the same n
     * Tracks the time spent in milliseconds to complete each method
     * @param n the integer n
     * @return a formatted string with n and the elapsed times
     * @throws NoSuchElementException if the return values of the two comparison methods are ​different
     */
    public  static  String compare ( long n ) throws  NoSuchElementException{
        long bruteForceBeginTime = System.currentTimeMillis(); // Time before bruteForce
        long bruteForceResult = ComparisonMethods.bruteForce(n); // Get the result of bruteForce
        long bruteForceEndTime = System.currentTimeMillis(); // Time after bruteForce
        long bruteForceTime = bruteForceEndTime - bruteForceBeginTime; // Calculate the time spent
        long formulaBeginTime = System.currentTimeMillis(); // Time before formula
        long formulaResult = ComparisonMethods.constantTime(n); // Get the result of formula
        long formulaEndTime = System.currentTimeMillis(); // Time after formula
        long formulaTime = formulaEndTime - formulaBeginTime; // Calculate the time spent
        if(bruteForceResult!=formulaResult){
            // throws NoSuchElementException as the return values of the two comparison methods are ​different
            throw new NoSuchElementException("the return values of the two comparison methods are different");
        }

        return n + "\t" + bruteForceTime + "\t" + formulaTime + "\n";
    }
    /**
     * Calls compare(n) using each of an array of values
     * Writes the results to a file specified by the parameter
     * @param f the file created for the result
     * @throws Exception raised by the methods it uses
     */
    public static void createResultsFile(File f, long[] queryNs) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(f, true);
            // throws IOException if failed to open file

            for (int i = 0; i < queryNs.length; i++) {
                if (queryNs[i]<=0){
                    throw new NoSuchElementException("n is negative, which is incorrect");
                    // throws NoSuchElementExceptionn if queryNs[i] is negative
                }
                writer.write(compare(queryNs[i]));
            }
        } catch (IOException e) {
            System.out.println("Caught IOException: "+e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Caught NoSuchElementExceptionn: "+e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    // close the file
                } catch (IOException e) {
                    // This is unrecoverable. Just report it and move on
                    System.out.println("Caught IOException: "+e.getMessage());
                }
            }
        }
    }
}
