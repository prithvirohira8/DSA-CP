// https://leetcode.com/problems/continuous-subarray-sum/description/

import java.util.*;

class ContinuousSubArraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixRem = new HashMap<Integer, Integer>();
        int sum = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % k == 0) {
                if (count == 0)
                    count++;
                else if (count == 1)
                    return true;
            } else
                count = 0;
            sum += nums[i];
            int rem = sum % k;
            if (i != 0 && rem == 0)
                return true;
            if (prefixRem.containsKey(rem) && i - prefixRem.get(rem) >= 2)
                return true;
            prefixRem.put(rem, i);
        }
        return false;
    }
}