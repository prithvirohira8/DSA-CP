// Problem Link: https://leetcode.com/problems/combination-sum/description/

// Python Code
// class Solution(object):
//     def __init__(self):
//         self.res = []

//     def generate_combinations(self, nums, lst, ind, sum, target):
//         if sum > target:
//             return
//         if sum == target:
//             self.res.append(list(lst))

//         for i in range(ind, len(nums)):
//             sum += nums[i]
//             lst.append(nums[i])

//             self.generate_combinations(nums, lst, i, sum, target)
//             sum -= nums[i]
//             lst.pop()

//     def combinationSum(self, nums, target):
//         nums = sorted(nums)
//         self.res = []
//         lst = []
//         self.generate_combinations(nums, lst, 0, 0, target)
//         return self.res

package Recursion;

import java.util.*;

class CombinationSum1 {
    List<List<Integer>> res;

    void generateCombinations(int[] nums, int ind, int target, List<Integer> temp) {
        // base cases
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = ind; i < nums.length; i++) {
            if (nums[i] > target)
                break;
            temp.add(nums[i]);
            generateCombinations(nums, i, target - nums[i], temp);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> temp = new ArrayList<>();
        generateCombinations(nums, 0, target, temp);
        return res;
    }
}