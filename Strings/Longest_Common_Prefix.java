// Longest Common Prefix

// Problem Link: https://leetcode.com/problems/longest-common-prefix/description/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String s = "";

        Arrays.sort(strs);
        for(int i = 0; i < strs[0].length(); i++){
            if(strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) s+= strs[0].charAt(i);
            else break;
        }
        return s;
    }
}

// Time Complexity: O(N*log(M))
// N = length of shortest string, M = total no of strings 