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

/**
 * A class include two different to measure running time.
 * @author Xianfu Luo
 * @version 1.0
 */

public class ComparisonMethods {
    /**
     * Calculates and returns the sum of all integers 1 to n
     * Uses a loop and running total to calculate
     * @param n the integer n
     * @return the sum of all integers 1 to n
     */
    public static long bruteForce(long n){ // f(N)= 1+1+1+N(1+2) =3+3N = O(N)
        //  calculate result of each loop
        long sum=0; // 1 operation
        // 1 operation (only executed once)
        for (int i=1;i<=n;i++){ // 2 operations
            // N iteration
            sum+=i; // 1 operation
        }
        return sum;
    }
    /**
     * Calculates and returns the sum of all integers 1 to n
     * Uses a formula to calculate
     * @param n the integer n
     * @return the sum of all integers 1 to n
     */
    public static long constantTime(long n){ // f(N) = O(1)
        // using formula to calculate the sum of all integers 1 to n
        // if n <=0, return 0
        return n>0? n*(n+1)/2:0; // 2 operations
    }
}
