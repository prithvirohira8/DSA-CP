// Longest Pallindromic Substring

// Question Link: https://leetcode.com/problems/longest-palindromic-substring/description/

// Approach: In this question, one thing was obvious that is, the 2 pointer approach needs to be used.
// Simplest Brute Force Approach would be to generate all strings in nested for loops and check if each string was a
// pallindrome. However that would give a time complexity of O(N^3). O(N^2) would be the time complexity to generate
// all substrings and O(N) would be to check if the string is a pallindrome or no.
// Another approach would be to place the one pointer at the start and one pointer at the end of the string.
// Check if the string was a pallindrome, if not then increase, the first pointer, decrease the second pointer or increase and decrease both.
// This would work recursively and in a way this too would generate all strings taking up a time complexity of O(N^2) and an 
// time complexity of O(N) to check if the string is a pallindrome or no leading to the overall time complexity of
// O(N^3).
// Now we know that we have to use 2 pointers and there cannot be a way to generate the required strings in a time complexity of 
// less than O(N^2). However we can use a technique to make sure that we do not use O(N) time to check if the string is a pallindrome
// and greedily set conditions in the inner loop which makes its iterations only if a pallindrome is possible.
// While we iterate over the n characters of the string in the first loop. For ever iteration we use 2 pointers,
// which will travel from the ith index.
// The boundry conditions for this traversal would be that the pointers cannot go beyond the indices of the string and that
// at every iteration in the inner loop, it will be checked if the characters at the indices of both the pointers are equal.
// If they are not equal, the inner loop breaks.
// However it is important to note that the traversal of the inner loop would be different for checking odd length pallindromes and even length pallindromes.
// This is because in the case of odd length pallindromes the pointers will begin from the same index and for even length pallindromes,
// they will begin from adjacent indices. 

// Note: Rather than taking s.substring() in every step of the inner loop, the indices are stored incase the pallindrome is longer
// than maxLen and s.substring() is done in the end because s.substring() too takes O(N) time to extract the substring from a string

class Solution {

    public String longestPalindrome(String s) {
        if(s.length() == 0) return "";
        
        int res_l = 0, res_r = 1, maxLen = 1, l = 0, r = 0;
        // checking for odd length pallindromes
        for(int i = 0; i < s.length(); i++){
            l = i;
            r = i;
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                if(r - l + 1 > maxLen){
                    res_l = l;
                    res_r = r + 1;
                    maxLen = r - l + 1;
                }
                l--;
                r++;
            }

            l = i - 1;
            r = i;
            // checking for even length pallindromes
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                if(r - l + 1 > maxLen){
                    res_l = l;
                    res_r = r + 1;
                    maxLen = r - l + 1;
                }
                l--;
                r++;                
            }
        }
        return s.substring(res_l, res_r);
    }
}

// Time Complexity: O(N^2)