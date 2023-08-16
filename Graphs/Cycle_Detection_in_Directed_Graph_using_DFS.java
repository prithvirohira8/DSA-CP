// Detection of Cycles in an directed Graph using DFS

// Question Link: https://www.codingninjas.com/studio/problems/1062626?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=1

// Approach: We cannot use the approach of creating only a visited array and tracerse
// like we did in the case of an undirected graph. This is because if a particular node 
// has been reached by two other nodes which form a cycle it is possible that the
// direction of traversal to reach the node form the other particular nodes is opposite
// and not in the same direction. In this case it wil not be considered to be a cycle.
// Hence for detecting a cycle in directed graphs we use a dfsVisited array along with 
// the normal visited array. The dfsVisited and normal visited array are updated each time
// the node is visited, however if a cycle is not formed, backtracking needs to be done to check for
// the other children of the particular node in the heirarchy as we are using DFS and one particular child
// is explored entirely before moving ahead. While backtracking from a node we set the dfsVisited as false again
// While traversal of children if we find that a particlar node is visited by both the visited and dfsVisited
// array, we detect a cycle and return true. 
// For this kind of approach we cannot implement an iterative approach and have to rely on recursion


import java.util.*;
import java.util.ArrayList;
public class Solution {
  static boolean checkCycle(List<List<Integer>> adj, boolean[] vis, boolean[] dfsVis, int source){
    vis[source] = true;
    dfsVis[source] = true;

    for(int i = 0; i < adj.get(source).size(); i++){
      int child = adj.get(source).get(i);
      if(!vis[child]){
        if(checkCycle(adj, vis, dfsVis, child)) return true;
      }
      else if(dfsVis[child]) return true;
    }
    // Setting false for backracking
    dfsVis[source] = false;

    return false;
  }
  public static boolean detectCycleInDirectedGraph(int n, ArrayList < ArrayList < Integer >> edges) {
    // Creating an adjaceny list
    List<List<Integer>> adj = new ArrayList<>();
    for(int i = 0; i <= n; i++){
      adj.add(new ArrayList<>());
    }
    for(int i = 0; i < edges.size(); i++){
      // Direction v1 -> v2
      int v1 = edges.get(i).get(0);
      int v2 = edges.get(i).get(1);
      adj.get(v1).add(v2);
    }
    // System.out.println(adj);
    boolean vis[] = new boolean[n+1];
    boolean dfsVis[] = new boolean[n+1];

    for(int i = 1; i <= n; i++){
      if(checkCycle(adj, vis, dfsVis, i)){
        return true;
      } 
    }
    return false;
  }
}

// Time Complexity: O(N + E)
// (All the edges and nodes are exlplored)

// Space Complexity: O(2N)
// (2 boolean arrays created)