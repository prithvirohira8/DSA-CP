// Breadth First Search

// Reference Problem: https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
// Approch: Use Queue to add elements and process the 1st element of the Queue for BFS.

class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];
        ArrayList<Integer> lst = new ArrayList<>();
        for(int i = 0; i < adj.get(0).size(); i++){
            q.add(adj.get(0).get(i));
        }
        visited[0] = true;
        lst.add(0);
        
        
        while(!q.isEmpty()){
            // System.out.println(q);
            int element = q.poll();
            if(visited[element]) continue;
            for(int i = 0; i < adj.get(element).size(); i++){
                if(visited[adj.get(element).get(i)]) continue;
                q.add(adj.get(element).get(i));
            }
            visited[element] = true;
            lst.add(element);
        }
        return lst;
    }
}

// Time Complexity: O(V + E)

// Space Complexity: O(V)