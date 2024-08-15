// Question Link: https://leetcode.com/problems/single-element-in-a-sorted-array/

// Approach: The most important thing required to solve these type  of questions is the observation
// skills in the input examples.
// What are the observations:
// 1. We know the array is sorted and that gives the first indication to the possibility of binary search
// 2. Binary Search essentially requires seacrh space to be reduced by half after choosing the left half or right half.
// 3. If we observe that a single element can only appear if the total no of elements are odd.
// 4. Additionally if we see the elements on the left hand side of the single element will have their 2nd element on an odd index
//  and the elements to the right of the single element will have their second index on an even index.

class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums[0] != nums[1])
            return nums[0];
        if (nums[nums.length - 2] != nums[nums.length - 1])
            return nums[nums.length - 1];
        int l = 1, r = nums.length - 2;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (m % 2 == 0) {
                if (nums[m] == nums[m + 1])
                    l = m + 1;
                else if (nums[m] == nums[m - 1])
                    r = m - 1;
                else
                    return nums[m];
            } else {
                if (nums[m] == nums[m + 1])
                    r = m - 1;
                else if (nums[m] == nums[m - 1])
                    l = m + 1;
                else
                    return nums[m];
            }
        }
        return nums[l];
    }
}

// Time Complexity: O(log n)
// Time Complexity for binary Search

// Space Complexity: O(1)
// No additional data structures used, only variables used