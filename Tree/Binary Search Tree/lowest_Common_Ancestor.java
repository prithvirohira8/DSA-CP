// Problem Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

// Approach:
// In the case of a Binary Search Tree (assuming p.val < q.val):
// if there is any node that is greater than or equal to p.val and 
// less than or equal to q.val
// then that node is the LCA.
// That's it using the properties of a binary search tree we traverse through the nodes
// to find a node that satisfies that condition.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class Solutio99 {
    static TreeNode solve(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val >= p.val && root.val <= q.val)
            return root;
        else if (root.val > p.val && root.val > q.val)
            return solve(root.left, p, q);
        else
            return solve(root.right, p, q);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val)
            return lowestCommonAncestor(root, q, p);
        return solve(root, p, q);
    }
}

// Time Complexity: O(log N)
// Search Space is being divided in half

// Space Complexity : O(log N)
// Recursive stack generated