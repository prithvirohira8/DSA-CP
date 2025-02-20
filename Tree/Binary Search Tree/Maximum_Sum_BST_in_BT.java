// Problem Link: https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/

// Approach:
// This problem requires clear understanding of the concepts of a binary tree and a BST.
// We need the max_sum of any BST in the tree.
// We had preveiously learned how to check if a tree is a BST or no, by applying the ranges.
// Similar concept needs to be used here.
// All leaf nodes can be treated as BST's (the base case covers that)
// The base case also covers he case if a particular node of the BSt has only one child
// Note: if a node has 2 children, we cannot consider the node and its other part to be part of a bst and exclude the other child
// We check if the largest value from the left portion is smaller than the node val and the smallest val from the right portion is larger than the node val
// if not, null is returned since it ain't a BST.

class Solution {
    static int ans = 0;

    static Triplet solve(TreeNode node) {
        // base case
        if (node == null) {
            return new Triplet(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        Triplet t1 = solve(node.left);
        Triplet t2 = solve(node.right);

        // If either left or right subtree is not a BST, then the current tree is not a
        // BST
        if (t1 == null || t2 == null) {
            return null;
        }

        // Check if the current node's value is within the valid range for a BST, if mot
        // a BST return null
        if (node.val <= t1.largest_node_val || node.val >= t2.smallest_node_val) {
            return null;
        }

        // Calculate the sum of the current BST
        int sum = node.val + t1.sum + t2.sum;
        ans = Math.max(ans, sum);

        // Return the new Triplet for the current BST
        return new Triplet(sum, Math.max(node.val, t2.largest_node_val), Math.min(node.val, t1.smallest_node_val));
    }
    // Math.max and min are used to handle the edge cases of child nodes, to set the
    // large and min values to that of the child node

    public int maxSumBST(TreeNode root) {
        ans = 0; // Reset the answer before starting
        solve(root);
        return ans;
    }
}

class Triplet {
    int sum;
    int largest_node_val;
    int smallest_node_val;

    Triplet(int sum, int largest_node_val, int smallest_node_val) {
        this.sum = sum;
        this.largest_node_val = largest_node_val;
        this.smallest_node_val = smallest_node_val;
    }
}