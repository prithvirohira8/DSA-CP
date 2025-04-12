// Topological Sorting using DFS

// Question Link: https://www.codingninjas.com/studio/problems/982938?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=1

// Approach: DFS is all about moving through a single path
// and then backracking. That's it. We use this philosophy of DFS
// and solve this question with the stack.
// Topological sorting requires us to maintain the order of the 
// directed path, such that if u -> v, u should be before v in the path.
// So when we use DFS and traverse and reach that node from which we cannot
// move down any further, we backtrack, however in topological sorting the
// last node we reach should be in the end. This is an indication that tells
// us we can use Stack. When we reach the last node and begin backtracking, we start
// adding the nodes in the stack. So once when we pop() the nodes from the stack
// the topological order will be maintained. 

import java.util.*;
import java.io.*;

public class Topological_Sorting_using_DFS {
    static Stack<Integer> solve(List<List<Integer>> adj, boolean[] vis, Stack<Integer> stk, int source) {
        vis[source] = true;
        for (int i = 0; i < adj.get(source).size(); i++) {
            int child = adj.get(source).get(i);
            if (!vis[child])
                stk = solve(adj, vis, stk, child);
        }
        stk.push(source);
        return stk;
    }

    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges, int v, int e) {
        // creating an adjacency list for the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.size(); i++) {
            int v1 = edges.get(i).get(0);
            int v2 = edges.get(i).get(1);
            adj.get(v1).add(v2);
        }

        boolean[] vis = new boolean[v];
        Stack<Integer> stk = new Stack();

        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                stk = solve(adj, vis, stk, i);
            }
        }

        ArrayList<Integer> lst = new ArrayList<>();
        while (!stk.isEmpty()) {
            int element = stk.pop();
            lst.add(element);
        }

        // System.out.println(lst);
        return lst;
    }
}

// Time Complexity: O(V + E)
// For traversing through all edges and vertices

// Space Complexity: O(2N)
// Visited array and Stack