// Problem Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

// Approach:
// Traverse the Tree using BFS and if the node is the last node at a level then it points
// to null else it points to the node at the right.
// While processing each node add the children of the node in another temporary queue.
// When that level iteration has been completed, add the elements of the temp q in the first q and empty the temp q.
// Continue this in a while loop till the first q isn't empty

// O(1) Space Approach given below this solution

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

class Solution10 {
    public Node connect(Node root) {
        if (root == null)
            return root;
        List<Node> lst = new ArrayList<Node>();
        List<Node> temp_lst = new ArrayList<Node>();
        lst.add(root);
        while (!lst.isEmpty()) {
            for (int i = 0; i < lst.size(); i++) {
                Node node = lst.get(i);
                if (i == lst.size() - 1)
                    node.next = null;
                else
                    node.next = lst.get(i + 1);

                if (node.left != null) {
                    temp_lst.add(node.left);
                    temp_lst.add(node.right);
                }
            }
            lst = new ArrayList<>(temp_lst);
            temp_lst = new ArrayList<Node>();
        }
        return root;
    }
}

// Time Complexity Analysis: O(N)
// We are processing every node once, additionally
// lst = new ArrayList<>(temp_lst); would take O(N) for each level.
// However this operation is not inside the for loop. and is in the while loop.
// So overall the for loop will take O(N) & 'lst = new ArrayList<>(temp_lst);'
// will take O(N)
// There fore overall time complexity = O(2*N) which is same as O(N)

// Space Complexity: O(N)
// 2 queues used.

class Solution3 {
    public Node connect(Node root) {
        if (root == null)
            return root;
        Node curr = root;
        Node nxt = (root.left != null) ? root.left : null;

        // nxt shouldn't be null, (we are assigning next values to the children of curr
        // in each loop iteration, (nxt is a child of curr) if nxt is null it means
        // there are no children and that that the next values of curr had already been
        // given in the previous iteration or the initialization above.)
        while (nxt != null) {
            curr.left.next = curr.right;
            if (curr.next != null)
                curr.right.next = curr.next.left;

            // If node ot the right is null then we need to move one level down, else we can
            // move to the node on the right and continue the loop to assign the next values
            // to the children.
            curr = curr.next;
            if (curr == null) {
                curr = nxt;
                // move next one level below
                nxt = curr.left;
            }
        }
        return root;
    }
}
// Time Complexity: O(N)
// Space Complexity: O(1)