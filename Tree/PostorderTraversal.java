// Order: Left Most Child Node - Right Most Child Node - Parent of that Child Node

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

class PostorderTraversal {
    static List<Integer> lst = new ArrayList<>();

    static void solve(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null)
            solve(root.left);
        if (root.right != null)
            solve(root.right);
        lst.add(root.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        lst.clear();
        solve(root);
        return lst;
    }
}