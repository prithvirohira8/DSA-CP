// Problem Link: https://leetcode.com/problems/search-in-rotated-sorted-array/submissions/1341269027/

// Approach: 
// Again this problem requires strong observation skills with the input examples.
// We can simply use binary search to find the element if we know, in which sorted half is the element present
// So if we observe if nums[mid] > nums[l], it means the left half is definately sorted, else the right half is sorted
// We then check in which half the target lies and continue binary search normally.

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[l] == target)
                return l;
            if (nums[r] == target)
                return r;
            if (nums[mid] > nums[l]) {
                if (target > nums[l] && target < nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else {
                if (target > nums[mid] && target < nums[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }
        return -1;
    }
}

// Time Complexity: O(log(n))
// Because of Binary Search

// Space Complexity: O(1)
// No additional data structure is required