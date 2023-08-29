// Bellman Ford Algorithm

// This algorithm is just a greedy way to find the shortest path from the ource vertex to the
// all the adjacent vertices of the graph. It is computationally more expensive than Dijkstra
// ,however it works for finding the shortest path in the case of negative edges too.

// Algorithm
// It is based on the same relaxation algorithm as that of the dijkstra's algorithm.
// however unlike the dijkstra's algorithm it does not start the relaxation algo from the nearest
// vertex of the source. It can be started from anywhere. 
// Bellman Ford Algortihm only maintains a list of all the edges in the graph. And then
// the relaxation algorithm can begin from any edge
// The relaxation algorithm is performed for all the edges of the graph for n - 1 times where
// n is the no of vertices. 
// the intuition of n-1 times is formed because let us consider a single path graph and we start
// the relaxatin algorithm in the reverse order.


// Relaxation Algorothm
// for a edge directed from u -> v with a cost.
// if(dist[u] != (int) 1e8 && dist[v] > dist[u] + cost){
//     dist[v] = dist[u] + cost;
//     didChange = true;
// }
// dist[u] != (int) 1e8 is an imp condition because if dist[u] is at an infinite dist, no point relaxing or checking

// Advantage: Works for negative weighted edges

// Disadvantage: Fails for negative weight cycles


// Problem Link: https://practice.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1

// Reference Video: https://www.youtube.com/watch?v=0vVofAhAYjc

class Solution {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e8);
        dist[S] = 0;
        
        boolean didChange = false;
        for(int i = 0; i < V - 1; i++){
            didChange = false;
            for(int j = 0; j < edges.size(); j++){
                int u = edges.get(j).get(0);
                int v = edges.get(j).get(1);
                int cost = edges.get(j).get(2);
                if(dist[u] != (int) 1e8 && dist[v] > dist[u] + cost){
                    dist[v] = dist[u] + cost;
                    didChange = true;
                }
            }
            if(!didChange) return dist;
        }
        
        // checking for negative weight cycles
        for(int i = 0; i < edges.size(); i++){
            int u = edges.get(i).get(0);
            int v = edges.get(i).get(1);
            int cost = edges.get(i).get(2);
            if(dist[u] != (int) 1e8 && dist[v] > dist[u] + cost){
                int[] temp = {-1};
                return temp;
            }
        }
        
        return dist;
    }
}

// Time Complexity: O(V*N)
// N = no of edges in the graph
