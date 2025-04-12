// Problem Link: https://leetcode.com/problems/4sum/

// Approach: Same as 3Sum, but instead of using 
package Arrays;

import java.util.*;

class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = j + 1, l = nums.length - 1;
                while (k < l) {
                    if (k != j + 1 && nums[k] == nums[k - 1]) {
                        k++;
                        continue;
                    }
                    if (l != nums.length - 1 && nums[l] == nums[l + 1]) {
                        l--;
                        continue;
                    }
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[k] + (long) nums[l];
                    // equality case:
                    if (sum == (long) target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(nums[l]);
                        res.add(temp);
                        k++;
                        l--;
                    } else if (sum > (long) target) {
                        l--;
                    } else {
                        k++;
                    }
                }
            }
        }
        return res;
    }
}

// Time Complexity: O(N^3)
// Space Complexity: O(N)