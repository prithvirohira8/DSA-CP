// Problem Link: https://leetcode.com/problems/subsets-ii/

// Approach:
// Take it or Leave it approach followed smartly with for loop, read Approach.txt to understand more.

// Python Code:
// class Solution(object):
//     def __init__(self):
//         self.res = []

//     def generate_subset(self, nums, ind, lst):
//         # base case
//         self.res.append(list(lst))

//         for i in range(ind, len(nums)):
//             if i != ind and nums[i] == nums[i - 1]:
//                 continue
//             lst.append(nums[i])    
//             self.generate_subset(nums, i + 1, lst)
//             lst.pop()

//     def subsetsWithDup(self, nums):
//         self.res = []
//         lst = []
//         nums = sorted(nums)
//         self.generate_subset(nums, 0, lst)
//         return self.res

package Recursion;

import java.util.*;

class Subset2 {
    List<List<Integer>> res;

    static void solve(int[] nums, int ind, List<List<Integer>> res, List<Integer> lst) {
        res.add(new ArrayList<>(lst));
        for (int i = ind; i < nums.length; i++) {
            if (i != ind && nums[i] == nums[i - 1])
                continue;
            lst.add(nums[i]);
            solve(nums, i + 1, res, lst);
            lst.remove(lst.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        res = new ArrayList<>();
        List<Integer> lst = new ArrayList<>();
        solve(nums, 0, res, lst);
        return res;
    }
}

// Time Complexity: O(2^N)
// There are 2 possiblitiles for every element to consider it or not to consider
// it

// Auxiliary Space Complexity: O(N)
// Max depth of recursion tree would be N

// Space Complelxity: O(N)