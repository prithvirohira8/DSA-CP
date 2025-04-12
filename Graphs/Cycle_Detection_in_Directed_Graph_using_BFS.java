// Cycle Detection in a Directed Graph using B.F.S 

// Problem Link: https://www.codingninjas.com/studio/problems/1062626?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=1

// Approach:
// Unlike D.F.S, B.F.S has no backtracking.
// So if we just traverse the graph using B.F.S there is no way we can
// say if the graph contains a cycle or no. However there is a hack.
// We can use the Kahn's algorithm that help us create the topological sorting
// order in B.F.S. The Kahn's algorithm was only valid for Directed Acyclic
// Graphs i.e. it cannot be used on a graph with cycle's. What would technically happen
// if the algorithm was used on a directed graph which contained cycles?
// The algorithm would be unable to traverse the entire graph and the topological sort
// order would be incomplete. But why would the algorithm be unable to travers the graph?
// It is because at one point where the cycles would be forming in the graph, the indegree
// of a particular node in the cycle would never be 0. This case would make sure the queue
// gets empty and the nodes of the cycle remain untraversed. (Take an example of a directed graph
// and try to apply Kahns algo.)

// Therefore in the case of a directed graph, the length of the list
// derived from the Kahns algo (containg the topological sorting order) will
// always be less than n (no of vertices of graph.).
// It will be equal only if it is a DAG.

import java.util.*;
import java.util.ArrayList;

public class Cycle_Detection_in_Directed_Graph_using_BFS {
  static List<Integer> topologicalSort(List<List<Integer>> adj, int[] indegree) {
    List<Integer> lst = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();

    for (int i = 1; i < indegree.length; i++) {
      if (indegree[i] == 0)
        q.add(i);
    }

    while (!q.isEmpty()) {
      int vertex = q.poll();
      lst.add(vertex);
      for (int i = 0; i < adj.get(vertex).size(); i++) {
        int child = adj.get(vertex).get(i);
        indegree[child]--;
        if (indegree[child] == 0)
          q.add(child);
      }

    }

    return lst;
  }

  public static boolean detectCycleInDirectedGraph(int n, ArrayList<ArrayList<Integer>> edges) {
    // Creating an adjaceny list
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }

    int[] indegree = new int[n + 1];
    for (int i = 0; i < edges.size(); i++) {
      // Direction v1 -> v2
      int v1 = edges.get(i).get(0);
      int v2 = edges.get(i).get(1);
      adj.get(v1).add(v2);
      indegree[v2]++;
    }

    List<Integer> lst = topologicalSort(adj, indegree);

    // System.out.println(lst+" "+n);
    if (lst.size() != n)
      return true;
    return false;
  }
}

// Time Complexity: O(V + E)
// Time complexity to traverse all vertices and edges

// Space Complexity: O(N)
// Indegree array