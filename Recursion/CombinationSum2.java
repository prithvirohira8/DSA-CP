// Problem Link: https://leetcode.com/problems/combination-sum-ii/

package Recursion;

import java.util.*;

class CombinatonSum2 {
    List<List<Integer>> res;

    void generateCombinations(int[] nums, int ind, int target, List<Integer> lst) {
        // base cases
        if (target == 0) {
            res.add(new ArrayList<>(lst));
            return;
        } else if (target < 0)
            return;

        for (int i = ind; i < nums.length; i++) {
            if (i != ind && nums[i] == nums[i - 1])
                continue;
            if (nums[i] > target)
                break;
            lst.add(nums[i]);
            generateCombinations(nums, i + 1, target - nums[i], lst);
            lst.remove(lst.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        res = new ArrayList<>();
        List<Integer> lst = new ArrayList<>();
        Arrays.sort(nums);
        generateCombinations(nums, 0, target, lst);
        return res;
    }
}
