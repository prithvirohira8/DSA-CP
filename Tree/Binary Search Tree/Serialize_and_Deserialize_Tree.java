// Problem Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

// Approach: 
// Apprach is easy, implementation is a little challenging to create a binary tree from string
// To searialize (create a string), we do a simple level ortder traversal (BFS).
// To deserialize (create a Tree), we need to understand that we can aproach this with a queue
// and an iterator 'itr'.
// Use pen and paper to try, The next two elements of the itr would be the children of the head of the queue.
// Assign them to left and right and poll() the element. If the children aren't null add them in the queue too.

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("# ");
            } else {
                sb.append(node.val).append(" ");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return sb.toString().trim();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty())
            return null;

        String[] arr = data.split(" ");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        q.add(root);

        // for(int i = 0; i < nodes.length; i++){
        // System.out.print(" "+nodes[i]);
        // }

        int i = 1;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (arr[i].equals("#"))
                node.left = null;
            else {
                TreeNode new_node = new TreeNode(Integer.valueOf(arr[i]));
                node.left = new_node;
                q.add(node.left);
            }
            if (arr[i + 1].equals("#"))
                node.right = null;
            else {
                TreeNode new_node = new TreeNode(Integer.valueOf(arr[i + 1]));
                node.right = new_node;
                q.add(node.right);
            }
            i = i + 2;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));