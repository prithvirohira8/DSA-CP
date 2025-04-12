# Longest Common Subsequence

# Approach: This is one of the most popular Dynamic Programming Questions
# For finding the longest common subsequence we need to do character comparison in the string.
# When we do character comparison in strings there can be 2 possibilities, 
# if the character's are equal, and the other being that the characters are not equal. 
# If the character's are equal then we move on to compare the
# next characters, i.e the characters ahead them, however if the character's were not equal we move on 
# to generate to further possibilities for comparison, where we compare the next character of the string A to the same
# character of string B or vice verca. This entire evaluation showcases that the problem can be solved recursively
# exploring paths, where pointers are used on the strings for comparison

# Optimization Where?
# If we see we can reach the same state via a different path, i.e. the pointers i, j can reach values 7 and 12 (for example)
# via different paths, however the no of common indices from index 7 and 12 to the final indexes of the string will always be the same.
# Hence this value can be stored and not computed again.
# If we see we first explore a path till the end (last indices of string reached for both pointers) and then store the value,
# since we then obtain the no of common indices in that path. So this approach is a bottom up approach, 
# and the size of the dp 2d array will be 2 times the sizes of the strings

class Solution(object):
    def solve(self, dp, text1, text2, i, j):
        if i == len(text1) or j == len(text2):
            dp[i][j] = 0
            return 0
        
        if dp[i][j] != -1:
            return dp[i][j]
        
        if(text1[i] == text2[j]):
            dp[i][j] = 1 + self.solve(dp, text1, text2, i + 1, j + 1)
            return dp[i][j]
        
        dp[i][j] = max(self.solve(dp, text1, text2, i, j + 1), self.solve(dp, text1, text2, i + 1, j))
        return dp[i][j]
        

    def longestCommonSubsequence(self, text1, text2):
        dp = [[-1 for j in range(len(text2) + 1)] for i in range(len(text1) + 1)]
        return self.solve(dp, text1, text2, 0, 0)
        
# Time Complexity: O(n*m), where n and m are the length of the 2 strings
# n = length of sting 1
# m = length of string 2