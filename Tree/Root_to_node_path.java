// Question Link: https://www.geeksforgeeks.org/problems/root-to-leaf-paths/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=root-to-leaf-paths

import java.util.*;

// Definition for Binary Tree Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

//
public class Root_to_node_path {
    static ArrayList<ArrayList<Integer>> ans;

    // Using DFS to traverse tree
    static void solve(Node node, ArrayList<Integer> lst) {
        lst.add(node.data);
        if (node.left == null && node.right == null) {
            ans.add(new ArrayList<>(lst));
            return;
        }
        if (node.left != null)
            solve(node.left, lst);
        if (!lst.isEmpty() && node.left != null)
            lst.remove(lst.size() - 1); // for left element
        if (node.right != null)
            solve(node.right, lst);
        if (!lst.isEmpty() && node.right != null)
            lst.remove(lst.size() - 1); // for right element
    }

    public static ArrayList<ArrayList<Integer>> Paths(Node root) {
        ans = new ArrayList<>();
        solve(root, new ArrayList<>());
        return ans;
    }
}

// Time Complexity: O(N)
// Space Complexity: O(N)