// Question Link: https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/

// Approach:
// We need to maximize the value of (nums[i] - nums[j]) * nums[k],
// such that i < j < k

// Also to maximize (nums[i] - nums[j]) * nums[k]
// i should be maximized, j should be minimized and k should be maximized too
// So if we have the max value possible of i and k stored in an array, We can just loop the array
// once for evey j and check the max possible value of i in the left (stored in an array)
// and max possible value of k towards the left of j (Already stored in an array)

// Code:

class Solution {
    public long maximumTripletValue(int[] nums) {
        int[] max_l = new int[nums.length];
        int[] max_r = new int[nums.length];
        int high = 0;
        long ans = 0l;
        
        // Computing values of max_l
        for(int i = 0; i < max_l.length - 1; i++){
            max_l[i] = high;
            if(nums[i] > high) high = nums[i];
        }
        
        // Computing values of max_r
        high = 0;
        for(int i = nums.length - 1; i >= 1; i--){
            max_r[i] = high;
            if(nums[i] > high) high = nums[i];
        }

        // Computing ans
        for(int i = 1; i < nums.length - 1; i++){
            ans = Math.max(ans, Long.valueOf(max_l[i] - nums[i]) * Long.valueOf(max_r[i]));
        }
        
        return ans;
    }
}

// Time Complexity: O(N)