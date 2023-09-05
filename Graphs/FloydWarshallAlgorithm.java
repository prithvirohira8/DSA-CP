// Floyyd Warshall Algorithm

// Dijkastra and Bellmann Ford were single source shortest path algorithms. Floyyd Warshall algorithm
// is a multi source shortest path algorithm, i.e. the algorithm devises the shortest path/dist for all
// the vertices to all the other vertices. Implementation of Floyd Warshall Algorithm is much more simpler.
// The implementation is simple brute force and the simplest use of Dynamic Programming for memoization.

// Algorithm
// The algorithm uses a n*n cost matrix which stores the shortest distance from each vertex to all other
// vertices. The matrix is updated by with respect to taking each vertex in consideration.
// First it takes the 1st vertex into consideration, i.e the distance from each vertex to all the other vertices
// will be computed with respect to the 1st vertex. If that dist is less than the original distance in the cost matrix,
// then the cost matrix will be updated. This same cost matrix will then be used by taking the 2nd vertex into consideration.
// Similarly all the vertices till n will be taken into consideration and the cost matrix will keep getting
// updated wherever possible. The matrix obtained after all these n iterations will be the resultant cost matrix showcasing
// the shortest distance from all the n nodes to all the other n nodes

// The formula used is
// dist[i][j] = dist[i][k] + dist[k][j]
// if(mat[i][j] == -1 || dist < mat[i][j]) mat[i][j] = dist;

// Here k is the vertex taken into consideration.

class Solution
{
    public void shortest_distance(int[][] mat)
    {
        for(int v = 0; v < mat.length; v++){
            for(int i = 0; i < mat.length; i++){
                for(int j = 0; j < mat[i].length; j++){
                    if(mat[i][v] == -1 || mat[v][j] == -1) continue;
                    int dist = mat[i][v] + mat[v][j];
                    if(mat[i][j] == -1 || dist < mat[i][j]) mat[i][j] = dist;
                }
            }
        }
    }
}

// Time Complexity: O(n^3)
// This is because for each iteration of taking n vertices into consideration, there will be
// O(n^2) operations to travers the entire matrix.