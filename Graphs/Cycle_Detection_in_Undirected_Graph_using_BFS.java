// Detection of Cycles in an undirected Graph using BFS

// Question Link: https://www.codingninjas.com/studio/problems/1062670?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website

// Hint: Check if node is visited, if already visited and not oarrent of curr node, then cycle is formed

// Approach: We first create an adjacency List to represent the graph
// We use breadth first search from any particular node and traverse the graph,
// as we traverse the graph we add the connecting nodes of a particular node in the 
// queue (similar to the B.F.S approach) and after polling (removing) the node from the queue, we mark the
// node as visited. If we land on a node that is visited and was not the parent of the current node in the previous
// cycle, it signifies it is connected to another node which is then forming a cycle. This where we set the boundry condition
// A 2*2 matrix representation can have multiple graphs in it rather than just being 1 connected graph representation.
// Hence we run a loop from 1 to N and run BFS for each vertex in the iteration, skip the ones which have been visited.
// This ensures all the possible graphs are covered.

// The vertices are from 1 to N (not 0 indexed)

import java.util.*;

public class Cycle_Detection_in_Undirected_Graph_using_BFS {
    static boolean checkCycle(List<List<Integer>> adj, boolean[] visited, int source) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(source, -1));
        visited[source] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int child = node.child;
            int parent = node.parent;

            for (int i = 0; i < adj.get(child).size(); i++) {
                // x is the child's child
                int x = adj.get(child).get(i);
                if (!visited[x]) {
                    q.add(new Node(x, child));
                    visited[x] = true;
                }
                // if adjacent node is visited and is not it's own parent node
                // the below statement will be true when the parent is visitend and then,
                // the children of the parent are being iterated upon, the children of the
                // parent,
                // will then consider the parent as its own child which is when the below
                // condition
                // and it needs to be neglected.
                else if (parent != x)
                    return true;
            }
        }
        return false;
    }

    public static String cycleDetection(int[][] edges, int n, int m) {
        // n = no of verices, m = edges.length;
        // Creating adjacency List
        List<List<Integer>> adj = new ArrayList<>();
        // Since the vertices are from 1, i goes till <= n.
        // Also the list at the zeroth index remains unused.
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }

        // Defining a boolean visited array
        boolean[] visited = new boolean[n + 1];

        // covering all possible graphs, incase there is more than 1 graph and
        // graphs are disconnected
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

// Time Complexity: O(N + 2E) + O(N), Where N = Nodes,
// 2E is for total degrees as we traverse all adjacent nodes.
// In the case of connected components of a graph,
// it will take another O(N) time.

// Space Complexity: O(N) + O(N) ~ O(N),
// Space for queue data structure and visited array.