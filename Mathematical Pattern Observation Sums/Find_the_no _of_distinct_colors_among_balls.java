// Problem Link: https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls/

// Problem: You are given an integer limit and a 2D array queries of size n x 2.

// There are limit + 1 balls with distinct labels in the range [0, limit]. 
// Initially, all balls are uncolored. 
// For every query in queries that is of the form [x, y], 
// you mark ball x with the color y. 
// After each query, you need to find the number of distinct colors among the balls.

// Return an array result of length n, where result[i] denotes the number of distinct colors after ith query.

// Note that when answering a query, lack of a color will not be considered as a color.

// Approach:
// This is a tricky sum.

// The test cases very well explain the different possibilities.
// What we need: We need the 'total distinct' colors at every query.
// If we just maintain a HashSet, that's not going to work! Why?
// Because if the first 3 balls are colored with the value '4' and the next query colors the ball 1
// with a value '2', while updating the HashSet the value 4 will be removed and 2 will be inserted.
// Wheras the value 4 should still exist in the HashSet because 2 other balls are colored 4.
// This tells us that the freq of colors is also important.
// If the freq becomes 0, then that color can be removed.
// What if we just maintain the freq of colors? That should work?
// 'Ideally' it should but mainting the freq typically means updating the value of the new color 
// that the ball takes by 1 and reducing the prev color by 1. However we would need an additional 
// HashMap maintainig the ball color to get the prev color and update it's frequence (reduce by 1).
// Additionally if the same ball is given the same color, the color's frequence should not increase.
// This approach works as after every query we can return the size of the color_freq HashMap.
// If a particular color's freq reaches 0 it needs to be removed.

import java.util.*;

class Solution2 {
    public int[] queryResults(int limit, int[][] q) {
        int[] ans = new int[q.length];
        HashMap<Integer, Integer> ball_color = new HashMap<>();
        HashMap<Integer, Integer> color_freq = new HashMap<>();

        for (int i = 0; i < q.length; i++) {
            int ball = q[i][0], color = q[i][1];

            if (!ball_color.containsKey(ball)) {
                ball_color.put(ball, color);
                if (!color_freq.containsKey(color))
                    color_freq.put(color, 1);
                else
                    color_freq.put(color, color_freq.get(color) + 1);
            } else {
                // if colored with same color again do not do anything
                if (ball_color.get(ball) != color) {
                    // reduce freq of prev color and inc freq of new color
                    int prev_color = ball_color.get(ball);
                    color_freq.put(prev_color, color_freq.get(prev_color) - 1);
                    if (color_freq.get(prev_color) == 0)
                        color_freq.remove(prev_color);
                    if (!color_freq.containsKey(color))
                        color_freq.put(color, 1);
                    else
                        color_freq.put(color, color_freq.get(color) + 1);
                    // Update the ball color
                    ball_color.put(ball, color);
                }
            }
            ans[i] = color_freq.size();
        }
        return ans;
    }
}

// Time Complexity Analysis: O(N)
// We are looping over the rows of the queries array.
// Cost of inserting an element, deleting an element, updating an element in a
// HashMap: O(1)

// Space Complexity: O(N)
// Maintaing 2 HashMaps