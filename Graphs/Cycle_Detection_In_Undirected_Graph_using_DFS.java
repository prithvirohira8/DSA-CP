// Detection of Cycles in an undirected Graph using DFS

// Question Link: https://www.codingninjas.com/studio/problems/1062670?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website

// Approach: Same approach as detection of cycle in an undirected graph using B.F.S just use 
// D.F.S in place of B.F.S

import java.util.*;

class Solution0 {
    static boolean checkCycle(List<List<Integer>> adj, boolean[] visited, int source) {
        Stack<Node> stk = new Stack();
        stk.add(new Node(source, -1));
        visited[source] = true;

        while (!stk.isEmpty()) {
            Node node = stk.pop();
            int child = node.child;
            int parent = node.parent;

            for (int i = 0; i < adj.get(child).size(); i++) {
                // x acts like the child's child (parent's grandchild)
                int x = adj.get(child).get(i);
                if (!visited[x]) {
                    stk.add(new Node(x, child));
                    visited[x] = true;
                    continue;
                }
                // if parents grandchild and parent are same, we ignore,
                // but if different and grandhild has already been traversed it means
                // there is a cycle
                else if (parent != x)
                    return true;
            }
        }
        return false;
    }

    public static String cycleDetection(int[][] edges, int n, int m) {
        // Creating an adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }

        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (visited[i])
                continue;
            if (checkCycle(adj, visited, i))
                return "Yes";
        }
        return "No";
    }
}

class Node {
    int child;
    int parent;

    Node(int child, int parent) {
        this.child = child;
        this.parent = parent;
    }
}