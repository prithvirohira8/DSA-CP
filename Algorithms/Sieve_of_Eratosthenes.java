// Sieve of Eratosthenes is used to find the prime nos from 1 to N 
// in the lowest time complexity possible
// The algorithm defines a boolean array of size N + 1. The elements in
// the boolean array are first initialized to be all true. The boolean
// value true signifies that the index is a prime no.
// Then in a for loop from 2 to sqrt of N iterates, and in every iteration
// if the index of the boolean value holds true, there is an inner for loop which runs from the squared index value of the above iteration to the square root of N, also
// the loop iterates i times (j++ does not happe, j += i because we set the multiples as false)
// and sets all the multiples of the indexes of the boolean array false, for every index in the inner for loop
// The boolean array has now only few of its elements as true values. The indexes
// of those elements are prime nos.

import java.util.*;
import java.lang.*;

public class Sieve_of_Eratosthenes{
    static List<Integer> solve(int n){
        List<Integer> lst = new ArrayList<>();
        boolean[] primeNos = new boolean[n+1];
        Arrays.fill(primeNos, true);

        primeNos[0] = false;
        primeNos[1] = false;
        for(int i = 2; i * i <= n; i++){
            if(!primeNos[i]) continue;
            for(int j = i * i; j <= n; j += i){
                primeNos[j] = false;
            }
        }

        for(int i = 2; i < primeNos.length; i++){
            if(primeNos[i]) lst.add(i);
        }
        return lst;
    }
    public static void main(String h[]){
        int N = 1000;
        List<Integer> primeNos = solve(N);
        System.out.println(primeNos);
    }
}

// Time Complexity:  O(N log(log(N)))
// Space Complexity: O(N)

// Reference: https://www.geeksforgeeks.org/program-to-print-first-n-prime-numbers/