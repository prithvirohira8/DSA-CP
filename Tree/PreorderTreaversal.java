
// Preorder traversal is typically traversing the tree using DFS.
// Hence implementation is similar to DFS with the null boundary to  be taken care of.
// Every node that is encountered is immidietly added in the list during the DFS traversal.

// Order: Parent - Left Child Node - Right Child Node
import java.util.*;

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
class Solution {
    static void solve(TreeNode root, List<Integer> lst) {
        if (root != null)
            lst.add(root.val);
        else
            return;
        if (root.left != null)
            solve(root.left, lst);
        if (root.right != null)
            solve(root.right, lst);
        return;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        solve(root, lst);
        return lst;
    }
}

// Time Complexity: O(N), where N = no of nodes