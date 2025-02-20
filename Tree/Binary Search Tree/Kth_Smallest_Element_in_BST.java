// Problem Links: https://leetcode.com/problems/kth-smallest-element-in-a-bst/

// Approach: 
// 2 easy implementations:
// 1. Use the inorder traversal, as inorder traversal of bst returns the values in a sorted order.
// Store the values in a list while traversal.
// Retreive the respective value from the list based on requirement.

// 2. Do not use a list, use a cnt variable and ans.
// instead of adding integers in the list, increment the cnt variable.
// if the cnt variable is equal to the key, the make that value the ans and return it.

// In similar sum kth largest element, we can first do a traversal to count the nodes and
// then find the n-kth smallest element that would be the same.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
import java.util.*;

class Solution {
    static void solve(TreeNode node, List<Integer> lst) {
        if (node.left != null)
            solve(node.left, lst);
        lst.add(node.val);
        if (node.right != null)
            solve(node.right, lst);
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> lst = new ArrayList<>();
        solve(root, lst);
        return lst.get(k - 1);
    }
}

// class Solution {
// static int cnt, ans;
// static int solve(TreeNode node, int k){
// if(node.left != null) solve(node.left, k);
// cnt++;
// if(cnt == k) ans = node.val;
// if(node.right != null) solve(node.right, k);
// return ans;
// }
// public int kthSmallest(TreeNode root, int k) {
// cnt = 0; ans = -1;
// return solve(root, k);
// }
// }

// Approach 1:
// Time Complexity: O(N)
// Space Complexity: O(N), Recursive stack + Additional list used

// Approach 2:
// Time Complexity: O(N)
// Space COmplexity: O(N), Recusrive stack uses O(N) i.e. auxiliary space.
// Additional list s not used.

// For space complexity to be O(1), morris traversal can be used.