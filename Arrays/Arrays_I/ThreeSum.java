// Pronlem link: https://leetcode.com/problems/3sum/description/

// Problem:
// Given an integer array nums, 
// return all the triplets [nums[i], nums[j], nums[k]] such that 
// i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

// Approach:
// We need to find 3 integers in the array that sum up to k or 0 in this case.
// This also tells us that atleast one of the nos needs to be negative and one of the nos need to be positive.
// Additionally we need to avoid all the duplicate pairs.

// Let's sort the array
// Then simply iterate over the array using a pointer over the integers 'i'.
// Since we start 'i' from 0 we know the first few nos need to be negative in order for the condition to satisfy.
// (Since the array is sorted)
// Hence we create a nested loop and iterate from i + 1 to n using to pointers 'j' & 'k' pointing
// at the i + 1 & ' n - 1' th element respectively.
// Now if nums[i] + nums[j] + nums[k] == 0, we can increment j & decrement k respectively to search for additional pairs
// if > 0 then decrement k and < 0 then increment j.
// to avoid storing the same sets we can check for all of i, j, k that they are not equal to the previous element,
// because that case would have already been covered earlier.
// The edge case is added to s.t, the above check would not take place if i == 0, j = i + 1 & k = n - 1.
package Arrays;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                int j = i + 1, k = nums.length - 1;
                while (j < k) {
                    if (i != j - 1 && nums[j] == nums[j - 1]) {
                        j++;
                        continue;
                    }
                    if (k != nums.length - 1 && nums[k] == nums[k + 1]) {
                        k--;
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        res.add(temp);
                        j++;
                        k--;
                    } else if (nums[i] + nums[j] + nums[k] > 0) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }
        }
        return res;
    }
}

// Time Complexity: O(N^2)
// Space Complexity: O(N)