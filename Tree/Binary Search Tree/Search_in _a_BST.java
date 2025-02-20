// Problem Link: https://leetcode.com/problems/search-in-a-binary-search-tree/

// Approach: traverse using the properties of a BST.

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

class Solution2 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root.val == val)
            return root;
        while (root != null) {
            if (val == root.val)
                return root;
            if (root.left == null && root.right == null)
                return null;
            if (val > root.val)
                root = root.right;
            else if (val < root.val)
                root = root.left;
        }
        return null;
    }
}

// Time Complexity: O(log N), N = No of nodes.
// Search space is being divided into a half in each iteration.

// Space Complexity: O(1)
// No additional space used.