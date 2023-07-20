// Depth First Search

// Reference Problem: https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1

// Approch: Use Stack to add elements and process the 1st element of the stack for DFS.

// Both Recursive and Iterative Approaches are highlighted. Both are same.
// However recursive approach can give Stack Overflow Error for a very large graph

class Solution {
    // static ArrayList<Integer> DFS(ArrayList<ArrayList<Integer>> adj, Stack<Integer> stk, ArrayList<Integer> lst, boolean[] visited){
    //     if(stk.isEmpty())return lst;
        
    //     int element = stk.pop();
    //     if(visited[element]) return DFS(adj, stk, lst, visited);
    //     for(int i = adj.get(element).size() - 1; i >= 0; i--){
    //         if(visited[adj.get(element).get(i)]) continue;
    //         stk.push(adj.get(element).get(i));
    //     }
    //     visited[element] = true;
    //     lst.add(element);
    //     return DFS(adj, stk, lst, visited);
    // }
    
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> stk = new Stack<>();
        ArrayList<Integer> lst = new ArrayList<>();
        for(int i = adj.get(0).size() - 1; i >= 0; i--){
            stk.push(adj.get(0).get(i));
        }
        visited[0] = true;
        lst.add(0);
        // return DFS(adj, stk, lst, visited);
        while(!stk.isEmpty()){
            int element = stk.pop();
            if(visited[element]) continue;
            for(int i = adj.get(element).size() - 1; i >= 0; i--){
                if(visited[adj.get(element).get(i)]) continue;
                stk.push(adj.get(element).get(i));
            }
            visited[element] = true;
            lst.add(element);
        }
        return lst;
    }
}

// V = no of vertices, E = no of edges
// Time Complexity: O(V + E)
// Space complexity: O(V)
