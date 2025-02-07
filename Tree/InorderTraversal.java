
// Inorder Traversal typically prints the nodes from the leftmost part of the tree
// Straitforward Approach similar to DFS, however important to note that while adding
// a node to the list the left part of the node should be null and the node should not be null

// Order: Left most child Node - Parent of that child - Right most child Node
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
class InorderTraversal {
    static void solve(TreeNode root, List<Integer> lst) {
        if (root == null)
            return;
        if (root.left != null)
            solve(root.left, lst);
        lst.add(root.val);
        if (root.right != null)
            solve(root.right, lst);

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        solve(root, lst);
        return lst;
    }
}

// Time Complexity: O(N), N = no of nodes in the binary tree