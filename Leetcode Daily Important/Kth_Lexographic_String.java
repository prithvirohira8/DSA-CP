// Problem Link: https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/

// Watch Video if you do not understand

class Solution {
    public String getHappyString(int n, int k) {
        // Quick check - if k is greater than total possible happy strings
        int totalPossible = 3 * (int) Math.pow(2, n - 1);
        if (k > totalPossible)
            return "";

        char[] letters = { 'a', 'b', 'c' };
        StringBuilder result = new StringBuilder();

        // Adjust k to be 0-indexed for easier computation (k / total possible = 4 if 1
        // based indexed)
        k = k - 1;

        // Choose first character (a, b, or c)
        int firstCharIndex = k / (int) Math.pow(2, n - 1);
        result.append(letters[firstCharIndex]);

        // For remaining positions, choose from 2 valid characters
        k %= (int) Math.pow(2, n - 1); // Important Step
        for (int i = 1; i < n; i++) {
            int options = (int) Math.pow(2, n - i - 1);
            int nextCharIndex = k / options;

            // Find valid characters (not equal to the last character)
            char lastChar = result.charAt(result.length() - 1);
            char nextChar;

            if (nextCharIndex == 0) {
                // Choose first valid option
                if (lastChar == 'a')
                    nextChar = 'b';
                else if (lastChar == 'b')
                    nextChar = 'a';
                else
                    nextChar = 'a';
            } else {
                // Choose second valid option
                if (lastChar == 'a')
                    nextChar = 'c';
                else if (lastChar == 'b')
                    nextChar = 'c';
                else
                    nextChar = 'b';
            }

            result.append(nextChar);
            k %= options; // Important Steps
        }

        return result.toString();
    }
}