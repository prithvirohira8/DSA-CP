// Travelling Salesman Problem

// Given a list of cities and the distance between each pair of cities,
// what is the shortes possible route that visits each city and returns to the origina city

import java.util.*;
import java.lang.*;

class Problem2 {
    // start from city 0 and come back to city 0
    static int startingCity = 0;
    
    static int solve(int[][] city, int[][] dp, int mask, int cityIndex){
        if(mask == (1 << (city.length+1)) - 1) return city[startingCity][cityIndex];
        
        if(dp[cityIndex][mask] != -1) return dp[cityIndex][mask];
        
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < city.length; i++){
            if((mask & (1 << i)) != 0) continue;
            
            // setting ith index of mask as 1
            mask = mask | (1 << i);
            
            minDist = Math.min(minDist, city[cityIndex][i] + solve(city, dp, mask, i));
            
            // clearing the ith index of mask
            mask &= ~(1 << i);
        }
        dp[cityIndex][mask] = minDist;
        return dp[cityIndex][mask];
    }
    public static void main(String[] args) {
        int[][] city = {
            {0, 20, 30, 40},
            {20, 0, 45, 55},
            {30, 45, 0, 35},
            {40, 55, 35, 0}
        };
        int[][] dp = new int[city.length][1 << city.length + 1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        
        
        int mask = 1 << city.length;
        mask = mask | (1 << startingCity);
        
        int shortestDistance = solve(city, dp, mask, 0);
        System.out.println("Shortest Distance from city 0 back to city 0 = "+shortestDistance);
    }
}

// Space Complexitt:- O(N*2^N)
// Reason:- cityIndex goes N times and 2^N mask size in the dp matrix

// Time Complexity:- O(N^2 * 2^N)
// N^2 because of the extra transition time for trying out all the cities not selected in the for loop
// Reference Video:- https://youtu.be/QukpHtZMAtM