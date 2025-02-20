// Problem Lnk: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

// Approach:
// To maintain balanced tree 
// root will always be median
// find the median each time of the left and the right halves of the tree and
// those would be the nodes of root.left, root,right
// Use this logic recursively

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    static TreeNode solve(int l, int h, int[] nums) {
        if (l > h)
            return null;

        int ind = (l + h) / 2;
        int node_val = nums[ind];
        TreeNode node = new TreeNode(node_val);

        node.left = solve(l, ind - 1, nums);
        node.right = solve(ind + 1, h, nums);

        return node;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return solve(0, nums.length - 1, nums);
    }
}

// Time Complexity: O(N)
// Every nodecreated once
// Space Complexity: O(N)
// For creating the BST / total no of recursive calls