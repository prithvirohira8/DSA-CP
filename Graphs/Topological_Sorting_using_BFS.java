// Topological Sorting using BFS / Kahn's Algorithm

// Brief: Topological Sorting can only be done on directed acyclic graphs (DAGs).

// Question Link: https://www.codingninjas.com/studio/problems/982938?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=1

// Approach: It may appear that we can start from the starting node and 
// directly run BFS, to topologically sort it, however that approach will
// give an incorrect solution when there is a node that is connected to a 
// node above it's heirarchy level and connected to another node below it's
// heirarchy level. When we use BFS in this case, the node will be added in the
// queue as we move down from one heirarchy to another, wheras via topologically
// sorting the node should be processed only after both the nodes directing towards
// it are processed.

// Hence we use Kahn's Algorithm here.
// To find the starting node, we calculate the indegree of each node,
// since it is a directed acyclic graph, there will be atleast 1 node
// with indegree as 0. That node will be the starting node.
// We maintain an indegree array which stores the indegree value
// of all nodes.
// Then we use a queue and perform BFS normally, however the node is added
// in the ans list only if it's indegree is 0. When ever we reach a node 
// in the queue we decrement it's
// indegree value, and at certain point the indegree of all the nodes is bound to be 0.

// the ans list consists of the topological sort order

import java.util.*;
import java.io.*;

public class Topological_Sorting_using_BFS {
    static ArrayList<Integer> solve(List<List<Integer>> adj, int[] indegree, Queue<Integer> q) {
        ArrayList<Integer> lst = new ArrayList<>();
        while (!q.isEmpty()) {
            int element = q.poll();
            lst.add(element);
            for (int i = 0; i < adj.get(element).size(); i++) {
                int index = adj.get(element).get(i);
                indegree[index]--;
                if (indegree[index] == 0)
                    q.add(index);
            }
        }
        return lst;
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

        int[] indegree = new int[v];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int index = adj.get(i).get(j);
                indegree[index]++;
            }
        }

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        ArrayList<Integer> lst = solve(adj, indegree, q);
        // System.out.println(lst);
        return lst;
    }
}

// Time Complexity: O(V + E)
// All the vertices and edges are traversed once

// Space Complexity: O(2N)
// The indegree array space, Queue space