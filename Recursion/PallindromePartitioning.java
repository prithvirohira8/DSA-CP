
// Approach: Don't get deceived by this sum, It's easy, 
// If a particular substring is a pallindrome, increment ind and check which of
// the substrings from ind + 1 to the length could be a pallindrome, if found follow same process as above.
// 

// Python Code
// class Solution(object):
//     def __init__(self):
//         self.res = []

//     def isPallindrome(self, s):
//         for i in range(0, len(s) // 2):
//             if s[i] != s[len(s) - i - 1]:
//                 return False
//         return True

//     def generate_pallindrome_partitions(self, s, lst, ind):
//         #  base case
//         if ind == len(s):
//             self.res.append(list(lst))
//             return

//         for i in range(ind, len(s)):
//             if self.isPallindrome(s[ind: i + 1]):
//                 lst.append(s[ind: i + 1])
//                 self.generate_pallindrome_partitions(s, lst, i + 1)
//                 lst.pop()

//     def partition(self, s):
//         lst = []
//         self.res = []
//         self.generate_pallindrome_partitions(s, lst, 0)
//         return self.res

package Recursion;

public class PallindromePartitioning {

}
