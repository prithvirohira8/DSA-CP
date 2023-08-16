// Longest Common Prefix

// Problem Link: https://leetcode.com/problems/longest-common-prefix/description/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String s = "";

        // n = length of shortest string
        int n = Integer.MAX_VALUE;
        for(int i = 0; i < strs.length; i++){
            if(strs[i].length() < n) n = strs[i].length();
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < strs.length - 1; j++){
                if(strs[j].charAt(i) != strs[j+1].charAt(i)) return s;
            }
            s += strs[0].charAt(i);
        }
        return s;
    }
}

// Time Complexity: O(N*M)
// N = length of shortest string, M = total no of strings 