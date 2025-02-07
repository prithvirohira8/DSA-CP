// Problem Link: https://leetcode.com/problems/tuple-with-same-product/description/?envType=daily-question&envId=2025-02-07

// Problem Statement:
// Given an array nums of distinct positive integers, 
// return the number of tuples (a, b, c, d) such that 
// a * b = c * d 
// where a, b, c, and d are elements of nums,
//  and a != b != c != d.

// Approach
// One cumbersome approach would be to generate all possible 4 number pairs.
// While that would work, it would be difficult to implement and give a high complexity of O(n^4)

// We know that for the condition to work we need a*b = c*d, which roughly means that:
// a*b = x, c*d = x. So the product x has arrived twice in the array and will generate 8 possible tuples.
// Similarly if the count of the product was 3, not 2 there would be another pair (e,f) generating the produce x.
// therefore the combinations (a,b,c,d), (e,f,c,d), (a,b,e,f) would all each generate 8 more tuples (8*3 = 24).
// so for 'n' pairs nC2 possibilities exist, & 8 * nC2 tuples.
// Thats it, we just need to keep a count of the product x with a HashMap, and if a product count is >= 2, handle it accordingly.

import java.util.*;

class Solution1 {
    public int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                if (!hm.containsKey(product))
                    hm.put(product, 1);
                else
                    hm.put(product, hm.get(product) + 1);
            }
        }
        int ans = 0;
        for (int key : hm.keySet()) {
            int value = hm.get(key);
            if (value < 2)
                continue;
            else
                ans += (8 * ((value * (value - 1)) / 2));
        }
        return ans;
    }
}

// Time Complexity: O(n^2)
// Nested Loop to create all posible pairs of 2 and calculate their product.
// Space Complexity: O(n) due to HashMap