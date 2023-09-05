// KMP Algorithm

// Knuth-Morris-Pratt String Matching Algorithm
// This is algorithm is used to check if the pattern is occuring in a string. The normal algorithm
// of 2 pointers used a time complexity of O(m*n). This algorithm is much more efficient and uses a time
// complexity of O(m+n).

// Where does the algorithm actually optimize?
// lets say we have a string s = "aaaab"
// and a pattern "aab", and we need to check if the pattern is present in a string.
// in the normal way of using 2 pointers when we would reach the 2nd index (o based indexing) 
// of the pattern and notice a mismatch with the 2nd index of s, we would move the pointer in
// the pattern, back to the 0th index of the string, and starth the comparison again from the 2nd index of s
// with the 0th index of the pattern.
// The optimization here is that instead of going back to the 0th index we go back to the 1st index
// in the pattern because the first and second index of the strings were a "repetition" and same as the respective strings in the string s.
// (This may seem overwhelming, take a pen and paper and try)

// Algorithm:
// First we create a lps array (longest prefix suffix) array, that notes the indices of repetition,
// for a possible trace back during a mismatch


// Reference Video: https://youtu.be/V5-7GzOfADQ?feature=shared

class HelloWorld {
    public static void main(String[] args) {
        String s = "ababcabcabababd";
        String pattern = "ababd";
        
        int[] lps = new int[pattern.length()];
        int i = 0, j = 1, x = 0;
        while(j < pattern.length()){
            if(pattern.charAt(i) == pattern.charAt(j)){
                i++;
                x++;
                lps[j] = x;
            }
            else{
                x = 0;
                i = 0;
            }
            j++;
        }
        
        i = 0;
        j = 0;
        while(i < s.length() && j < pattern.length()){
            if(s.charAt(i) == pattern.charAt(j)){
                j++;
                i++;
                continue;
            }
            else {
                if(j != 0){
                    j = lps[j - 1];
                    continue;
                } 
                else i++;
            }
        }
        if(j == pattern.length()) System.out.println("Pattern Match found");
        else System.out.println("Pattern Match not found");
    }
}

// Time Complexity: O(M + N)
// M = length of pattern
// O(M) = time to create lps array of pattern
// N = length of string
// O(N) = time to check if there is a substring that matches the pattern
