// Problem Link: https://leetcode.com/problems/validate-binary-search-tree/

// Approach:
// To valid a tree is a BST we need to take care of the following 2 things:
// 1.) the left child's val is less than the parent node val and the right child's val
// is greater than the parent node's val
// 2.) All the values of the grand children on the left of the parent should be less than 
// the parent's val and all on the right should have a value greater than the parent's val

// Hence each node will have a val that lies in a range.
// The root will lie in a range from -inf to +inf
// The left child will lie in the range -inf to parent val and
// the right child will lie in the range parent to +inf.
// If each node lies in the range appropriately then the tree is a valid BST
// else it isn't

// We can traverse the tree usng DFS and updating the ranges for the nodes appropriately

// class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode() {
//     }

//     TreeNode(int val) {
//         this.val = val;
//     }

//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

class Solution51 {
    static boolean solve(TreeNode root, long l, long h) {
        boolean flag = true;
        if (root.val <= l || root.val >= h)
            return false;
        if (root.left != null)
            flag = solve(root.left, l, root.val);
        if (!flag)
            return false;
        if (root.right != null)
            flag = solve(root.right, root.val, h);
        return flag;
    }

    public boolean isValidBST(TreeNode root) {
        if (root.left == null && root.right == null)
            return true;
        return solve(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}

// Time Complexity: O(N), N = total no of nodes in the tree
// Space Complexity: O(N)
// Space due to the recursive stack