
/*********************************************************

 Following is the TreeNode structure:

 class TreeNode {
     int data;
     TreeNode left;
     TreeNode right;
     TreeNode() {
         this.data = 0;
         this.left = null;
         this.right = null;
     }
     TreeNode(int data) {
         this.data = data;
         this.left = null;
         this.right = null;
     }
     TreeNode(int data, TreeNode left, TreeNode right) {
         this.data = data;
         this.left = left;
         this.right = right;
     }
 };
 ********************************************************/

import java.util.ArrayList;
import java.util.List;

public class AllTraversals {
    static List<Integer> preOrder;
    static List<Integer> inOrder;
    static List<Integer> postOrder;

    static void preOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        preOrder.add(node.data);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    static void inOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        inOrderTraversal(node.left);
        inOrder.add(node.data);
        inOrderTraversal(node.right);
    }

    static void postOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        postOrder.add(node.data);
    }

    public static List<List<Integer>> getTreeTraversal(TreeNode root) {
        preOrder = new ArrayList<Integer>();
        inOrder = new ArrayList<Integer>();
        postOrder = new ArrayList<>();
        preOrderTraversal(root);
        inOrderTraversal(root);
        postOrderTraversal(root);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(inOrder);
        ans.add(preOrder);
        ans.add(postOrder);
        return ans;
    }
}