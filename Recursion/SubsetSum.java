// Question Link: https://www.geeksforgeeks.org/problems/subset-sums2234/1

// Approach: take it or leave it

// #User function Template for python3
// class Solution:
//     def __init__(self):
//         self.res = []

//     def generate_subset_sum(self, arr, ind, sum):
//         self.res.append(sum)

//         for i in range(ind, len(arr)):
//             sum += arr[i]
//             self.generate_subset_sum(arr, i + 1, sum)
//             sum -= arr[i]

//     def subsetSums(self, arr):
//         self.res = []
//         self.generate_subset_sum(arr, 0, 0)
//         return self.res

package Recursion;

import java.util.*;

class subsetSum {
    ArrayList<Integer> res;

    void solve(int[] arr, int sum, int ind) {
        if (ind == arr.length) {
            return;
        }
        // not considering ind
        solve(arr, sum, ind + 1);
        // considering ind
        sum += arr[ind];
        res.add(sum);
        solve(arr, sum, ind + 1);
    }

    public ArrayList<Integer> subsetSums(int[] arr) {
        res = new ArrayList<>();
        res.add(0);
        solve(arr, 0, 0);
        return res;
    }
}

// Time Complexity: O(2^N)
// There are 2 possiblitiles for every element to consider it or not to consider
// it

// Auxiliary Space Complexity: O(N)
// Max depth of recursion tree would be N

// Space Complelxity: O(N)