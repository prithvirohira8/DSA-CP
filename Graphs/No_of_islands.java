// No of Islands

// Question Link:- https://leetcode.com/problems/number-of-islands/submissions/

// Approach:
// We need to find the no of islands.
// So technically we need to traverse all the one's that form a land mass
// If a one is connected to another one, then it is in the same island.
// The no of times such a traversal is required is the no of islands.

// We just maintain a visited 2d array and run DFS normally. While running
// DFS after traversing a 1 we mark it as visited. All 0's are initalized to be
// visited in the start.
// Once again the no of times the DFS is required to be done, is the
// no of graphs in the disconnected 2d array, which is the no if islands.
import java.util.*;

class Solution4 {
    static void DFS(char[][] grid, boolean[][] visited, int row, int col) {
        Stack<Node> stk = new Stack();
        stk.push(new Node(row, col));

        while (!stk.isEmpty()) {
            Node node = stk.pop();
            row = node.row;
            col = node.col;
            visited[row][col] = true;
            if (row != 0 && !visited[row - 1][col])
                stk.push(new Node(row - 1, col));
            if (row != grid.length - 1 && !visited[row + 1][col])
                stk.push(new Node(row + 1, col));
            if (col != 0 && !visited[row][col - 1])
                stk.push(new Node(row, col - 1));
            if (col != grid[0].length - 1 && !visited[row][col + 1])
                stk.push(new Node(row, col + 1));
        }
    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0')
                    visited[i][j] = true;
            }
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j]) {
                    count++;
                    DFS(grid, visited, i, j);
                }
            }
        }
        return count;
    }
}

class Node {
    int row;
    int col;

    Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

// Time Complexity: O(V + E)
// Traversing all the vertices and edges of the Graph

// Space Complexity: O(N*M)
// Size of the boolean visited array