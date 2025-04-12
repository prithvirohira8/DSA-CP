// Problem Link:  https://leetcode.com/problems/subarray-sum-equals-k/submissions/1557583619/

// Approach:
// This is a fantastic question. It is definitely tricky because it takes time to understand the many edge cases.
// While 'prefixSumCount.containsKey(sum - k)' seems to be a very straitforward step,
// It is important to note that the frequency of occurence of 'sum - k' in the past will increment the ans those many times.
// Take the example test cases of : [0, 3], [0,0,3], [0,0,0,0,3]

// The line prefixSumCount.put(0, 1); is because if sum == k, there has to be single increment

import java.util.*;

class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        int sum = 0, ans = 0;
        prefixSumCount.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (prefixSumCount.containsKey(sum - k))
                ans += prefixSumCount.get(sum - k);
            if (prefixSumCount.containsKey(sum))
                prefixSumCount.put(sum, prefixSumCount.get(sum) + 1);
            else
                prefixSumCount.put(sum, 1);
        }
        return ans;
    }
}

// Time Complexity: O(N)
// Space Complexity: O(N)