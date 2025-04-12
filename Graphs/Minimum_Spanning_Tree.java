// Problem: Minimum Spanning Tree

// Use loops as much as possible so that, stack Overflow Error does not arrive

import java.util.*;

class Minimum_Spanning_Tree {
    static int sum;

    static void solve(PriorityQueue<Pair> pq, List<List<int[]>> adj, boolean[] visited, int[] mst) {
        if (pq.isEmpty())
            return;

        Pair p = pq.peek();
        while (visited[p.node]) {
            if (pq.isEmpty())
                return;
            p = pq.poll();
        }

        sum += p.weight;
        visited[p.node] = true;
        mst[p.node] = p.parent;
        for (int i = 0; i < adj.get(p.node).size(); i++) {
            int[] temp = adj.get(p.node).get(i);
            int node = temp[0];
            int weight = temp[1];
            pq.add(new Pair(node, p.node, weight));
        }
        solve(pq, adj, visited, mst);
    }

    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        sum = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        boolean[] visited = new boolean[V];
        int[] mst = new int[V];

        pq.add(new Pair(0, -1, 0));
        mst[0] = -1;

        solve(pq, adj, visited, mst);
        // System.out.print("MST (val on ith index represents parent of i) = ");
        // for(int i = 0; i < mst.length; i++){
        // System.out.print(mst[i] + " ");
        // }
        // System.out.println();

        return sum;
    }
}

class Pair {
    int node;
    int parent;
    int weight;

    Pair(int node, int parent, int weight) {
        this.parent = parent;
        this.node = node;
        this.weight = weight;
    }
}