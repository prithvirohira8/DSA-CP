// Problem Statement

// Let there be N workers and N jobs. Any worker can be assigned to perform any job, incurring some cost that may vary depending on the work-job assignment. It is required to perform all jobs by assigning exactly one worker to each job and exactly one job to each agent in such a way that the total cost of the assignment is minimized.

// Input Format
// Number of workers and job: N
// Cost matrix C with dimension N*N where C(i,j) is the cost incurred on assigning ith Person to jth Job.

// Sample Input
// 4

// [
// 9 2 7 8
// 6 4 3 7
// 5 8 1 8
// 7 6 9 4
// ]

// Sample Output
// 13

// Constraints
// N <= 20


import java.util.*;
import java.lang.*;
 

class Problem1 {
    static int solve(int[][] cost, int[][] dp, int jobIndex, int mask){
        
        if(jobIndex == cost.length){
            return 0;
        } 
        
        if(dp[jobIndex][mask] != -1) return dp[jobIndex][mask];
        
        int minCost = Integer.MAX_VALUE;
        for(int personIndex = 0; personIndex < cost.length; personIndex++){
            // if the personIndex bit is 1 then ignore
            if((mask & (1 << personIndex)) != 0) continue;
            
            // setting the personIndex bit as 1
            mask = mask | (1 << personIndex);
            // taking the min cost in dp
            minCost = Math.min(minCost, cost[personIndex][jobIndex] + solve(cost, dp, jobIndex + 1, mask));
            
            // clearing the personIndex bit
            mask = mask & (~(1 << personIndex));
        }
        dp[jobIndex][mask] = minCost;
        return dp[jobIndex][mask];
    }
    public static void main(String[] args) {
        int[][] cost = {
            {9,2,7,8},
            {6,4,3,7},
            {5,8,1,8},
            {7,6,9,4}
        };
        int[][] dp = new int[cost.length][1 << cost.length + 1];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        System.out.println("Least Cost = "+solve(cost, dp, 0, 1 << cost.length));    }
}

// Space Complexity:- O(N*2^N)
// Reason:- N being all the jobs and 2^N the mask size in the dp matrix

// Time Complexity:- O(N^2*2^N)
// Extra O(N) has been there because of the transition time of the for loop.

// Reference Video: https://youtu.be/685x-rzOIlY