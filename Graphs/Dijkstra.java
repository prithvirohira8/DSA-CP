// Dijkstra's Algorithm

// Dijkstra's algorithm is an algorithm to find the shortest path from the source vertex
// to all the other vertices in the graph. It is greedy in nature

// Algorithm:
// The algorithm starts from the source and selcts the most nearby node and relaxes the nodes connected to
// it. Then it selects the next node which is closest to the source and relaxes the nodes connected to it.
// like this it continues for all the nodes in the graph.
// Now if we notice after performing relaxation for the nodes connected to the node we have selected, we once
// again find the closest node in the graph excluding that.
// So the most basic implementation for this is to take a visited array and mark the nodes visited and then run a 
// for loop to find the next node that is closest to the source.
// However this for loop will take an additional O(n) time to find the closest node.
// An optimized way of solving this problem is using the priority queue.
// We can use the priority queue and intially just add the pair of a source with the dist from the src
// i.e 0. Then we can pop the pair and add the nodes connecting to the source noting their respective distances
// in the pair too. The priority queue will give priority to the pair with the least distance (cost) from the source
// for removal. In this way the O(n) time complexity to search for the nearest unvisited
// node from the source and the addiional time complexity to maintain a visited array is reduced 
// that time complexity is brought down to log(n) (The time complexity of the priority queue to internally sort the elements
// based on priority).
// For every node that is polled from the pq thereafter, it's connecting nodes will also be added in the queue, 
// only if they can be relaxed. This is very imp, that the connecting nodes can be added only if they can be relaxed.
// Technically if we see, this condition makes the most sense, if a node can be relaxed we need to check for it's connecting 
// nodes too and see if shorter distances can be found for them using the new relaxed distance for the node.
// If that distance cannot be relaxed no point tryng to relax the distance of the connecting nodes.
// This process continues until the queue is empty

// Advantage: Computationally the most effective algorithm for finding the shortest distance in a graph,
// much better than Bellman Ford.

// Disadvantage: It does not work for negative edges, i.e it cannot find the shortest distance for a graph with
// negative edges

// Problem Link: https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1

// Reference Video: Strivers Dijkstra

import java.util.*;

public class Dijkstra {
    // Function to find the shortest distance of all the vertices
    // from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // Creating an adjacency matrix
        int[][] adjMatrix = new int[V][V];
        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int y = adj.get(i).get(j).get(0);
                int cost = adj.get(i).get(j).get(1);
                adjMatrix[i][y] = cost;
            }
        }

        // Creating priority queue with the priority being given to vertices with min
        // cost from source
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                if (p1.cost > p2.cost)
                    return 1;
                else
                    return -1;
            }
        });
        pq.add(new Pair(S, 0));

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int node = p.node;
            int cost = p.cost;

            // iterating throught the adjacent nodes of the source
            for (int i = 0; i < adjMatrix[node].length; i++) {
                // if node to i has a dist of 0 then node is not connected to vertice 'i'
                if (adjMatrix[node][i] == 0)
                    continue;
                // relaxation algorithm
                if (dist[i] > dist[node] + adjMatrix[node][i]) {
                    pq.add(new Pair(i, dist[node] + adjMatrix[node][i]));
                    parent[i] = node;
                    dist[i] = dist[node] + adjMatrix[node][i];
                }
            }
        }
        return dist;
    }
}

class Pair {
    int node;
    int cost;

    Pair(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

// Time Complexity: O(E*log(V))
// The priority queue iterates passes over all the edges and log(V) is the time
// complexity of
// the priority queue to sort the elements in priority order